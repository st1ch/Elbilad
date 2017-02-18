package inc.itnity.elbilad.domain.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by st1ch on 18.02.17.
 */

public class Journal {
  @SerializedName("numero") private String number;
  @SerializedName("fichier") private String filename;
  @SerializedName("date") private String date;
  @SerializedName("path") private String urlPath;

  public String getNumber() {
    return number;
  }

  public String getFilename() {
    return filename;
  }

  public String getDate() {
    return date;
  }

  public String getUrlPath() {
    return urlPath;
  }

  public String getDateString() {
    String[] splitDate = date.split(" ")[0].split("-");
    return splitDate[2] + "/" + splitDate[1] + "/" + splitDate[0];
  }

  @Override public String toString() {
    return "Journal{"
        + "number='"
        + number
        + '\''
        + ", filename='"
        + filename
        + '\''
        + ", date='"
        + date
        + '\''
        + ", urlPath='"
        + urlPath
        + '\''
        + '}';
  }
}
