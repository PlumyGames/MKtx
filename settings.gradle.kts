rootProject.name = "mktx"
pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        mavenLocal()
    }

    plugins {
        kotlin("jvm") version "1.7.10"
    }
}
include(
    "world",
    "core",
    "texture",
    "animation",
    "dsl",
)
