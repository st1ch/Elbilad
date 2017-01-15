package inc.itnity.elbilad.domain.models.categorie;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by st1ch on 15.01.17.
 */

public class CategoryData {

  @SerializedName("categorie") private List<Category> categories;

  public List<Category> getCategories() {
    return categories;
  }

  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }

  @Override public String toString() {
    return "CategoryData{" +
        "categories=" + categories +
        '}';
  }
}
