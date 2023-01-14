package com.example.arghavanazizimusicplayer.model;

import java.util.List;

public class DetailSongModel
{
    String id ;
    album album;
    List<artists> artists;
    audio audio;

    boolean copyrighted ,
            localized;

    String downloadCount;
    int duration;
    boolean hasVideo;
    String title;

    image image;
    String releaseDate;
    String lyrics;

    public String getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(String downloadCount) {
        this.downloadCount = downloadCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public com.example.arghavanazizimusicplayer.model.album getAlbum() {
        return album;
    }

    public void setAlbum(com.example.arghavanazizimusicplayer.model.album album) {
        this.album = album;
    }

    public List<com.example.arghavanazizimusicplayer.model.artists> getArtists() {
        return artists;
    }

    public void setArtists(List<com.example.arghavanazizimusicplayer.model.artists> artists) {
        this.artists = artists;
    }

    public com.example.arghavanazizimusicplayer.model.audio getAudio() {
        return audio;
    }

    public void setAudio(com.example.arghavanazizimusicplayer.model.audio audio) {
        this.audio = audio;
    }

    public boolean isCopyrighted() {
        return copyrighted;
    }

    public void setCopyrighted(boolean copyrighted) {
        this.copyrighted = copyrighted;
    }

    public boolean isLocalized() {
        return localized;
    }

    public void setLocalized(boolean localized) {
        this.localized = localized;
    }



    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isHasVideo() {
        return hasVideo;
    }

    public void setHasVideo(boolean hasVideo) {
        this.hasVideo = hasVideo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public com.example.arghavanazizimusicplayer.model.image getImage() {
        return image;
    }

    public void setImage(com.example.arghavanazizimusicplayer.model.image image) {
        this.image = image;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
