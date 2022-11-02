plugins {
	`java-library-conventions`
}

description = "JUnit Platform Test Kit"

dependencies {
	api(platform(projects.junitBom))
	api(libs.assertj)
	api(libs.opentest4j)
	api(projects.junitPlatformLauncher)

	compileOnlyApi(libs.apiguardian)
	compileOnly("org.checkerframework:checker-qual:3.26.0")

	osgiVerification(projects.junitJupiterEngine)
	osgiVerification(projects.junitPlatformLauncher)
}
