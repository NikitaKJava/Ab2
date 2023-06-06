package ab2.impl.BerishajVojticekKozlov;

import static java.lang.Math.floorMod;

/**
 * Liefert eine HashMap-Implementierung mit doppeltem Hashen. Die
 * Hashtabelle soll mindestens minSize Elemente aufnehmen können. Doppeltes
 * Hashen soll hier implementiert werden, indem für Input n als zweite
 * Hash-Funktion (n MOD p') + 1 verwendet wird, wobei p' die nächstkleinere
 * Primzahl bezogen auf die Größe der Hashtabelle ist.
 */
public class Double<K, V> extends AlgoDatHashMap<K, V> {

    private final int nextSmallerPrime;

    public Double(int capacity) {
        super(capacity);
        nextSmallerPrime = nextLowerPrime(capacity);
    }

    /**
     * Changing capacity to prime value
     * @param minSize actual capacity
     * @return prime capacity
     */
    protected static int getValidCapacity(int minSize) {
        int capacity = minSize;
        while (!isPrime(capacity)) {
            capacity++;
        }
        return capacity;
    }

    private int nextLowerPrime(int num) {
        if (num == 2) { // lowest prime number
            return num;
        }
        int tempSize = num - 1;
        while (!isPrime(tempSize)) {
            tempSize--;
        }
        return tempSize;
    }

    /**
     * Second Hash function
     * @param key actual key
     * @return second hashed index within Hashmap size
     */
    private int doubledHash(K key) {
        // p' = next smaller prime number
        // h'(k) = 1 + (k * mod(p'))
        // floorMod -> index inside hashmap size
        return floorMod((1 + (hash(key))), nextSmallerPrime);
    }

    /**
     *
     * @param key actual key
     * @param step from (1...n)
     * @return hashed index
     */
    private int hashedProbing(K key, int step) {
        // h' = doubled Hash
        // k = hash(key)
        // j = step (1...n)
        // s(k,J) = j * h'(k)
        // floorMod -> index inside hashmap size
        return step * doubledHash(key);
    }

    /**
     * @param key   der einzufügende Schlüssel
     * @param value der einzufügende Wert
     * @return null, if input was correct
     * @throws IllegalStateException, when Hashmap is full
     */
    @Override
    public V put(K key, V value) throws IllegalStateException {

        if (isFull()) {
            throw new IllegalStateException("Kein freier Platz mehr in der Hashtabelle vorhanden");
        }
        int index = hash(key);
        int step = 0;

        if (entries[index] == null) { // if hash index is null (no kollision), insert new Node
            entries[index] = new Entry<>(key, value);
            SIZE++;
            return null;
        } else { // if kollision, find next free Node
            while (entries[index] != null) { // if Node not null
                if (entries[index].getKey().equals(key) && entries[index].getValue().equals(value)) { // if element exists with same key, it will be overridden
                    entries[index].setValue(value);
                    return null;
                }
                step++;
                index = hashedProbing(key, step);
            }
            // if next Node is null, create and put new key and value
            Entry<K, V> newEntry = new Entry<>(key, value);
            newEntry.setNext(entries[index]);
            entries[index] = newEntry;
        }
        SIZE++;
        return null;
    }
}