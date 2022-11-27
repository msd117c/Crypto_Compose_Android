plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.msd.core.presentation"
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
}

dependencies {

    implementation(project(":core:navigation"))

    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)

    implementation(Dependencies.viewModelLifecycleKtx)

    testImplementation(Dependencies.jUnit)
    androidTestImplementation(Dependencies.espressoJunitExt)
    androidTestImplementation(Dependencies.espressoCore)
}