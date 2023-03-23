plugins{
    id("multiplatform-setup")
    id("android-setup")
    kotlin("plugin.serialization")
    id("com.squareup.sqldelight")
}

kotlin{
    sourceSets{
        commonMain{
            dependencies{
                api(project(":common:games:api"))
                implementation(project(":common:core"))

                implementation(Dependencies.SqlDelight.core)
            }
        }
    }
}

sqldelight {
    database("Database") {
        packageName = "com.example.kmm_sample.games"
        dependency(project(":common:core"))
    }
}