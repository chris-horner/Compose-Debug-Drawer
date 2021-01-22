import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform") // kotlin("jvm") doesn't work well in IDEA/AndroidStudio (https://github.com/JetBrains/compose-jb/issues/22)
    id("org.jetbrains.compose")
}

kotlin {
    jvm {
        withJava()
    }
    sourceSets {
        named("jvmMain") {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(project(":drawer-base"))
                implementation(project(":drawer-modules"))
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "com.alorma.composedrawer.MainKt"
    }
}
