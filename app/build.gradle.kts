plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    //id ("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.nearbyarticles"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.nearbyarticles"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    viewBinding {
        enable = true
    }

}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //Serialization JSON
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    //Splash Screen
    implementation ("androidx.core:core-splashscreen:1.0.1")
    //ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    //LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    //by viewModels extension for Fragments
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    //by viewModels extension for Activity
    implementation("androidx.activity:activity-ktx:1.8.2")
    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0")
    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    //Navigation components
    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.6")
    //Google Maps self location
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation ("com.google.android.gms:play-services-location:21.0.1") //for LocationServices and FusedLocation
    implementation ("com.google.android.gms:play-services-maps:18.2.0") //for type of LatLong
    //Glide to use images
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    //Card View
    implementation ("androidx.cardview:cardview:1.0.0")
    //Dagger Hilt
    implementation ("com.google.dagger:hilt-android:2.50")
    kapt ("com.google.dagger:hilt-android-compiler:2.50")
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}