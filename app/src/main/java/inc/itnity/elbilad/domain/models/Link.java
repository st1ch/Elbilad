package inc.itnity.elbilad.domain.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by st1ch on 15.01.17.
 */

public class Link {

  @SerializedName("href")
  private String href;

  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  @Override public String toString() {
    return "Link{" +
        "href='" + href + '\'' +
        '}';
  }
}
