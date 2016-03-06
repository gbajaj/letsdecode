package com.letsdecode.problems.trees;

import org.junit.Assert;
import org.junit.Test;


public class BSTTest {

	@Test
	public void test() {
		// int a [] = {8, 10, 12, 15, 16, 20, 25};
		int a[] = { 2, 1 };
		// BalancedBST bst = new BalancedBST(a);
		// bst.call();
		TreeNode root = getNode(10);
		root.left = getNode(5);
		root.right = getNode(15);
		root.right.left = getNode(6);
		root.right.right = getNode(20);
		boolean res = new BST().isBST(root);
		Assert.assertTrue(res);
	}

	TreeNode getNode(int n) {
		return new TreeNode(n);
	}
}
