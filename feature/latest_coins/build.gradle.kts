plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    namespace = "com.msd.latest_coins"
    compileSdk = Configuration.compileSdkVersion

    defaultConfig {
        minSdk = Configuration.minSdkVersion
        targetSdk = Configuration.compileSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
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
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

dependencies {

    implementation(project(":core:ui"))
    implementation(project(":core:presentation"))
    implementation(project(":core:navigation"))
    implementation(project(":domain:latest_coins_list"))
    implementation(project(":domain:coin_details"))

    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)

    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeFoundation)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.composeTooling)
    implementation(Dependencies.composePaging)

    implementation(Dependencies.hilt)
    kapt(Dependencies.hiltAndroidCompiler)
    kapt(Dependencies.hiltCompiler)

    testImplementation(Dependencies.jUnit)
    androidTestImplementation(Dependencies.espressoCore)
    androidTestImplementation(Dependencies.espressoJunitExt)
}