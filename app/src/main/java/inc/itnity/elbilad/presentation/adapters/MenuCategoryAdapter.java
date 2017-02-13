package inc.itnity.elbilad.presentation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.domain.models.categorie.Category;
import inc.itnity.elbilad.presentation.custom.listeners.CategoryClickListener;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by st1ch on 24.01.17.
 */

public class MenuCategoryAdapter
    extends RecyclerView.Adapter<MenuCategoryAdapter.CategoryViewHolder> {

  private List<Category> categories = new ArrayList<>();

  private CategoryClickListener categoryClickListener;

  @Inject MenuCategoryAdapter() {
  }

  @Override public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new CategoryViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_menu_category, parent, false));
  }

  @Override public void onBindViewHolder(CategoryViewHolder holder, int position) {
    Category category = getItem(position);

    holder.tvCategoryName.setText(category.getTitle());
    holder.itemView.setOnClickListener(
        v -> categoryClickListener.onCategoryClick(position, category.getId(),
            category.getTitle()));
  }

  @Override public int getItemCount() {
    return categories.size();
  }

  private Category getItem(int position) {
    return categories.get(position);
  }

  public void setCategories(List<Category> categories) {
    this.categories.clear();
    this.categories.addAll(categories);
    notifyDataSetChanged();
  }

  public void setCategoryClickListener(CategoryClickListener categoryClickListener) {
    this.categoryClickListener = categoryClickListener;
  }

  class CategoryViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_category_name) TextView tvCategoryName;

    CategoryViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
