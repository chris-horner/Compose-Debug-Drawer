plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.jfrog.bintray")
    id("maven-publish")
}

apply(from = "../publication.gradle")

android {
    compileSdkVersion(30)

    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
        freeCompilerArgs = freeCompilerArgs.toMutableList().apply {
            add("-Xopt-in=kotlin.RequiresOptIn")
            add("-Xopt-in=kotlin.Experimental")
            add("-Xuse-experimental=kotlin.Experimental")
        }
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.0-alpha10"
        kotlinCompilerVersion = "1.4.21"
    }
    lintOptions {
        isCheckReleaseBuilds = false
        isAbortOnError = false
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.21")

    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.2.1")
    implementation("androidx.compose.ui:ui:1.0.0-alpha10")
    implementation("androidx.compose.material:material:1.0.0-alpha10")
    implementation("androidx.compose.material:material-icons-core:1.0.0-alpha10")
    implementation("androidx.compose.material:material-icons-extended:1.0.0-alpha10")
    implementation("androidx.compose.ui:ui-tooling:1.0.0-alpha10")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.0-rc01")
}