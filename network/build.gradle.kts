plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.baseandroidtemplate.base"
    compileSdk = libs.versions.compile.sdk.get().toInt()

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
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

}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.logging.interceptor)
    implementation(libs.moshi)
    implementation(libs.moshi.convertor)
}