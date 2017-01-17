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
  String ACCEPT_JSON = "application/json";
  String ACCEPT_HAL_JSON = "application/hal+json";
  String ACCEPT_ELBILAD = "application/vnd.elbilad.v1+json";


}
