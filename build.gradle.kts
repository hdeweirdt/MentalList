// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
        maven { url = uri("https://jitpack.io") }
    }
    dependencies {
        classpath(BuildPlugins.androidGradlePlugin)
        classpath(BuildPlugins.kotlinGradlePlugin)

        classpath(DatabaseDependencies.sqldelightGradlePlugin)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
    apply(plugin = BuildPlugins.ktLint)
    apply(plugin = BuildPlugins.detekt)
}

plugins {
    id(BuildPlugins.ktLint) version BuildPlugins.Versions.ktlintVersion
    id(BuildPlugins.detekt) version BuildPlugins.Versions.detektVersion
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
