package inc.itnity.elbilad.presentation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import inc.itnity.elbilad.R;
import inc.itnity.elbilad.domain.models.article.Video;
import inc.itnity.elbilad.presentation.activities.MainActivity;
import inc.itnity.elbilad.presentation.activities.base.AbstractBaseActivity;
import inc.itnity.elbilad.presentation.fragments.base.AbstractBaseFragment;
import inc.itnity.elbilad.presentation.presenters.VideoDetailsPresenter;
import inc.itnity.elbilad.presentation.views.VideoDetailsView;
import inc.itnity.elbilad.utils.ElbiladUtils;
import inc.itnity.elbilad.utils.ImageLoaderHelper;
import inc.itnity.elbilad.utils.YouTubeHelper;
import javax.inject.Inject;

/**
 * Created by st1ch on 15.02.17.
 */

public class VideoDetailsFragment extends AbstractBaseFragment implements VideoDetailsView {

  @BindView(R.id.youtube_content_video) ImageView youtubeContentVideo;
  @BindView(R.id.iv_share) ImageView ivShare;
  @BindView(R.id.iv_bookmark) ImageView ivBookmark;
  @BindView(R.id.tv_description) TextView tvDescription;

  @Inject VideoDetailsPresenter presenter;

  @Inject ImageLoaderHelper imageLoaderHelper;
  @Inject YouTubeHelper youtubeHelper;
  @Inject ElbiladUtils elbiladUtils;

  private static final String ARG_VIDEO_ID = "video_id_arg";

  public static VideoDetailsFragment newInstance(String videoId) {
    Bundle args = new Bundle();
    args.putString(ARG_VIDEO_ID, videoId);
    VideoDetailsFragment fragment = new VideoDetailsFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public int getContentView() {
    return R.layout.fragment_video_details;
  }

  @Override public void injectComponent() {
    MainActivity.getMainActivityComponent().inject(this);
  }

  @Override protected void bindPresenter() {
    presenter.bind(this);
  }

  @Override protected void unbindPresenter() {
    presenter.onDestroy();
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View rootView = super.onCreateView(inflater, container, savedInstanceState);
    ((AbstractBaseActivity) getActivity()).hideToolbar();

    presenter.onCreate(getArguments().getString(ARG_VIDEO_ID));

    return rootView;
  }

  @Override public void showVideo(Video video) {
    tvDescription.setText(video.getPreview());

    ivShare.setOnClickListener(v -> elbiladUtils.shareArticleLink(video.getLink()));
    ivBookmark.setOnClickListener(v -> presenter.addToBookmarks(video));

    if (!TextUtils.isEmpty(video.getImage())) {
      imageLoaderHelper.loadUrlImageLarge(video.getImage(), youtubeContentVideo);
    }

    youtubeContentVideo.setOnClickListener(v -> youtubeHelper.startPlayer(video.getYoutubeId()));
  }

  @Override public void showAddedToBookmarks() {
    super.showSnackbarMessage(getString(R.string.added_to_bookmarks));
  }
}
