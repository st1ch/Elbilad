package inc.itnity.elbilad.domain.models.categorie;

import com.google.gson.annotations.SerializedName;
import inc.itnity.elbilad.domain.models.Links;

/**
 * Created by st1ch on 15.01.17.
 */

public class Category {
  @SerializedName("id") private int id;
  @SerializedName("titre") private String title;
  @SerializedName("active") private String active;
  @SerializedName("_links") private Links links;

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

  public String getActive() {
    return active;
  }

  public void setActive(String active) {
    this.active = active;
  }

  public Links getLinks() {
    return links;
  }

  public void setLinks(Links links) {
    this.links = links;
  }

  @Override public String toString() {
    return "Category{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", active='" + active + '\'' +
        ", links=" + links +
        '}';
  }
}
