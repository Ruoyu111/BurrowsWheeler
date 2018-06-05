import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {

    private static final int R = 256;

    // apply Burrows-Wheeler transform, reading from standard input and writing to
    // standard output
    public static void transform() {
        // read the input
        String s = BinaryStdIn.readString();
        CircularSuffixArray suffixArray = new CircularSuffixArray(s);

        int len = suffixArray.length();
        int first = -1;
        char[] t = new char[len];

        for (int i = 0; i < len; i++) {
            if (suffixArray.index(i) == 0) {
                first = i;
                t[i] = s.charAt(len - 1);
            } else {
                t[i] = s.charAt(suffixArray.index(i) - 1);
            }
        }

        // print
        BinaryStdOut.write(first);
        for (int j = 0; j < len; j++) {
            BinaryStdOut.write(t[j]);
        }
        BinaryStdOut.flush();
    }

    // apply Burrows-Wheeler inverse transform, reading from standard input and
    // writing to standard output
    public static void inverseTransform() {
        int first = BinaryStdIn.readInt();
        // tail of Sorted Suffixes
        char[] t = BinaryStdIn.readString().toCharArray();
        // head of Sorted Suffixes
        int len = t.length;

        // Key-indexed sort t and get h
        // h is heading of each suffix
        char[] h = new char[len];
        int[] count = new int[R + 1];

        for (int i = 0; i < len; i++)
            count[t[i] + 1]++;
        for (int r = 0; r < R; r++)
            count[r + 1] += count[r];
        for (int i = 0; i < len; i++)
            h[count[t[i]]++] = t[i];

        // debug
        // System.out.println("print t: "); for (char c : t) { System.out.println(c); }
        // System.out.println("print h: "); for (char c : h) { System.out.println(c); }

        // build next array
        int[] next = new int[len];
        boolean[] marked = new boolean[len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (i == j)
                    continue;
                if (h[i] == t[j] && !marked[j]) {
                    next[i] = j;
                    marked[j] = true;
                    break;
                }
            }
        }

        // debug
        // System.out.println("next array: "); for (int i = 0; i < len; i++) {
        // System.out.println("next[" + i + "] = " + next[i]); }

        // build origin string
        char[] origin = new char[len];

        for (int i = 0, nextIndex = first; i < len; i++, nextIndex = next[nextIndex])
            origin[i] = h[nextIndex];

        // output
        for (int i = 0; i < len; i++) {
            BinaryStdOut.write(origin[i]);
        }
        BinaryStdOut.close();
    }

    // if args[0] is '-', apply Burrows-Wheeler transform
    // if args[0] is '+', apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if (args[0].equals("-")) {
            transform();
        } else if (args[0].equals("+")) {
            inverseTransform();
        }
    }
}
