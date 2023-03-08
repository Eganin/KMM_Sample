object Dependencies {

    object Kotlin{
        private const val version = "1.6.21"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    }

    object Compose{
        private const val version = "1.1.1"
        const val gradlePlugin = "org.jetbrains.compose:compose-gradle-plugin:$version"
    }

    object Android{
        const val gradlePlugin = "com.android.tools.build:gradle:7.2.2"
    }
}