package com.khalturin.dmitriy.data.repository

import android.content.Context
import com.khalturin.dmitriy.data.database.SQLiteDatabase
import com.khalturin.dmitriy.data.database.entity.FeedEntity
import com.khalturin.dmitriy.data.database.entity.SettingsEntity
import com.khalturin.dmitriy.data.dto.mapper.ArticleDataMapper
import com.khalturin.dmitriy.data.dto.mapper.FeedEntityMapper
import com.khalturin.dmitriy.domain.repository.FeedRepository
import com.khalturin.dmitriy.domain.vo.Feed
import com.khalturin.dmitriy.library.rx.MyObserverCompose
import com.prof.rssparser.Parser
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 28.10.18 20:38.
 */
class FeedRepositoryImpl @Inject constructor(mContext: Context) : FeedRepository {

  private val database: SQLiteDatabase = SQLiteDatabase.getInstance(mContext) !!
  private val feedDao = database.feedDao()
  private val articleDao = database.articleDao()
  private val settingsDao = database.settingsDao()

  override fun getFeedsList() = Observable.create<List<Feed>> { emitter ->
    feedDao.getObservableFeedsList()
      .map { list -> FeedEntityMapper.transform(list) }
      .subscribe(
        emitter::onNext,
        emitter::onError
      )
  } .compose(MyObserverCompose.applyMainThreadScheduler()) !!

  override fun addFeed(feedUrl: String) = Observable.create<Boolean> { emitter ->
    val parse = Parser()

    parse.execute(feedUrl)

    parse.onFinish(object : Parser.OnTaskCompleted {
      override fun onTaskCompleted(p0: ArrayList<com.prof.rssparser.Article>?) {
        try {
          val data = p0 ?: throw NullPointerException() // TODO: throw null articles exception
          val feedId = feedDao.addFeed(FeedEntity(
            null,
            feedUrl,
            Date()
          ))

          articleDao.addArticles(ArticleDataMapper.transform(data, feedId))

          settingsDao.updateSettings(SettingsEntity(
            1L,
            feedId
          ))

          emitter.onNext(true)
          emitter.onComplete()
        } catch (exception: Exception) {
          emitter.onError(exception)
        }
      }

      override fun onError() = emitter.onError(NullPointerException()) // TODO: pipe for parser exception
    })
  } .compose(MyObserverCompose.applyMainThreadScheduler()) !!

  override fun setFeed(feedId: Long) = Observable.create<Boolean> { emitter ->
    settingsDao.updateSettings(SettingsEntity(
      1L,
      feedId
    ))

    emitter.onNext(true)
  } .compose(MyObserverCompose.applyMainThreadScheduler()) !!

}
