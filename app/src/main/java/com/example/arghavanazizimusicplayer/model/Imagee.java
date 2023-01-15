package com.example.arghavanazizimusicplayer.model;

import java.util.List;

public class Imagee
{
    List<Slider> slider;
    List<ThumbnailSmall> thumbnailSmall;
    List<Thumbnail> thumbnail;
    List<CoverSmall> cover_small;
    List<Cover> cover;

    public List<Slider> getSlider() {
        return slider;
    }

    public void setSlider(List<Slider> slider) {
        this.slider = slider;
    }

    public List<ThumbnailSmall> getThumbnail_small() {
        return thumbnailSmall;
    }

    public void setThumbnail_small(List<ThumbnailSmall> thumbnailSmall) {
        this.thumbnailSmall = thumbnailSmall;
    }

    public List<Thumbnail> getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(List<Thumbnail> thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<CoverSmall> getCover_small() {
        return cover_small;
    }

    public void setCover_small(List<CoverSmall> cover_small) {
        this.cover_small = cover_small;
    }

    public List<Cover> getCover() {
        return cover;
    }

    public void setCover(List<Cover> cover) {
        this.cover = cover;
    }
}
