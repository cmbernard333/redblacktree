package com.beardfish.tree;

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
		if(this.root==null) {
			this.root = new Node<E>(ele,null,null,null);
            this.root.setColor(Node.Color.Black);
		} else {
			if(this.comparator!=null) {
                this.searchInTreeWithComparator(this.root,ele);
            } else {
                this.searchInTreeWithComparable(this.root,ele);
            }
		}
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> col) {
		return false;
	}

    /**
     * Search downward in the tree using comparable until the appropriate node is found
     * @param node
     */
    public void searchInTreeWithComparable(Node<E> node, E element) {
        Comparable<? super E> key = (Comparable<? super E>) element;
        int compare = key.compareTo(node.getValue());
        if(compare<0) {
            /* element is less than node value */
            if(node.getLeft()!=null) {
                searchInTreeWithComparable(node.getLeft(),element);
            } else {
                node.setLeft(new Node<E>(element,node,null,null));
            }
        } else if(compare>0) {
            /* element is greater than node value */
            if(node.getRight()!=null) {
                searchInTreeWithComparable(node.getRight(),element);
            } else {
                node.setRight(new Node<E>(element,node,null,null));
            }
        }
    }

    /** Search downard in the tree using the comparator until the appropriate node is found
     * @param node
     */
    public void searchInTreeWithComparator(Node<E> node,E element) {
        int compare = this.comparator.compare(element,node.getValue());
        if(compare<0) {
            /* element is less than node value */
        } else if (compare>0) {
            /* element is greater than node value*/
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
		
		return size==0;
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

    private static Node<?> getGrandParent(Node<?> node) {
        if(node!=null && node.getParent()!=null) {
            node.getParent().getParent();
        }
        return null;
    }

    private static Node<?> getUncle(Node<?> node) {
        Node<?> grandParent = getGrandParent(node);
        if(grandParent==null) {
            return null;
        }
        if(node.getParent()==grandParent.getLeft()) {
            return grandParent.getRight();
        }
        return grandParent.getLeft();
    }
	
	private static class Node<E> {

        public static enum Color {Black,Red};
		
		private final E value;
        private Node<E> parent;
		private Node<E> left;
		private Node<E> right;
		private Color color;

        public Node(E value, Node<E> parent, Node<E> left, Node<E> right) {
            this(value,parent,left,right,Color.Red);
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

        public Node<E> setParent(Node<E> parent) {
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
