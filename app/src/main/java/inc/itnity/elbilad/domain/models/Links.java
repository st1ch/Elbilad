package inc.itnity.elbilad.domain.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by st1ch on 15.01.17.
 */

public class Links {

  @SerializedName("self") private Link self;
  @SerializedName("first") private Link first;
  @SerializedName("last") private Link last;

  public Link getSelf() {
    return self;
  }

  public void setSelf(Link self) {
    this.self = self;
  }

  public Link getFirst() {
    return first;
  }

  public void setFirst(Link first) {
    this.first = first;
  }

  public Link getLast() {
    return last;
  }

  public void setLast(Link last) {
    this.last = last;
  }

  @Override public String toString() {
    return "Links{" +
        "self=" + self +
        ", first=" + first +
        ", last=" + last +
        '}';
  }
}
