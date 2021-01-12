plugins {
    id("io.codearte.nexus-staging")
}

nexusStaging {
    username = System.getenv("ossUser")
    password = System.getenv("ossToken")
    packageGroup = "com.github.alorma"
    numberOfRetries = 50
    delayBetweenRetriesInMillis = 3000
}
