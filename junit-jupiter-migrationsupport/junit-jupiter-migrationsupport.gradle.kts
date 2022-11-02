plugins {
	`java-library-conventions`
	`junit4-compatibility`
	`testing-conventions`
}

description = "JUnit Jupiter Migration Support"

dependencies {
	api(platform(projects.junitBom))
	api(libs.junit4)
	api(projects.junitJupiterApi)

	compileOnly("org.checkerframework:checker-qual:3.26.0")
	compileOnlyApi(libs.apiguardian)

	testImplementation(projects.junitJupiterEngine)
	testImplementation(projects.junitPlatformLauncher)
	testImplementation(projects.junitPlatformSuiteEngine)
	testImplementation(projects.junitPlatformTestkit)

	osgiVerification(projects.junitJupiterEngine)
	osgiVerification(projects.junitPlatformLauncher)
	osgiVerification("org.checkerframework:checker-qual:3.26.0")
}

tasks.jar {
	bundle {
		bnd("""
			# Import JUnit4 packages with a version
			Import-Package: \
				${extra["importAPIGuardian"]},\
				org.junit;version="[${libs.versions.junit4Min.get()},5)",\
				org.junit.platform.commons.logging;status=INTERNAL,\
				org.junit.rules;version="[${libs.versions.junit4Min.get()},5)",\
				*
		""")
	}
}
