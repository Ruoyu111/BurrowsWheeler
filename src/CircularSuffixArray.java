public class CircularSuffixArray {

    private static final int R = 256;

    private final int[] index;
    private final int length;

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null)
            throw new IllegalArgumentException("argument is null");
        this.length = s.length();
        this.index = new int[this.length];

        for (int i = 0; i < this.length; i++) {
            index[i] = i;
        }

        // MSD string sort suffixes
        int[] aux = new int[this.length];
        sort(s, index, 0, this.length - 1, 0, aux);
    }

    // length of s
    public int length() {
        return length;
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i >= length)
            throw new IllegalArgumentException("input is outside of its prescribed range.");
        return index[i];
    }

    private void sort(String s, int[] index, int lo, int hi, int d, int[] aux) {

        if (hi <= lo) {
            return;
        }

        // compute frequency counts
        int[] count = new int[R + 2];
        for (int i = lo; i <= hi; i++) {
            int c = s.charAt((index[i] + d) % this.length);
            count[c + 2]++;
        }

        // transform counts to indicies
        for (int r = 0; r < R + 1; r++) {
            count[r + 1] += count[r];
        }

        // distribute
        for (int i = lo; i <= hi; i++) {
            int c = s.charAt((index[i] + d) % this.length);
            aux[count[c + 1]++] = index[i];
        }

        // copy back
        for (int i = lo; i <= hi; i++) {
            index[i] = aux[i - lo];
        }

        // recursively sort for each character (excludes)
        for (int r = 0; r < R; r++) {
            sort(s, index, lo + count[r], lo + count[r + 1] - 1, d + 1, aux);
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        String s = "*************";
        CircularSuffixArray csa = new CircularSuffixArray(s);
        System.out.println("Length: " + csa.length());
        for (int i = 0; i < csa.length(); i++) {
            System.out.println(csa.index(i));
        }
    }
}
