package com.letsdecode.problems.strings;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringQuestionsTest {

	@Test
	public void test() {
		new StringQuestions().snakePrinting("Google Worked");
	}

	@Test
	public void testInterleaving() {
		new StringQuestions().interleavings("", "ABC");
	}
	
	@Test
	public void isInterleaving() {
		System.out.println(new StringQuestions().isInterleaving("abc", "xyz", "abcxyz"));
	}

	@Test
	public void isInterleavingDP() {
		System.out.println(new StringQuestions().isInterleavingDP("aa", "ab", "aaba"));
	}

	@Test
	public void isInterleav() {
		Long l = new Long(23445555);
		Long d = new Long(232343453);
		System.out.println( (int)(l-d));
	}
	
	
}
