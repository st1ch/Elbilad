package inc.itnity.elbilad.presentation.adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.domain.models.article.Article;
import inc.itnity.elbilad.domain.models.article.ArticleItem;
import inc.itnity.elbilad.utils.ElbiladUtils;
import inc.itnity.elbilad.utils.FragmentNavigator;
import inc.itnity.elbilad.utils.ImageLoaderHelper;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by st1ch on 04.02.17.
 */

public class LastNewsAdapter extends RecyclerView.Adapter<LastNewsAdapter.SimpleNewsViewHolder> {

  //private static final int TYPE_TOP = 0;
  private static final int TYPE_SIMPLE = 1;
  private static final int TYPE_BANNER_100 = 2;
  private static final int TYPE_BANNER_50 = 3;

  private List<Article> articles = new ArrayList<>();

  private ImageLoaderHelper imageLoaderHelper;
  private ElbiladUtils elbiladUtils;
  private FragmentNavigator fragmentNavigator;

  @Inject LastNewsAdapter(ImageLoaderHelper imageLoaderHelper, ElbiladUtils elbiladUtils,
      FragmentNavigator fragmentNavigator) {
    this.imageLoaderHelper = imageLoaderHelper;
    this.elbiladUtils = elbiladUtils;
    this.fragmentNavigator = fragmentNavigator;
  }

  @Override public int getItemViewType(int position) {
    //if (position == 0) {
    //  return TYPE_TOP;
    //}
    if (getItem(position).getType() == ArticleItem.TYPE.BANNER_100) {
      return TYPE_BANNER_100;
    } else if (getItem(position).getType() == ArticleItem.TYPE.BANNER_50) {
      return TYPE_BANNER_50;
    }
    return TYPE_SIMPLE;
  }

  @Override public SimpleNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    //if (viewType == TYPE_TOP) {
    //  return new TopNewsViewHolder(LayoutInflater.from(parent.getContext())
    //      .inflate(R.layout.item_category_news_top, parent, false));
    //}

    if (viewType == TYPE_BANNER_100) {
      return new BannerViewHolder(LayoutInflater.from(parent.getContext())
          .inflate(R.layout.item_banner_4588, parent, false));
    }

    if (viewType == TYPE_BANNER_50) {
      return new BannerViewHolder(LayoutInflater.from(parent.getContext())
          .inflate(R.layout.item_banner_6582, parent, false));
    }

    return new SimpleNewsViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_category_news, parent, false));
  }

  @Override public void onBindViewHolder(SimpleNewsViewHolder holder, int position) {
    int viewType = getItemViewType(position);

    if (viewType != TYPE_BANNER_100 && viewType != TYPE_BANNER_50) {
      Article article = getItem(position);

      //if (viewType == TYPE_TOP) {
      //  ((TopNewsViewHolder) holder).tvCategory.setText(article.getCategoryTitle());
      //
      //  if (!TextUtils.isEmpty(article.getImage())) {
      //    imageLoaderHelper.loadUrlImageLarge(article.getImage(), holder.ivAvatar);
      //  }
      //} else {
      if (!TextUtils.isEmpty(article.getImage())) {
        imageLoaderHelper.loadFlashImageThumb(article.getImage(), holder.ivAvatar);
      }
      //}

      holder.tvDate.setText(elbiladUtils.getArticleTimeDate(article.getTime(), article.getDate()));
      holder.tvPreview.setText(article.getTitle());

      holder.itemView.setOnClickListener(
          v -> fragmentNavigator.startArticleDetailsFragment(true, article.getId()));
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

    if (articles.size() > 8) {
      for (int i = 0; i < 3; i++) {
        this.articles.add(articles.get(i));
      }
      this.articles.add(new Article(ArticleItem.TYPE.BANNER_100));
      for (int i = 3; i < 8; i++) {
        this.articles.add(articles.get(i));
      }
      this.articles.add(new Article(ArticleItem.TYPE.BANNER_50));
      for (int i = 8; i < articles.size(); i++) {
        this.articles.add(articles.get(i));
      }
    } else if (articles.size() > 3) {
      for (int i = 0; i < 3; i++) {
        this.articles.add(articles.get(i));
      }
      this.articles.add(new Article(ArticleItem.TYPE.BANNER_100));
      for (int i = 3; i < articles.size(); i++) {
        this.articles.add(articles.get(i));
      }
    } else {
      this.articles.addAll(articles);
    }

    notifyDataSetChanged();
  }

  class SimpleNewsViewHolder extends RecyclerView.ViewHolder {

    @Nullable @BindView(R.id.iv_image) ImageView ivAvatar;
    @Nullable @BindView(R.id.tv_preview) TextView tvPreview;
    @Nullable @BindView(R.id.tv_date) TextView tvDate;

    SimpleNewsViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  //class TopNewsViewHolder extends SimpleNewsViewHolder {
  //
  //  @BindView(R.id.tv_category) TextView tvCategory;
  //
  //  TopNewsViewHolder(View itemView) {
  //    super(itemView);
  //  }
  //}

  class BannerViewHolder extends SimpleNewsViewHolder {

    @BindView(R.id.adView) AdView adView;

    BannerViewHolder(View itemView) {
      super(itemView);
      AdRequest adRequest = new AdRequest.Builder().build();
      adView.loadAd(adRequest);
    }
  }
}
