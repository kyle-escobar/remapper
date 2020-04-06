package remapper.matcher.classifier

/**
 * Represents a check performed within a classifier type
 */
interface ClassifierCheck<T> {

    /**
     * The name of the check.
     */
    val name: String

    /**
     * The weight multiplier of the check
     */
    var weight: Int

    /**
     * Get the score of the check given two targets.
     */
    fun getScore(targetA: T, targetB: T): Double
}