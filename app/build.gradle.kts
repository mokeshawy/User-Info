plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.mapsplatform.secrets.gradle.plugin)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.hilt.plugin)
}

android {
    namespace = "com.tawuniya.userinfo"
    compileSdk = 35


    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.tawuniya.userinfo"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    flavorDimensions += listOf("version")
    productFlavors {
        create("staging") {
            dimension = "version"
            versionNameSuffix = ".stage"
        }
        create("live") {
            dimension = "version"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // compose viewModel
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // navigation-compose
    implementation(libs.androidx.navigation.compose)


    // maps
    implementation(libs.maps.compose)
    implementation(libs.play.services.maps)
    implementation(libs.play.services.location)
    implementation(libs.play.services.places)
    implementation(libs.places)


    //FireBase
    implementation(platform(libs.firebase.bom))
    implementation(libs.bundles.firebase.bundle)


    //process-phoenix
    implementation(libs.process.phoenix)

    // timber
    implementation(libs.timber)

    //Android Remote Debugger
    debugImplementation(libs.debugger)
    releaseImplementation(libs.noop)

    //Play Store App Update Libraries
    implementation(libs.app.update)
    implementation(libs.app.update.ktx)

    //Paging compose library
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.paging.compose)

    //Coil
    implementation(libs.coil)
    implementation(libs.coil.svg)

    // Lottie
    implementation(libs.lottie.compose)

    // encrypted shared preference
    implementation(libs.androidx.security.crypto)

    // Ktor
    implementation(libs.bundles.ktor.bundle)


    //Retrofit
    implementation(libs.gson)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)


    // logging - interceptor
    implementation(libs.logging.interceptor)

    // koin
    implementation(libs.koin.android)

    //Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
}