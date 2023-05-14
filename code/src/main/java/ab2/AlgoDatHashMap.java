package ab2;

/**
 * Schnittstelle, welche eine Hashtabelle darstellt. Um aus dem Schlüssel K,
 * der keine Zahl sein muss, einen Wert zu extrahieren, mit dem man die
 * Position in der Hashtabelle ausrechnen kann, verwenden Sie die Methode
 * hashCode(), welche die Klasse Object bietet (modulo der Größe der
 * Hashtabelle). Sie können für ihre Implementierung davon ausgehen, dass diese
 * Methode passende Werte liefert. Zur Überprüfung, ob zwei Schlüssel bzw. zwei
 * Werte identisch sind, muss die equals()-Methode verwendet werden (jedes
 * Objekt besitzt diese).
 * 
 * @param <K>
 *            Typ des Schlüssels
 * @param <V>
 *            Typ der zu speichernden Werte
 */
public interface AlgoDatHashMap<K, V> {

	/**
	 * Löscht den Inhalt der Hashtabelle.
	 */
	public void clear();

	/**
	 * Liefert true, wenn der Schlüssel key in der Hashtabelle vorhanden ist.
	 * 
	 * @param key
	 *            der zu suchende Schlüssel
	 * @return true, wenn der Schlüssel in der Hashtabelle vorhanden ist. Sonst
	 *         false.
	 */
	public boolean containsKey(K key);

	/**
	 * Liefert den Wert zu dem angegebenen Schlüssel. Ist der Schlüssel nicht
	 * vorhanden, ist null zurück zu geben.
	 * 
	 * @param key
	 *            der zu suchende Schlüssel
	 * @return Wert, der mit dem Schlüssel assoziiert ist. Ist der Schlüssel
	 *         nicht vorhanden, wird null zurück gegeben.
	 */
	public V get(K key);

	/**
	 * 
	 * @return true, wenn die Hashtabelle leer ist (d.h. keine Elemente enthält)
	 */
	public boolean isEmpty();

	/**
	 * 
	 * @return true, wenn die Hashtabelle voll ist (d.h. es können keine
	 *         weiteren Elemente mehr einfügt werden)
	 */
	public boolean isFull();

	/**
	 * Liefert ein Set, welches alle Schlüssel enthält, die in der Hashtabelle
	 * gespeichert sind.
	 * 
	 * @return die in der Hashtabelle gespeicherten Schlüssel
	 */
	public K[] keys();

	/**
	 * Fügt einen Wert mit dem angegebenen Schlüssel in die Hashtabelle ein. Ein
	 * bereits bestehendes Element mit dem selben Schlüssel (equals-Methode!) wird
	 * dabei überschrieben und zurückgegeben.
	 * 
	 * @param key
	 *            der einzufügende Schlüssel
	 * @param value
	 *            der einzufülgende Wert
	 * @return null, wenn unter dem Schlüssel noch kein Wert eingefügt war.
	 *         Liefert das bisher gespeicherte Element, wenn mit dem Key schon
	 *         ein Wert vorhanden war.
	 * @throws IllegalStateException
	 *             Wenn kein freier Platz mehr in der Hashtabelle vorhanden ist
	 */
	public V put(K key, V value) throws IllegalStateException;

	/**
	 * Liefert die Anzahl an Werten, die in der Hashtabelle gespeichert sind.
	 * 
	 * @return Anzahl der Elemente in der Tabelle
	 */
	public int size();

	/**
	 * Liefert die Größe der Hashtabelle zurück
	 * 
	 * @return Größe der Hashtabelle (nicht Anzahl der gespeicherten Elemente)
	 */
	public int capacity();

	/**
	 * Liefert eine Sammlung der in der Hashtabelle enthaltenen Werte.
	 * 
	 * @return Sammlung der in der Hashtabelle enthaltenen Werte
	 */
	public V[] values();

}
