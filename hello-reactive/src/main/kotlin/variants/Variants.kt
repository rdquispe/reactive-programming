package variants

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import org.slf4j.LoggerFactory


fun main() {

	val LOG = LoggerFactory.getLogger("Variants")


	val source: Observable<String> = Observable.just("Alex", "Justin", "Jack")
	val source1: Observable<String> = Observable.empty()


	source1
		.first("Name")
		.subscribe{ element -> LOG.info("Source1: {}", element) }

	Single.just("Alex")
		.subscribe{ element -> LOG.info("Single: {}", element) }


	source1
		.firstElement()
		.subscribe(System.out::println, { e -> e.printStackTrace() }) { LOG.info("Completed") }

	val completable = Completable.complete()

	println()

	completable.subscribe { LOG.info("Completed") }

	Completable
		.fromRunnable { LOG.info("Some process executing") }
		.subscribe { LOG.info("The process executed succesfully") }
}