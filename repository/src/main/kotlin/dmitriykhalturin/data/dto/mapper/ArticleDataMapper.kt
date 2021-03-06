package dmitriykhalturin.data.dto.mapper

import dmitriykhalturin.data.database.entity.ArticleEntity
import com.prof.rssparser.Article

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for android-rss-reader on 29.10.18 0:39.
 */
class ArticleDataMapper {

  companion object {

    private fun transform(data: Article, feedId: Long) = ArticleEntity(
      null,
      feedId,
      data.title,
      data.author,
      data.description,
      data.content,
      data.image,
      data.link,
      data.pubDate,
      data.categories
    )

    fun transform(data: MutableList<Article>, feedId: Long): MutableList<ArticleEntity> {
      val list = ArrayList<ArticleEntity>()

      data.map { item -> list.add(transform(item, feedId)) }

      return list
    }

  }

}
