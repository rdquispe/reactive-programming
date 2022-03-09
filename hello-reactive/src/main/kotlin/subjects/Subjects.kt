package subjects

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject
import org.slf4j.LoggerFactory


object Subjects {

    val LOG = LoggerFactory.getLogger(Subjects::class.java)

    @Throws(InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val src1 = Observable.just(5, 10, 15, 20)
            .subscribeOn(Schedulers.computation())
        val src2 = Observable.just(50, 100, 150, 200)
            .subscribeOn(Schedulers.computation())

        val subject: Subject<Any> = PublishSubject.create()
        subject.subscribe { e -> LOG.info("Subject: $e") } //Observer 1

		src1.subscribe{e -> LOG.info(e.toString())}
		src2.subscribe{e -> LOG.info(e.toString())}

        Thread.sleep(9000)
    }
}