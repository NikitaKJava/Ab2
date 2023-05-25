package ab2.impl.BerishajVojticekKozlov;

import ab2.AlgoDatHashMap;

import java.util.Arrays;
import java.util.Random;

class Entry<K, V> {
    private final K key;
    private V value;
    private Entry<K, V> next;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

//    Entry(K key, V value, Entry<K, V> next) {
//        this.key = key;
//        this.value = value;
//        this.next = next;
//    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Entry<K,V> getNext() {
        return next;
    }

    public void setNext(Entry<K, V> next) {
        this.next = next;
    }
}

/**
 * Creates new array
 * @param <K>
 * @param <V>
 */
public class HashMap<K,V> implements AlgoDatHashMap<K,V> {
    private static int SIZE;
    private static int CAPACITY;
    private Entry<K, V>[] entries;

    /**
     * Constructor for HashMap with entries array
     *
     * @param capacity size of an array
     */
    public HashMap(int capacity) {
        CAPACITY = capacity;
        this.entries = new Entry[CAPACITY];
        SIZE = 0;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % entries.length);
    }

    private void resize() {
        Entry<K, V>[] oldEntries = entries;
        entries = new Entry[oldEntries.length * 2];
        SIZE = 0;

        for (Entry<K, V> entry : oldEntries) {
            if (entry != null) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * Löscht den Inhalt der Hashtabelle.
     */
    @Override
    public void clear() {

    }

    /**
     * Liefert true, wenn der Schlüssel key in der Hashtabelle vorhanden ist.
     *
     * @param key der zu suchende Schlüssel
     * @return true, wenn der Schlüssel in der Hashtabelle vorhanden ist. Sonst
     * false.
     */
    @Override
    public boolean containsKey(K key) {
        int index = hash(key);

        Entry<K, V> current = entries[index];
        while (current != null) {
            if (current.getKey().equals(key)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Liefert den Wert zu dem angegebenen Schlüssel. Ist der Schlüssel nicht
     * vorhanden, ist null zurück zu geben.6
     *
     * @param key der zu suchende Schlüssel
     * @return Wert, der mit dem Schlüssel assoziiert ist. Ist der Schlüssel
     * nicht vorhanden, wird null zurück gegeben.
     */
    @Override
    public V get(K key) {
        int index = hash(key);

        Entry<K, V> current = entries[index];
        while (current != null) {
            if (current.getKey().equals(key)) {
                return current.getValue();
            }
            current = current.getNext();
        }
        return null;
    }

    /**
     * @return true, wenn die Hashtabelle leer ist (d.h. keine Elemente enthält)
     */
    @Override
    public boolean isEmpty() {
        return SIZE == 0;
    }

    /**
     * @return true, wenn die Hashtabelle voll ist (d.h. es können keine
     * weiteren Elemente mehr einfügt werden)
     */
    @Override
    public boolean isFull() {
        return CAPACITY == SIZE;
    }

    /**
     * Liefert ein Set, welches alle Schlüssel enthält, die in der Hashtabelle
     * gespeichert sind.
     *
     * @return die in der Hashtabelle gespeicherten Schlüssel
     */
    @Override
    public K[] keys() {
        K[] keysArray = (K[]) new Object[CAPACITY];
        int index = 0;

        for (Entry<K, V> entry : entries) {
            Entry<K, V> current = entry;
            while (current != null) {
                keysArray[index] = current.getKey();
                current = current.getNext();
                index++;
            }
        }
        return keysArray;
    }

    /**
     * Fügt einen Wert mit dem angegebenen Schlüssel in die Hashtabelle ein. Ein
     * bereits bestehendes Element mit dem selben Schlüssel (equals-Methode!) wird
     * dabei überschrieben und zurückgegeben.
     *
     * @param key   der einzufügende Schlüssel
     * @param value der einzufülgende Wert
     * @return null, wenn unter dem Schlüssel noch kein Wert eingefügt war.
     * Liefert das bisher gespeicherte Element, wenn mit dem Key schon
     * ein Wert vorhanden war.
     * @throws IllegalStateException Wenn kein freier Platz mehr in der Hashtabelle vorhanden ist
     */
    @Override
    public V put(K key, V value) throws IllegalStateException {
        if (isFull()) {
            throw new IllegalStateException("Kein freier Platz mehr in der Hashtabelle vorhanden");
        }
        int index = hash(key);

        if (entries[index] == null) { // if Node null (empty), insert value
            entries[index] = new Entry<>(key, value);
            SIZE++;
            return null;
        } else { // if not, create new entry
            Entry<K, V> current = entries[index];
            while (current != null) { // if key not null, check for right index
                if (current.getKey().equals(key)) {
                    current.setValue(value);
                    return null;
                }
                current = current.getNext(); // if not the right index, create next Node
            }
            // if next Node is null, create and put new key and value
            Entry<K, V> newEntry = new Entry<>(key, value);
            newEntry.setNext(entries[index]);
            entries[index] = newEntry;
        }
        SIZE++;

        if (SIZE >= entries.length * 0.75) {
            resize();
        }
        return null;
    }

    /**
     * Liefert die Anzahl an Werten, die in der Hashtabelle gespeichert sind.
     *
     * @return Anzahl der Elemente in der Tabelle
     */
    @Override
    public int size() {
        return SIZE;
    }

    /**
     * Liefert die Größe der Hashtabelle zurück
     *
     * @return Größe der Hashtabelle (nicht Anzahl der gespeicherten Elemente)
     */
    @Override
    public int capacity() {
        return CAPACITY;
    }

    /**
     * Liefert eine Sammlung der in der Hashtabelle enthaltenen Werte.
     *
     * @return Sammlung der in der Hashtabelle enthaltenen Werte
     */
    @Override
    public V[] values() {
        V[] valuesArray = (V[]) new Object[SIZE];
        int index = 0;

        for (Entry<K, V> entry : entries) {
            Entry<K, V> current = entry;
            while (current != null) {
                valuesArray[index] = current.getValue();
                current = current.getNext();
                index++;
            }
        }
        return valuesArray;
    }

//    public static <V, K> HashMap<K, V> linear(int minSize) {
//        HashMap<K, V>[] hashMap = new HashMap[minSize];
//        int index = 0;
//
//        for (HashMap<K, V> entry : hashMap) {
//
//            index++;
//
//        }
//
////        return hashMap;
//        return null;
//    }



    public static void main(String[] args) {
        AlgoDatHashMap<Integer, String> hm = new HashMap<>(12);
//        checkFullMap(hm);
//        printing(hm);
//        System.out.println("It's full?: " + hm.isFull());

        Random rand = new Random(System.currentTimeMillis());

//        hm.put(0, "1");
        int keyk = rand.nextInt(hm.capacity());

//        System.out.println(Arrays.toString(hm.values()));
        System.out.println(keyk);
    }

    public static void checkFullMap(AlgoDatHashMap<Integer, String> hashMap) {
        char c = 'A';
        for(int i = 0; i < hashMap.capacity(); i++) {
            hashMap.put(i, i + String.valueOf(c));
            c++;
        }
    }

    public static void printing(AlgoDatHashMap<Integer, String> hashMap) {
        for (int i = 0; i < hashMap.capacity(); i++) {
            System.out.print("KEY: " + i + ", VALUE: " + hashMap.get(i) + ", ");
        }
    }
}
