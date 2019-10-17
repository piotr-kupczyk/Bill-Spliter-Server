package com.example.billspliter.security

import io.jsonwebtoken.*
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.web.server.ResponseStatusException
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthorizationFilter(authenticationManager: AuthenticationManager) : BasicAuthenticationFilter(authenticationManager) {
    private val log = LoggerFactory.getLogger(JwtAuthorizationFilter::class.java)

    override fun doFilterInternal(
            request: HttpServletRequest,
            response: HttpServletResponse,
            chain: FilterChain
    ) {
        val authentication = request.getAuthentication()

        if (authentication == null) {
            chain.doFilter(request, response);
            return;
        }

        SecurityContextHolder.getContext().authentication = authentication
        chain.doFilter(request, response);
    }

    private fun HttpServletRequest.getAuthentication(): UsernamePasswordAuthenticationToken? {
        val token: String? = getHeader(SecurityConstants.TOKEN_HEADER)
        if (token != null && token.isNotEmpty() && token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            try {
                val signingKey = SecurityConstants.JWT_SECRET

                val parsedToken = Jwts.parser()
                        .setSigningKey(signingKey.toByteArray())
                        .parseClaimsJws(token.replace("Bearer ", ""))

                val username = parsedToken.body.subject

                val authorities = (parsedToken.body["rol"] as List<*>)
                        .map { SimpleGrantedAuthority(it as String) }

                if (username.isNotEmpty()) {
                    return UsernamePasswordAuthenticationToken(username, null, authorities);
                }
            } catch (exception: ExpiredJwtException) {
                log.warn("Request to parse expired JWT : {} failed : {}", token, exception.message)
            } catch (exception: JwtException) {
                log.warn("Request to parse unsupported JWT : {} failed : {}", token, exception.message)
            } catch (exception: IllegalArgumentException) {
                log.warn("Request to parse empty or null JWT : {} failed : {}", token, exception.message)
            } catch (exception: IllegalStateException) {
                log.warn("Request to parse empty or null JWT : {} failed : {}", token, exception.message)
            }
        }
        return null
    }
}