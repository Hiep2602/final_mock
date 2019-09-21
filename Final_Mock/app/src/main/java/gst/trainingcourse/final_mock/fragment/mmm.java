package gst.trainingcourse.final_mock.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
import gst.trainingcourse.final_mock.MainActivity;
import gst.trainingcourse.final_mock.R;
import gst.trainingcourse.final_mock.models.ItemPhoto;
import gst.trainingcourse.final_mock.utils.OnItemClick;

public class mmm extends Fragment {
    private ArrayList<ItemPhoto> mItemPhotos = new ArrayList<>();
    private PhotoAdapter mPhotoAdapter;
    private List<Uri> file;

    private RecyclerView mRvPhoto;
    private gst.trainingcourse.final_mock.presenter.mmm mMmm;
    private FloatingActionButton fab;

    private gst.trainingcourse.final_mock.presenter.mmm.PhotoUi mPhotoUi = itemPhotos -> {
        if (mItemPhotos != null) {
            mItemPhotos.clear();
            mItemPhotos.addAll(itemPhotos);
            mPhotoAdapter.notifyDataSetChanged();
        }
    };


    private OnItemClick mOnItemClick = new OnItemClick() {
        @Override
        public void onItemClick(int position) {
            openImage(position);
        }

        @Override
        public void onITemOnLongClick(int position) {
            toggleSelection(position);
        }
    };

    public static mmm newPhotoInstance() {
        return new mmm();
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
        fab = getActivity().findViewById(R.id.fab);
        MainActivity m = (MainActivity) getActivity();
        if (Objects.requireNonNull(m).checkPermision(getContext())) {
            mMmm = new gst.trainingcourse.final_mock.presenter.mmm();
            mMmm.parseAllImages(getActivity(), mPhotoUi);
            mPhotoAdapter = new PhotoAdapter(getActivity(), mItemPhotos, mOnItemClick);
        }
        fab.setOnClickListener(clickFab);

        mRvPhoto.setLayoutManager(new GridLayoutManager(getContext(), 3, LinearLayoutManager.VERTICAL, false));
        mRvPhoto.setAdapter(mPhotoAdapter);
    }

    private View.OnClickListener clickFab = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (file == null) {
                Snackbar.make(v, "No Item Selects", Toast.LENGTH_SHORT).show();
            } else {
                SharePK(file);
            }
        }
    };

    private void toggleSelection(int position) {
        mPhotoAdapter.toggleSelection(position);
        int count = mPhotoAdapter.getSelectedItemCount();

        if (count == 0) {

        } else {
            file = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                Uri u = FileProvider.getUriForFile(getContext(),
                        BuildConfig.APPLICATION_ID + ".provider", new File(mItemPhotos.get(position).getPathImage()));
                file.add(u);
            }
            Log.d("hsize", "toggleSelection: " + file.size());
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_photo, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.photo_bluetooth_on:
                break;
            case R.id.photo_bluetooth_off:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void SharePK(List<Uri> files) {

        try {
            Intent share = new Intent();
            share.setAction(Intent.ACTION_SEND_MULTIPLE);
            share.setType("*/*");
            share.putParcelableArrayListExtra(Intent.EXTRA_STREAM, (ArrayList<? extends Parcelable>) files);
            share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

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
