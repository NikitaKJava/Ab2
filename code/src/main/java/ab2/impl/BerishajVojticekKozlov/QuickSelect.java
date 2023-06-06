package ab2.impl.BerishajVojticekKozlov;

/**
 * QuickSelect-Implementierung zur Bestimmung des i-größten Elements in
 * einem unsortierten Array.
 */

public class QuickSelect {
    /** Initial Quick Select function
     * @param data Die zu durchsuchenden Daten in Array-Form
     * @param i    Der Index i für das i-größte Element (beginnend bei "1" für das größte Element im Array).
     * @return das i-größte Element im Array
     */
    public static int quickSelect(int[] data, int i) {

        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        if (i < 0 || i > data.length) {
            throw new IllegalArgumentException("The index is out of bounds");
        }
        return quickSelect(data, 0, data.length - 1, i);
    }

    // Random pivot element
    private static int randomPivot(int left, int right) {
        return left + (int) Math.floor(Math.random() * (right - left + 1));
    }

    // Median of Medians pivot element
    private static int getMOMPivotIndex(int[] data, int low, int high) {
        if (high - low < 5) {
            return partitionMOM(data, low, high);
        }

        int numMedians = (high - low + 1) / 5;
        int[] medians = new int[numMedians];

        for (int i = 0; i < numMedians; i++) {
            int medianStart = low + i * 5;
            int medianEnd = medianStart + 4;
            medians[i] = partitionMOM(data, medianStart, medianEnd);
        }
        return quickSelect(medians, 0, numMedians - 1, numMedians / 2);
    }

    private static int partitionMOM(int[] data, int left, int right) {
        int pivot = data[right];
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (data[j] >= pivot) {
                i++;
                swap(data, i, j);
            }
        }
        swap(data, i + 1, right);
        return i + 1;
    }

    public static int partition(int[] data, int left, int right, int pivotIndex) {

        int pivotElement = data[pivotIndex];

        // swap pivot index to the end
        swap(data, pivotIndex, right);

        // left <= pivotIndex => right
        pivotIndex = left;

        // Sorting elements according to pivot element
        for (int i = left; i < right; i++) {
            if (data[i] <= pivotElement) { // pivot element is the latest element in the partition
                swap(data, i, pivotIndex);
                pivotIndex++;
            }
        }

        // Swap pivot element to the end on the right
        swap(data, pivotIndex, right);

        return pivotIndex;
    }

    public static int quickSelect(int[] data, int left, int right, int k) {
        // if their only one element in array, return left side
        if (left == right) {
            return data[left];
        }
        // to create different methods of selecting the pivot element
        int random = (int) Math.floor(Math.random() * 3) + 1;
        int pIndex = 0;

        if (random == 1) {
            pIndex = randomPivot(left, right);
        }

        if(random == 2) {
            pIndex = getMOMPivotIndex(data, left, right);
        }

        if(random == 3) {
            pIndex = right;
        }

        pIndex = partition(data, left, right, pIndex);

        // pivot element is at its final position
        if (k == pIndex) {
            return data[k];
        }

        // if k is smaller than pivot index swap pivot index to left
        if (k < pIndex) {
            return quickSelect(data, left, pIndex - 1, k);
        }

        // if k is greater than pivot index swap pivot index to left
        return quickSelect(data, pIndex + 1, right, k);
    }

    private static void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}
