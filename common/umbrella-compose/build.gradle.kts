plugins {
    id("multiplatform-compose-setup")
    id("android-setup")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":common:core"))
                implementation(project(":common:core-compose"))
                api(project(":common:umbrella-core"))
                implementation(project(":common:core-utils"))

                implementation(project(":common:games:data"))
                implementation(project(":common:tournaments:data"))
                implementation(project(":common:auth:data"))
                implementation(project(":common:auth:compose"))

                implementation(project(":common:main:compose"))

                implementation(Dependencies.Other.ViewModel.core)
                implementation(Dependencies.Other.ViewModel.compose)
                implementation(Dependencies.Other.ViewModel.odyssey)

                implementation(Dependencies.Other.Navigation.compose)
                implementation(Dependencies.Other.Navigation.core)
            }
        }

        androidMain{
            dependencies{
                implementation(Dependencies.Android.composeActivity)
            }
        }
    }
}