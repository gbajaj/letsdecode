package com.letsdecode.problems.trees;

import java.util.ArrayList;
import java.util.Stack;

public class TreeProblems {
	private TreeNode root;

	public TreeProblems(TreeNode root) {
		this.root = root;
	}

	public TreeNode cloneTree(TreeNode t) {
		if (t == null) {
			return null;
		}
		TreeNode n = new TreeNode(t.val);
		n.left = cloneTree(t.left);
		n.right = cloneTree(t.right);
		return n;
	}

	public void flipTree(TreeNode t) {
		if (t == null) {
			return;
		}
		flipTree(t.left);
		flipTree(t.right);

		TreeNode temp = t.left;
		t.left = t.right;
		t.right = temp;

	}

	// //=++++++++++++++UTIL FUNCTIONS++++++++++++++++++
	public void printInOrder(TreeNode t) {
		if (t == null) {
			return;
		}
		printInOrder(t.left);
		System.out.print(t.val + " ");
		printInOrder(t.right);
	}

	public void printPaths() {
		printAllPathsRecursive(this.root, new ArrayList<Integer>());
	}

	public void printAllPathsRecursive(TreeNode root, ArrayList<Integer> list) {
		if (root == null) {
			return;
		}
		list.add(root.val);
		printAllPathsRecursive(root.left, list);
		printAllPathsRecursive(root.right, list);
		if (root.left == null && root.right == null) {
			print(list);
		}
		list.remove(list.size() - 1);
	}

	private void print(ArrayList<Integer> list) {
		for (Integer a : list) {
			System.out.print(a + " ");
		}
		System.out.println("");
	}

	// public TreeNode treeToListOptimized(TreeNode root) {
	// TreeNode temp = new TreeNode(0);
	// temp.left = root;
	//
	// TreeNode head = treeToListOptimized(root, null, temp);
	// head.left = temp.left;
	// temp
	// }
	public TreeNode treeToListOptimized(TreeNode root, TreeNode prev,
			TreeNode tail) {
		TreeNode ret = root;
		TreeNode right = null;
		if (root.right != null) {
			right = root.right;
			if (tail != null) {
				tail.left = right;
			}
		}
		if (root.left != null) {
			TreeNode left = treeToListOptimized(root.left, root, null);
			left.right = root;
			root.right = prev;
			if (prev != null) {
				prev.left = root;
			}
			left.left = null;
			ret = left;
		}
		if (right != null) {
			TreeNode successor = treeToListOptimized(right, root, tail);
			if (prev != null) {
				root.left = prev;
				prev.right = root;
			}

			root.right = successor;
			successor.left = root;

		}
		return ret;
	}

	public TreeNode treeToList(TreeNode root) {
		if (root == null) {
			return null;
		}
		if (root.left == null && root.right == null) {
			root.left = root;
			root.right = root;
			return root;
		} else if (root.left != null && root.right != null) {
			TreeNode leftSideLeftCorner = treeToList(root.left);
			TreeNode leftSideRightCorner = leftSideLeftCorner.left;

			TreeNode rightSideLeftCorner = treeToList(root.right);
			TreeNode rightSideRightCorner = rightSideLeftCorner.left;

			leftSideRightCorner.right = root;
			root.left = leftSideRightCorner;

			rightSideLeftCorner.left = root;
			root.right = rightSideLeftCorner;

			leftSideLeftCorner.left = rightSideRightCorner;
			rightSideRightCorner.right = leftSideLeftCorner;

			return leftSideLeftCorner;
		} else if (root.left != null) {
			TreeNode leftCorner = treeToList(root.left);
			TreeNode rightCorner = leftCorner.left;
			root.right = leftCorner;
			root.left = rightCorner;
			rightCorner.left = root;
			leftCorner.left = root;
			return leftCorner;
		} else {
			TreeNode leftCorner = treeToList(root.right);
			TreeNode rightCorner = leftCorner.left;

			root.left = rightCorner;
			root.right = leftCorner;
			leftCorner.left = root;

			rightCorner.right = root;
			return root;
		}
	}

	int pick = 0;

	public TreeNode buildTree(int[] inorder, int[] preorder, int start, int end) {

		if (start > end)
			return null;
		// if (pick >preorder.length-1) return null;
		TreeNode node = new TreeNode(preorder[pick]);
		pick++;

		if (start == end)
			return node;
		int index = findIndex(start, end, inorder, node.val);
		node.left = buildTree(inorder, preorder, start, index - 1);
		node.right = buildTree(inorder, preorder, index + 1, end);
		return node;
	}

	int findIndex(int s, int e, int[] inorder, int val) {
		for (int i = s; i <= e; i++) {
			int j = inorder[i];
			if (j == val) {
				return i;
			}
		}
		return -1;
	}

