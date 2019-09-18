package gst.trainingcourse.final_mock.presenter;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;

import gst.trainingcourse.final_mock.models.ITemVideo;


public class VideoPresenter {
    private VideoUi mVideoUi;

    public VideoPresenter(VideoUi mVideoUi) {
        this.mVideoUi = mVideoUi;
    }

    public void parseAllVideo(Context context) {
        try {
            ArrayList<ITemVideo> itemVideos = new ArrayList<>();
            String name ;
            String[] thumbColumns = {MediaStore.Video.Thumbnails.DATA,
                    MediaStore.Video.Thumbnails.VIDEO_ID};

            int video_column_index;
            String[] project = {MediaStore.Video.Media._ID,
                    MediaStore.Video.Media.DATA,
                    MediaStore.Video.Media.DISPLAY_NAME,
                    MediaStore.Video.Media.SIZE};
            Cursor videoCursor = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    project, null, null, null);
            int count = 0;
            if (videoCursor != null) {
                count = videoCursor.getCount();
            }
            Log.d("No of video", "" + count);
            for (int i = 0; i < count; i++) {
                ITemVideo mediaFileInfo = new ITemVideo();
                video_column_index = videoCursor
                        .getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME);
                videoCursor.moveToPosition(i);
                name = videoCursor.getString(video_column_index);

                mediaFileInfo.setFileName(name);

                int column_index = videoCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                videoCursor.moveToPosition(i);
                String filepath = videoCursor.getString(column_index);

                mediaFileInfo.setFilePath(filepath);
                itemVideos.add(mediaFileInfo);
                mVideoUi.videoData(itemVideos);
                Log.d("video", "parseAllVideo: "+itemVideos.size());

            }
            if (videoCursor != null) {
                videoCursor.close();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface VideoUi {
        void videoData(ArrayList<ITemVideo> itemPhotos);

    }
}
