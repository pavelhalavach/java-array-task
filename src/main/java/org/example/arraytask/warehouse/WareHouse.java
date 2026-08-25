package org.example.arraytask.warehouse;

import java.util.HashMap;
import java.util.Map;

public class WareHouse {
    private static WareHouse instance;
    private Map<Integer, ArrayParameters> data = new HashMap<>();

    private WareHouse() {
    }

    public static WareHouse getInstance() {
        if (instance == null) {
            instance = new WareHouse();
        }
        return instance;
    }

    public ArrayParameters get(Integer key) {
        return data.get(key);
    }

    public ArrayParameters put(Integer key, ArrayParameters value) {
        return data.put(key, value);
    }

    public ArrayParameters remove(Integer key) {
        return data.remove(key);
    }

}
