# Kotori

Kotori is a Telegram bot for learning Japanese, written in Java.

The project is still in its early stages. Currently, the bot only receives messages and sends a predefined response. Learning features haven't been implemented yet.

## Tech stack

- Java 21
- Gradle
- TelegramBots (long polling)
- JUnit 5 (planned for tests)

## Getting started

1. Clone the repository.
2. Open the project in IntelliJ IDEA or another Java IDE.
3. Make sure JDK 21 is installed.
4. Create a Telegram bot using [@BotFather](https://t.me/BotFather).
5. Set the `BOT_TOKEN` environment variable to your bot token.
6. Run `dev.kotori.Main`.

In IntelliJ IDEA, the token can be configured under **Run → Edit Configurations → Environment variables**.

Do not commit your bot token to the repository.

## Current state

The bot runs using long polling and responds to incoming messages with a fixed text.

The next step is to add actual learning functionality, starting with basic commands and Japanese vocabulary practice.