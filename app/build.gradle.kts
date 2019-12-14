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
    dataBinding {
        isEnabled = true
    }
}

dependencies {
    implementation(fileTree("libs"))

    implementation(project(":domain"))
    implementation(project(":database"))

    implementation(CommonDependencies.kotlinStdLib)
    implementation(AndroidDependencies.appCompat)
    implementation(AndroidDependencies.core)

    implementation(UiDependencies.constraintLayout)
    implementation(UiDependencies.materialDesign)
    implementation(UiDependencies.recyclerview)
    implementation(UiDependencies.navigation_fragment)
    implementation(UiDependencies.navigation_ui)

    testImplementation(TestDependencies.junit)
    testImplementation(TestDependencies.mockk)
    testImplementation(TestDependencies.androidArchCoreTest)

    androidTestImplementation(TestDependencies.android_junit)
    androidTestImplementation(TestDependencies.espresso)
}
