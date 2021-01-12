val properties = java.util.Properties()
if (rootProject.file("local.properties").exists()) {
    properties.load(rootProject.file("local.properties").inputStream())
}

plugins {
    id("io.codearte.nexus-staging")
}

nexusStaging {
    username = obtainProperty("ossUser")
    password = obtainProperty("ossToken")
    packageGroup = "com.github.alorma"
    numberOfRetries = 50
    delayBetweenRetriesInMillis = 3000
}

fun obtainProperty(property: String) =
    properties.getProperty(property) ?: System.getenv(property)