package subjects

import io.reactivex.rxjava3.subjects.PublishSubject
import org.slf4j.LoggerFactory


object Demo {

    val LOG = LoggerFactory.getLogger(Demo::class.java)

    @JvmStatic
    fun main(args: Array<String>) {
        val subject = PublishSubject.create<String>()
        val serialized = subject.toSerialized() //Thread safe
        serialized.subscribe { x -> LOG.info(x) }
        serialized.subscribe { e -> LOG.info("Observer 2 $e") }
        serialized.onNext("Hello")
        serialized.onNext("BasicsStrong")
        serialized.onComplete()
        serialized.onNext("BasicsStrong")
    }
}