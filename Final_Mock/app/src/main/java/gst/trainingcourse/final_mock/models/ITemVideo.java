package gst.trainingcourse.final_mock.models;

public class ITemVideo {
    private String fileName, filePath, fileVideo;
    private String duration;

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


    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}
