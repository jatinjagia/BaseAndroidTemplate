plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.baseandroidtemplate.base"
    compileSdk = libs.versions.compile.sdk.get().toInt()
    buildFeatures {
        buildConfig = true
    }
    buildTypes {
        getByName("debug"){
            val defaultUrl = "https://stag.o4s.io/akzo/"
            isMinifyEnabled = false
            buildConfigField("String", "BASE_URL", "\"${System.getenv("BASE_URL") ?: defaultUrl }\"")
        }
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
    implementation(libs.retrofit)
    implementation(libs.logging.interceptor)
    implementation(libs.moshi)
    implementation(libs.moshi.convertor)
}