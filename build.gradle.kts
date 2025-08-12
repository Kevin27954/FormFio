plugins {
    java
    eclipse
    id("org.springframework.boot") version "3.5.3"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.formkio"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework:spring-web:6.2.8")
    implementation("org.springframework.boot:spring-boot-starter-web:3.5.3")
    implementation("com.nimbusds:nimbus-jose-jwt:10.3")
    implementation("com.fasterxml.jackson.core:jackson-core:2.19.0")
    implementation("redis.clients:jedis:6.0.0")
    implementation("com.resend:resend-java:3.1.0")
    implementation("org.postgresql:postgresql:42.7.7")
    implementation("com.stripe:stripe-java:29.0.0")

    // This can be thought of as a dev no? Cause I only use it in dev mode.
    implementation("org.xerial:sqlite-jdbc:3.50.2.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
