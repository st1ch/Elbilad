package inc.itnity.elbilad.domain.models.article;

/**
 * Created by st1ch on 10.02.17.
 */

public interface ArticleItem {

  String getId();

  String getCategoryTitle();

  String getPreview();

  int getType();

  String getImage();

  String getTime();

  String getDate();

  interface TYPE {
    int SIMPLE = 101;
    int TOP = 0;
    int TOP_5 = 1;
    int IMPORTANT = 2;
    int VIDEO_ARTICLE = 3;
    int INTERNATIONAL = 4;
    int SPORT = 5;
    int MUSIC = 6;
    int CULTURE = 7;
    int RASID = 8;
    int MAHAKIM = 9;
    int MOST_READ = 10;
    int VIDEO = 11;
    int GALLERY = 12;
    int CATEGORY_HEADER = 13;
    int CATEGORY_HEADER_ORANGE = 14;
  }
}
