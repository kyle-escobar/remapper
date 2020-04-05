package remapper.matcher

import org.tinylog.kotlin.Logger

/**
 * Responsible for controlling the various matching operations.
 * @param config The configuration arguments for the matcher.
 */
class MatchController(private val config: MatcherConfig) {

    /**
     * Runs the matchers based on [config] input.
     */
    fun startMatcher() {
        Logger.info("Starting matcher...")


    }
}