package com.example.billspliter.security

import com.example.billspliter.usermanagement.UserDAO
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component
import java.sql.Date
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter : UsernamePasswordAuthenticationFilter {

    constructor(authenticationManager: AuthenticationManager): super() {
        this.authenticationManager = authenticationManager
    }

    init {
        setFilterProcessesUrl(SecurityConstants.AUTH_LOGIN_URL)
    }

    override fun attemptAuthentication(
            request: HttpServletRequest,
            response: HttpServletResponse
    ): Authentication {
        val username = request.getParameter("login")
        val password = request.getParameter("password")
        val authenticationToken = UsernamePasswordAuthenticationToken(username, password)

        return authenticationManager.authenticate(authenticationToken)
    }

    override fun successfulAuthentication(
            request: HttpServletRequest,
            response: HttpServletResponse,
            filterChain: FilterChain,
            authentication: Authentication
    ) {
        val user = authentication.principal as User
        val objectMapper = jacksonObjectMapper()

        val roles = user.authorities.map { it.authority }
        val signingKey = SecurityConstants.JWT_SECRET.toByteArray()

        val token = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
                .setIssuer(SecurityConstants.TOKEN_ISSUER)
                .setAudience(SecurityConstants.TOKEN_AUDIENCE)
                .setSubject(user.username)
                .setExpiration(Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION))
                .claim("rol", roles)
                .compact()

        val tokenResponse = TokenResponse(SecurityConstants.TOKEN_PREFIX + token, SecurityConstants.TOKEN_EXPIRATION)
        with(response) {
            contentType = "application/json"
            characterEncoding = "UTF-8"
            writer.println(objectMapper.writeValueAsString(tokenResponse))
            writer.flush()
        }
    }
}

data class TokenResponse(
        val token: String,
        val expiresAfter: Long
)