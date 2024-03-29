plugins {
    kotlin("jvm") version "1.7.21"
    id("org.jetbrains.dokka") version "1.4.30"
    id("maven-publish")
}

val loggerVersion: String by project

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation("junit", "junit", "4.13.1")
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    val isSnapshotRelease =
        project.hasProperty("isSnapshot") && project.findProperty("isSnapshot") == "yes"
    publications {
        register("mavenJava", MavenPublication::class) {
            from(components["java"])

            pom {
                groupId = "com.asamm.logger"
                artifactId = "logger-asamm"
                version = loggerVersion + if (isSnapshotRelease) "-SNAPSHOT" else ""
            }
        }
    }
}
