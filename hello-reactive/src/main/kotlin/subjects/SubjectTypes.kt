package subjects

import io.reactivex.rxjava3.subjects.AsyncSubject
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.ReplaySubject
import io.reactivex.rxjava3.subjects.Subject
import org.slf4j.LoggerFactory


object SubjectTypes {

    val LOG = LoggerFactory.getLogger(SubjectTypes::class.java)

    @Throws(InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {
//        val subject: Subject<String> = PublishSubject.create()
//        val subject: Subject<String> = ReplaySubject.create()
//        val subject: Subject<String> = BehaviorSubject.create()
        val subject: Subject<String> = AsyncSubject.create()
        subject.subscribe { e -> LOG.info("Subscriber 1: $e") }
        subject.onNext("a");
        subject.onNext("b");
        subject.onNext("c");

        subject.subscribe { e -> LOG.info("Subscriber 2: $e") }

        subject.onNext("d")
        subject.onNext("e")
        subject.onComplete()
    }
}