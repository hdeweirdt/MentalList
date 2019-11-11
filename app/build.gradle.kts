plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
}

android {
    compileSdkVersion(AndroidSdk.compile)
    defaultConfig {
        applicationId = "be.harm.mentallist"
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(fileTree("libs"))
    implementation(CommonDependencies.kotlinStdLib)
    implementation(AndroidDependencies.appCompat)
    implementation(AndroidDependencies.core)

    testImplementation(TestDependencies.junit)
    testImplementation(TestDependencies.mockk)

    androidTestImplementation(TestDependencies.android_junit)
    androidTestImplementation(TestDependencies.espresso)
}
