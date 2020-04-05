package remapper.matcher

import org.objectweb.asm.ClassReader
import org.objectweb.asm.tree.ClassNode
import org.tinylog.kotlin.Logger
import remapper.matcher.asm.ClassGroup
import java.io.File
import java.util.jar.JarFile

/**
 * Responsible for controlling the various matching operations.
 * @param config The configuration arguments for the matcher.
 */
class MatchController(private val config: MatcherConfig) {

    /**
     * The input jar [ClassGroup]
     */
    private val inputGroup = ClassGroup()

    /**
     * The reference jar [ClassGroup]
     */
    private val referenceGroup = ClassGroup()

    /**
     * Runs the matchers based on [config] input.
     */
    fun init() {
        Logger.info("Initializing matcher...")

        /**
         * Create the output directory if it does not exist.
         */
        if(!config.outputDir.exists()) {
            Logger.info("Creating output directory.")
            config.outputDir.mkdirs()
        }

        /**
         * Load the input jar
         */
        Logger.info("Loading input jar file: '${config.inputJar.name}'.")
        this.loadJar(config.inputJar).asSequence().forEach { inputGroup.add(it) }

        /**
         * Load reference jar
         */
        Logger.info("Loading reference jar file: '${config.referenceJar.name}'.")
        this.loadJar(config.referenceJar).asSequence().forEach { referenceGroup.add(it) }

        Logger.info("Building hierarchy.")
        this.inputGroup.buildHierarchy()
        this.referenceGroup.buildHierarchy()

        /**
         * Execute the target matchers.
         */
        this.executeMatchers()
    }

    /**
     * Runs the matcher
     */
    private fun executeMatchers() {

    }

    /**
     * Loads a jar's classes as [ClassNode] objects.
     * @param file The jar file to load from.
     * @return [Collection] The classes in the jar as [ClassNode] objects.
     */
    private fun loadJar(file: File): Collection<ClassNode> {
        val list = mutableListOf<ClassNode>()

        JarFile(file).use { jar ->
            jar.stream().iterator().asSequence()
                .filter { it.name.endsWith(".class") }
                .forEach {
                    val node = ClassNode()
                    val reader = ClassReader(jar.getInputStream(it))
                    reader.accept(node, ClassReader.SKIP_FRAMES or ClassReader.SKIP_DEBUG)
                    list.add(node)
                }
        }

        return list
    }
}