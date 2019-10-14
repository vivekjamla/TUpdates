package com.patel.tupdates.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrafficDuration {

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "TrafficDuration{" +
                "text='" + text + '\'' +
                '}';
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @JsonProperty("text")
    private String text;

    @JsonProperty("value")
    private String value;
}
