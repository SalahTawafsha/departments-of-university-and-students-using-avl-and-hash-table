package application;

public class Node<Salah extends Comparable<Salah>> {
	private Salah data;
	private Node<Salah> left;
	private Node<Salah> right;

	public Node(Salah data) {
		this.data = data;
	}

	public void setData(Salah data) {
		this.data = data;
	}

	public Node<Salah> getLeft() {
		return left;
	}

	public void setLeft(Node<Salah> left) {
		this.left = left;
	}

	public Node<Salah> getRight() {
		return right;
	}

	public boolean isLeaf() {
		return !hasLeft() && !hasRight();
	}

	public boolean hasLeft() {
		return left != null;
	}

	public boolean hasRight() {
		return right != null;
	}

	public void setRight(Node<Salah> right) {
		this.right = right;
	}

	public Salah getData() {
		return data;
	}

	@Override
	public String toString() {
		return data + "";
	}
}
