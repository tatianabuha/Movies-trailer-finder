package com.example.myapplication.moviedetail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.google.android.youtube.player.YouTubeInitializationResult;

import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.List;

public class YoutubeThumbnailAdapter extends RecyclerView.Adapter<YoutubeThumbnailAdapter.ViewHolder> {
    public interface OnThumbnailClickListener {
        void onItemClick(String videoUrl);
    }

    private List<String> videoUrlList;
    private OnThumbnailClickListener listener;
    private static final String API_KEY = "AIzaSyBjltoG7SvAYBGGq_u-oSg4VQuC-zuMUYw";

    public YoutubeThumbnailAdapter(List<String> videoUrlList, OnThumbnailClickListener listener) {
        this.videoUrlList = videoUrlList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public YoutubeThumbnailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.youtube_thumbnail_view, viewGroup, false);
        return new YoutubeThumbnailAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull YoutubeThumbnailAdapter.ViewHolder viewHolder, int position) {
        viewHolder.bind(videoUrlList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return videoUrlList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public YouTubeThumbnailView videoView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.video_view);
        }

        public void bind(String videoUrl, OnThumbnailClickListener listener) {

            videoView.initialize(API_KEY,
                    new YouTubeThumbnailView.OnInitializedListener() {
                        @Override
                        public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {
                            youTubeThumbnailLoader.setVideo(videoUrl);
                            youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                                @Override
                                public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                                    youTubeThumbnailLoader.release();
                                }

                                @Override
                                public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {

                                }
                            });
                        }

                        @Override
                        public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

                        }
                    });

            itemView.setOnClickListener(v -> listener.onItemClick(videoUrl));
        }
    }
}
