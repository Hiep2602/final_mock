package gst.trainingcourse.final_mock.photo;

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

import gst.trainingcourse.final_mock.BuildConfig;
import gst.trainingcourse.final_mock.R;
import gst.trainingcourse.final_mock.adapter.PhotosAdapter;
import gst.trainingcourse.final_mock.models.ItemPhoto;
import gst.trainingcourse.final_mock.utils.Constants;
import gst.trainingcourse.final_mock.utils.OnItemClick;

public class PhotoFragment extends Fragment implements PhotoContract.View {
    private RecyclerView mRvPhoto;
    private PhotosAdapter mAdapter;
    private List<Uri> uris;

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

    private void toggleSelection(int position) {
        mAdapter.toggleSelection(position);
        int count = mAdapter.getSelectedItemCount();

        if (count == 0) {
        } else {
            uris = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                Uri u = FileProvider.getUriForFile(getContext(),
                        BuildConfig.APPLICATION_ID + ".provider", new File(mAdapter.getmData()
                                .get(position).getPathImage()));
                uris.add(u);
            }
            Log.d("hsize", "toggleSelection: " + uris.size());
        }
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
        mAdapter.setOnItemClick(mOnclickItem);
        mRvPhoto.setAdapter(mAdapter);
    }

    private OnItemClick mOnclickItem = new OnItemClick() {
        @Override
        public void onItemClick(int position) {
            openImage(position);
            Toast.makeText(getContext(), "asfaf" + position, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onITemOnLongClick(View v, int position) {
            Toast.makeText(getContext(), "" + position, Toast.LENGTH_SHORT).show();
            toggleSelection(position);
            Log.d("size", "onITemOnLongClick: " + uris.size());
        }
    };

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.senddata:
                if (uris != null) {
                    Constants.shareData(uris, "*/*", getContext());
                } else {
                    Toast.makeText(getContext(), "Please select item", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
