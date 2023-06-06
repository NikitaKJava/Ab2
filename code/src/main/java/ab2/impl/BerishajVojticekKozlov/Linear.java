package ab2.impl.BerishajVojticekKozlov;

import static java.lang.Math.floorMod;

/**
 * Liefert eine HashMap-Implementierung mit linearer Sondierungsstrategie.
 * Die Hashtabelle soll mindestens minSize Elemente aufnehmen können.
 */
public class Linear<K,V> extends AlgoDatHashMap<K,V> {
    /**
     * Constructor for HashMap with entries array
     *
     * @param capacity Anzahl an Elementen, die gespeichert werden können müssen
     */
    public Linear(int capacity) {
        super(capacity);
    }

    private int linearProbing(int hash, int step) {
        return floorMod((hash - step) , entries.length);
    }

    /**
     * Fügt einen Wert mit dem angegebenen Schlüssel in die Hashtabelle ein.
     * Ein bereits bestehendes Element mit demselben Schlüssel (equals-Methode!) wird
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
                index = linearProbing(hash(key),step);
            }
            // if next Node is null, create and put new key and value
            Entry<K, V> newEntry = new Entry<>(key,value);
            newEntry.setNext(entries[index]);
            entries[index] = newEntry;
        }
        SIZE++;
        return null;
    }

    /**
     * Liefert den Wert zu dem angegebenen Schlüssel. Ist der Schlüssel nicht
     * vorhanden, ist null zurückzugeben
     *
     * @param key der zu suchende Schlüssel
     * @return Wert, der mit dem Schlüssel assoziiert ist. Ist der Schlüssel
     * nicht vorhanden, wird null zurückgegeben.
     */
    @Override
    public V get(K key) {
        return super.get(key);
    }
}
