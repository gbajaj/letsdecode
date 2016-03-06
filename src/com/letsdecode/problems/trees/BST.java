package com.letsdecode.problems.trees;

import com.letsdecode.problems.Problem;

public class BST extends Problem<Boolean> {
	public boolean isBST(TreeNode t) {
		if (t == null) {
			return true;
		}
		boolean circuit = true;
		if (t.left != null) {
			if (t.left.val > t.val)
				return false;
			circuit = isBST(t.left);
		}
		if (circuit && t.right != null) {
			if (t.right.val < t.val)
				return false;

			circuit = isBST(t.right);
		}
		return circuit;
	}
}
