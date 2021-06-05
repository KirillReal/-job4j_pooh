# Pooh JMS

[![Build Status](https://travis-ci.org/KirillReal/job4j_pooh.svg?branch=main)](https://travis-ci.org/KirillReal/job4j_pooh)

# Техническое задание

* Нужно сделать аналог асинхронной очереди RabbitMQ.
* Приложение запускает Socket и ждет клиентов.
* Клиенты могут быть двух типов: отправители (publisher), получатели (subscriber).
* В качестве клиента используется cURL.
* В качестве протокола будем использовать HTTP. Сообщения в формате JSON.
* Существуют два режима: queue, topic.
* В коде не должно быть синхронизации. Все нужно сделать на Executors и conccurent коллекциях.

<h2>Технологии</h2>
<ul>
    <li>Java Concurrency (классы из пакета java.util.concurrent)</li>
    <li>Sockets</li>
    <li>Java IO</li>
    <li>Библиотека GSON</li>
</ul>

<h2>Queue</h2>
<p>
    Отправитель посылает сообщение с указанием очереди.<br>
    Получатель читает первое сообщение и удаляет его из очереди. <br>
    Если приходят несколько получателей, то они читают из одной очереди. <br>
    Уникальное сообщение может быть прочитано, только одним получателем.
</p>
<h4>Отправить данные в очередь</h4>
<p>
  Producer отправляет на сервер запрос вида:
    
    <pre><code>{
    "action": "POST",
    "mode": "queue",
    "key": "weather",
    "text": "temperature +18 C"
}</code></pre>
    в очередь weather будет добавлено сообщение "temperature +18 C"<br>
<h4>Получить данные из очереди</h4>
