plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.baseandroidtemplate.base"
    compileSdk = libs.versions.compile.sdk.get().toInt()
    buildFeatures{
        dataBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    defaultConfig {
        minSdk = libs.versions.min.sdk.get().toInt()
    }
}

dependencies {
    api("com.google.android.material:material:1.9.0")
    api(libs.bundles.androidx.base.core)
    api(libs.bundles.androidx.architecture.components)
}