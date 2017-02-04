package inc.itnity.elbilad.presentation.custom;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by alexeyverbitskiy on 1/24/17.
 */

public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {
  private final int verticalSpaceHeight;

  public VerticalSpaceItemDecoration() {
    this.verticalSpaceHeight = 16;
  }

  @Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
      RecyclerView.State state) {
    if(parent.getChildAdapterPosition(view) == 0)
      outRect.top = verticalSpaceHeight;

    if(parent.getChildAdapterPosition(view) == parent.getChildCount() - 1)
      outRect.bottom = verticalSpaceHeight;

    outRect.bottom = verticalSpaceHeight;
  }
}
