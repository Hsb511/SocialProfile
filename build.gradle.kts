// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version Versions.ANDROID_PLUGIN apply false
    id("com.android.library") version "7.1.3" apply false
    id("org.jetbrains.kotlin.android") version "1.6.21" apply false
}

tasks {
    val clean by registering(Delete::class) {
        delete(rootProject.buildDir)
    }
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT}")
    }
}