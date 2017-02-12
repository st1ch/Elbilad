package inc.itnity.elbilad.domain.models.article;

import static inc.itnity.elbilad.domain.models.article.ArticleItem.TYPE.CATEGORY_HEADER;

/**
 * Created by st1ch on 12.02.17.
 */

public class CategoryHeader implements ArticleItem {

  private String title;

  public CategoryHeader(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override public int getType() {
    return CATEGORY_HEADER;
  }
}
