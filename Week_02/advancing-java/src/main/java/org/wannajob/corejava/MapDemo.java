package org.wannajob.corejava;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapDemo {

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put(null, null);

        Set<Map.Entry<String, Object>> entries = map.entrySet();
        for (Map.Entry entry: entries) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
//        for (Map.Entry : map.entrySet() ) {
//
//        }
    }
}
