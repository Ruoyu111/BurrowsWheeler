import java.util.Arrays;

public class CircularSuffixArray {

    private final int[] index;
    private final int length;

    private class IndexString implements Comparable<IndexString> {
        String str;
        int index;

        @Override
        public int compareTo(IndexString indexString) {
            return this.str.compareTo(indexString.str);
        }
    }

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null)
            throw new IllegalArgumentException("argument is null");
        this.length = s.length();
        this.index = new int[this.length];
        IndexString[] suffixes = new IndexString[this.length];

        char[] chars = (s + s).toCharArray();
        for (int i = 0; i < this.length; i++) {
            IndexString is = new IndexString();
            is.str = new String(chars, i, this.length);
            is.index = i;
            suffixes[i] = is;
        }

        Arrays.sort(suffixes);

        for (int c = 0; c < this.length; c++) {
            index[c] = suffixes[c].index;
        }
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

    // unit testing (required)
    public static void main(String[] args) {
        String s = "ABRACADABRA!";
        CircularSuffixArray csa = new CircularSuffixArray(s);
        System.out.println("Length: " + csa.length());
        for (int i = 0; i < csa.length(); i++) {
            System.out.println(csa.index(i));
        }
    }
}
