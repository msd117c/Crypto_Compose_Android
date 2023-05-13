plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("kotlin-kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":domain:coin_details"))

    implementation(Dependencies.inject)

    implementation(Dependencies.json)
    kapt(Dependencies.moshiCodeGen)

    testImplementation(project(":core:unit_test"))
}