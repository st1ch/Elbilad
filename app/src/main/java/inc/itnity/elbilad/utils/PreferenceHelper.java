package inc.itnity.elbilad.utils;

import android.content.SharedPreferences;

import static inc.itnity.elbilad.constants.Constants.PREFS_PUSH_ARTICLE_ID;

public class PreferenceHelper {

    private SharedPreferences sharedPreferences;

    public PreferenceHelper(SharedPreferences sharedPreferences){
        this.sharedPreferences = sharedPreferences;
    }

    public String getArticleId() {
        return sharedPreferences.getString(PREFS_PUSH_ARTICLE_ID, "");
    }

    public void setArticleId(String articleId) {
        sharedPreferences.edit().putString(PREFS_PUSH_ARTICLE_ID, articleId).apply();
    }

    public void clearInfo() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(PREFS_PUSH_ARTICLE_ID);
        editor.apply();
    }
}
