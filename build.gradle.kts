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
        mindustry mirror "v139"
        arc on "v139"
    }
    client {
        mindustry official "v139"
    }
    server {
        mindustry official "v139"
    }
    deploy {
        noFatJar
    }
}