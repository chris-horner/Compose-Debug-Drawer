plugins {
    `kotlin-dsl`
}

val nexus = "0.22.0"

repositories {
    jcenter()
    mavenCentral()
    google()
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    gradlePluginPortal()
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())
    implementation("io.codearte.gradle.nexus:gradle-nexus-staging-plugin:$nexus")
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}
