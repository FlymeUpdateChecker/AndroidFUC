plugins {
    id("com.android.application")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    kotlin("plugin.serialization") version "1.8.10"
}

android {
    namespace = "ru.transaero21.fuc"
    compileSdk = 33

    defaultConfig {
        applicationId = "ru.transaero21.fuc"
        minSdk = 27
        targetSdk = 33
        versionCode = 1
        versionName = "0.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("debug") {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.2"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Kotlin
    val kotlinVersion: String by project
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")

    // Core Ktx
    val coreKtxVersion: String by project
    implementation("androidx.core:core-ktx:$coreKtxVersion")

    // Kotlin Coroutines
    val coroutinesVersion: String by project
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")

    // Kotlin Serialization
    val serializerVersion: String by project
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializerVersion")

    // Material
    val materialVersion: String by project
    implementation("com.google.android.material:material:$materialVersion")

    // Jetpack Compose
    val composeVersion: String by project
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeVersion")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")

    // Jetpack Compose Activity
    val composeActivityVersion: String by project
    implementation("androidx.activity:activity-compose:$composeActivityVersion")

    // Jetpack Compose Navigation
    val composeNavVersion: String by project
    implementation("androidx.navigation:navigation-compose:$composeNavVersion")

    // Jetpack Compose Material
    val composeMaterialVersion: String by project
    implementation("androidx.compose.material:material-icons-extended:$composeMaterialVersion")

    // Jetpack Compose Material 3
    val composeMaterial3Version: String by project
    implementation("androidx.compose.material3:material3:$composeMaterial3Version")

    // Android Lifecycle
    val androidLifecycleVersion: String by project
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$androidLifecycleVersion")

    // DataStore
    val dataStoreVersion: String by project
    implementation("androidx.datastore:datastore-preferences:$dataStoreVersion")

    // Ktor
    val ktorVersion: String by project
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    testImplementation("io.ktor:ktor-client-mock:$ktorVersion")

    // Room
    val roomVersion: String by project
    implementation("androidx.room:room-ktx:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-runtime:$roomVersion")
    testImplementation("androidx.room:room-testing:$roomVersion")

    // Hilt
    val hiltVersion: String by project
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")

    // Libsu
    val libsuVersion: String by project
    implementation("com.github.topjohnwu.libsu:core:$libsuVersion")

    // Others
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.hamcrest:hamcrest:2.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

kapt {
    correctErrorTypes = true
}