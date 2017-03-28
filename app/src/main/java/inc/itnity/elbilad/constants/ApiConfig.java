package inc.itnity.elbilad.constants;

/**
 * Created by st1ch on 15.01.17.
 */

public interface ApiConfig {
  int HTTP_TIMEOUT = 180000;

  String BASE_URL = "https://api.elbilad.net/";
  String Authorization = "authorization";
  String Accept = "Accept";
  String TOKEN = "Basic bXlraGFpbG92aXNoOjgsc3RjMkZPWE49fDN5dUg=";
  String YOUTUBE_KEY = "AIzaSyA_cpBzUR8o_Eg_m00LchbOsf09791lzgs";
  String ACCEPT_JSON = "application/json";
  String ACCEPT_HAL_JSON = "application/hal+json";
  String ACCEPT_ELBILAD = "application/vnd.elbilad.v1+json";

  String IMAGE_BASE_URL = "https://cdn.elbilad.net/media/images/article/thumbs/";
  String VIDEO_IMAGE_BASE_URL = "https://cdn.elbilad.net/media/images/video/thumbs/";
  String GALERY_IMAGE_BASE_URL = "https://cdn.elbilad.net/media/images/galerie/thumbs/";

  String UNE = "une-"; // 555x316
  String VIDEO_UNE = "une"; // 555x316
  String THUMB = "thumb-"; // 112x84
  String VIDEO_THUMB = "thumb-"; // 112x84
  String THUMB_CAT = "thumbCat-"; // 150x110
  String LARGE_CAT = "largeCat-"; // 399x169
  String REP = "rep-"; // 166x102
  String LARGE = "large-"; // 600x355
  String VIDEO_LARGE = "large"; // 600x355
  String UNE_SLIDE = "uneSlide-"; // 722x410
  String UNETH_SLIDE = "unethSlide-"; // 230x163

  int LAST_NEWS_CATEGORY_ID = 28;
  int LAST_NEWS_LIMIT = 6;

}
