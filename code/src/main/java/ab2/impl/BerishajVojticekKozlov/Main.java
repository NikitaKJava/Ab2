package ab2.impl.BerishajVojticekKozlov;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.lang.Math.floorMod;

public class Main {
    static Random rand = new Random(System.currentTimeMillis());
    private static final int MOM_TEST_COUNT = 10;
    private static final int MOM_TEST_MINSIZE = 1;
    private static final int MOM_TEST_MAXSIZE = (int) Math.pow(2, 10);

    private static final int HASH_TEST_COUNT = 10;
    private static final int HASH_TEST_MINSIZE = 1;
    private static final int HASH_TEST_MAXSIZE = (int) Math.pow(2, 10);
    private static final double HASH_MAX_LOADFACTOR = 0.5;

    public static void main(String[] args) {

        ab2.AlgoDatHashMap<Integer, String> hm = new AlgoDatHashMap<>(12);
        ab2.AlgoDatHashMap<Integer, String> linearHM = new Linear<>(12);
        Map<Integer, String> hashMapRef = new HashMap<>(12);
        linearHM.put(5, "KEY5");
        hashMapRef.put(5, "KEY5");
//        hashMapRef.put(17,"KEY17");
        linearHM.put(17, "KEY17");
        linearHM.put(17, "KEY17");
//        System.out.println("HM KEY SET: " +Arrays.toString(hm.keys()) + " HM KEY VALUES: " + Arrays.toString(hm.values()));
//        System.out.println("HM REF KEY SET: " + hashMapRef.keySet() + " HM REF VALUES: " + hashMapRef.values());
        int hash = 25;
        int step = 2;
//        System.out.println(floorMod((hash - step), 12));
//        System.out.println(Math.abs(hash % 12));
//        System.out.println(quadraticProbing(hash,step));
//        for (int i = 0; i < hm.capacity(); i++) {
        System.out.println(hash % 12);
//            System.out.println(hm.get(i));
//            System.out.println(i + ": " + rand.nextInt(12));
//        }

//        for (int size = HASH_TEST_MINSIZE; size <= HASH_TEST_MAXSIZE; size *= 2) {
//
//            int maxElements = (int) Math.max(size * HASH_MAX_LOADFACTOR, 1);
////            System.out.println("31: " + maxElements);
//            for (int i = 0; i < HASH_TEST_COUNT; i++) {
//                ab2.AlgoDatHashMap<Integer, String> hashMapLinear = new Linear<>(size);
//
//                for (int j = 0; j < maxElements; j++) {
//                    int key = rand.nextInt(12);
//                    String value = "Wert " + key;
//
//                    hashMapLinear.put(key, value);
//                    hashMapRef.put(key, value);
////                    System.out.println("HML KEY: " + key + " HML VALUE: " + hashMapLinear.get(key) + "\n" + "HML REF KEY: " + key + " REF VALUE: " + hashMapRef.get(key));
//                }
//            }
//        }


//        hm.put(5,"KEY5");
//        int hash = 0;
//        int key = 2;
//        int result = 0;
//
//        System.out.println(hm.get(5));
//        System.out.println(-20 % 12);
//        System.out.println(Math.floorMod(-20,12));

//        ab2.AlgoDatHashMap<Integer, String> linearHM = new Linear<>(12);
//        linearHM.put(5, "KEY5");
//        linearHM.put(17, "KEY17");
//        for (int i = 0; i < linearHM.capacity(); i++) {
//            System.out.println(linearHM.get(i));
//        }

    }

    public static void checkFullMap(ab2.AlgoDatHashMap<Integer, String> hashMap) {
        char c = 'A';
        for (int i = 0; i < hashMap.capacity(); i++) {
            hashMap.put(i, i + String.valueOf(c));
            c++;
        }
    }

    public static void printing(ab2.AlgoDatHashMap<Integer, String> hashMap) {
        for (int i = 0; i < hashMap.capacity(); i++) {
            System.out.print("KEY: " + i + ", VALUE: " + hashMap.get(i) + ", ");
        }
    }

    public static int quadraticProbing(int hash, int step) {
        return floorMod(hash - (int) (Math.pow((int) Math.ceil((double) step/2),2.0) * (Math.pow((double)-1, (double) step))), 12);
    }
}
