package application;

public class BST<Salah extends Comparable<Salah>> {
	protected Node<Salah> root;

	public BST() {
	}

	public Node<Salah> getRoot() {
		return root;
	}

	public void insert(Salah data) {
		Node<Salah> add = new Node<Salah>(data);
		if (root == null)
			root = add;
		else {
			Node<Salah> curr = root;
			while (curr != null) {
				if (curr.getData().compareTo(data) > 0)
					if (curr.hasLeft())
						curr = curr.getLeft();
					else {
						curr.setLeft(add);
						break;
					}
				else if (curr.getData().compareTo(data) < 0)
					if (curr.hasRight())
						curr = curr.getRight();
					else {
						curr.setRight(add);
						break;
					}
			}

		}
	}

	public Node<Salah> max() {
		Node<Salah> curr = root;
		while (curr.hasRight())
			curr = curr.getRight();

		return curr;
	}

	public Node<Salah> min() {
		Node<Salah> curr = root;
		while (curr.hasLeft())
			curr = curr.getLeft();

		return curr;
	}

	public String inOrdar() {
		return inOrdar(root);
	}
	

	private String inOrdar(Node<Salah> root) {
		if (root != null) {
			if (root.getLeft() != null && root.getRight() != null)
				return inOrdar(root.getLeft()) + "\n" + root.getData() + "\n" + inOrdar(root.getRight());
			else if (root.getRight() != null)
				return root.getData() + "\n" + inOrdar(root.getRight());
			else if (root.getRight() != null)
				return inOrdar(root.getLeft()) + "\n" + root.getData();
			else
				return root.getData().toString();
		} else
			return "";
	}

	public String toString() {
		return toStringLevels(root);
	}

	private String toStringLevels(Node<Salah> curr) {
		int x = height();
		String soso = "";
		for (int i = 0; i < x; i++) {
			soso += printLevel(root, i, 0) + "\n";
		}
		return soso;
	}

	private String printLevel(Node<Salah> root, int i, int j) {

		if (root != null) {
			if (i == j)
				return root.getData() + " ";
			if (j > i)
				return "NULL";

			return printLevel(root.getLeft(), i, j + 1) + " " + printLevel(root.getRight(), i, j + 1);
		} else
			return "NULL";

	}

	public Node<Salah> find(Salah data) {
		return find(data, root);
	}

	public Node<Salah> find(Salah data, Node<Salah> node) {
		if (node != null) {
			int comp = node.getData().compareTo(data);
			if (comp == 0)
				return node;
			else if (comp > 0 && node.hasLeft())
				return find(data, node.getLeft());
			else if (comp < 0 && node.hasRight())
				return find(data, node.getRight());
		}
		return null;
	}

	public int height() {
		return hieght(root);
	}

	private int hieght(Node<Salah> curr) {
		if (curr == null)
			return 0;
		if (curr.isLeaf())
			return 1;
		else
			return Math.max(1 + hieght(curr.getLeft()), 1 + hieght(curr.getRight()));
	}

	public Node<Salah> delete(Salah data) {
		if (root == null)
			return null;

		Node<Salah> dad = root;
		Node<Salah> curr = root;
		boolean isLeft = false;
		int comp = curr.getData().compareTo(data);
		while (curr != null) {
			comp = curr.getData().compareTo(data);
			if (comp == 0)
				break;
			dad = curr;
			if (comp > 0) {
				isLeft = true;
				curr = curr.getLeft();
			} else {
				isLeft = false;
				curr = curr.getRight();
			}
		}

		if (comp != 0)
			return null;
		else {
			if (curr.isLeaf()) {
				if (curr == root)
					root = null;
				else if (isLeft)
					dad.setLeft(null);
				else
					dad.setRight(null);
			} else if (curr.hasLeft() && !curr.hasRight()) {

				if (root == curr)
					root = curr.getLeft();
				else if (isLeft)
					dad.setLeft(curr.getLeft());
				else
					dad.setRight(curr.getLeft());
			} else if (curr.hasRight() && !curr.hasLeft()) {

				if (curr == root)
					root = curr.getRight();
				else if (isLeft)
					dad.setLeft(curr.getRight());
				else
					dad.setRight(curr.getRight());
			} else {
				Node<Salah> successor = successor(curr);
				if (curr == root)
					root = successor;
				else if (isLeft)
					dad.setLeft(successor);
				else
					dad.setRight(successor);
				successor.setLeft(curr.getLeft());
			}
			return curr;
		}
	}

	private Node<Salah> successor(Node<Salah> node) {
		Node<Salah> parent = node;
		Node<Salah> successor = node.getRight();
		while (successor.hasLeft()) {
			parent = successor;
			successor = successor.getLeft();
		}
		if (successor.getData().compareTo(node.getRight().getData()) != 0) {
			parent.setLeft(successor.getRight());
			successor.setRight(node.getRight());
		}
		return successor;
	}
}
