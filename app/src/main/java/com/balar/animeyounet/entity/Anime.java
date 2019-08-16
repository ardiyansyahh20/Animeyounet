package com.balar.animeyounet.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Anime implements Parcelable {
    private String id, judul, gambar, tanggal, video, video1, video2, judul_series, gambar_series, url;

    public Anime(String id, String judul, String gambar, String tanggal, String video, String video1, String video2,
                 String judul_series, String gambar_series, String url){
        this.id=id;
        this.judul = judul;
        this.gambar = gambar;
        this.tanggal = tanggal;
        this.video = video;
        this.video1 = video1;
        this.video2 = video2;
        this.judul_series=judul_series;
        this.gambar_series=gambar_series;
        this.url=url;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul_series() {
        return judul_series;
    }

    public void setJudul_series(String judul_series) {
        this.judul_series = judul_series;
    }

    public String getGambar_series() {
        return gambar_series;
    }

    public void setGambar_series(String gambar_series) {
        this.gambar_series = gambar_series;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getVideo1() {
        return video1;
    }

    public void setVideo1(String video1) {
        this.video1 = video1;
    }

    public String getVideo2() {
        return video2;
    }

    public void setVideo2(String video2) {
        this.video2 = video2;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.judul);
        dest.writeString(this.gambar);
        dest.writeString(this.tanggal);
        dest.writeString(this.video);
        dest.writeString(this.video1);
        dest.writeString(this.video2);
        dest.writeString(this.judul_series);
        dest.writeString(this.gambar_series);
        dest.writeString(this.url);
    }

    protected Anime(Parcel in) {
        this.id = in.readString();
        this.judul = in.readString();
        this.gambar = in.readString();
        this.tanggal = in.readString();
        this.video = in.readString();
        this.video1 = in.readString();
        this.video2 = in.readString();
        this.judul_series = in.readString();
        this.gambar_series = in.readString();
        this.url = in.readString();
    }

    public static final Creator<Anime> CREATOR = new Creator<Anime>() {
        @Override
        public Anime createFromParcel(Parcel source) {
            return new Anime(source);
        }

        @Override
        public Anime[] newArray(int size) {
            return new Anime[size];
        }
    };
}
