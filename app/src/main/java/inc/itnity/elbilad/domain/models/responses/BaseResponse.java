package inc.itnity.elbilad.domain.models.responses;

import com.google.gson.annotations.SerializedName;
import inc.itnity.elbilad.domain.models.Links;

/**
 * Created by st1ch on 15.01.17.
 */

public class BaseResponse<T> {
  @SerializedName("_links") private Links links;

  @SerializedName("_embedded") private T data;

  @SerializedName("page_count") private int pageCount;

  @SerializedName("page_size") private int pageSize;

  @SerializedName("total_items") private int totalItems;

  @SerializedName("page") private int page;

  public Links getLinks() {
    return links;
  }

  public void setLinks(Links links) {
    this.links = links;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public int getPageCount() {
    return pageCount;
  }

  public void setPageCount(int pageCount) {
    this.pageCount = pageCount;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getTotalItems() {
    return totalItems;
  }

  public void setTotalItems(int totalItems) {
    this.totalItems = totalItems;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  @Override public String toString() {
    return "BaseResponse{" +
        "links=" + links +
        ", data=" + data +
        ", pageCount=" + pageCount +
        ", pageSize=" + pageSize +
        ", totalItems=" + totalItems +
        ", page=" + page +
        '}';
  }
}
