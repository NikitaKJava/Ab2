package ab2;

import ab2.impl.Nachnamen.Ab2Impl;
import ab2.AlgoDatHashMap;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class Ab2Tests {

    private Random rand = new Random(System.currentTimeMillis());

    private static Ab2 ab2Impl = new Ab2Impl();

    private static final int MOM_TEST_COUNT = 10;
    private static final int MOM_TEST_MINSIZE = 1;
    private static final int MOM_TEST_MAXSIZE = (int) Math.pow(2, 10);

    private static final int HASH_TEST_COUNT = 10;
    private static final int HASH_TEST_MINSIZE = 1;
    private static final int HASH_TEST_MAXSIZE = (int) Math.pow(2, 10);
    private static final double HASH_MAX_LOADFACTOR = 0.5;

    @Test
    public void testHashLinear() {
        //Test die tatsächliche Größe der Hashtabelle
        AlgoDatHashMap<Integer, String> hm = ab2Impl.<Integer, String>newHashMapLinear(12);
        assertEquals(13, hm.capacity());

        checkFullMap(hm);

        for (int size = HASH_TEST_MINSIZE; size <= HASH_TEST_MAXSIZE; size *= 2) {

            int maxElements = (int) Math.max(size * HASH_MAX_LOADFACTOR, 1);
            for (int i = 0; i < HASH_TEST_COUNT; i++) {
                AlgoDatHashMap<Integer, String> hashMap = ab2Impl.<Integer, String>newHashMapLinear(size);

                testAlgoDatHashMap(hashMap, maxElements);
            }
        }
    }

    @Test
    public void testHashQuadratic() {
        //Test die tatsächliche Größe der Hashtabelle
        AlgoDatHashMap<Integer, String> hm = ab2Impl.<Integer, String>newHashMapQuadratic(14);
        assertEquals(19, hm.capacity());

        checkFullMap(hm);

        for (int size = HASH_TEST_MINSIZE; size <= HASH_TEST_MAXSIZE; size *= 2) {

            int maxElements = (int) Math.max(size * HASH_MAX_LOADFACTOR, 1);
            for (int i = 0; i < HASH_TEST_COUNT; i++) {
                AlgoDatHashMap<Integer, String> hashMap = ab2Impl.<Integer, String>newHashMapQuadratic(size);

                testAlgoDatHashMap(hashMap, maxElements);
            }
        }
    }

    @Test
    public void testHashDouble() {
        //Test die tatsächliche Größe der Hashtabelle
        AlgoDatHashMap<Integer, String> hm = ab2Impl.<Integer, String>newHashMapDouble(14);
        assertEquals(17, hm.capacity());

        checkFullMap(hm);

        for (int size = HASH_TEST_MINSIZE; size <= HASH_TEST_MAXSIZE; size *= 2) {

            int maxElements = (int) Math.max(size * HASH_MAX_LOADFACTOR, 1);
            for (int i = 0; i < HASH_TEST_COUNT; i++) {
                AlgoDatHashMap<Integer, String> hashMap = ab2Impl.<Integer, String>newHashMapDouble(size);

                testAlgoDatHashMap(hashMap, maxElements);
            }
        }
    }

    @Test
    public void testMediansOfMedians() {
        for (int size = MOM_TEST_MINSIZE; size <= MOM_TEST_MAXSIZE; size *= 2) {
            int[] data = new int[size];

            for (int i = 0; i < MOM_TEST_COUNT; i++) {
                fillRandomInts(data, MOM_TEST_MAXSIZE);

                // kopiere das Array, sortiere es und bestimme den Median (das length/2-größte Element)
                int[] data_sorted = Arrays.copyOf(data, data.length);
                Arrays.sort(data_sorted);
                int median_expected = data_sorted[data_sorted.length / 2 + 1];

                // bestimme den berechneten Wert laut Implementierung
                int median = ab2Impl.quickselectMax(data, data.length / 2);

                assertEquals(median_expected, median);
            }
        }
    }

    private void fillRandomInts(int[] data, int maxValue) {
        for (int i = 0; i < data.length; i++) {
            data[i] = rand.nextInt(maxValue + 1);
        }
    }

    private void checkFullMap(AlgoDatHashMap<Integer, String> hashMap) {
        for(int i = 0; i < hashMap.capacity(); i++) {
            hashMap.put(i, i+"");
            assertFalse(hashMap.isFull());
        }

        assertTrue(hashMap.isFull());

        assertThrows(IllegalStateException.class, () -> {
            hashMap.put(hashMap.capacity() + 1, "Ein Wert zu viel.");
        });
    }

    private void testAlgoDatHashMap(AlgoDatHashMap<Integer, String> hashMap, int elementCount) {
        int capacity = hashMap.capacity();
        assertTrue(hashMap.isEmpty());

        // Als Referenz wird eine Map erzeugt, um die Implementierung zu testen
        Map<Integer, String> hashMapRef = new HashMap<>(capacity);

        // Befülle die HashMaps
        for (int i = 0; i < elementCount; i++) {
            int key = rand.nextInt(capacity);
            String value = "Wert " + key;

            hashMap.put(key, value);
            assertTrue(hashMap.containsKey(key));
            hashMapRef.put(key, value);
        }

        assertEquals(hashMapRef.size(), hashMap.size());

        // Teste, ob die Werte wiedergefunden werden
        for (int i = 0; i < 10*capacity; i++) {
            int key = rand.nextInt(capacity);

            String value = hashMap.get(key);
            String valueRef = hashMapRef.get(key);

            assertEquals(valueRef, value);
        }
    }
}
