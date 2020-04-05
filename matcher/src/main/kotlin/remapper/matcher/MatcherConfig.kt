package remapper.matcher

import java.io.File

data class MatcherConfig(
    val inputJar: File,
    val referenceJar: File,
    val outputDir: File
)