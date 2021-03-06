import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.4.32"
    kotlin("plugin.spring") version "1.4.31"
}

group = "com.epam"
version = "0.1"
val kotestVersion = "4.2.6"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
}

sourceSets {
    getByName("main").java.srcDirs("main")
    getByName("main").resources.srcDirs("resources")
    getByName("test").java.srcDirs("test")
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs = listOf("-Xjsr305=strict")
        useIR = true
    }
}
tasks.withType<Test> {
    useJUnitPlatform()
}