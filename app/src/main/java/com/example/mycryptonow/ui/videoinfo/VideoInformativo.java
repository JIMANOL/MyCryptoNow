package com.example.mycryptonow.ui.videoinfo;

import androidx.lifecycle.ViewModelProvider;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.mycryptonow.R;

public class VideoInformativo extends Fragment {

    private VideoInformativoViewModel mViewModel;

    public static VideoInformativo newInstance() {
        return new VideoInformativo();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.video_informativo_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        VideoView videoView = (VideoView) getView().findViewById(R.id.videoview);

        String videoPath = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.videoejemplo;
        Uri videoUri = Uri.parse(videoPath);
        videoView.setVideoURI(videoUri);

        MediaController mediaController = new MediaController(this.getContext());
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);


    }

}