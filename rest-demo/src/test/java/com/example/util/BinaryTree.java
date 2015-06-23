package com.example.util;

/**
 * @author bloodkilory
 *         generate on 15/6/13
 */
public class BinaryTree {
	private Node root;
	private int size;
	private int foot;
	private Object[] elements;

	public BinaryTree() {
//		this.elements = new Object[10];
	}

	private class Node {
		private int data;
		private Node left;
		private Node right;

		public Node(int data) {
			this.data = data;
		}

		public void addNode(Node newNode) {
			if(this.data > newNode.data) {
				if(this.left == null) {
					this.left = newNode;
					size++;
				} else {
					this.left.addNode(newNode);
				}
			} else {
				if(this.right == null) {
					this.right = newNode;
					size++;
				} else {
					this.right.addNode(newNode);
				}
			}
		}

		/**
		 * 二叉树的遍历，采用中序遍历可进行由小到大排序
		 */
		public void traverse() {
			if(this.left != null) {
				this.left.traverse();
			}
			elements[foot++] = this.data;
			if(this.right != null) {
				this.right.traverse();
			}
		}
	}

	public void add(int data) {
		Node newNode = new Node(data);
		if(this.root == null) {
			this.root = newNode;
			size++;
		} else {
			this.root.addNode(newNode);
		}
	}

	public Object[] toArray() {
		this.elements = new Object[this.size];
		this.root.traverse();
		// 将角标清0；
		this.foot = 0;
		return this.elements;
	}
}