	int l = 0;

	public TreeNode rebuildTree(int[] pre, int[] in, int i, int j) {
		TreeNode ret = null;
		if (i > j) {
			return ret;
		}
		ret = new TreeNode(in[l]);
		l++;
		if (i == j)
			return ret;
		int mid = findInd(in, i, j, pre[l]);

		ret.left = rebuildTree(pre, in, i, mid - 1);
		ret.right = rebuildTree(pre, in, mid + 1, j);
		return ret;
	}

	int findInd(int[] in, int i, int j, int f) {
		for (int s = i; s <= j; s++) {
			if (in[s] == f) {
				return s;
			}
		}
		return -1;
	}

	/*
	 * Time complexity of the above solution is O(n) as the method does a simple
	 * tree traversal in bottom up fashion. Note that the above method assumes
	 * that keys are present in Binary Tree. If one key is present and other is
	 * absent, then it returns the present key as LCA (Ideally should have
	 * returned NULL).
	 */

	public TreeNode leastCommonAncestorWithBothKeysPresent(TreeNode root,
			int v1, int v2) {
		if (root == null) {
			return null;
		}

		if (root.val == v1 || root.val == v2) {
			return root;
		}
		TreeNode left = leastCommonAncestorWithBothKeysPresent(root.left, v1,
				v2);
		TreeNode right = leastCommonAncestorWithBothKeysPresent(root.right, v1,
				v2);

		if (left != null && right != null) {
			return root;
		}
		return left != null ? left : right;
	}

	public boolean isBST(TreeNode root) {

		return isBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
	}

	public boolean isBST(TreeNode root, long min, long max) {
		if (root == null)
			return true;
		if (root.val > min && root.val < max) {

			// equals are supported
			return isBST(root.left, min, root.val)
					&& isBST(root.right, root.val, max);
		}
		return false;
	}

	public boolean univalSubTrees(TreeNode root, int[] count) {

		if (root == null) {
			return true;
		}

		boolean l = univalSubTrees(root.left, count);
		boolean r = univalSubTrees(root.right, count);
		if (l && r) {
			if (root.left == null && root.right == null) {
				// leaf// always unival
				count[0]++;
				return true;
			} else if (root.left != null && root.right != null) {
				// Both nodes present check if both matches the root;
				if (root.left.val == root.val && root.right.val == root.val) {
					count[0]++;
					return true;
				}
			} else if (root.left != null) {
				if (root.left.val == root.val) {
					count[0]++;
					return true;
				}
			} else if (root.right != null) {
				if (root.right.val == root.val) {
					count[0]++;
					return true;
				}
			}
		}

		return false;

	}

	public void inorderIterative(TreeNode root) {
		if (root == null) {
			return;
		}
		Stack<TreeNode> s = new Stack<>();
		TreeNode c = root;
		while (s.isEmpty() == false || c != null) {
			if (c != null) {
				s.push(c);
				c = c.left;
			} else {
				c = s.pop();
				System.out.print(" " + c.val);
				c = c.right;
			}
		}
	}

	public void postorderIterative(TreeNode root) {
		if (root == null) {
			return;
		}
		Stack<TreeNode> s = new Stack<>();
		TreeNode c = root;
		TreeNode visited = null;
		// Set<TreeNode> set = new HashSet<>();

		while (s.isEmpty() == false || c != null) {

			if (c != null) {
				s.push(c);
				c = c.left;
			} else {
				c = s.pop();
				// if (set.contains(c)) {
				if (c.right == null || c.right == visited) {
					System.out.print(" " + c.val);
					visited = c;
					// set.remove(c);
					c = null;
				} else {
					s.push(c);
					// set.add(c);
					c = c.right;
				}
			}
		}

	}

	public ArrayList<TreeNode> boundryTraversal(TreeNode node) {
		ArrayList<TreeNode> list = new ArrayList<>();
		if (node != null) {
			list.add(node);
			leftBranch(node.left, true, list);
			rightBranch(node.right, true, list);
		}
		return list;
	}

	private void leftBranch(TreeNode node, boolean leftBranch,
			ArrayList<TreeNode> list) {
		if (node == null) {
			return;
		}
		
		if (leftBranch || node.isLeaf()) {
			list.add(node);
		}
		leftBranch(node.left, leftBranch, list);
		leftBranch(node.right, node.left == null? true:false, list);
	}

	private void rightBranch(TreeNode node, boolean rightBranch, ArrayList<TreeNode> list) {
		if (node == null) {
			return;
		}

		leftBranch(node.left, node.right == null? true:false, list);
		leftBranch(node.right, rightBranch, list);
		if (rightBranch || node.isLeaf()) {
			list.add(node);
		}
	}
}
