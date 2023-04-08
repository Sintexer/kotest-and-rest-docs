plugins {
    id("org.springframework.boot") version "3.0.4"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.asciidoctor.jvm.convert") version "3.3.2"      // (1)
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
}

repositories {
    mavenCentral()
}

val snippetsDir = file(property("snippetsDirPath")!!)   // (2)

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("io.kotest:kotest-runner-junit5:5.5.5")     // (3)
    testImplementation("io.kotest:kotest-assertions-core:5.5.5")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
}

tasks.asciidoctor {
    inputs.dir(snippetsDir)     // (4)
    dependsOn(tasks.test)
}