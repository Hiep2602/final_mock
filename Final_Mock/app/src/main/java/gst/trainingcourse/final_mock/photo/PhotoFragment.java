package gst.trainingcourse.final_mock.photo;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import gst.trainingcourse.final_mock.BuildConfig;
import gst.trainingcourse.final_mock.R;
import gst.trainingcourse.final_mock.adapter.PhotosAdapter;
import gst.trainingcourse.final_mock.fragment.BaseFragment;
import gst.trainingcourse.final_mock.models.ItemPhoto;
import gst.trainingcourse.final_mock.utils.OnItemClick;

public class PhotoFragment extends BaseFragment implements PhotoContract.View {
    private RecyclerView mRvPhoto;
    private PhotosAdapter mAdapter;
    private List<Uri> uris;
    private FloatingActionButton fab;
    private Button btnButton;

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
        mRvPhoto = view.findViewById(R.id.rv_photo);
        fab = getActivity().findViewById(R.id.fab);
        mRvPhoto.setLayoutManager(new GridLayoutManager(getContext(), 3));
        fab.setBackgroundResource(R.drawable.ic_launcher_background);
        fab.setOnClickListener(clickFab);
        btnButton = view.findViewById(R.id.btn_data);
        btnButton.setOnClickListener(clickFab);

    }

    private void getPhoto() {
        PhotoPresenter mPhoto = new PhotoPresenter(this, getContext());
        mPhoto.getItemPhoto();
    }

    private void toggleSelection(int position) {
        mAdapter.toggleSelection(position);
        int count = mAdapter.getSelectedItemCount();

        uris = new ArrayList<>();
        if (count == 0) {
        } else {
            for (int i = 0; i < count; i++) {
                Uri u = FileProvider.getUriForFile(getContext(),
                        BuildConfig.APPLICATION_ID + ".provider", new File(mAdapter.getmData()
                                .get(position).getPathImage()));
                uris.add(u);
            }
            Log.d("hsize", "toggleSelection: " + uris.size());
        }
    }

    protected View.OnClickListener clickFab = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            if (uris == null) {
////                Log.d("dfasdf", "onClick: " + uris.size());
////                Snackbar.make(v, "No Item Selects", Toast.LENGTH_SHORT).show();
//            } else {
//                shareData(uris, "*/*");
//            }
            Toast.makeText(getContext(), "" + getFragmentManager().getFragments().getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
        }
    };


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
            Toast.makeText(getContext(), "asfaf" + position, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onITemOnLongClick(int position) {
            Toast.makeText(getContext(), "" + position, Toast.LENGTH_SHORT).show();
            toggleSelection(position);
            Log.d("size", "onITemOnLongClick: " + uris.size());
        }
    };

}
