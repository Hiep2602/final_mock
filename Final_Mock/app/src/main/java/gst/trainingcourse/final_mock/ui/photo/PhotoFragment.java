package gst.trainingcourse.final_mock.ui.photo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import gst.trainingcourse.final_mock.BuildConfig;
import gst.trainingcourse.final_mock.R;
import gst.trainingcourse.final_mock.RecyclerItemClickListener;
import gst.trainingcourse.final_mock.adapter.PhotosAdapter;
import gst.trainingcourse.final_mock.fragmentdialog.FragmentPlayMusic;
import gst.trainingcourse.final_mock.models.ItemMusic;
import gst.trainingcourse.final_mock.models.ItemPhoto;
import gst.trainingcourse.final_mock.utils.Constants;
import gst.trainingcourse.final_mock.utils.OnItemClick;

public class PhotoFragment extends Fragment implements PhotoContract.View {
    private RecyclerView mRvPhoto;
    private PhotosAdapter mAdapter;
    private List<Uri> uris = new ArrayList<>();
    private List<String> itemSelected = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.photo_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        getPhoto();
    }

    private void initView(View view) {
        setHasOptionsMenu(true);
        mRvPhoto = view.findViewById(R.id.rv_photo);
        mRvPhoto.setLayoutManager(new GridLayoutManager(getContext(), 3));

    }

    private void getPhoto() {
        PhotoPresenter mPhoto = new PhotoPresenter(this, getContext());
        mPhoto.getItemPhoto();
    }


    private void openImage(int position) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri data = Uri.parse(mAdapter.getmData().get(position).getPathImage());
        intent.setDataAndType(data, "image/*");
        getContext().startActivity(intent);
    }

    @Override
    public void showPhoto(List<ItemPhoto> itemPhotos) {
        Log.d("photo", "showPhoto: " + itemPhotos.size());
        mAdapter = new PhotosAdapter(getContext());
        mAdapter.setData(itemPhotos);
        mRvPhoto.setAdapter(mAdapter);
        mRvPhoto.addOnItemTouchListener(new RecyclerItemClickListener(getContext()
                , mRvPhoto, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (itemSelected.size() > 0) {
                    multiSelect(position);
                } else {
                    openImage(position);
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
                multiSelect(position);
            }
        }));
    }

    private void multiSelect(int position) {
        ItemPhoto data = mAdapter.getData(position);
        if (data != null) {
            if (itemSelected.contains(data.getPathImage()))
                itemSelected.remove(data.getPathImage());
            else
                itemSelected.add(data.getPathImage());
            mAdapter.setmItemSelect(itemSelected);
        }
    }

//    private OnItemClick mOnclickItem = new OnItemClick() {
//        @Override
//        public void onItemClick(View v, int position) {
//            if (itemSelected.size() > 0) {
//                toggleSelection(position);
//            } else {
//                openImage(position);
//            }
//        }
//
//        @Override
//        public void onITemOnLongClick(View v, int position) {
//            toggleSelection(position);
//        }
//    };

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.senddata:
                if (itemSelected.size() != 0) {
                    for (int i = 0; i < itemSelected.size(); i++) {
                        Uri u = FileProvider.getUriForFile(Objects.requireNonNull(getContext()),
                                BuildConfig.APPLICATION_ID + ".provider", new File(itemSelected.get(i)));
                        uris.add(u);
                    }
                    Constants.shareData(uris, "*/*", getContext());
                } else {
                    Toast.makeText(getContext(), "Please selected item", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
