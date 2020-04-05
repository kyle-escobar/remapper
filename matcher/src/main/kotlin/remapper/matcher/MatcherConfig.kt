package remapper.matcher

import java.io.File

data class MatcherConfig(
    val inputJar: File,
    val referenceJar: File,
    val outputDir: File,
    val target: MatchTarget = MatchTarget.ALL
)

enum class MatchTarget {
    CLASS,
    METHOD,
    FIELD,
    ALL
}