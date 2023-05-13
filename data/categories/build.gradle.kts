plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":domain:categories"))

    implementation(Dependencies.inject)

    testImplementation(project(":core:unit_test"))
}