plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 30

    defaultConfig {
        applicationId = "com.alorma.composedrawer"
        minSdk = 21
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()

        freeCompilerArgs = freeCompilerArgs + listOf(
            "-Xopt-in=kotlin.RequiresOptIn",
            "-Xopt-in=kotlin.Experimental",
            "-Xuse-experimental=kotlin.Experimental"
        )
    }
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.0-rc01"
    }

    lint {
        isCheckReleaseBuilds = false
        isAbortOnError = false
    }
}

dependencies {
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")

    debugImplementation(project(":drawer-ui-modules"))
    debugImplementation(project(":drawer-modules"))
    debugImplementation(project(":drawer-base"))

    implementation("androidx.activity:activity-compose:1.3.0-rc02")

    implementation("androidx.compose.foundation:foundation:1.0.0-rc02")
    implementation("androidx.compose.foundation:foundation-layout:1.0.0-rc02")
    implementation("androidx.compose.ui:ui:1.0.0-rc02")
    implementation("androidx.compose.material:material:1.0.0-rc02")
    implementation("androidx.compose.ui:ui-tooling:1.0.0-rc02")
}
