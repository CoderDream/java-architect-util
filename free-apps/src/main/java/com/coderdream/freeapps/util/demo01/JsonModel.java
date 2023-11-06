package com.coderdream.freeapps.util.demo01;

/*
 * author:合肥工业大学 管院学院 钱洋
 *1563178220@qq.com
 */
public class JsonModel {

    private String VideoID;
    private String MovieID;
    private String ShortTitle;
    private String prmovieId;
    private String url;
    public String getVideoID() {
        return VideoID;
    }
    public String getShortTitle() {
        return ShortTitle;
    }
    public void setShortTitle(String shortTitle) {
        ShortTitle = shortTitle;
    }
    public String getPrmovieId() {
        return prmovieId;
    }
    public void setPrmovieId(String prmovieId) {
        this.prmovieId = prmovieId;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public void setVideoID(String videoID) {
        VideoID = videoID;
    }
    public String getMovieID() {
        return MovieID;
    }
    public void setMovieID(String movieID) {
        MovieID = movieID;
    }


}
