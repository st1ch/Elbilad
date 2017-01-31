package inc.itnity.elbilad.presentation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by st1ch on 30.01.17.
 */

public class PhotoSlideAdapter extends RecyclerView.Adapter<PhotoSlideAdapter.PhotoSlideItemViewHolder> {

  @Override public PhotoSlideItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return null;
  }

  @Override public void onBindViewHolder(PhotoSlideItemViewHolder holder, int position) {

  }

  @Override public int getItemCount() {
    return 0;
  }

  class PhotoSlideItemViewHolder extends RecyclerView.ViewHolder {

    public PhotoSlideItemViewHolder(View itemView) {
      super(itemView);
    }
  }
}
