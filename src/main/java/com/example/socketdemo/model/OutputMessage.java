package com.example.socketdemo.model;

import lombok.Data;

import java.util.Date;

@Data
public class OutputMessage {
    String from;
    String text;
    String time;

    public OutputMessage(String sender, String text, String data) {
        this.from = sender;
        this.text = text;
        this.time = data;
    }
}
