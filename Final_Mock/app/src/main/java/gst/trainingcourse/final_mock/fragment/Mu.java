package gst.trainingcourse.final_mock.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

import gst.trainingcourse.final_mock.BuildConfig;
import gst.trainingcourse.final_mock.MainActivity;
import gst.trainingcourse.final_mock.R;
import gst.trainingcourse.final_mock.models.ItemMusic;
import gst.trainingcourse.final_mock.presenter.MusicPresenter;
import gst.trainingcourse.final_mock.utils.OnItemClick;

public class Mu extends Fragment {
    private ArrayList<ItemMusic> mItemMusics;

    private MusicAdapter mMusicAdapter;

    private MusicPresenter.MusicUi musicUi = new MusicPresenter.MusicUi() {
        @Override
        public void musicData(ArrayList<ItemMusic> musics) {
            mItemMusics = new ArrayList<>();
            mItemMusics.addAll(musics);

        }
    };

    public static Mu newMusicInstance() {
        return new Mu();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return Objects.requireNonNull(inflater).inflate(R.layout.music_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.rv_music);
        MainActivity m = (MainActivity) getActivity();
        if (Objects.requireNonNull(m).checkPermision(getContext())) {
        }
        MusicPresenter mMusicPresenter = new MusicPresenter(musicUi);
        mMusicPresenter.parseAllAudio(Objects.requireNonNull(getContext()));
        mMusicAdapter = new MusicAdapter(getContext(), mItemMusics, onItemClick);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mMusicAdapter);
    }

    private OnItemClick onItemClick = new OnItemClick() {
        @Override
        public void onItemClick(int position) {
            Toast.makeText(getContext(), "position", Toast.LENGTH_SHORT).show();
//            shareMusic(position);
//            openMusic(position);
            String nameSong = mItemMusics.get(position).getNameSong();
            String filePath = mItemMusics.get(position).getPathMusic();
            FragmentPlayMusic m = new FragmentPlayMusic(nameSong, filePath);
            m.show(getFragmentManager(), "Title");
        }

        @Override
        public void onITemOnLongClick( int position) {

        }
    };

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchMusic = (SearchView) menuItem.getActionView();
        searchMusic.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchMusic.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mMusicAdapter.getFilter().filter(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void openMusic(int position) {
        Intent intent = new Intent();
        intent.setAction(android.content.Intent.ACTION_GET_CONTENT);
        intent.setDataAndType(Uri.parse(mItemMusics.get(position).getPathMusic()), "audio/mp3");
        getContext().startActivity(intent);
    }

    public void shareMusic(int position) {

        try {
            Intent share = new Intent();
            share.setAction(Intent.ACTION_SEND);
            share.setType("*/*");
//            share.putParcelableArrayListExtra(Intent.EXTRA_STREAM, (ArrayList<? extends Parcelable>) files);
            share.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(getContext(),
                    BuildConfig.APPLICATION_ID + ".provider", new File(mItemMusics.get(position).getPathMusic())));
            getContext().startActivity(share);
        } catch (Exception e) {
            Log.e("ShareApp", e.getMessage());
        }

    }


}
