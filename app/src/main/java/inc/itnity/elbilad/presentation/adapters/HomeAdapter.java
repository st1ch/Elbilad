package inc.itnity.elbilad.presentation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.domain.models.article.Article;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by st1ch on 19.01.17.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeItemViewHolder> {

  private static final int TYPE_TOP = 0;
  private static final int TYPE_REGULAR = 1;

  private List<Article> articles = new ArrayList<>();

  @Inject HomeAdapter() {
  }

  @Override public int getItemViewType(int position) {
    if (position == 0) {
      return TYPE_TOP;
    } else {
      return TYPE_REGULAR;
    }
  }

  @Override public HomeItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    switch (viewType) {
      case TYPE_TOP:
        return new TopNewsItemViewHolder(getView(parent, R.layout.item_home_news_top));
      case TYPE_REGULAR:
        return new RegularNewsItemViewHolder(getView(parent, R.layout.item_home_news));
      default:
        return null;
    }
  }

  private View getView(ViewGroup parent, int layoutId) {
    return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
  }

  @Override public void onBindViewHolder(HomeItemViewHolder holder, int position) {
    int viewType = getItemViewType(position);

    Article article = getItem(position);

    if (viewType == TYPE_TOP) {
      ((TopNewsItemViewHolder) holder).ivImage.setImageResource(R.drawable.sample_image);
      ((TopNewsItemViewHolder) holder).tvTitle.setText(article.getTitle());
      ((TopNewsItemViewHolder) holder).tvDate.setText(article.getDate());
      ((TopNewsItemViewHolder) holder).tvAuthor.setText(article.getAuthor());
    } else if (viewType == TYPE_REGULAR) {
      ((RegularNewsItemViewHolder) holder).ivImage.setImageResource(R.drawable.sample_image);
      ((RegularNewsItemViewHolder) holder).tvPreview.setText(article.getPreview());
      ((RegularNewsItemViewHolder) holder).tvDate.setText(article.getDate());
      ((RegularNewsItemViewHolder) holder).tvAuthor.setText(article.getAuthor());
    }
  }

  @Override public int getItemCount() {
    return articles.size();
  }

  private Article getItem(int position) {
    return articles.get(position);
  }

  public void setArticles(List<Article> articles) {
    this.articles.clear();
    this.articles.addAll(articles);
    notifyDataSetChanged();
  }

  class HomeItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_image) ImageView ivImage;
    @BindView(R.id.tv_date) TextView tvDate;
    @BindView(R.id.tv_category) TextView tvAuthor;

    HomeItemViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  class TopNewsItemViewHolder extends HomeItemViewHolder {

    @BindView(R.id.tv_title) TextView tvTitle;

    TopNewsItemViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  class RegularNewsItemViewHolder extends HomeItemViewHolder {

    @BindView(R.id.tv_preview) TextView tvPreview;

    RegularNewsItemViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
