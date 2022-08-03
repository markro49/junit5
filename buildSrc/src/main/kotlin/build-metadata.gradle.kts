import org.codehaus.groovy.runtime.ProcessGroovyMethods
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

val buildTimeAndDate: OffsetDateTime by extra {

	// SOURCE_DATE_EPOCH is a UNIX timestamp for pinning build metadata against
	// in order to achieve reproducible builds
	//
	// More details - https://reproducible-builds.org/docs/source-date-epoch/

	if (System.getenv().containsKey("SOURCE_DATE_EPOCH")) {

		val sourceDateEpoch = System.getenv("SOURCE_DATE_EPOCH").toLong()

		Instant.ofEpochSecond(sourceDateEpoch).atOffset(ZoneOffset.UTC)

	} else {
		OffsetDateTime.now()
	}
}

val buildDate: String by extra { DateTimeFormatter.ISO_LOCAL_DATE.format(buildTimeAndDate) }
val buildTime: String by extra { DateTimeFormatter.ofPattern("HH:mm:ss.SSSZ").format(buildTimeAndDate) }
val buildRevision: String by extra {
	ProcessGroovyMethods.getText(ProcessGroovyMethods.execute("git rev-parse --verify HEAD"))
}
val builtByValue by extra { project.findProperty("builtBy") ?: project.property("defaultBuiltBy") }
