package com.balar.animeyounet;

import java.io.Serializable;

public class AnimeItem implements Serializable {
    private String judul, gambar, tanggal, genre, video, video1, video2, judul_series, gambar_series, url, halaman;

    public AnimeItem(String judul, String gambar, String tanggal, String genre, String video, String  video1,
                     String video2, String judul_series, String gambar_series, String url, String halaman){
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
}
