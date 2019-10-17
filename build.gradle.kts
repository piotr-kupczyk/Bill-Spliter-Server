import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.1.8.RELEASE"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	kotlin("jvm") version "1.2.71"
	kotlin("plugin.spring") version "1.2.71"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8
repositories {
	mavenCentral()
}

dependencies {
	// Tests
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	// Spring web
	implementation(Dependencies.springWeb)
	implementation(Dependencies.springWebStarter)
	//Spring security
	implementation(Dependencies.springSecurityJWT)
	implementation(Dependencies.springSecurityWeb)
	implementation(Dependencies.springSecurityConfig)
	implementation(Dependencies.springSecurityCore)
	implementation(Dependencies.jjwtApi)
	implementation(Dependencies.jjwtImpl)
	implementation(Dependencies.jjwtJackson)

	// Spring data + DynamoDB
	implementation(Dependencies.springData)
	implementation(Dependencies.dynamoDB)
	implementation(Dependencies.sprindDynamoDB)

	// Kotlin
	implementation(Dependencies.kotlinReflect)
	implementation(Dependencies.kotlin)

	// Jackson
	implementation(Dependencies.jacksonKotlin)
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
