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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Objects;

import gst.trainingcourse.final_mock.MainActivity;
import gst.trainingcourse.final_mock.R;
import gst.trainingcourse.final_mock.adapter.PhotoAdapter;
import gst.trainingcourse.final_mock.models.ItemPhoto;
import gst.trainingcourse.final_mock.presenter.PhotoPresenter;

public class PhotoFragment extends Fragment implements PhotoAdapter.OnClickImage {
    private ArrayList<ItemPhoto> mItemPhotos = new ArrayList<>();

    private PhotoAdapter mPhotoAdapter;

    private RecyclerView mRvPhoto;

    private PhotoPresenter mPhotoPresenter;

    private PhotoPresenter.PhotoUi mPhotoUi = itemPhotos -> {
        if (mItemPhotos != null) {
            mItemPhotos.clear();
            mItemPhotos.addAll(itemPhotos);
            mPhotoAdapter.notifyDataSetChanged();
        }
    };

    public static PhotoFragment newPhotoInstance() {
        return new PhotoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
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
            mPhotoAdapter = new PhotoAdapter(getActivity(), mItemPhotos, this);

        }

        mRvPhoto.setLayoutManager(new GridLayoutManager(getContext(), 3, LinearLayoutManager.VERTICAL, false));
        mRvPhoto.setAdapter(mPhotoAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_photo, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.photo_bluetooth_on:

                break;
            case R.id.photo_bluetooth_off:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClickImage(int position) {
//        SharePK(position);
        openImage(position);
    }

    public void SharePK(int position) {
        try {
            Intent share = new Intent();
            share.setAction(Intent.ACTION_SEND);
            share.setType("*/*");
            share.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(getContext(),
                    BuildConfig.APPLICATION_ID + ".provider", new File(mItemPhotos.get(position).getPathImage())));
            getContext().startActivity(share);
        } catch (Exception e) {
            Log.e("ShareApp", e.getMessage());
        }

    }

    private void openImage(int position) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri data = Uri.parse(mItemPhotos.get(position).getPathImage());
        intent.setDataAndType(data, "image/*");
        getContext().startActivity(intent);
    }
}
