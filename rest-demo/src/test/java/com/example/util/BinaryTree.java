package com.example.util;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 二叉树
 *
 * @author bloodkilory
 *         generate on 15/6/13
 */
@SuppressWarnings("unchecked")
public class BinaryTree<T extends Comparable> implements Iterable<BinaryTree> {
	private Node root;
	private int size;
	private int foot;
	private Object[] elements;

	public BinaryTree() {
	}

	/**
	 * Returns an iterator over elements of type {@code T}.
	 *
	 * @return an Iterator.
	 */
	@Override
	public Iterator<BinaryTree> iterator() {
		return null;
	}

	/**
	 * Performs the given action for each element of the {@code Iterable}
	 * until all elements have been processed or the action throws an
	 * exception.  Unless otherwise specified by the implementing class,
	 * actions are performed in the order of iteration (if an iteration order
	 * is specified).  Exceptions thrown by the action are relayed to the
	 * caller.
	 *
	 * @param action The action to be performed for each element
	 * @throws NullPointerException if the specified action is null
	 * @implSpec <p>The default implementation behaves as if:
	 * <pre>{@code
	 *     for (T t : this)
	 *         action.accept(t);
	 * }</pre>
	 * @since 1.8
	 */
	@Override
	public void forEach(Consumer<? super BinaryTree> action) {

	}

	/**
	 * Creates a {@link Spliterator} over the elements described by this
	 * {@code Iterable}.
	 *
	 * @return a {@code Spliterator} over the elements described by this
	 * {@code Iterable}.
	 * @implSpec The default implementation creates an
	 * <em><a href="Spliterator.html#binding">early-binding</a></em>
	 * spliterator from the iterable's {@code Iterator}.  The spliterator
	 * inherits the <em>fail-fast</em> properties of the iterable's iterator.
	 * @implNote The default implementation should usually be overridden.  The
	 * spliterator returned by the default implementation has poor splitting
	 * capabilities, is unsized, and does not report any spliterator
	 * characteristics. Implementing classes can nearly always provide a
	 * better implementation.
	 * @since 1.8
	 */
	@Override
	public Spliterator<BinaryTree> spliterator() {
		return null;
	}

	private class Node {
		private T data;
		private Node left;
		private Node right;

		Node(T data) {
			this.data = data;
		}

		void addNode(Node newNode) {
			if(this.data.compareTo(newNode.data) > 0) {
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
		void traverse() {
			if(this.left != null) {
				this.left.traverse();
			}
			elements[foot++] = this.data;
			if(this.right != null) {
				this.right.traverse();
			}
		}
	}

	public void add(T data) {
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
