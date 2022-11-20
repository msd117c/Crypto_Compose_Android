plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":domain:categories"))

    implementation(Dependencies.inject)

    implementation(Dependencies.retrofit)
    implementation(Dependencies.moshi)
}