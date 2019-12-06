package com.akan.qf.view.img;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akan.qf.Constants;
import com.akan.qf.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by taoh on 2017/11/16.
 */

public class ImageFragment extends Fragment {

    private static final String IMAGE_URL = "image";
    PhotoView image;
    private String imageUrl;

    public ImageFragment() {
        // Required empty public constructor
    }

    private String type;

    public static ImageFragment newInstance(String param1, String type) {
        ImageFragment fragment = new ImageFragment();
        fragment.type = type;
        Bundle args = new Bundle();
        args.putString(IMAGE_URL, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imageUrl = getArguments().getString(IMAGE_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        image = (PhotoView) view.findViewById(R.id.image);
        image.setOnPhotoTapListener(new PhotoTapListener());
        if ("0".equals(type)) {
            Glide.with(getContext()).load(Constants.BASE_URL + imageUrl)
                    .error(R.drawable.error_img).crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.NONE).into(image);
        } else {
            Glide.with(getContext()).load("http://qifei.akan.com.cn:8088/AKMaster/" + imageUrl)
                    .error(R.drawable.error_img).crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.NONE).into(image);
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    private class PhotoTapListener implements PhotoViewAttacher.OnPhotoTapListener {
        @Override
        public void onPhotoTap(View view, float v, float v1) {
            getActivity().finish();
        }

        @Override
        public void onOutsidePhotoTap() {
            getActivity().finish();
        }
    }
}