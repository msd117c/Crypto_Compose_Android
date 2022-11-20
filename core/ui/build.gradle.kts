plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.msd.core.ui"
    compileSdk = Configuration.compileSdkVersion

    defaultConfig {
        minSdk = Configuration.minSdkVersion
        targetSdk = Configuration.compileSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.5.1")

    implementation("androidx.compose.ui:ui:1.3.0")
    implementation("androidx.compose.ui:ui-tooling:1.3.0")
    implementation("androidx.compose.foundation:foundation:1.3.0")
    implementation("androidx.compose.material:material:1.3.0")
    implementation("androidx.compose.material:material-icons-core:1.3.0")
    implementation("androidx.compose.material:material-icons-extended:1.3.0")
    implementation("androidx.activity:activity-compose:1.6.1")

    implementation("com.valentinilk.shimmer:compose-shimmer:1.0.2")
    implementation("com.github.skydoves:landscapist-glide:1.4.3")

    androidTestImplementation("androidx.test.ext:junit:1.1.4")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.0")
}