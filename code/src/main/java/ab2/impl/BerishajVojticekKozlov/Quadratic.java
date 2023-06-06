package ab2.impl.BerishajVojticekKozlov;

import static java.lang.Math.floorMod;

/**
 * Liefert eine HashMap-Implementierung mit quadratischer
 * Sondierungsstrategie. Die Hashtabelle soll mindestens minSize Elemente
 * aufnehmen können. Achten Sie darauf, dass beim quadratischen Sondieren
 * nur bei einer Primzahl p = 4n + 3 garantiert ist, dass das
 * Sondierverfahren alle Zellen der Hashtabelle erreicht.
 */
public class Quadratic<K, V> extends AlgoDatHashMap<K, V> {
    /**
     * Constructor for HashMap with entries array
     *
     * @param capacity of Hashmap
     */
    public Quadratic(int capacity) {
        super(capacity);
    }

    /**
     * @param hash h initial hashed function, where (key * mod(capacity))
     * @param step from (1...n)
     * @return quadratic index (-1,+1,-4,+4,-9,+9, ...)
     */
    private int quadraticProbing(int hash, int step) {
        // s = sondieren
        // k = hash(key)
        // j = step (1...n)
        // s(k,j) = ceil(j/2)^2 * (-1)^j
        // floorMod -> index inside hashmap size
        return floorMod(hash - (int) (Math.pow((int) Math.ceil((double) step / 2), 2.0) * (Math.pow(-1, step))), 12);
    }
    // Size is next higher prime number Primzahl p = 4n + 3
    protected static int getValidCapacityQuadratic(int minSize) {
        int capacity = minSize;
        while (!isPrimeQuadratic(capacity)) {
            capacity++;
        }
        return capacity;
    }

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
                index = quadraticProbing(hash(key), step);
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
