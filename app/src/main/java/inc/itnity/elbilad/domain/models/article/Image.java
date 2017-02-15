package inc.itnity.elbilad.domain.models.article;

import com.google.gson.annotations.SerializedName;

/**
 * Created by st1ch on 10.02.17.
 */

public class Image extends BaseArticle {

  @SerializedName("id_galerie_categorie") private int categoryId;
  @SerializedName("description") private String preview;

  public Image() {
    super.setType(TYPE.GALLERY);
  }

  @Override public String getPreview() {
    return preview;
  }

  public int getCategoryId() {
    return categoryId;
  }

  @Override public String toString() {
    return super.toString()
        + " Image{"
        + "categoryId="
        + categoryId
        + ", preview='"
        + preview
        + '\''
        + '}';
  }
}
