package ab2.impl.BerishajVojticekKozlov;

public class QuickSelect {

    public static int quickselectMax(int[] data, int i) {

        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty or null.");
        }

        if (i < 0 || i > data.length) {
            throw new IllegalArgumentException("Der Index liegt außerhalb des gültigen Bereichs");
        }
        return quickselectMax(data, 0, data.length - 1, i);
    }

        private static int quickselectMax(int[] data, int low, int high, int i) {
            if (low == high) {
                return data[low];
            }

            int pivotIndex = getPivotIndex(data, low, high);
            int rank = pivotIndex - low + 1;

            if (i == rank) {
                return data[pivotIndex];
            } else if (i < rank) {
                return quickselectMax(data, low, pivotIndex - 1, i);
            } else {
                return quickselectMax(data, pivotIndex + 1, high, i - rank);
            }
        }

        private static int getPivotIndex(int[] data, int low, int high){
                if (high - low < 5) {
                    return partition(data, low, high);
                }

                int numMedians = (high - low + 1) / 5;
                int[] medians = new int[numMedians];

                for (int i = 0; i < numMedians; i++) {
                    int medianStart = low + i * 5;
                    int medianEnd = medianStart + 4;
                    medians[i] = partition(data, medianStart, medianEnd);
                }

                return quickselectMax(medians, 0, numMedians - 1, numMedians / 2);
            }

            private static int partition(int[] data, int low, int high){
                int pivot = data[high];
                int i = low -1;

                for(int j= low; j<high; j++){
                    if(data[j]>= pivot){
                        i++;
                        swap(data, i, j);
                    }
                }

                swap(data, i+1, high);
                return i+1;
            }

    private static void swap(int[] data, int i, int j) {
                int temp = data[i];
                data[i] = data[j];
                data[j] = temp;
            }






    }
