
package dev.kotori;

import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

public class KotoriBot implements LongPollingSingleThreadUpdateConsumer {

    private final TelegramClient client;
    private final DialogEngine dialog;

    public KotoriBot(String token) {
        client = new OkHttpTelegramClient(token);

        dialog = new DialogEngine(
                new QuizService(
                        new InMemoryQuestionRepository()
                )
        );
    }

    @Override
    public void consume(Update update) {

        if (!update.hasMessage()
                || !update.getMessage().hasText()) {
            return;
        }

        long chatId = update.getMessage().getChatId();

        String answer = dialog.handle(
                update.getMessage().getText()
        );

        SendMessage response = SendMessage.builder()
                .chatId(chatId)
                .text(answer)
                .build();

        try {
            client.execute(response);
        } catch (TelegramApiException e) {
            System.err.println("Ошибка отправки сообщения:");
            e.printStackTrace();
        }
    }
}
