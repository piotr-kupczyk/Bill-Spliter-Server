package com.example.billspliter.security

object SecurityConstants {

    val AUTH_LOGIN_URL = "/authenticate"

    // Signing key for HS512 algorithm
    // You can use the page http://www.allkeysgenerator.com/ to generate all kinds of keys
    val JWT_SECRET = "0ddf5597e02d981f8803c4cc11f015a4e52679d706edb29b595d9e466def5bcf95273a3053ab5d97ee893c23e4023b912daefaade316406a33b7685d4d223dfa"

    // JWT token defaults
    val TOKEN_HEADER = "Authorization"
    val TOKEN_PREFIX = "Bearer "
    val TOKEN_TYPE = "JWT"
    val TOKEN_ISSUER = "secure-api"
    val TOKEN_AUDIENCE = "secure-app"
}