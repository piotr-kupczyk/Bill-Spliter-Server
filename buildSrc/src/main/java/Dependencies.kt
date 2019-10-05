object Version {
    val springWeb = "5.1.9.RELEASE"
    val springDataDynamoDB = "5.1.0"
    val dynamoDB = "1.11.490"
}

object Dependencies {
    val springWeb = "org.springframework:spring-web:${Version.springWeb}"
    val springWebStarter = "org.springframework.boot:spring-boot-starter-web"

    val springData = "org.springframework.data:spring-data-releasetrain:Hopper-SR10"
    val dynamoDB = "com.amazonaws:aws-java-sdk-dynamodb:${Version.dynamoDB}"
    val sprindDynamoDB = "com.github.derjust:spring-data-dynamodb:${Version.springDataDynamoDB}"

    val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect"
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8"

    val jacksonKotlin = "com.fasterxml.jackson.module:jackson-module-kotlin"
}