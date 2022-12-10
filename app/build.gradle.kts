import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
    id("de.mannodermaus.android-junit5")
    id("kotlin-kapt")
}

val apiKey: String = gradleLocalProperties(rootDir).getProperty("apiKey")

android {
    compileSdk = Configuration.compileSdkVersion
    buildToolsVersion = Configuration.buildToolsVersion

    defaultConfig {
        applicationId = "com.msd117.cryptocompose"
        minSdk = Configuration.minSdkVersion
        targetSdk = Configuration.compileSdkVersion
        versionCode = Configuration.versionCode
        versionName = Configuration.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "API_KEY", apiKey)
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11

        kotlin {
            kotlinOptions {
                freeCompilerArgs.plus("-Xjvm-default=compatibility")
                freeCompilerArgs.plus(
                    listOf(
                        "-P",
                        "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" +
                                project.buildDir.absolutePath + "/compose_compiler"
                    )
                )
            }
        }
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
    namespace = "com.msd117.cryptocompose"
}

dependencies {

    implementation(project(":core:navigation"))
    implementation(project(":core:network"))
    implementation(project(":core:network_capabilities"))
    implementation(project(":core:presentation"))
    implementation(project(":core:ui"))

    implementation(project(":feature:categories"))
    implementation(project(":feature:home"))
    implementation(project(":feature:latest_coins"))

    implementation(project(":data:categories"))
    implementation(project(":data:coin_details"))
    implementation(project(":data:latest_coins"))

    implementation(project(":domain:categories"))
    implementation(project(":domain:coin_details"))
    implementation(project(":domain:latest_coins_list"))
    implementation(project(":domain:network_capabilities"))

    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)

    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeRuntime)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.composeActivity)
    implementation(Dependencies.composeNavigation)

    implementation(Dependencies.hilt)
    kapt(Dependencies.hiltAndroidCompiler)
    kapt(Dependencies.hiltCompiler)
    implementation(Dependencies.hiltNavigation)
    implementation("com.google.android.material:material:1.7.0")
    implementation(Dependencies.retrofit)
}
