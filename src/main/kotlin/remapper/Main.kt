package remapper

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.versionOption

object Main {

    /**
     * Root CLI command.
     */
    private class Cli : CliktCommand(
        name = "Remapper",
        help = "A tool for remap and mapping the OSRS game client.",
        printHelpOnEmptyArgs = true
    ) {

        init {
            versionOption("1.0")
        }

        override fun run() {}
    }

    /**
     * Main method.
     * @param args The cli arguments.
     */
    @JvmStatic
    fun main(args: Array<String>) {

    }
}