package com.example;

import org.joda.time.LocalTime;

public class Greeting {

    private final long id;
    private final String content;
    private final LocalTime builtTime;
    private final Quote quote;

    public Greeting(long id, String content, Quote quote) {
        this.id = id;
        this.content = content;
        this.builtTime = new LocalTime();
        this.quote = quote;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalTime getBuildTime() {
        return builtTime;
    }

    public Quote getQuote() {
        return quote;
    }

    @Override
    public String toString() {
        return "<h1>" + content + "</h1><h2>" + builtTime + "</h2><p>" + quote.getValue().getQuote() + "</p>";
    }
}

