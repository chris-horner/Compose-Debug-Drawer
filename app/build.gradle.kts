plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdkVersion(30)

    defaultConfig {
        applicationId = "com.alorma.composedrawer"
        minSdkVersion(23)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    lintOptions {
        isCheckReleaseBuilds = false
        isAbortOnError = false
    }
}

dependencies {
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.1")

    implementation(project(":drawer-modules"))
    implementation(project(":drawer-base"))

    implementation("androidx.activity:activity-compose:1.3.0-alpha02")
    implementation("androidx.compose.foundation:foundation:1.0.0-alpha12")
    implementation("androidx.compose.foundation:foundation-layout:1.0.0-alpha12")
    implementation("androidx.compose.ui:ui:1.0.0-alpha12")
    implementation("androidx.compose.material:material:1.0.0-alpha12")
    implementation("androidx.compose.ui:ui-tooling:1.0.0-alpha12")

    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.3.0")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.0")

}