plugins {
    kotlin("jvm") version "2.0.21"
    `maven-publish`
}

val versionNumber = "0.1.1"
group = "team.azalea"
version = versionNumber

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io/")
}

dependencies {
    compileOnly("net.minestom:minestom-snapshots:9803f2bfe3")
    implementation("cc.ekblad:4koma:1.2.0")
}

kotlin {
    jvmToolchain(21)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "team.azalea"
            artifactId = "plugins"
            version = versionNumber

            from(components["java"])
        }
    }
}