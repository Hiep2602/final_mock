package gst.trainingcourse.final_mock.models;






public class ItemMusic {

    private String author;
    private String nameSong;
    private String pathImage;
    private int duration;

    public ItemMusic() {
    }

    public ItemMusic(String author, String nameSong, String pathImage, int duration) {
        this.author = author;
        this.nameSong = nameSong;
        this.pathImage = pathImage;
        this.duration = duration;
    }



    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getDuration() {
        return duration;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }


}
