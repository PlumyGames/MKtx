rootProject.name = "mkutils"
pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }

    plugins {
        kotlin("jvm") version "1.7.10"
    }
}
include(
    "core",
)
