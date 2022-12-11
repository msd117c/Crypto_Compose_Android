plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    api(Dependencies.jUnit)
    api(Dependencies.mockitoKotlin)
    api(Dependencies.coroutinesTest)

    testImplementation("org.mockito:mockito-inline:4.0.0")
}
