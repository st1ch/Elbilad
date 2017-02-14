package inc.itnity.elbilad.presentation.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.domain.models.article.Image;
import inc.itnity.elbilad.utils.ElbiladUtils;
import inc.itnity.elbilad.utils.FragmentNavigator;
import inc.itnity.elbilad.utils.ImageLoaderHelper;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by st1ch on 04.02.17.
 */

public class PhotoCategoryNewsAdapter
    extends RecyclerView.Adapter<PhotoCategoryNewsAdapter.SimpleNewsViewHolder> {

  private static final int TYPE_TOP = 0;
  private static final int TYPE_SIMPLE = 1;

  private List<Image> articles = new ArrayList<>();

  private ImageLoaderHelper imageLoaderHelper;
  private ElbiladUtils elbiladUtils;
  private FragmentNavigator fragmentNavigator;

  @Inject PhotoCategoryNewsAdapter(ImageLoaderHelper imageLoaderHelper, ElbiladUtils elbiladUtils,
      FragmentNavigator fragmentNavigator) {
    this.imageLoaderHelper = imageLoaderHelper;
    this.elbiladUtils = elbiladUtils;
    this.fragmentNavigator = fragmentNavigator;
  }

  @Override public int getItemViewType(int position) {
    if (position == 0) {
      return TYPE_TOP;
    }
    return TYPE_SIMPLE;
  }

  @Override public SimpleNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (viewType == TYPE_TOP) {
      return new TopNewsViewHolder(LayoutInflater.from(parent.getContext())
          .inflate(R.layout.item_photo_news_top, parent, false));
    }
    return new SimpleNewsViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo_news, parent, false));
  }

  @Override public void onBindViewHolder(SimpleNewsViewHolder holder, int position) {
    int viewType = getItemViewType(position);

    Image article = getItem(position);

    if (viewType == TYPE_TOP) {
      ((TopNewsViewHolder) holder).tvCategory.setText(article.getCategoryTitle());

      if (!TextUtils.isEmpty(article.getImage())) {
        imageLoaderHelper.loadUrlImageLarge(article.getImage(), holder.ivAvatar);
      }
    } else {
      if (!TextUtils.isEmpty(article.getImage())) {
        imageLoaderHelper.loadUrlImageThumb(article.getImage(), holder.ivAvatar);
      }
    }

    holder.tvDate.setText(elbiladUtils.getArticleTimeDate(article.getTime(), article.getDate()));
    holder.tvPreview.setText(article.getPreview());

    //holder.itemView.setOnClickListener(
    //    v -> fragmentNavigator.startArticleDetailsFragment(article.getId()));
  }

  @Override public int getItemCount() {
    return articles.size();
  }

  private Image getItem(int position) {
    return articles.get(position);
  }

  public void setArticles(List<Image> articles) {
    this.articles.clear();
    this.articles.addAll(articles);
    notifyDataSetChanged();
  }

  class SimpleNewsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_image) ImageView ivAvatar;
    @BindView(R.id.tv_preview) TextView tvPreview;
    @BindView(R.id.tv_date) TextView tvDate;

    SimpleNewsViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  class TopNewsViewHolder extends SimpleNewsViewHolder {

    @BindView(R.id.tv_category) TextView tvCategory;

    TopNewsViewHolder(View itemView) {
      super(itemView);
    }
  }
}
