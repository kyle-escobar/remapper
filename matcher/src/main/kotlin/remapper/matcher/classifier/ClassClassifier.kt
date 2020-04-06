package remapper.matcher.classifier

import org.objectweb.asm.Opcodes
import remapper.matcher.asm.Class
import kotlin.math.pow

/**
 * Responsible for classifying ASM [Class]s
 */
class ClassClassifier : Classifier<Class>() {

    init {
        addCheck(classTypeCheck, 20)
    }

    companion object {
        private val classTypeCheck = object : BaseClassifierCheck("classTypeCheck") {
            override fun getScore(targetA: Class, targetB: Class): Double {
                val mask = Opcodes.ACC_ENUM or Opcodes.ACC_INTERFACE or Opcodes.ACC_ABSTRACT
                val resultA = targetA.access and mask
                val resultB = targetB.access and mask
                return (1 - Integer.bitCount(resultA.toDouble().pow(resultB).toInt()) / 4).toDouble()
            }
        }

        /**
         * The base classifier check
         */
        private abstract class BaseClassifierCheck(override val name: String) : ClassifierCheck<Class> {
            override var weight = 1
        }
    }
}