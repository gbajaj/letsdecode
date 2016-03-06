package com.letsdecode.problems;

public class quickSortIntArray {
	public int[] a;

	public quickSortIntArray(final int[] a) {
		this.a = a;
	}

	public void call() {
		quickSort(a, 0, a.length - 1);
	}

	private void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	 void quickSort(final int a[], int i, int j) {
		if (i < j) {
			int index = partition(a, i, j);
			quickSort(a, i, index - 1);
			quickSort(a, index + 1, j);
		}

	}

	int partitionOrig(final int a[], int i, int j) {
		int pivot = a[i];
		int start = i;
		int k = i;

		for (; k <= j; k++) {
			if (a[k] < pivot) {
				swap(a, k, i + 1);
				i++;
			}
		}
		swap(a, start, i);
		return i;
	}

	 int partition(final int a[], int i, int j) {
		int mid = i + (j - i) / 2;
		int pivot  = a[mid];
		while (i < j) {
			while (a[i] < pivot)
				i++;
			while (a[j] > pivot)
				j--;
			if (i < j) {
				swap(a, i, j);
			}
		}
		return i;
	}

}
