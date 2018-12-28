package com.example.zhm.weather;

public class TodayEntity {
    private String city;
    private String date_y;
    private String week;
    private String temperature;
    private String weather;
    private String fa;
    private String fb;
    private String wind;
    private String dressing_index;
    private String dressing_advide;
    private String uv_index;
    private String comfort_index;
    private String wash_index;
    private String travel_index;
    private String exercise_index;
    private String drying_index;

    public TodayEntity(String city, String date_y, String week, String temperature, String weather, String fa, String fb, String wind, String dressing_index, String dressing_advide, String uv_index, String comfort_index, String wash_index, String travel_index, String exercise_index, String drying_index) {
        this.city = city;
        this.date_y = date_y;
        this.week = week;
        this.temperature = temperature;
        this.weather = weather;
        this.fa = fa;
        this.fb = fb;
        this.wind = wind;
        this.dressing_index = dressing_index;
        this.dressing_advide = dressing_advide;
        this.uv_index = uv_index;
        this.comfort_index = comfort_index;
        this.wash_index = wash_index;
        this.travel_index = travel_index;
        this.exercise_index = exercise_index;
        this.drying_index = drying_index;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate_y() {
        return date_y;
    }

    public void setDate_y(String date_y) {
        this.date_y = date_y;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getFa() {
        return fa;
    }

    public void setFa(String fa) {
        this.fa = fa;
    }

    public String getFb() {
        return fb;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getDressing_index() {
        return dressing_index;
    }

    public void setDressing_index(String dressing_index) {
        this.dressing_index = dressing_index;
    }

    public String getDressing_advide() {
        return dressing_advide;
    }

    public void setDressing_advide(String dressing_advide) {
        this.dressing_advide = dressing_advide;
    }

    public String getUv_index() {
        return uv_index;
    }

    public void setUv_index(String uv_index) {
        this.uv_index = uv_index;
    }

    public String getComfort_index() {
        return comfort_index;
    }

    public void setComfort_index(String comfort_index) {
        this.comfort_index = comfort_index;
    }

    public String getWash_index() {
        return wash_index;
    }

    public void setWash_index(String wash_index) {
        this.wash_index = wash_index;
    }

    public String getTravel_index() {
        return travel_index;
    }

    public void setTravel_index(String travel_index) {
        this.travel_index = travel_index;
    }

    public String getExercise_index() {
        return exercise_index;
    }

    public void setExercise_index(String exercise_index) {
        this.exercise_index = exercise_index;
    }

    public String getDrying_index() {
        return drying_index;
    }

    public void setDrying_index(String drying_index) {
        this.drying_index = drying_index;
    }
}
