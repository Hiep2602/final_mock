package gst.trainingcourse.final_mock.models;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ITemVideo {
    private String fileName, filePath, fileVideo;
    private String duration;

    public ITemVideo(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public ITemVideo() {

    }

    public String getFileVideo() {
        return fileVideo;
    }

    public void setFileVideo(String fileVideo) {
        this.fileVideo = fileVideo;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<ITemVideo> parseAllVideo(Context context) {
        List<ITemVideo> mItemVideos = null;
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
                pathVideo = videocursor.getString(videocursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
                name = videocursor.getString(video_column_index);
                iTemVideo.setFileName(name);

                int column_index = videocursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                videocursor.moveToPosition(i);
                String filepath = videocursor.getString(column_index);
                iTemVideo.setFilePath(filepath);
                iTemVideo.setFileVideo(pathVideo);
                iTemVideo.setDuration(duration);

                mItemVideos.add(iTemVideo);

                // videocursor.getString(video_column_index);


            }
            videocursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mItemVideos;
    }
}
