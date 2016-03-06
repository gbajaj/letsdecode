package com.letsdecode.problems.arrays;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class ArrayProblemsTest {

	@Test
	public void test() {
		int actual = 0;
		int input[] = null;
		int out = 0;
		int a[] = { 1, 1, 0, 0, 1, 1, 1, 0, 1, 1 };
		input = a;
		out = new ArrayProblems().findContigousSubarrayOf0sAnd1s(input, 1);
		Assert.assertEquals(out, 6);

		int c[] = { 0, 0 };
		input = c;
		actual = new ArrayProblems().findContigousSubarrayOf0sAnd1s(input, 1);
		Assert.assertEquals(0, actual);

		int d[] = { 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1 };
		input = d;
		int k = 2;
		out = new ArrayProblems().findContigousSubarrayOf0sAnd1s(input, k);
		Assert.assertEquals(out, 8);

	}

	@Test
	public void testTappingWater() {
//		int [] a = {0,1,0,2,1,0,1,3,2,1,2,1};
//		int [] a = {0,1,0,1};
		int [] a = {0,1,0,2,1,0,1,3};
		System.out.println(new ArrayProblems().tappingRainWater(a));
	}
}
