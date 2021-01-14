val properties = java.util.Properties()
if (rootProject.file("local.properties").exists()) {
    properties.load(rootProject.file("local.properties").inputStream())
}

plugins {
    id("io.codearte.nexus-staging")
}

nexusStaging {
    username = obtainProperty("OSS_USER")
    password = obtainProperty("OSS_TOKEN")
    packageGroup = "com.github.alorma"
    numberOfRetries = 50
    delayBetweenRetriesInMillis = 3000
}

fun obtainProperty(property: String): String {
    val localProperty = properties.getProperty(property)?.takeIf { it.isNotBlank() }
    val systemProperty = System.getenv(property)?.takeIf { it.isNotBlank() }

    return localProperty ?: systemProperty ?: "INVALID_$property"
}
