package com.tech.lru;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lw
 * @since 2023/6/1
 */
public class LRUCache {
    
    private Map<String, String> map;

    public LRUCache(int size) {
        this.map = new LinkedHashMap<String, String>(size, 0.75f, true) {
            
            public boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return size()>size;
            }
        };
    }
    
    public String get(String key){
        return map.get(key);
    }
    
    public void put(String key,String val){
        map.put(key,val);
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.put("a","a");
        lruCache.put("b","b");
        lruCache.get("a");
        lruCache.put("c","c");
        lruCache.put("d","d");
        System.out.println(lruCache.map);
    }
}
