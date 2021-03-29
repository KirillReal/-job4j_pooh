package ru.job4j.pooh;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueService implements Service{
    private final Map<String, ConcurrentLinkedQueue<String>> queueMap = new ConcurrentHashMap<>();

    private boolean put(String name, String text) {
        queueMap.putIfAbsent(name,new ConcurrentLinkedQueue<>());
        return queueMap.get(name).offer(text);
    }

    private String take(String name) {
        return queueMap.get(name).poll();
    }
    @Override
    public Resp process(Req req) {
        System.out.println("QueueService process");
        int status = 200;
        String text;
        switch (req.method()) {
            case "GET" -> {
                text = take(req.getQueue());
                if (text == null) {
                    status = 404;
                    text = "\n\n queue is empty";
                } else {
                    text = "\n\n" + text;
                }
            }
            case "POST" -> {
                if (!put(req.getQueue(), req.getMessage()))
                    status = 404;
                text = req.text();
            }
            default -> {
                status = 404;
                text = "null";
            }
        }

        return new Resp(text, status);
    }
}
