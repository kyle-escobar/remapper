package remapper.matcher.asm

import org.objectweb.asm.Type
import org.objectweb.asm.tree.ClassNode

/**
 * Represents an ASM loaded [ClassNode]
 * @param node The associated [ClassNode]
 */
class Class(private val node: ClassNode) : Matchable<Class> {

    /**
     * The name of the class
     */
    var name: String
        get() = node.name
        set(value) { node.name = value }

    /**
     * The parent class name
     */
    var superName: String
        get() = node.superName
        set(value) { node.superName = value }


    /**
     * The interfaces of this class.
     */
    var interfaces
        get() = node.interfaces
        set(value) { node.interfaces = value }

    /**
     * The type for this class
     */
    val type get() = Type.getObjectType(node.name)

    /**
     * The access integer
     */
    val access get() = node.access

    /**
     * The parent [Class]s for this class.
     */
    val parents = mutableListOf<Class>()

    /**
     * The [Class]s which inherit this class.
     */
    val children = mutableListOf<Class>()

    /**
     * The matched class in reference group.
     */
    override var match: Matchable<Class>? = null
}