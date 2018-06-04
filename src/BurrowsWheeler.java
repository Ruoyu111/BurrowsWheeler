import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {
	
	// apply Burrows-Wheeler transform, reading from standard input and writing to standard output
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
	
	// apply Burrows-Wheeler inverse transform, reading from standard input and writing to standard output
	public static void inverseTransform() {
		
	}

	// if args[0] is '-', apply Burrows-Wheeler transform
	// if args[0] is '+', apply Burrows-Wheeler inverse transform
	public static void main(String[] args) {
		if (args[0].equals("-")) {
			transform();
		}
	}
}
