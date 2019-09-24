package gst.trainingcourse.final_mock.ui.video;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import gst.trainingcourse.final_mock.models.ITemVideo;

public class GetInfoVideo {
    private List<ITemVideo> mItemVideos;

    public List<ITemVideo> getmItemVideos(Context context) {
        try {
            mItemVideos = new ArrayList<>();
            String name, pathVideo;

            int video_column_index;
            String[] proj = {MediaStore.Video.Media._ID,
                    MediaStore.Video.Media.DATA,
                    MediaStore.Video.Media.DISPLAY_NAME,
                    MediaStore.Video.Media.SIZE};
            Cursor videocursor = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    proj, null, null, null);
            int count = videocursor.getCount();
            Log.d("No of video", "" + count);
            for (int i = 0; i < count; i++) {
                ITemVideo iTemVideo = new ITemVideo();
                video_column_index = videocursor
                        .getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME);
                videocursor.moveToPosition(i);
                pathVideo = videocursor.getString(videocursor.getColumnIndexOrThrow(MediaStore.Video.Thumbnails.DATA));
                name = videocursor.getString(video_column_index);
                iTemVideo.setFileName(name);

                int column_index = videocursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                videocursor.moveToPosition(i);
                String filepath = videocursor.getString(column_index);
                iTemVideo.setFilePath(filepath);
                iTemVideo.setFileVideo(pathVideo);

                mItemVideos.add(iTemVideo);

            }
            videocursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mItemVideos;
    }

}
