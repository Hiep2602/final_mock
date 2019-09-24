package gst.trainingcourse.final_mock.ui.music;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import gst.trainingcourse.final_mock.adapter.MusicsAdapter;
import gst.trainingcourse.final_mock.fragmentdialog.FragmentPlayMusic;
import gst.trainingcourse.final_mock.models.ItemMusic;
import gst.trainingcourse.final_mock.utils.Constants;

public class MusicFragment extends Fragment implements MusicContract.View {
    private RecyclerView rvMusic;
    private MusicsAdapter mMusicAdapter;
    private List<Uri> uris = new ArrayList<>();
    private List<String> itemSelected = new ArrayList<>();

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

    private void multiSelect(int position) {
        ItemMusic data = mMusicAdapter.getItem(position);
        if (data != null) {
            if (itemSelected.contains(data.getPathMusic()))
                itemSelected.remove(data.getPathMusic());
            else
                itemSelected.add(data.getPathMusic());
            mMusicAdapter.setItemSelected(itemSelected);
        }
    }


    private void getInfoMusic() {
        MusicPresenter musicPresenter = new MusicPresenter(getContext(), this);
        musicPresenter.getDataMusic();
    }

    @Override
    public void showInfoMusic(List<ItemMusic> itemMusics) {
        mMusicAdapter = new MusicsAdapter(getContext());
        mMusicAdapter.setData(itemMusics);
        rvMusic.setAdapter(mMusicAdapter);
        rvMusic.addOnItemTouchListener(new RecyclerItemClickListener(getContext()
                , rvMusic, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (itemSelected.size() > 0) {
                    multiSelect(position);
                } else {
                    String nameSong = mMusicAdapter.getmData().get(position).getNameSong();
                    String filePath = mMusicAdapter.getmData().get(position).getPathMusic();
                    FragmentPlayMusic m = new FragmentPlayMusic(nameSong, filePath);
                    m.show(getFragmentManager(), "Title");
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
                multiSelect(position);
            }
        }));
    }

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
