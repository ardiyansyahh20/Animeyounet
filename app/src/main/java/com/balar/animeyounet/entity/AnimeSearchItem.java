package com.balar.animeyounet.entity;

import java.io.Serializable;

public class AnimeSearchItem implements Serializable {
    private String judul, gambar, tanggal, genre;

    public AnimeSearchItem (String judul, String gambar, String tanggal, String genre){
        this.judul = judul;
        this.gambar = gambar;
        this.tanggal = tanggal;
        this.genre = genre;
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
}
