package gst.trainingcourse.final_mock.music;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
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

import gst.trainingcourse.final_mock.BuildConfig;
import gst.trainingcourse.final_mock.R;
import gst.trainingcourse.final_mock.adapter.MusicsAdapter;
import gst.trainingcourse.final_mock.fragment.FragmentPlayMusic;
import gst.trainingcourse.final_mock.models.ItemMusic;
import gst.trainingcourse.final_mock.utils.Constants;
import gst.trainingcourse.final_mock.utils.OnItemClick;

public class MusicFragment extends Fragment implements MusicContract.View {
    private RecyclerView rvMusic;
    private MusicsAdapter mMusicAdapter;
    private List<Uri> uris;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.music_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        getInfoMusic();
    }

    private void initView(View view) {
        setHasOptionsMenu(true);
        rvMusic = view.findViewById(R.id.rv_music);
        rvMusic.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void getInfoMusic() {
        MusicPresenter musicPresenter = new MusicPresenter(getContext(), this);
        musicPresenter.getDataMusic();
    }

    private OnItemClick mOnclickItem = new OnItemClick() {
        @Override
        public void onItemClick(int position) {
            Toast.makeText(getContext(), "asfaf" + position, Toast.LENGTH_SHORT).show();
            String nameSong = mMusicAdapter.getmData().get(position).getNameSong();
            String filePath = mMusicAdapter.getmData().get(position).getPathMusic();
            FragmentPlayMusic m = new FragmentPlayMusic(nameSong, filePath);
            m.show(getFragmentManager(), "Title");
        }

        @Override
        public void onITemOnLongClick(View v, int position) {
            Toast.makeText(getContext(), "" + position, Toast.LENGTH_SHORT).show();
            toggleSelection(position);
        }
    };

    @Override
    public void showInfoMusic(List<ItemMusic> itemMusics) {
        mMusicAdapter = new MusicsAdapter(mOnclickItem);
        mMusicAdapter.setData(itemMusics);
        rvMusic.setAdapter(mMusicAdapter);
    }

    private void toggleSelection(int position) {
        mMusicAdapter.toggleSelection(position);
        int count = mMusicAdapter.getSelectedItemCount();

        if (count == 0) {

        } else {
            uris = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                Uri u = FileProvider.getUriForFile(getContext(),
                        BuildConfig.APPLICATION_ID + ".provider", new File(mMusicAdapter.getmData()
                                .get(position).getPathMusic()));
                uris.add(u);
            }
            Log.d("hsize", "toggleSelection: " + uris.size());
        }
    }

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
