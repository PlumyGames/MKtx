plugins {
    id("io.github.liplum.mgpp") version "1.1.12"
}
buildscript {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri("https://www.jitpack.io")
        }
    }
}
allprojects {
    group = "net.liplum"
    version = "1.0"
    repositories {
        mavenCentral()
        maven {
            url = uri("https://www.jitpack.io")
        }
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform {
            excludeTags("slow")
        }
        testLogging {
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
            showStandardStreams = true
        }
    }
}
mindustry {
    isLib = true
    dependency {
        mindustry mirror "1a64344e5a"
        arc on "v137"
    }
    client {
        mindustry official "v137"
    }
    server {
        mindustry official "v137"
    }
    deploy {
        noFatJar
    }
}