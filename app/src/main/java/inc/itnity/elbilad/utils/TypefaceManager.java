package inc.itnity.elbilad.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.constants.Typefaces;
import java.util.Hashtable;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton public class TypefaceManager {

  private static final Hashtable<String, Typeface> fontsCache = new Hashtable<>();

  private Context context;

  @Inject public TypefaceManager(Context context) {
    this.context = context;
  }

  public Typeface getFont(String name) {
    synchronized (fontsCache) {
      if (!fontsCache.containsKey(name)) {
        String path = "fonts/" + name;
        try {
          Typeface t = Typeface.createFromAsset(context.getAssets(), path + ".ttf");
          fontsCache.put(name, t);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      return fontsCache.get(name);
    }
  }

  public void setupFontDroidSansArabic(TextView textView) {
    if (textView != null) {
      textView.setTypeface(getFont(Typefaces.Names.Droid_Sans_Arabic));
    }
  }

  public void setupFontHacenSamra(TextView textView) {
    if (textView != null) {
      textView.setTypeface(getFont(Typefaces.Names.Hacen_Samra));
    }
  }

  public void setupFontHacenSamraLt(TextView textView) {
    if (textView != null) {
      textView.setTypeface(getFont(Typefaces.Names.Hacen_Samra_Lt));
    }
  }

  public void setupFontHacenTunisia(TextView textView) {
    if (textView != null) {
      textView.setTypeface(getFont(Typefaces.Names.Hacen_Tunisia));
    }
  }

  public void setupFontOpenSansBold(TextView textView) {
    if (textView != null) {
      textView.setTypeface(getFont(Typefaces.Names.Open_Sans_Bold));
    }
  }

  public void setupFontOpenSansSemibold(TextView textView) {
    if (textView != null) {
      textView.setTypeface(getFont(Typefaces.Names.Open_Sans_Semibold));
    }
  }

  public void setupFontRobotoRegular(TextView textView) {
    if (textView != null) {
      textView.setTypeface(getFont(Typefaces.Names.Roboto_Regular));
    }
  }

  public void setupFontsByAttributeSet(TextView textView, AttributeSet attrs) {
    if (attrs != null) {
      TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ElbiladTextView);

      int typeFace = a.getInt(R.styleable.ElbiladTextView_text_typeface, -1);
      try {
        switch (typeFace) {
          case Typefaces.Droid_Sans_Arabic:
            setupFontDroidSansArabic(textView);
            break;
          case Typefaces.Hacen_Samra:
            setupFontHacenSamra(textView);
            break;
          case Typefaces.Hacen_Samra_Lt:
            setupFontHacenSamraLt(textView);
            break;
          case Typefaces.Hacen_Tunisia:
            setupFontHacenTunisia(textView);
            break;
          case Typefaces.Open_Sans_Bold:
            setupFontOpenSansBold(textView);
            break;
          case Typefaces.Open_Sans_Semibold:
            setupFontOpenSansSemibold(textView);
            break;
          case Typefaces.Roboto_Regular:
            setupFontRobotoRegular(textView);
            break;
          default:
            break;
        }
      } finally {
        a.recycle();
      }
    }
  }
}
