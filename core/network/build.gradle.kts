plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("kotlin-kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(Dependencies.okHttp)
    implementation(Dependencies.okHttpLoggingInterceptor)

    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitMoshiConverter)

    implementation(Dependencies.moshi)
    kapt(Dependencies.moshiCodeGen)
}