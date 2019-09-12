package gst.trainingcourse.final_mock;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ITemVideo {
    private String fileName, filePath, fileType;
    private List<ITemVideo> mItemVideos = new ArrayList<>();

    public ITemVideo(String fileName, String filePath, String fileType) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileType = fileType;
    }

    public ITemVideo() {

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

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void parseAllVideo(Context context) {
        try {
            String name = null;
            String[] thumbColumns = {MediaStore.Video.Thumbnails.DATA,
                    MediaStore.Video.Thumbnails.VIDEO_ID};

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
                ITemVideo mediaFileInfo = new ITemVideo();
                video_column_index = videocursor
                        .getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME);
                videocursor.moveToPosition(i);
                name = videocursor.getString(video_column_index);

                mediaFileInfo.setFileName(name);

                int column_index = videocursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                videocursor.moveToPosition(i);
                String filepath = videocursor.getString(column_index);

                mediaFileInfo.setFilePath(filepath);
                mediaFileInfo.setFileType("video");
                mItemVideos.add(mediaFileInfo);
                // id += " Size(KB):" +
                // videocursor.getString(video_column_index);


            }
            videocursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
