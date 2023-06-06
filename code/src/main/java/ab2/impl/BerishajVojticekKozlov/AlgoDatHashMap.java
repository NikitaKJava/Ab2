package ab2.impl.BerishajVojticekKozlov;

class Entry<K, V> {
    private final K key;
    private V value;
    private Entry<K, V> next;

    Entry(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Entry<K, V> getNext() {
        return next;
    }

    public void setNext(Entry<K, V> next) {
        this.next = next;
    }
}

public class AlgoDatHashMap<K, V> implements ab2.AlgoDatHashMap<K, V> {
    int SIZE; // stored values
    int CAPACITY; // capacity of the Hashmap
    Entry<K, V>[] entries;

    /**
     * Constructor for HashMap with entries array
     *
     * @param capacity size of an array
     */
    @SuppressWarnings("unchecked")
    public AlgoDatHashMap(int capacity) {
        CAPACITY = capacity;
        entries = (Entry<K, V>[]) new Entry[CAPACITY];
        SIZE = 0;
    }

    protected int hash(K key) {
        return key.hashCode() % CAPACITY;
    }

    @SuppressWarnings("unchecked")
    void resize() {
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
        entries = null;
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
     * vorhanden, ist null zurückzugeben.
     *
     * @param key der zu suchende Schlüssel
     * @return Wert, der mit dem Schlüssel assoziiert ist. Ist der Schlüssel
     * nicht vorhanden, wird null zurückgegeben.
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
    @SuppressWarnings("unchecked")
    @Override
    public K[] keys() {
        K[] keysArray = (K[]) new Object[SIZE];
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
     * bereits bestehendes Element mit demselben Schlüssel (equals-Methode!) wird
     * dabei überschrieben und zurückgegeben.
     *
     * @param key   der einzufügende Schlüssel
     * @param value der einzufügende Wert
     * @return null, wenn unter dem Schlüssel noch kein Wert eingefügt war.
     * Liefert das bisher gespeicherte Element, wenn mit dem Key schon
     * ein Wert vorhanden war.
     * @throws IllegalStateException, wenn kein freier Platz mehr in der Hashtabelle vorhanden ist
     */
    @Override
    public V put(K key, V value) throws IllegalStateException {
        if (isFull()) {
            throw new IllegalStateException("Kein freier Platz mehr in der Hashtabelle vorhanden");
        }
        int index = hash(key);

        if (entries[index] == null) { // if hash index is null (no kollision), insert new Node
            entries[index] = new Entry<>(key, value);
            SIZE++;
            return null;
        } else { // if not, create new entry
            Entry<K, V> current = entries[index];
            while (current != null) { // if Node not null
                if (current.getKey().equals(key) && current.getValue().equals(value)) { // if element exists with same key, it will be overridden
                    current.setValue(value);
                    return null;
                }
                current = current.getNext(); // get next Node and check keys and values
            }
            // if next Node is null, create and put new key and value
            Entry<K, V> newEntry = new Entry<>(key, value);
            newEntry.setNext(entries[index]);
            entries[index] = newEntry;
        }
        SIZE++;
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
    @SuppressWarnings("unchecked")
    @Override
    public V[] values() {
        V[] valuesArray = (V[]) new Object[SIZE];
        int index = 0;

        for (Entry<K, V> entry : entries) {
            Entry<K, V> current = entry;
            while (current != null) {
                if (current.getValue() != null) {
                    valuesArray[index] = current.getValue();
                }
                current = current.getNext();
                index++;
            }
        }
        return valuesArray;
    }

    protected static boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
    // p = 4 * n + 3 (OR p % 4 = 3)
    protected static boolean isPrimeQuadratic(int num) {
        if (num < 2) {
            return false;
        }
        if (num % 2 == 0 || num % 3 == 0) {
            return false;
        }
        for (int i = 2; i < num; i ++) {
            if (num % 4 == 3) {
                return true;
            }
        }
        return false;
    }
}