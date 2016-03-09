package com.letsdecode.problems.arrays;

public class ArrayProblems {
	public int findContigousSubarrayOf0sAnd1s(int a[], int k) {
		int maxi = -1;
		int maxj = -1;
		int zc = 0;
		int i = 0;
		int j = 0;
		while (j < a.length) {
			if (a[j] == 0) {
				zc++;
			}
			if (zc <= k) {
				if (j - i > maxj - maxi) {
					maxi = i;
					maxj = j;
				}
			} else {
				while (zc > k && i <= j) {
					if (a[i] == 0) {
						zc--;
					}
					i++;
				}
			}
			j++;
		}
		if (maxj == -1 && maxi == -1) {
			return 0;
		}
		return maxj + 1 - maxi;
	}

	public int tappingRainWater(int[] water) {
		int ret = 0;
		int sum = 0;
		for (int i = 0; i < water.length - 1;) {
			int e = i + 1;
			int s = i;
			int min = Math.min(water[s], water[e]);
			while (e < water.length) {
				min = Math.min(water[s], water[e]);
				if (water[e] < water[s]) {
					break;
				}
				e++;
			}
			if (e >= water.length) {
				break;
			}
			if (water[e] >= water[s]) {
				while (s < e) {
					sum += water[s] - min;
					s++;
				}
			}
			i = e;
		}
		ret = sum;
		return ret;
	}

	/**
	 * You are given an array of integers. Out of all of the sums of two
	 * elements, return the one closest to 0.
	 * 
	 * Example input [15, 18, 30] Output: 33 Explanation: 15 + 18 give 33, and
	 * no two elements give a sum closer than that to 0
	 *
	 * Example input [-7, -10, 6] Output: -1 Explanation: -7 +6 gives a sum of
	 * -1, no two elements give a sum closer to 0
	 * 
	 * NOTE: Input array is not sorted
	 * 
	 */

	public int findClosestToZero(int a[]) {
		int retVal = 0;
		if (a.length < 2) {
			throw new IllegalArgumentException(
					"Input array should be atleast of size 2");
		}

		int l = 0;
		int m = 1;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = i + 1; j < a.length; j++) {
				int sum = Math.abs(a[i] + a[j]);
				if (sum < min) {
					min = sum;
					l = i;
					m = j;
				}

			}
		}
		retVal = a[l] + a[m];
		return retVal;
	}

	/**
	 * You are given an array of integers. Out of all of the sums of two
	 * elements, return the one closest to 0.
	 *
	 * Example input [15, 18, 30] Output: 33 Explanation: 15 + 18 give 33, and
	 * no two elements give a sum closer than that to 0
	 *
	 * Example input [-7, -10, 6] Output: -1 Explanation: -7 +6 gives a sum of
	 * -1, no two elements give a sum closer to 0
	 * 
	 * NOTE: Input array is Sorted
	 * 
	 */

	public int findClosestToZeroWithSortedArray(int a[]) {
		int retVal = 0;
		if (a.length < 2) {
			throw new IllegalArgumentException(
					"Input array should be atleast of size 2");
		}

		int min = Integer.MAX_VALUE;
		int i = 0;
		int j = a.length - 1;
		int l = 0;
		int m = 1;
		if (a[0] >= 0) {
			return a[i] + a[j];
		} else if (a[0] < 0 && a[a.length - 1] <= 0) {
			return a[a.length - 2] + a[a.length - 1];
		} else {
			while (i < j) {
				int sum = Math.abs(a[i] + a[j]);
				if (Math.abs(a[i] + a[j]) < min || sum == 0) {
					min = sum;
					l = i;
					m = j;
				}

				if (sum < 0) {
					i++;
				} else {
					j--;
				}

			}
		}
		retVal = a[l] + a[m];
		return retVal;
	}
}
