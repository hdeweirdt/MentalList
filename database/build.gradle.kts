plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(DatabaseDependencies.sqldelight)
}

// TODO: find a way to make this a non-android module (inject android driver)
android {
    compileSdkVersion(AndroidSdk.compile)
    defaultConfig {
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

    implementation(project(":domain"))

    implementation(CommonDependencies.kotlinStdLib)

    implementation(DatabaseDependencies.sqldelightAndroidDriver)

    testImplementation(TestDependencies.junit)
    testImplementation(DatabaseDependencies.sqldelightJdbcDriver)
    testImplementation(TestDependencies.mockk)
}
