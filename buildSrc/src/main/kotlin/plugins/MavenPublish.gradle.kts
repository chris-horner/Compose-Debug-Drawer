val properties = java.util.Properties()
if (rootProject.file("local.properties").exists()) {
    properties.load(rootProject.file("local.properties").inputStream())
}

plugins {
    id("maven-publish")
    signing
}

signing {
    useGpgCmd()
    sign(publishing.publications)
}

val isRelease: Boolean = !version.toString().endsWith("SNAPSHOT")

publishing {
    publications.withType<MavenPublication> {
        pom {
            name.set("Compose DebugDrawer")
            description.set("Debug drawer for compose")
            url.set("http://github.com/alorma/compose-debug-drawer")
            licenses {
                license {
                    name.set("The Apache License, Version 2.0")
                    url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                }
            }
            developers {
                developer {
                    id.set("alorma")
                    name.set("Bernat Borrás Paronella")
                    email.set("bernaatbor15@gmail.com")
                }
            }
            scm {
                url.set("https://github.com/alorma/compose-debug-drawer")
                connection.set("scm:git:https://github.com/alorma/compose-debug-drawer.git")
                developerConnection.set("scm:git:git@github.com:alorma/compose-debug-drawer.git")
            }
        }
        repositories {
            val releasesRepo = "https://oss.sonatype.org/service/local/staging/deploy/maven2"
            val snapshotsRepo = "https://oss.sonatype.org/content/repositories/snapshots"

            maven(if (isRelease) releasesRepo else snapshotsRepo) {
                credentials {
                    username = obtainProperty("OSS_USER")
                    password = obtainProperty("OSS_TOKEN").also { println(it) }
                }
            }
        }
    }
}

fun obtainProperty(property: String): String {
    val localProperty = properties.getProperty(property)?.takeIf { it.isNotBlank() }.also { println("Local: $it") }
    val systemProperty = System.getenv(property)?.takeIf { it.isNotBlank() }.also { println("System: $it") }

    return localProperty ?: systemProperty ?: error("Property: $property is null")
}