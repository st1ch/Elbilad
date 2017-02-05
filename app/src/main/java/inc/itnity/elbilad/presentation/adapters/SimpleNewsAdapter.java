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
import inc.itnity.elbilad.constants.ApiConfig;
import inc.itnity.elbilad.domain.models.article.Article;
import inc.itnity.elbilad.utils.FragmentNavigator;
import inc.itnity.elbilad.utils.ImageLoaderHelper;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by st1ch on 04.02.17.
 */

public class SimpleNewsAdapter
    extends RecyclerView.Adapter<SimpleNewsAdapter.SimpleNewsViewHolder> {

  private List<Article> articles = new ArrayList<>();

  private ImageLoaderHelper imageLoaderHelper;
  private FragmentNavigator fragmentNavigator;

  @Inject SimpleNewsAdapter(ImageLoaderHelper imageLoaderHelper,
      FragmentNavigator fragmentNavigator) {
    this.imageLoaderHelper = imageLoaderHelper;
    this.fragmentNavigator = fragmentNavigator;
  }

  @Override public SimpleNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new SimpleNewsViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_category_news, parent, false));
  }

  @Override public void onBindViewHolder(SimpleNewsViewHolder holder, int position) {
    Article article = getItem(position);

    if (!TextUtils.isEmpty(article.getImage())) {
      imageLoaderHelper.loadUrlImage(ApiConfig.IMAGE_BASE_URL + ApiConfig.THUMB +
          article.getImage(), holder.ivAvatar);
    }

    holder.tvDate.setText(article.getDate());
    holder.tvPreview.setText(article.getPreview());

    holder.itemView.setOnClickListener(
        v -> fragmentNavigator.startArticleDetailsFragment(article.getId()));
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

  class SimpleNewsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_image) ImageView ivAvatar;
    @BindView(R.id.tv_preview) TextView tvPreview;
    @BindView(R.id.tv_date) TextView tvDate;

    SimpleNewsViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
