package com.example.arghavanazizimusicplayer.model;

import java.util.List;

public class album
{
    String name;
    List<artists> artists;
    image image;
    String releaseDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<com.example.arghavanazizimusicplayer.model.artists> getArtists() {
        return artists;
    }

    public void setArtists(List<com.example.arghavanazizimusicplayer.model.artists> artists) {
        this.artists = artists;
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
