
package dev.kotori;

import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

public class KotoriBot implements LongPollingSingleThreadUpdateConsumer {

    private final TelegramClient client;

    public KotoriBot(String token) {
        this.client = new OkHttpTelegramClient(token);
    }

    @Override
    public void consume(Update update) {

        if (!update.hasMessage()) {
            return;
        }

        long chatId = update.getMessage().getChatId();

        SendMessage response = SendMessage.builder()
                .chatId(chatId)
                .text("Привет! Я Kotori. Пока я умею только отвечать на сообщения.")
                .build();

        try {
            client.execute(response);
        } catch (TelegramApiException e) {
            System.err.println("Ошибка отправки сообщения:");
            e.printStackTrace();
        }
    }
}
