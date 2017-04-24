package inc.itnity.elbilad.presentation.custom;

import android.content.Context;
import android.util.AttributeSet;
import inc.itnity.elbilad.ElbiladApplication;
import inc.itnity.elbilad.utils.TypefaceManager;
import javax.inject.Inject;

public class ElbiladTextView extends android.support.v7.widget.AppCompatTextView {

  @Inject TypefaceManager mTypefaceManager;

  public ElbiladTextView(Context context) {
    super(context);
    if (!isInEditMode()) init(null);
  }

  public ElbiladTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
    if (!isInEditMode()) init(attrs);
  }

  public ElbiladTextView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    if (!isInEditMode()) init(attrs);
  }

  private void init(AttributeSet attributeSet) {
    ElbiladApplication.getApplicationComponent().inject(this);

    mTypefaceManager.setupFontsByAttributeSet(this, attributeSet);
  }
}
