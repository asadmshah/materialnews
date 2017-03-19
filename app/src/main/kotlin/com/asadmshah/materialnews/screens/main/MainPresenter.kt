package com.asadmshah.materialnews.screens.main

import android.os.Bundle
import com.asadmshah.materialnews.models.Article
import com.asadmshah.materialnews.models.Publisher
import com.asadmshah.materialnews.news.NewsClient
import com.asadmshah.materialnews.schedulers.Schedulers
import com.asadmshah.materialnews.screens.base.PresenterComponent
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import timber.log.Timber

class MainPresenter(private val view: MainContract.View, private val component: PresenterComponent) : MainContract.Presenter {

    val client: NewsClient by lazy { component.newsClient() }
    val schedulers: Schedulers by lazy { component.schedulers() }

    var newsDisposable: Disposable? = null
    var content: List<Pair<Publisher, List<Article>>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        requestContent()
    }

    override fun onSaveInstanceState(outState: Bundle) {

    }

    override fun onDestroy() {
        newsDisposable?.dispose()
    }

    override fun getPublishersCount(): Int {
        return content?.size ?: 0
    }

    override fun getPageContent(position: Int): Pair<Publisher, List<Article>> {
        return content?.get(position)!!
    }

    fun requestContent() {
        client.get()
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.main())
                .toList()
                .subscribe(object : SingleObserver<MutableList<Pair<Publisher, List<Article>>>> {
                    override fun onSuccess(response: MutableList<Pair<Publisher, List<Article>>>) {
                        Timber.d("Received ${response.size}")

                        content = response
                        view.setAdapter()
                    }

                    override fun onError(e: Throwable) {
                        Timber.e(e)

                        newsDisposable = null
                    }

                    override fun onSubscribe(d: Disposable) {
                        Timber.d("onSubscribe")

                        newsDisposable = d
                    }
                })
    }
}