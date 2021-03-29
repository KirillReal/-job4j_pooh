package ru.job4j.pooh;

import java.util.Arrays;
import java.util.List;

public class Req {
    private final String message;
    private final String method;
    private final String mode;
    private final String text;
    private final String queue;
    private final int idConsumer;

    Req(String text) {
        List<String> reqLines = splitReq(text, "\n");
        String param = reqLines.get(0);

        List<String> paramList = splitReq(splitReq(param, " ").get(1), "/");

        this.mode = paramList.get(1);
        this.method = splitReq(param, " ").get(0);
        this.queue = paramList.get(2);
        this.message =  reqLines.get(reqLines.size() - 1);
        this.text = text;

        if (paramList.size() >= 4) {
            idConsumer = Integer.parseInt(paramList.get(3));
        } else {
            idConsumer = -1;
        }
    }

    public static Req of(String content) {
        /* TODO parse a content */
        return new Req(content);
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

    public String getQueue() {
        return queue;
    }

    public int getIdConsumer() {
        return idConsumer;
    }

    public String getMessage() {
        return message;
    }

    private List<String> splitReq(String str, String regex){
        return Arrays.asList(str.split(regex));
    }
}
