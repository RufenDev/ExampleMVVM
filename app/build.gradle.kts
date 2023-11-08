plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    //Dagger hilt y KAPT
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.examplemvvm"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.examplemvvm"
        minSdk = 27
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

    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")

    // ViewModel
    val viewVersion = "2.6.2"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$viewVersion")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$viewVersion")
    // Activity
    implementation ("androidx.activity:activity-ktx:1.8.0")
    // Fragment
    implementation ("androidx.fragment:fragment-ktx:1.6.1")

    // Retrofit2
    val retrofitVersion = "2.9.0"
    implementation ("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofitVersion")

    // Corrutinas
    val coroutinesVersion = "1.7.1"
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
    //Testing
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")

    //Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.48.1")
    ksp("com.google.dagger:hilt-compiler:2.48.1")

    //ROOM
    implementation("androidx.room:room-ktx:2.6.0")
    ksp("androidx.room:room-compiler:2.6.0")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    //Testing Mock
    testImplementation("io.mockk:mockk:1.12.3")
    //Testing core
    testImplementation("androidx.arch.core:core-testing:2.2.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}