plugins {
    kotlin("jvm") version "2.2.0"
//    id("com.gradleup.shadow") version "8.3.2"
    `maven-publish`
}

group = "team.azalea"
version = "0.1.2"

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io/")
}

dependencies {
    compileOnly("net.minestom:minestom-snapshots:4fe2993057")
    implementation("cc.ekblad:4koma:1.2.0")
}

kotlin {
    jvmToolchain(21)
}

tasks.build {
    dependsOn("shadowJar", "publishToMavenLocal")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "plugins"
            version = project.version.toString()

            from(components["java"])
        }
    }

    java {
        withSourcesJar()
        withJavadocJar()
    }
}