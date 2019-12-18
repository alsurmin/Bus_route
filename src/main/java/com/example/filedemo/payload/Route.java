package com.example.filedemo.payload;

public class Route {
    String to;
    String from;

    public Route(String to, String from) {
        this.to = to;
        this.from = from;
    }

    public Route() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
