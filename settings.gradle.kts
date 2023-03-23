pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "KMM_Sample"
include(":androidApp")
include(":desktop")
include(":common:auth:data")
include(":common:auth:api")
include(":common:auth:presentation")
include(":common:auth:compose")
include(":common:tournaments:data")
include(":common:tournaments:api")
include(":common:tournaments:presentation")
include(":common:tournaments:compose")
include(":common:games:data")
include(":common:games:api")
include(":common:games:presentation")
include(":common:games:compose")
include(":common:core")
include(":common:core-compose")
include(":common:core-utils")
include(":common:umbrella-ios")
include(":common:umbrella-compose")
include(":common:umbrella-core")
include(":common:main:api")
include(":common:main:data")
include(":common:main:presentation")
include(":common:main:compose")