package test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.HashSet;

public class LRU implements CacheReplacementPolicy {
    private Queue<String> queue = new LinkedList<>();
    private HashSet<String> set = new HashSet<>();
    private int capacity;

    public LRU() {
        this.capacity = 5;
    }

    @Override
    public void add(String word) {
        if (set.contains(word)) {
            queue.removeIf(w -> w.equals(word));
        } else {
            if (queue.size() == capacity) {
                String removedWord = queue.remove();
                set.remove(removedWord);
            }
            set.add(word);
        }
        queue.add(word);
    }

    @Override
    public String remove() {
        String word = queue.remove();
        set.remove(word);
        return word;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LRU lru = (LRU) obj;
        return capacity == lru.capacity && queue.equals(lru.queue) && set.equals(lru.set);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}