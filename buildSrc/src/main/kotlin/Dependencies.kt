const val kotlinVersion = "1.3.50"

object BuildPlugins {

    object Versions {
        const val gradleVersion = "3.5.2"
        const val sqlDelightVersion = "1.2.0"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.gradleVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val kotlinAndroid = "kotlin-android"
    const val kotlin = "kotlin"
    const val library = "java-library"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"

    const val sqldelight = "com.squareup.sqldelight"
    const val sqldelightGradlePlugin =
            "com.squareup.sqldelight:gradle-plugin:${Versions.sqlDelightVersion}"
}

object AndroidSdk {
    const val min = 19
    const val compile = 29
    const val target = compile
}

object CommonDependencies {
    private object Versions

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
}

object AndroidDependencies {
    private object Versions {
        const val appcompat = "1.1.0"
        const val core = "1.1.0"
    }

    const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val core = "androidx.core:core-ktx:${Versions.core}"
}

object TestDependencies {
    private object Versions {
        const val junit = "4.12"
        const val androidJunit = "1.1.1"
        const val espresso = "3.2.0"
        const val mockk = "1.9.3.kotlin12"
    }

    const val junit = "junit:junit:${Versions.junit}"
    const val android_junit = "androidx.test.ext:junit:${Versions.androidJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
}