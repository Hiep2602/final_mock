package gst.trainingcourse.final_mock.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Objects;

import gst.trainingcourse.final_mock.MainActivity;
import gst.trainingcourse.final_mock.R;
import gst.trainingcourse.final_mock.adapter.MusicAdapter;
import gst.trainingcourse.final_mock.adapter.PhotoAdapter;
import gst.trainingcourse.final_mock.models.ItemPhoto;
import gst.trainingcourse.final_mock.presenter.MusicPresenter;
import gst.trainingcourse.final_mock.presenter.PhotoPresenter;

public class PhotoFragment extends Fragment {
    private ArrayList<ItemPhoto> mItemPhotos = new ArrayList<>();

    private PhotoAdapter mPhotoAdapter;

    private RecyclerView mRvPhoto;

    private PhotoPresenter mPhotoPresenter;

    private PhotoPresenter.PhotoUi mPhotoUi = new PhotoPresenter.PhotoUi() {
        @Override
        public void photoData(ArrayList<ItemPhoto> itemPhotos) {
            if (mItemPhotos != null) {
                mItemPhotos.clear();
                mItemPhotos.addAll(itemPhotos);
            }
        }
    };

    public static PhotoFragment newPhotoInstance() {
        return new PhotoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return Objects.requireNonNull(inflater).inflate(R.layout.photo_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRvPhoto = view.findViewById(R.id.rv_photo);
        MainActivity m = (MainActivity) getActivity();
        if (Objects.requireNonNull(m).checkPermision(getContext())) {
            mPhotoPresenter = new PhotoPresenter(mPhotoUi);
            mPhotoPresenter.parseAllImages(getActivity());
            mPhotoAdapter = new PhotoAdapter(getActivity(), mItemPhotos);

        }

        mRvPhoto.setLayoutManager(new GridLayoutManager(getContext(), 3, LinearLayoutManager.VERTICAL, false));
        mRvPhoto.setAdapter(mPhotoAdapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }
}
