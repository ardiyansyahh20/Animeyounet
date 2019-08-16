package com.balar.animeyounet.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Series implements Parcelable {
    private String id,judul_series, gambar_series, tanggal_series, title_series, judul_lain_series, japanese_series,
            type_series, status_series, episode_series, aired_series, studios_series, genre_series, durasi_series,
            score_series, sinopsis_series;

    public Series(String id, String judul_series, String gambar_series, String tanggal_series, String title_series, String judul_lain_series,
                  String japanese_series, String type_series, String status_series, String episode_series,String  aired_series,
                  String studios_series, String genre_series, String durasi_series, String score_series, String sinopsis_series ){
        this.id=id;
        this.judul_series=judul_series;
        this.gambar_series=gambar_series;
        this.tanggal_series=tanggal_series;
        this.title_series=title_series;
        this.judul_lain_series=judul_lain_series;
        this.japanese_series=japanese_series;
        this.type_series=type_series;
        this.status_series=status_series;
        this.episode_series=episode_series;
        this.aired_series=aired_series;
        this.studios_series=studios_series;
        this.genre_series=genre_series;
        this.durasi_series=durasi_series;
        this.score_series=score_series;
        this.sinopsis_series=sinopsis_series;
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

    public String getTanggal_series() {
        return tanggal_series;
    }

    public void setTanggal_series(String tanggal_series) {
        this.tanggal_series = tanggal_series;
    }

    public String getTitle_series() {
        return title_series;
    }

    public void setTitle_series(String title_series) {
        this.title_series = title_series;
    }

    public String getJudul_lain_series() {
        return judul_lain_series;
    }

    public void setJudul_lain_series(String judul_lain_series) {
        this.judul_lain_series = judul_lain_series;
    }

    public String getJapanese_series() {
        return japanese_series;
    }

    public void setJapanese_series(String japanese_series) {
        this.japanese_series = japanese_series;
    }

    public String getType_series() {
        return type_series;
    }

    public void setType_series(String type_series) {
        this.type_series = type_series;
    }

    public String getStatus_series() {
        return status_series;
    }

    public void setStatus_series(String status_series) {
        this.status_series = status_series;
    }

    public String getEpisode_series() {
        return episode_series;
    }

    public void setEpisode_series(String episode_series) {
        this.episode_series = episode_series;
    }

    public String getAired_series() {
        return aired_series;
    }

    public void setAired_series(String aired_series) {
        this.aired_series = aired_series;
    }

    public String getStudios_series() {
        return studios_series;
    }

    public void setStudios_series(String studios_series) {
        this.studios_series = studios_series;
    }

    public String getGenre_series() {
        return genre_series;
    }

    public void setGenre_series(String genre_series) {
        this.genre_series = genre_series;
    }

    public String getDurasi_series() {
        return durasi_series;
    }

    public void setDurasi_series(String durasi_series) {
        this.durasi_series = durasi_series;
    }

    public String getScore_series() {
        return score_series;
    }

    public void setScore_series(String score_series) {
        this.score_series = score_series;
    }

    public String getSinopsis_series() {
        return sinopsis_series;
    }

    public void setSinopsis_series(String sinopsis_series) {
        this.sinopsis_series = sinopsis_series;
    }

    public static Creator<Series> getCREATOR() {
        return CREATOR;
    }

    protected Series(Parcel in) {
        this.id=in.readString();
        this.judul_series=in.readString();
        this.gambar_series=in.readString();
        this.tanggal_series=in.readString();
        this.title_series=in.readString();
        this.judul_lain_series=in.readString();
        this.japanese_series=in.readString();
        this.type_series=in.readString();
        this.status_series=in.readString();
        this.episode_series=in.readString();
        this.aired_series=in.readString();
        this.studios_series=in.readString();
        this.genre_series=in.readString();
        this.durasi_series=in.readString();
        this.score_series=in.readString();
        this.sinopsis_series=in.readString();
    }

    public static final Creator<Series> CREATOR = new Creator<Series>() {
        @Override
        public Series createFromParcel(Parcel in) {
            return new Series(in);
        }

        @Override
        public Series[] newArray(int size) {
            return new Series[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.judul_series);
        parcel.writeString(this.gambar_series);
        parcel.writeString(this.tanggal_series);
        parcel.writeString(this.title_series);
        parcel.writeString(this.judul_lain_series);
        parcel.writeString(this.japanese_series);
        parcel.writeString(this.type_series);
        parcel.writeString(this.status_series);
        parcel.writeString(this.episode_series);
        parcel.writeString(this.aired_series);
        parcel.writeString(this.studios_series);
        parcel.writeString(this.genre_series);
        parcel.writeString(this.durasi_series);
        parcel.writeString(this.score_series);
        parcel.writeString(this.sinopsis_series);
    }
}
