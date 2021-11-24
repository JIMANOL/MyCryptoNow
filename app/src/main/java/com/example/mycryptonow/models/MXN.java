package com.example.mycryptonow.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MXN implements Serializable
{

    private final static long serialVersionUID = -4300650181662130498L;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("volume_24h")
    @Expose
    private Double volume24h;
    @SerializedName("volume_change_24h")
    @Expose
    private Double volumeChange24h;
    @SerializedName("percent_change_1h")
    @Expose
    private Double percentChange1h;
    @SerializedName("percent_change_24h")
    @Expose
    private Double percentChange24h;
    @SerializedName("percent_change_7d")
    @Expose
    private Double percentChange7d;
    @SerializedName("percent_change_30d")
    @Expose
    private Double percentChange30d;
    @SerializedName("percent_change_60d")
    @Expose
    private Double percentChange60d;
    @SerializedName("percent_change_90d")
    @Expose
    private Double percentChange90d;
    @SerializedName("market_cap")
    @Expose
    private Double marketCapitalizacion;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getVolume24h() {
        return volume24h;
    }

    public void setVolume24h(Double volume24h) {
        this.volume24h = volume24h;
    }

    public Double getVolumeChange24h() {
        return volumeChange24h;
    }

    public void setVolumeChange24h(Double volumeChange24h) {
        this.volumeChange24h = volumeChange24h;
    }

    public Double getPercentChange1h() {
        return percentChange1h;
    }

    public void setPercentChange1h(Double percentChange1h) {
        this.percentChange1h = percentChange1h;
    }

    public Double getPercentChange24h() {
        return percentChange24h;
    }

    public void setPercentChange24h(Double percentChange24h) {
        this.percentChange24h = percentChange24h;
    }

    public Double getPercentChange7d() {
        return percentChange7d;
    }

    public void setPercentChange7d(Double percentChange7d) {
        this.percentChange7d = percentChange7d;
    }

    public Double getPercentChange30d() {
        return percentChange30d;
    }

    public void setPercentChange30d(Double percentChange30d) {
        this.percentChange30d = percentChange30d;
    }

    public Double getPercentChange60d() {
        return percentChange60d;
    }

    public void setPercentChange60d(Double percentChange60d) {
        this.percentChange60d = percentChange60d;
    }

    public Double getPercentChange90d() {
        return percentChange90d;
    }

    public void setPercentChange90d(Double percentChange90d) {
        this.percentChange90d = percentChange90d;
    }

    public Double getMarketCapitalizacion() {
        return marketCapitalizacion;
    }

    public void setMarketCapitalizacion(Double marketCapitalizacion) {
        this.marketCapitalizacion = marketCapitalizacion;
    }
}
