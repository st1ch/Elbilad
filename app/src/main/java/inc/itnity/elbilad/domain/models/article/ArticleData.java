package inc.itnity.elbilad.domain.models.article;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by st1ch on 15.01.17.
 */

public class ArticleData {
  @SerializedName("article") private List<BaseArticle> articles;

  public List<BaseArticle> getArticles() {
    return articles;
  }

  public void setArticles(List<BaseArticle> articles) {
    this.articles = articles;
  }

  @Override public String toString() {
    return "ArticleData{" +
        "articles=" + articles +
        '}';
  }
}
