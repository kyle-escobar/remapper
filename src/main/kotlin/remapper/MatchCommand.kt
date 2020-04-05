package remapper

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.file
import remapper.matcher.MatchController
import remapper.matcher.MatcherConfig
import java.io.File

/**
 * The CLI match command.
 */
class MatchCommand : CliktCommand(
    name = "match",
    help = "Matches two client jars and generates mappings."
) {

    private val inputJar by argument(name = "Input jar", help = "The file path to the input jar.").file(mustExist = true, canBeDir = false)

    private val referenceJar by argument(name = "Reference jar", help = "The file path to the reference jar.").file(mustExist = true, canBeDir = false)

    private val outputDir by option("-o", "--output", help = "The directory to output the mappings.").file(canBeDir = true).default(File("mappings-output/"))

    override fun run() {
        val config = MatcherConfig(this.inputJar, this.referenceJar, this.outputDir)
        MatchController(config).init()
    }
}