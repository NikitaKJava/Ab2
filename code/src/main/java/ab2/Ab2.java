package ab2;

public interface Ab2
{
    /**
     * Lierfert eine HashMap-Implementierung mit linearer Sondierungsstrategie.
     * Die Hashtabelle soll mindestens minSize Elemente aufnehmen können.
     *
     * @param minSize Anzahl an Elementen, die gespeichert werden können müssen
     *
     * @return Hashmap-Implementierung
     */
    public <K, V> AlgoDatHashMap<K, V> newHashMapLinear(int minSize);

    /**
     * Liefert eine HashMap-Implementierung mit qudratischer
     * Sondierungsstrategie. Die Hashtabelle soll mindestens minSize Elemente
     * aufnehmen können. Achten Sie darauf, dass beim quadratischen Sondieren
     * nur bei einer Primzahl p = 4n + 3 garantiert ist, dass das
     * Sondierverfahren alle Zellen der Hashtabelle erreicht.
     *
     * @param minSize Anzahl an Elementen, die gespeichert werden können müssen
     *
     * @return Hashmap-Implementierung
     */
    public <K, V> AlgoDatHashMap<K, V> newHashMapQuadratic(int minSize);

    /**
     * Liefert eine HashMap-Implementierung mit doppeltem Hashen. Die
     * Hashtabelle soll mindestens minSize Elemente aufnehmen können. Doppeltes
     * Hashen soll hier implementiert werden, indem für Input n als zweite
     * Hash-Funktion (n MOD p') + 1 verwendet wird, wobei p' die nächstkleinere
     * Primzahl bezogen auf die Größe der Hashtabelle ist.
     *
     * @param minSize Anzahl an Elementen, die gespeichert werden können müssen
     *
     * @return Hashmap-Implementierung
     */
    public <K, V> AlgoDatHashMap<K, V> newHashMapDouble(int minSize);

    /**
     * Quickselect-Implementierung zur Bestimmung des i-größten Elements in
     * einem unsortierten Array.
     *
     * @param data Die zu durchsuchenden Daten in Array-Form
     * @param i Der Index i für das i-größte Element (beginnend bei "1" für
     * das größte Element im Array).
     *
     * @return das i-größte Element im Array
     */
    public int quickselectMax(int[] data, int i);
}
