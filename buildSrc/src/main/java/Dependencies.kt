object Version {
    const val springWeb = "5.1.9.RELEASE"
    const val springDataDynamoDB = "5.1.0"
    const val dynamoDB = "1.11.490"
    const val springSecurity = "1.0.11.RELEASE"
    const val springWebSecurity = "5.2.0.RELEASE"
    const val jjwt = "0.10.7"
}

object Dependencies {
    const val springWeb = "org.springframework:spring-web:${Version.springWeb}"
    const val springWebStarter = "org.springframework.boot:spring-boot-starter-web"
//    const val springSecurity = "org.springframework.security:spring-security-jwt:${Version.springSecurity}"
    const val springSecurityJWT = "org.springframework.security:spring-security-jwt:${Version.springSecurity}"
    const val springSecurityWeb = "org.springframework.security:spring-security-web:${Version.springWebSecurity}"
    const val springSecurityConfig = "org.springframework.security:spring-security-config:${Version.springWebSecurity}"
    const val springSecurityCore = "org.springframework.security:spring-security-core:${Version.springWebSecurity}"
    const val jjwtApi = "io.jsonwebtoken:jjwt-api:${Version.jjwt}"
    const val jjwtImpl = "io.jsonwebtoken:jjwt-impl:${Version.jjwt}"
    const val jjwtJackson = "io.jsonwebtoken:jjwt-jackson:${Version.jjwt}"


    const val springData = "org.springframework.data:spring-data-releasetrain:Hopper-SR10"
    const val dynamoDB = "com.amazonaws:aws-java-sdk-dynamodb:${Version.dynamoDB}"
    const val sprindDynamoDB = "com.github.derjust:spring-data-dynamodb:${Version.springDataDynamoDB}"

    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8"

    const val jacksonKotlin = "com.fasterxml.jackson.module:jackson-module-kotlin"
}