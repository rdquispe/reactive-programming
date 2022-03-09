package subjects

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.Subject
import io.reactivex.rxjava3.subjects.UnicastSubject
import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit


object SubjectTypes2 {

    val LOG = LoggerFactory.getLogger(SubjectTypes2::class.java)

    @Throws(InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val subject: Subject<Long> = UnicastSubject.create()

        Observable.interval(500, TimeUnit.MILLISECONDS)
            .subscribe(subject)

        Thread.sleep(2000)

        subject.subscribe { e -> LOG.info("Subscriber 1: $e") }

        Thread.sleep(2000)
    }
}