val properties = java.util.Properties()
properties.load(rootProject.file("local.properties").inputStream())

val isReleaseVersion = !version.toString().endsWith("SNAPSHOT")

plugins {
    `maven-publish`
    signing
}

project.extra.set("bintray.user", properties.getProperty("bintray.user"))
project.extra.set("bintray.key", properties.getProperty("bintray.key"))

publishing {

    publications.configureEach {
        if (this is MavenPublication) {
            val publicName = "${rootProject.name} ${name.capitalize()}"
            pom {
                name.set(publicName)
                description.set("Compose DebugDrawer")
                url.set("https://github.com/alorma/Compose-Debug-Drawer")
                licenses {
                    license {
                        name.set("The Apache Software License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        distribution.set("repo")
                    }
                }
                developers {
                    developer {
                        id.set("alorma")
                        name.set("Bernat Borrás")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/alorma/Compose-Debug-Drawer.git")
                    developerConnection.set("scm:git:ssh://git@github.com/alorma/Compose-Debug-Drawer.git")
                    url.set("https://github.com/alorma/Compose-Debug-Drawer")
                }
            }
        }
    }

    repositories {
        maven {
            url = uri("https://api.bintray.com/maven/alorma/maven/com.alorma:compose_drawer/;publish=1")
            credentials {
                username = properties.getProperty("bintray.user")
                password = properties.getProperty("bintray.key")
            }
        }
    }

}

tasks.withType<Sign>().configureEach {
    onlyIf { isReleaseVersion }
}

signing {
    sign(publishing.publications)
}