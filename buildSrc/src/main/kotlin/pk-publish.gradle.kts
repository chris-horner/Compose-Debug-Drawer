val properties = java.util.Properties()
properties.load(rootProject.file("local.properties").inputStream())

val isReleaseVersion = !version.toString().endsWith("SNAPSHOT")

plugins {
    `maven-publish`
}

publishing {

    publications.configureEach {
        if (this is MavenPublication) {
            val publicName = "${rootProject.name} ${name.capitalize()}"
            pom {
                name.set(publicName)
                description.set("Compose dDebugDrawer")
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
            val releasesRepoUrl = uri(properties.getProperty("nexus.pro_url"))
            val snapshotsRepoUrl = uri(properties.getProperty("nexus.snap_url"))
            url = if (isReleaseVersion) releasesRepoUrl else snapshotsRepoUrl
            credentials {
                username = properties.getProperty("nexus.username")
                password = properties.getProperty("nexus.password")
            }
        }
    }

}

tasks.withType<Sign>().configureEach {
    onlyIf { isReleaseVersion }
}