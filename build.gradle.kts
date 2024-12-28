plugins {
    kotlin("jvm") version "2.0.21"
    `maven-publish`
}

val versionNumber = "0.1.1"
group = "gg.airbrush"
version = versionNumber

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io/")
}

dependencies {
    compileOnly("net.minestom:minestom-snapshots:4305006e6b")
    implementation("cc.ekblad:4koma:1.2.0")
}

kotlin {
    jvmToolchain(21)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "gg.airbrush"
            artifactId = "plugins"
            version = versionNumber

            from(components["java"])
        }
    }
}