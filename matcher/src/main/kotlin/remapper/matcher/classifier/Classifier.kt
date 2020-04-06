package remapper.matcher.classifier

/**
 * Represents an ASM type classification object
 */
abstract class Classifier<T> {

    /**
     * A storage of checks.
     */
    private val checks = mutableListOf<ClassifierCheck<T>>()

    /**
     * Adds a check to the [checks] store table.
     * @param check The classifier check to add.
     * @param weight The weight multiplier of the check.
     */
    fun addCheck(check: ClassifierCheck<T>, weight: Int) {
        check.weight = weight
        checks.add(check)
    }
}