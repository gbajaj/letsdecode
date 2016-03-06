package com.letsdecode.problems.trees;

import com.letsdecode.problems.Problem;

public class RightPointerBinaryTree extends Problem<RightPointerTreeNode> {
	RightPointerTreeNode root;

	public RightPointerBinaryTree() {
	}

	public RightPointerBinaryTree(RightPointerTreeNode root) {
		this.root = root;
	}

	public void call() {
		buildRightPointingNodes(this.root);
		result = root;
	}

	public void printNodesByLevel(RightPointerTreeNode root) {
		if (root == null) {
			return;
		}
		System.out.println("");
		RightPointerTreeNode first = null;
		for (RightPointerTreeNode p = root; p != null; p = p.next) {
			System.out.print(p.val + " ");
			if (first == null) {
				if (p.left != null) {
					first = p.left;
				} else if (p.right != null) {
					first = p.right;
				}
			}
		}
		printNodesByLevel(first);
	}

	public void buildRightPointingNodes(final RightPointerTreeNode root) {
		if (root == null) {
			return;
		}
		RightPointerTreeNode first = null;
		RightPointerTreeNode prev = null;
		for (RightPointerTreeNode p = root; p != null; p = p.next) {
			if (p.left != null) {
				if (first == null) {
					first = p.left;
				}
				if (prev != null) {
					prev.next = p.left;
				}
				prev = p.left;
			}
			if (p.right != null) {
				if (first == null) {
					first = p.right;
				}
				if (prev != null) {
					prev.next = p.right;
				}
				prev = p.right;
			}
		}

		buildRightPointingNodes(first);
	}

	public RightPointerTreeNode buildTree(int[] a, int i, int j) {
		RightPointerTreeNode n = null;
		if (i <= j) {
			int mid = i + (j - i) / 2;
			RightPointerTreeNode tr = new RightPointerTreeNode(a[mid]);
			tr.left = buildTree(a, i, mid - 1);
			tr.right = buildTree(a, mid + 1, j);
			n = tr;
		}
		return n;
	}

	public void printInOrder(RightPointerTreeNode t) {
		if (t == null) {
			return;
		}
		printInOrder(t.left);
		System.out.print(t.val + " ");
		printInOrder(t.right);
	}

}
