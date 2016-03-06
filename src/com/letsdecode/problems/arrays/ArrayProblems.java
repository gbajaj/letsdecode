package com.letsdecode.problems.arrays;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

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
					sum += water[s]  -min;
					s++;
				}
			}
			i = e;
		}
		ret = sum;
		return ret;
	}
}
