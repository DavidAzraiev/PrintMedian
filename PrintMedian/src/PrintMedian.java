/*
David Azraiev
 */


import java.util.Random;

public class PrintMedian {

    private int[] array;
    private int size;
    private int count;
    private String name;


    public static void main(String[] args) {
        PrintMedian A = new PrintMedian(200, "A");
        PrintMedian B = new PrintMedian(400, "B");
        PrintMedian C = new PrintMedian(800, "C");

        int[] a = A.getArray();
        int[] b = B.getArray();
        int[] c = C.getArray();

        System.out.println("Print Median from the following array: ");
        System.out.println(A);
        System.out.println(B);
        System.out.println(C);
        System.out.println("The Median of the array is: ");
        System.out.println(A.printMedian(10));
        System.out.println(B.printMedian(50));
        System.out.println(C.printMedian(100));

        A.setArray(a);
        B.setArray(b);
        C.setArray(c);
    }

    private int median(int[] a, int p, int r) {
        int[] c = new int[r - p + 1];
        c = insert(a, p, r);
        return c[(p + (int)Math.ceil((r - p) / 2.0D))];
    }

    private int[] insert(int[] a, int p, int r) {
        for (int j =p+1;j<r+1;j++) {
            int key = a[j];
            int i = j - 1;
            while ((i > p - 1) && (a[i] > key)) {
                a[(i + 1)] = a[i];
                i--;
            }
            a[(i + 1)] = key;
        }
        return a;
    }

    public PrintMedian(int size, String name) {
        this.name = name;
        this.size = size;
        array = new int[size];

        Random random = new Random();

        for (int i = 0; i < size; i++) {
            array[i] = (random.nextInt(1023) + 1);
        }
    }

    public PrintMedian(int[] arr, String name) {
        this.name = name;
        size = arr.length;
        array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = arr[i];
        }
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                str = name + "[";
            }
            str = str + array[i];

            if (i + 1 < size) {
                str = str + ",";
            } else {
                str = str + "]";
            }
        }
        return str;
    }

    private void showArray() {
        System.out.println(toString());
    }

    private void printArray(int[] x) {
        for (int i = 0; i < x.length; i++) {
            if (i == 0) {
                System.out.println("[ ");
            }
            System.out.println(x[i]);

            if (i == x.length - 1) {
                System.out.println(" ]\n");
            } else {
                System.out.println(" , ");
            }
        }
    }

    public int[] getArray() {
        int[] a = new int[size];
        for (int i = 0; i < size; i++) {
            a[i] = array[i];
        }
        return a;
    }

    public void setArray(int[] arr) {
        size = arr.length;
        array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = arr[i];
        }
    }

    public int printMedian(int i) {
        return printMedian(array, 0, size - 1, i);
    }

    private int printMedian(int[] arr, int p, int r, int i) {
        int n = r - p + 1;

        if (n == 1) {
            return arr[p];
        }

        int m = (int) Math.ceil(n / 5.0D);
        int[] b = new int[m];
        int[] c = new int[arr.length];

        for (int j = 0; j < arr.length; j++) {

            c[j] = arr[j];
        }

        for (int j = 0; j < m; j++) {
            b[j] = median(c, p + 5 * j, Math.min(p + 5 * j + 4, r));
        }

        int x = printMedian(b, 0, m - 1, (int) Math.ceil(m / 2.0D));
        int q = partitionWithPivot(arr, p, r, x);
        int k = q - p + 1;

        if (i <= k) {
            return printMedian(arr, p, q, i);
        }
        return printMedian(arr, q + 1, r, i - k);
    }


    private int partitionWithPivot(int[] a, int p, int r, int x) {
        int ix = findIndex(a, p, r, x);
        if (ix == -1) {
            System.out.println("ERROR: the value is not in a[" + p + "..." + r + "]");
            return -1;
        }
        int temp = a[p];
        a[p] = a[ix];
        a[ix] = temp;

        int i = p - 1;
        int j = r + 1;

        for (; ; ) {
            j--;
            if (a[j] <= x) {
                count += 1L;
                do {
                    i++;
                    count += 1L;
                } while (a[i] < x);
                count += 1L;

                if (i >= j) {
                    break;
                }
                temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }

        return j;
    }

    private int findIndex(int[] a, int p, int r, int x)
    {
        for (int i = p; i < r + 1; i++)
        {
            count += 1L;
            if (a[i] == x)
                return i;
        }
        return -1;
    }
}