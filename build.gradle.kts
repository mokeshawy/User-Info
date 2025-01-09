// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.mapsplatform.secrets.gradle.plugin) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.hilt.plugin) apply false
    id("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false

}

buildscript {
    dependencies {
        classpath(libs.google.services)
        classpath(libs.firebase.perf.plugin)
        classpath(libs.firebase.crashlytics.gradle)
        classpath(libs.easylauncher)
    }
}