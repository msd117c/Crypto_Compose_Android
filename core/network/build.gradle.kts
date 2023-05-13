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
    implementation(Dependencies.okHttp)
    implementation(Dependencies.okHttpLoggingInterceptor)

    api(Dependencies.retrofit)
    implementation(Dependencies.retrofitMoshiConverter)

    api(Dependencies.moshi)
    kapt(Dependencies.moshiCodeGen)
}