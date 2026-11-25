package dev.kotori;

import java.util.Objects;

public final class UserSession {
    private final DialogEngine dialog;

    public UserSession(DialogEngine dialog) {
        this.dialog = Objects.requireNonNull(dialog);
    }

    public synchronized String handle(String message) {
        return dialog.handle(message);
    }
}