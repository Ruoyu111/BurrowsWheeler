import java.util.LinkedList;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {

    public static final int EXTENDED_ASCII = 256;

    // apply move-to-front encoding, reading from standard input and writing to
    // standard output
    public static void encode() {

        LinkedList<Character> chars = new LinkedList<>();
        for (int i = 0; i < EXTENDED_ASCII; i++) {
            chars.add((char) i);
        }

        while (!BinaryStdIn.isEmpty()) {
            // read char
            char c = BinaryStdIn.readChar();
            BinaryStdOut.write((char) chars.indexOf(c));

            // move to front
            if (chars.indexOf(c) != 0) {
                chars.remove(c);
                chars.addFirst(c);
            }
        }
        BinaryStdOut.flush();
    }

    // apply move-to-front decoding, reading from standard input and writing to
    // standard output
    public static void decode() {
        LinkedList<Character> chars = new LinkedList<>();
        for (int i = 0; i < EXTENDED_ASCII; i++) {
            chars.add((char) i);
        }

        while (!BinaryStdIn.isEmpty()) {
            // read integer
            int index = BinaryStdIn.readChar();
            char c = chars.get(index);
            BinaryStdOut.write(c);

            // move to front
            if (index != 0) {
                chars.remove(index);
                chars.addFirst(c);
            }
        }
        BinaryStdOut.flush();
    }

    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
    public static void main(String[] args) {
        if (args[0].equals("-")) {
            encode();
        } else if (args[0].equals("+")) {
            decode();
        }
    }

}
