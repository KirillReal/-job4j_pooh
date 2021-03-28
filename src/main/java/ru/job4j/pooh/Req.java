package ru.job4j.pooh;

public class Req {
    private final String method;
    private final String mode;
    private final String text;

    private Req(String method, String mode, String text) {
        this.method = method;
        this.mode = mode;
        this.text = text;
    }

    public static Req of(String content) {
        /* TODO parse a content */
        return new Req(null, null, null);
    }

    public String method() {
        return method;
    }

    public String mode() {
        return mode;
    }

    public String text() {
        return text;
    }
}
