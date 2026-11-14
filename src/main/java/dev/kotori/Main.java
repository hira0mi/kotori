
package dev.kotori;

import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;

public class Main {

    public static void main(String[] args) {

        String token = System.getenv("BOT_TOKEN");

        if (token == null || token.isBlank()) {
            throw new IllegalStateException(
                    "Не задана переменная окружения BOT_TOKEN"
            );
        }

        try (TelegramBotsLongPollingApplication app =
                     new TelegramBotsLongPollingApplication()) {

            app.registerBot(token, new KotoriBot(token));

            System.out.println("Kotori успешно запущена!");

            Thread.currentThread().join();

        } catch (Exception e) {
            System.err.println("Ошибка работы бота:");
            e.printStackTrace();
        }
    }
}
