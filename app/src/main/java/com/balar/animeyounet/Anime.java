package com.balar.animeyounet;

import android.os.Parcel;
import android.os.Parcelable;

public class Anime implements Parcelable {
    private String judul, gambar, tanggal, genre, video, video1, video2, judul_series, gambar_series, url, halaman;

    public Anime(String judul, String gambar, String tanggal, String genre, String video, String video1, String video2,
                 String judul_series, String gambar_series, String url, String halaman){
        this.judul = judul;
        this.gambar = gambar;
        this.tanggal = tanggal;
        this.genre = genre;
        this.video = video;
        this.video1 = video1;
        this.video2 = video2;
        this.judul_series = judul_series;
        this.gambar_series = gambar_series;
        this.url = url;
        this.halaman = halaman;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHalaman() {
        return halaman;
    }

    public void setHalaman(String halaman) {
        this.halaman = halaman;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.judul);
        dest.writeString(this.gambar);
        dest.writeString(this.tanggal);
        dest.writeString(this.genre);
        dest.writeString(this.video);
        dest.writeString(this.video1);
        dest.writeString(this.video2);
        dest.writeString(this.judul_series);
        dest.writeString(this.gambar_series);
        dest.writeString(this.url);
        dest.writeString(this.halaman);
    }

    protected Anime(Parcel in) {
        this.judul = in.readString();
        this.gambar = in.readString();
        this.tanggal = in.readString();
        this.genre = in.readString();
        this.video = in.readString();
        this.video1 = in.readString();
        this.video2 = in.readString();
        this.judul_series = in.readString();
        this.gambar_series = in.readString();
        this.url = in.readString();
        this.halaman = in.readString();
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
