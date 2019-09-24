package gst.trainingcourse.final_mock.fragmentdialog;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import gst.trainingcourse.final_mock.R;

@SuppressLint("ValidFragment")
public class FragmentPlayMusic extends DialogFragment {

    private String nameSong;
    private String mFilePathMusic;
    private TextView mTvNameSong, mmTvPos, mTvDur;
    private SeekBar mSeekbar;
    private Handler mHandler = new Handler();
    private MediaPlayer mediaPlayer;

    public FragmentPlayMusic(String nameSong, String mFilePathMusic) {
        this.nameSong = nameSong;
        this.mFilePathMusic = mFilePathMusic;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);
        getDialog().setTitle("Play Music");

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTvNameSong = view.findViewById(R.id.tvNameSongD);
        mSeekbar = view.findViewById(R.id.seekbartimeline);
        mmTvPos = view.findViewById(R.id.pos);
        mTvDur = view.findViewById(R.id.dur);
        if (nameSong != null) {
            mTvNameSong.setText("               " + nameSong + "              ");
            cmdSetDataSource(Uri.fromFile(new File(mFilePathMusic)));
            schedulService();
            cmdStart();

        }
    }

    private void updateTimeSong() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int startTime = mediaPlayer.getCurrentPosition();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                mmTvPos.setText(simpleDateFormat.format(startTime));
                mSeekbar.setProgress(startTime);
                handler.postDelayed(this, 500);
            }
        }, 100);
    }

    private void mediaPlayerMonitor() {
        if (mediaPlayer.isPlaying()) {

            int mediaDuration = mediaPlayer.getDuration();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
            mTvDur.setText(simpleDateFormat.format(mediaDuration));
            mSeekbar.setMax(mediaDuration);
            setTimeMusic();
            updateTimeSong();
        }
    }

    private void setTimeMusic() {
        mSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(mSeekbar.getProgress());
            }
        });
    }

    private void cmdSetDataSource(Uri uri) {
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(getContext(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void schedulService() {
        ScheduledExecutorService myScheduledExecutorService
                = Executors.newScheduledThreadPool(1);

        myScheduledExecutorService.scheduleWithFixedDelay(
                new Runnable() {
                    @Override
                    public void run() {
                        monitorHandler.sendMessage(monitorHandler.obtainMessage());
                    }
                },
                200, //initialDelay
                200, //delay
                TimeUnit.MILLISECONDS);

    }

    Handler monitorHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            mediaPlayerMonitor();
        }

    };

    private void cmdStart() {
        try {
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            Toast.makeText(getContext(),
                    e.toString(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
//        mediaPlayer.release();
        super.onDestroy();
    }
}
