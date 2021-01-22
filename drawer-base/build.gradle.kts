plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
//    id("com.jfrog.bintray")
//    id("maven-publish")
}

//apply(from = "../publication.gradle")

kotlin {
    jvm()

    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.material)
                implementation(compose.materialIconsExtended)
                implementation(compose.ui)
            }
        }
    }
}
