import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.3.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.44")
        classpath("de.mannodermaus.gradle.plugins:android-junit5:1.8.2.0")
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}

// subprojects {
//     tasks.withType<KotlinCompile>().configureEach {
//         kotlinOptions {
//             if (project.findProperty("composeCompilerReports") == "true") {
//                 freeCompilerArgs.plus(
//                     listOf(
//                         "-P",
//                         "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" +
//                                 project.buildDir.absolutePath + "/compose_compiler"
//                     )
//                 )
//                 freeCompilerArgs.plus(
//                     listOf(
//                         "-P",
//                         "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" +
//                                 project.buildDir.absolutePath + "/compose_compiler"
//                     )
//                 )
//             }
//         }
//     }
// }