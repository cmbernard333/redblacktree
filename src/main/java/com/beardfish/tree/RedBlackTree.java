package com.beardfish.tree;

/**
 * Red Black Tree Properties :
 *
 * 1. A node is either red or black.
 * 2. All leaves (NIL) are black. (All leaves are same color as the root.)
 * 3. Every red node must have two black child nodes.
 * 4. Every path from a given node to any of its descendant leaves contains the same number of black nodes.
 */

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;

public class RedBlackTree<E> implements Set<E> {

    private final Comparator<? super E> comparator;
    private int size = 0;
    private Node<E> root = null;

    public RedBlackTree(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }


    @Override
    public boolean add(E ele) {
        boolean inserted = false;
        if (this.root == null) {
            this.root = new Node<E>(ele, null, null, null);
            this.root.setColor(Node.Color.Black);
            inserted = true;
        } else {
            if (this.comparator != null) {
                inserted = this.binarySearchWithComparator(this.root, ele);
            } else {
                inserted = this.binarySearchWithComparable(this.root, ele);
            }
        }
        return inserted;
    }

    @Override
    public boolean addAll(Collection<? extends E> col) {
        return false;
    }

    /**
     * Adds a node into the tree using the comparator
     *
     * @param node    -- the starting node
     * @param element -- the element to be added
     */
    public boolean binarySearchWithComparator(Node<E> node, E element) {
        int compare = this.comparator.compare(element, node.getValue());
        if (compare < 0) {
            /* element is less than node value */
            if (node.getLeft() != null) {
                binarySearchWithComparator(node.getLeft(), element);
            } else {
                node.setLeft(new Node<E>(element, node, null, null));
            }
        } else if (compare > 0) {
            /* element is greater than node value */
            if (node.getRight() != null) {
                binarySearchWithComparator(node.getRight(), element);
            } else {
                node.setRight(new Node<E>(element, node, null, null));
            }
        } else if (compare==0) {
            return false;
        }
    }

    /**
     * Adds a node into the tree using the comparable
     *
     * @param node    -- the starting node
     * @param element -- the element to be added
     */
    public boolean binarySearchWithComparable(Node<E> node, E element) {
        Comparable<? super E> key = (Comparable<? super E>) element;
        int compare = key.compareTo(node.getValue());
        if (compare < 0) {
            /* element is less than node value */
            if (node.getLeft() != null) {
                binarySearchWithComparable(node.getLeft(), element);
            } else {
                node.setLeft(new Node<E>(element, node, null, null));
            }
        } else if (compare > 0) {
            /* element is greater than node value */
            if (node.getRight() != null) {
                binarySearchWithComparable(node.getRight(), element);
            } else {
                node.setRight(new Node<E>(element, node, null, null));
            }
        } else if(compare==0) {
            return false;
        }
    }

    /**
     * Rebalance the tree until all properties are fullfilled
     *
     * @param node -- the node to start the rebalance from
     */
    public void rebalanceTree(Node<E> node) {
        /* root */
        if (node.getParent() == null) {
            node.setColor(Node.Color.Black);
        }
        /* if the parent is black its okay to add a red child */
        if (node.getParent().getColor().equals(Node.Color.Black)) {
            return;
        } else if (node.getParent().getColor().equals(Node.Color.Red)) {
            /* check the uncle to see if he is red as well */
            Node<E> uncle = (Node<E>) getUncle(node);
            if (uncle != null) {
                Node<E> grandParent = (Node<E>) getGrandParent(node);
                if (uncle.getColor().equals(Node.Color.Red)) {
                    node.getParent().setColor(Node.Color.Black);
                    uncle.setColor(Node.Color.Black);
                    grandParent.setColor(Node.Color.Red);
                    rebalanceTree(grandParent);
                }
                else if (uncle.getColor().equals(Node.Color.Black)) {
                    if(node.getParent().getRight()==node && grandParent.getLeft()==node.getParent()) {
                        rotateLeft(node.getParent());
                    } else if(node.getParent().getLeft()==node && grandParent.getRight()==node.getParent()) {
                        rotateRight(node.getParent());
                    }
                }
            }
        }
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> col) {

        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {

        return null;
    }

    @Override
    public boolean remove(Object o) {

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> col) {

        return false;
    }

    @Override
    public boolean retainAll(Collection<?> col) {

        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Object[] toArray() {

        return null;
    }

    @Override
    public <T> T[] toArray(T[] arr) {

        return null;
    }


    /* utility methods */

    private static Node<?> getGrandParent(Node<?> node) {
        if (node != null && node.getParent() != null) {
            node.getParent().getParent();
        }
        return null;
    }

    private static Node<?> getUncle(Node<?> node) {
        Node<?> grandParent = getGrandParent(node);
        if (grandParent == null) {
            return null;
        }
        if (node.getParent() == grandParent.getLeft()) {
            return grandParent.getRight();
        }
        return grandParent.getLeft();
    }

    private void rotateLeft(Node<E> node) {
        Node<E> grandParent = node.getParent();
        Node<E> rightChild = node.getRight();
        Node<E> rightChildLeft = rightChild.getLeft();
        grandParent.setLeft(rightChild);
        rightChild.setLeft(node);
        node.setRight(rightChildLeft);

    }

    private void rotateRight(Node<E> node) {
        Node<E> grandParent = node.getParent();
        Node<E> leftChild = node.getLeft();
        Node<E> leftChildRight = leftChild.getRight();
        grandParent.setRight(leftChild);
        leftChild.setRight(node);
        node.setLeft(leftChildRight);
    }

    /* node inner class */

    private static class Node<E> {

        public static enum Color {Black, Red}

        ;

        private final E value;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;
        private Color color;

        public Node(E value, Node<E> parent, Node<E> left, Node<E> right) {
            this(value, parent, left, right, Color.Red);
        }

        public Node(E value, Node<E> parent, Node<E> left, Node<E> right, Color color) {
            this.value = value;
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.color = color;
        }

        public E getValue() {
            return value;
        }

        public Node<E> getParent() {
            return this.parent;
        }

        public void setParent(Node<E> parent) {
            this.parent = parent;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }

        public Color getColor() {
            return this.color;
        }

        public void setColor(Color color) {
            this.color = color;
        }
    }

}
