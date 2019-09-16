package gst.trainingcourse.final_mock.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Objects;

import gst.trainingcourse.final_mock.MainActivity;
import gst.trainingcourse.final_mock.R;
import gst.trainingcourse.final_mock.adapter.MusicAdapter;
import gst.trainingcourse.final_mock.models.ItemMusic;
import gst.trainingcourse.final_mock.presenter.MusicPresenter;

public class MusicFragment extends Fragment {
    private ArrayList<ItemMusic> mItemMusics;

    private MusicAdapter mMusicAdapter;

    private MusicPresenter.MusicUi musicUi = new MusicPresenter.MusicUi() {
        @Override
        public void musicData(ArrayList<ItemMusic> musics) {
            mItemMusics = new ArrayList<>();
            mItemMusics.addAll(musics);

        }
    };

    public static MusicFragment newMusicInstance() {
        return new MusicFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return Objects.requireNonNull(inflater).inflate(R.layout.music_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.rv_music);
        MainActivity m = (MainActivity) getActivity();
        if (Objects.requireNonNull(m).checkPermision(getContext())) {
            MusicPresenter mMusicPresenter = new MusicPresenter(musicUi);
            mMusicPresenter.parseAllMusic(Objects.requireNonNull(getContext()));
            mMusicAdapter = new MusicAdapter(getActivity());
            mMusicAdapter.setData(mItemMusics);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mMusicAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
    }
}
