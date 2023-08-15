package com.backend.backend.model;

public enum TimeRange {
    
    YEAR(31536000),
    MONTH(2628288),
    WEEK(604800),
    DAY(86400);

    private final int seconds;

    TimeRange(int seconds) {
        this.seconds = seconds;
    }

    public int getSeconds() {
        return seconds;
    }
}
