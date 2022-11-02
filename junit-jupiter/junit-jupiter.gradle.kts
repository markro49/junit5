plugins {
	`java-library-conventions`
}

description = "JUnit Jupiter (Aggregator)"

dependencies {
	api(platform(projects.junitBom))
	api(projects.junitJupiterApi)
	api(projects.junitJupiterParams)

	compileOnly("org.checkerframework:checker-qual:3.26.0")

	runtimeOnly(projects.junitJupiterEngine)

	osgiVerification(projects.junitJupiterEngine)
	osgiVerification(projects.junitPlatformLauncher)
}
