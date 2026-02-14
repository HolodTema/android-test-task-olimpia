plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.terabyte.fitnesslist"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.terabyte.fitnesslist"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
        viewBinding = true
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    //dagger
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)

    //for roboelectric
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.robolectric)
    testImplementation(libs.androidx.espresso.core)

    //Mockito
    testImplementation(libs.mockito.core)

    //for ViewModel and LiveData using
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    //KotlinJsonAdapterFactory() for Retrofit
    implementation(libs.squareup.moshi.kotlin)

    //retrofit
    implementation(libs.squareup.retrofit)
    implementation(libs.squareup.retrofit.moshi.converter)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}