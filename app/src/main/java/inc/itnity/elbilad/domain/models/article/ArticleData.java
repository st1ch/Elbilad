package inc.itnity.elbilad.domain.models.article;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by st1ch on 15.01.17.
 */

public class ArticleData {
  @SerializedName("article") private List<Article> articles;

  public List<Article> getArticles() {
    return articles;
  }

  public void setArticles(List<Article> articles) {
    this.articles = articles;
  }

  @Override public String toString() {
    return "ArticleData{" +
        "articles=" + articles +
        '}';
  }
}
