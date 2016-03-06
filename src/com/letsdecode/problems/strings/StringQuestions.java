package com.letsdecode.problems.strings;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

public class StringQuestions {
	/*
	 * Print a string Sinusoidally Also called "SnakeString". For example, the
	 * phrase "Google Worked" should be printed as follows (where ~ is the word
	 * separator):
	 * 
	 * o ~ k o g e W r e G l o d
	 */
	public void snakePrinting(String str) {
		int MAXSIZE = 3;

		ArrayList<ArrayList<String>> list = new ArrayList<>();
		for (int i = 0; i < MAXSIZE; i++) {
			list.add(new ArrayList<String>());
		}
		int level = 0;
		boolean inc = true;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == ' ') {
				list.get(level).add("~");
			} else {
				list.get(level).add("" + str.charAt(i));
			}
			if (inc) {
				level++;
				if (level == MAXSIZE) {
					level -= 2;
					inc = false;
				}
			} else {
				level--;
				if (level == -1) {
					level += 2;
					inc = true;
				}
			}
		}
		System.out.println(list.get(0));
		for (int i = list.size() - 1; i >= 0; i--) {
			ArrayList<String> levelList = list.get(i);
			for (int k = MAXSIZE - i; k >= 0; k--) {
				System.out.print(" ");
			}
			for (int j = 0; j < levelList.size(); j++) {
				for (int k = 0; k < i; k++) {
					System.out.print(" ");
				}

				System.out.print(levelList.get(j));
				for (int k = MAXSIZE - i; k > 0; k--) {
					System.out.print(" ");
				}
			}
			System.out.println("");
		}

	}

	public void interleavings(String a, String b) {
		char[] out = new char[a.length() + b.length()];
		interleavings(out, a, b, 0, 0, 0);
	}

	public boolean isInterleaving(String a, String b, String c) {
		return isInterleaving(a, 0, b, 0, c, 0);
	}

	public boolean isInterleavingDP(String a, String b, String c) {
	     if (c.length() != a.length() + b.length())
	         return false;
		boolean dp[][] = new boolean[a.length() + 1][b.length() + 1];
		dp[0][0] = true;
		for (int j = 1; j <= b.length(); j++) {
			// A might be empty; then all the characters in B should match we
			// C's
			dp[0][j] = dp[0][j - 1] && c.charAt(j - 1) == b.charAt(j - 1);
		}
		// B might be empty; then all the characters in A should match we C's
		for (int i = 1; i <= a.length(); i++) {
			dp[i][0] = dp[i - 1][0] && c.charAt(i - 1) == a.charAt(i - 1);
		}

		for (int i = 1; i <= a.length(); i++) {
			for (int j = 1; j <= b.length(); j++) {
				//Set current cell to true 
				dp[i][j] = dp[i][j - 1] && c.charAt(i + j - 1) == b.charAt(j - 1)
						|| dp[i - 1][j] && c.charAt(i + j - 1) == a.charAt(i - 1);
			}
		}

		return dp[a.length()][b.length()];
	}

	public boolean isInterleaving(String a, int i, String b, int j, String I,
			int l) {
		if (l == I.length() && i == a.length() && j == b.length()) {
			return true;
		}
		boolean res = false;
		if (l >= I.length()) {
			return res;
		}

		char ch = I.charAt(l);
		if (i >= a.length()) {
			return matchString(b, I, j, l);
		}
		if (i < a.length() && a.charAt(i) == ch) {
			res = isInterleaving(a, i + 1, b, j, I, l + 1);
		}

		if (j >= b.length()) {
			return matchString(a, I, i, l);
		}
		if (!res && j < b.length() && b.charAt(j) == ch) {
			res = isInterleaving(a, i, b, j + 1, I, l + 1);
		}

		return res;
	}

	boolean matchString(String a, String b, int i, int j) {
		while (i < a.length() && j < b.length()) {
			if (a.charAt(i) != b.charAt(j)) {
				return false;
			}
			i++;
			j++;
		}
		if (i == a.length() && j == b.length()) {
			return true;
		}
		return false;
	}

	private void interleavings(char[] out, String stri, String strj, int i,
			int j, int pos) {
		if (i == stri.length() && j == strj.length()) {
			System.out.println(new String(out));
		}
		if (i < stri.length()) {
			out[pos] = stri.charAt(i);
			interleavings(out, stri, strj, i + 1, j, pos + 1);
		} else if (j < strj.length()) {
			// Extra optimization
			while (j < strj.length()) {
				out[pos++] = strj.charAt(j);
				j++;
			}
			System.out.println(new String(out));
			return;
		}
		if (j < strj.length()) {
			out[pos] = strj.charAt(j);
			interleavings(out, stri, strj, i, j + 1, pos + 1);
		} else if (i < stri.length()) {
			// Extra optimization
			while (i < stri.length()) {
				out[pos++] = strj.charAt(i);
				i++;
			}
			System.out.println(new String(out));
			return;
		}
	}
}
