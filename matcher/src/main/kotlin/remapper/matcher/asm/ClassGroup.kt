package remapper.matcher.asm

import org.objectweb.asm.tree.ClassNode

/**
 * Represents a group of ASM [ClassNode]s loaded from a Jar file.
 */
class ClassGroup {

    /**
     * Class store
     */
    private val classes = hashSetOf<Class>()

    /**
     * Add a [ClassNode] to the class store.
     * @param node The [ClassNode] object to add.
     * @return The result status as a [Boolean]
     */
    fun add(node: ClassNode): Boolean {
        return classes.add(Class(node))
    }

    /**
     * Builds the hierarchy for all entries in [classes]
     */
    fun buildHierarchy() {
        classes.forEach {
            it.parents.clear()
            it.children.clear()
        }

        classes.forEach classLoop@ { c ->
            run {
                val parent = this[c.superName] ?: return@run
                c.parents.add(parent)
                parent.children.add(c)
            }

            c.interfaces.mapNotNull { this[it] }.forEach {
                c.parents.add(it)
                it.children.add(c)
            }
        }
    }

    /**
     * Gets a [Class] in the [classes] store which has a class name of [name]
     * @param name The name of the class to get.
     * @return [Class] The desired [Class] object.
     */
    operator fun get(name: String): Class? = classes.firstOrNull { it.name == name }
}