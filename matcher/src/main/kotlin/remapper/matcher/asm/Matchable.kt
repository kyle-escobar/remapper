package remapper.matcher.asm

interface Matchable<T> {

    /**
     * The matched [T] object
     */
    var match: Matchable<T>?
}