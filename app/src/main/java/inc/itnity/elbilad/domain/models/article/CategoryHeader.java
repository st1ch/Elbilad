package inc.itnity.elbilad.domain.models.article;

import static inc.itnity.elbilad.domain.models.article.ArticleItem.TYPE.CATEGORY_HEADER;
import static inc.itnity.elbilad.domain.models.article.ArticleItem.TYPE.CATEGORY_HEADER_ORANGE;

/**
 * Created by st1ch on 12.02.17.
 */

public class CategoryHeader implements ArticleItem {

  private String title;
  private boolean orange;

  public CategoryHeader(String title, boolean orange) {
    this.title = title;
    this.orange = orange;
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
    if(orange){
      return CATEGORY_HEADER_ORANGE;
    }
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
