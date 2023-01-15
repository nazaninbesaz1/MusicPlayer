package com.example.arghavanazizimusicplayer.model;

public class Image
{
    ThumbnailSmall thumbnailSmall;
    Thumbnail thumbnail;
    CoverSmall cover_small;
    Cover cover;

    public ThumbnailSmall getThumbnail_small() {
        return thumbnailSmall;
    }

    public void setThumbnail_small(ThumbnailSmall thumbnailSmall) {
        this.thumbnailSmall = thumbnailSmall;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public CoverSmall getCover_small() {
        return cover_small;
    }

    public void setCover_small(CoverSmall cover_small) {
        this.cover_small = cover_small;
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }
}
