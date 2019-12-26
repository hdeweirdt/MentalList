plugins {
    id(BuildPlugins.kotlin)
    id(BuildPlugins.library)
    id(DatabaseDependencies.sqldelight)
}

sqldelight {
    database("ListDatabase") {
        packageName = "be.harm.mentallist"
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(CommonDependencies.kotlinStdLib)
    implementation(CommonDependencies.coroutines)

    testImplementation(TestDependencies.junit)
    testImplementation(DatabaseDependencies.sqldelightJdbcDriver)
    testImplementation(TestDependencies.mockk)
    testImplementation(CommonDependencies.coroutinesTest)
}

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
