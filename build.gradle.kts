import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.0-M1"
}

group = "me.renespies"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.apache.poi:poi-ooxml:5.0.0")

}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}