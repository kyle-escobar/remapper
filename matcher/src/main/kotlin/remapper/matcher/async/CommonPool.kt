package remapper.matcher.async

import java.util.concurrent.ForkJoinPool

object CommonPool : Pool(ForkJoinPool.commonPool())