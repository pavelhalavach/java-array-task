package org.example.arraytask.util;

public final class IdUtil {

    private static int currentId = 0;

    private IdUtil() {
    }

    public static int incrementId() {
        return ++currentId;
    }
}
