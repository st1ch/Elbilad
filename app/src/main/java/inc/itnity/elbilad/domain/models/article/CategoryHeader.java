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

  @Override public String getId() {
    return null;
  }

  @Override public String getCategoryTitle() {
    return null;
  }

  @Override public String getPreview() {
    return null;
  }

  @Override public int getType() {
    return CATEGORY_HEADER;
  }

  @Override public String getImage() {
    return null;
  }

  @Override public String getTime() {
    return null;
  }

  @Override public String getDate() {
    return null;
  }
}
