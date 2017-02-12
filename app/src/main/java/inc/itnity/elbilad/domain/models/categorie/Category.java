package inc.itnity.elbilad.domain.models.categorie;

import com.google.gson.annotations.SerializedName;

/**
 * Created by st1ch on 15.01.17.
 */

public class Category {
  @SerializedName("id") private int id;
  @SerializedName("titre") private String title;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override public String toString() {
    return "Category{" +
        "id=" + id +
        ", title='" + title + '\'' +
        '}';
  }
}
