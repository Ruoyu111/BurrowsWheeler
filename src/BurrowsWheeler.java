import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

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
		int first = BinaryStdIn.readInt();
		// tail of Sorted Suffixes
		List<Character> t = new ArrayList<>();
		// head of Sorted Suffixes
		List<Character> h = new ArrayList<>();
		while (!BinaryStdIn.isEmpty()) {
		    char c = BinaryStdIn.readChar();
		    t.add(c);
		    h.add(c);
		}
		Collections.sort(h);
		
		int len = t.size();
		
		// origin string
		char[] origin = new char[len];
		origin[0] = h.get(first);
		origin[len - 1] = t.get(first);
		
		// build next array
		int[] next = new int[len];
		HashSet<Integer> set = new HashSet<>();
		
		for (int i = 0; i < len; i++) {
		    for (int j = 0; j < len; j++) {
		        if (i == j) continue;
		        if (h.get(i).equals(t.get(j)) && !set.contains(j)) {
		            next[i] = j;
		            set.add(j);
		        }
		    }
		}
		
		// build origin string
		for (int i = 1, nextIndex = next[first]; i < len - 1; i++, nextIndex = next[nextIndex]) {
		    origin[i] = h.get(nextIndex);
		}
		
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
