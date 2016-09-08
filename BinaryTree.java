
public class BinaryTree {

	Node root;

	public static void main(String[] args) {

		// BC Test Cases

		BinaryTree theTree = new BinaryTree();
		theTree.addNode("Bob", "Smith", "bsmith@somewhere.com", "555-235-1111");
		theTree.addNode("Jane", "Williams", "jw@something.com", "555-235-1112");
		theTree.addNode("Mohammed", "al-Salam", "mas@someplace.com", "555-235-1113");
		theTree.addNode("Pat", "Jones", "pjones@homesweethome.com", "555-235-1114");
		theTree.addNode("Billy", "Kidd", "billy_the_kid@nowhere.com", "555-235-1115");
		theTree.addNode("H.", "Houdini", "houdini@noplace.com", "555-235-1116");
		theTree.addNode("Jack", "Jones", "jjones@hill.com", "555-235-1117");
		theTree.addNode("Jill", "Jones", "jillj@hill.com", "555-235-1118");
		theTree.addNode("John", "Doe", "jdoe@somedomain.com", "555-235-1119");
		theTree.addNode("Jane", "Doe", "jdoe@somedomain.com", "555-235-1120");
		theTree.findNode("Pat", "Jones");
		theTree.findNode("Billy", "Kidd");
		theTree.remove("John", "Doe");
		theTree.addNode("Test", "Case", "Test_Case@testcase.com", "555-235-1121");
		theTree.addNode("Nadezhda", "Kanachekhovskaya", "dr.nadezhda.kanacheckovskaya@somehospital.moscow.ci.ru", "555-235-1122");
		theTree.addNode("Jo", "Wu", "wu@h.com", "555-235-1123");
		theTree.addNode("Millard", "Fillmore", "millard@theactualwhitehouse.us", "555-235-1124");
		theTree.addNode("Bob", "vanDyke", "vandyke@nodomain.com", "555-235-1125");
		theTree.addNode("Upside", "Down", "upsidedown@rightsideup.com", "555-235-1126");
		theTree.findNode("Jack", "Jones");
		theTree.findNode("Nadezhda", "Kanachekhovskaya");
		theTree.remove("Jill", "Jones");
		theTree.remove("John", "Doe");
		theTree.findNode("Jill", "Jones");
		theTree.findNode("John", "Doe");

	}

	public void addNode(String firstName, String lastName, String eMail, String phone) {

		// BC Create a new Node and initialize it using concatenated first and last name.

		String fullName = new String(firstName + " " + lastName);
		fullName = fullName.toUpperCase();
		Node newNode = new Node(fullName, eMail, phone);

		System.out.println("Address entry created for " + fullName); // BC Print that the entry has been created (even though it has not happened yet)
		if (root == null) {

			root = newNode;

		} else {

			Node focusNode = root;
			Node parent;

			while (true) {

				parent = focusNode;

				// BC Check to see what side the node should go and place on correct side.

				if (fullName.compareTo(focusNode.fullName) < 0) {

					focusNode = focusNode.lChild;

					if (focusNode == null) {

						parent.lChild = newNode;
						return;
					}

				} else {

					focusNode = focusNode.rChild;

					if (focusNode == null) {

						parent.rChild = newNode;
						return;

					}

				}

			}
		}

	}

	public Node findNode(String firstName, String lastName) {

		// BC Search for node using concatenated name.

		String fullName = new String(firstName + " " + lastName);
		fullName = fullName.toUpperCase();

		Node focusNode = root;

		// BC Start the search

		while (focusNode.fullName.compareTo(fullName) != 0) {

			if (fullName.compareTo(focusNode.fullName) < 0) {

				focusNode = focusNode.lChild;

			} else {

				focusNode = focusNode.rChild;

			}

			// BC The node was not found
			if (focusNode == null) {
				System.out.println("Name not found: " + fullName); // BC Print if node is not found
				return null;
			}

		}
		System.out.println(focusNode); // BC Print node that is found
		return focusNode;

	}

	public boolean remove(String firstName, String lastName) {

		// BC Search for node using concatenated name.

		String fullName = new String(firstName + " " + lastName);
		fullName = fullName.toUpperCase();

		Node focusNode = root;
		Node parent = root;
		boolean isLeftChild = true;

		// BC Start the search for node to remove.

		while (focusNode.fullName.compareTo(fullName) != 0) {

			parent = focusNode;

			if (fullName.compareTo(focusNode.fullName) < 0) {

				isLeftChild = true;
				focusNode = focusNode.lChild;

			} else {

				isLeftChild = false;
				focusNode = focusNode.rChild;

			}

			// BC Return if Node not found.

			if (focusNode == null){
				System.out.println("Nothing deleted because name not found: " + fullName); // BC Print when not found.
				return false;
			}

		}

		// BC Delete if no children

		if (focusNode.lChild == null && focusNode.rChild == null) {

			if (focusNode == root)
				root = null;

			else if (isLeftChild)
				parent.lChild = null;

			else
				parent.rChild = null;

		}

		// BC If no right child

		else if (focusNode.rChild == null) {

			if (focusNode == root)
				root = focusNode.lChild;

			else if (isLeftChild)
				parent.lChild = focusNode.lChild;

			else
				parent.rChild = focusNode.lChild;

		}

		// BC If no left child

		else if (focusNode.lChild == null) {

			if (focusNode == root)
				root = focusNode.rChild;

			else if (isLeftChild)
				parent.lChild = focusNode.rChild;

			else
				parent.rChild = focusNode.rChild;

		}

		// BC Two children so replacement needed

		else {

			Node replacement = getReplacementNode(focusNode);

			if (focusNode == root)
				root = replacement;

			else if (isLeftChild)
				parent.lChild = replacement;

			else
				parent.rChild = replacement;

			replacement.lChild = focusNode.lChild;

		}
		System.out.println("Address entry deleted for " + fullName); // BC Print that the entry has been deleted
		return true;

	}

	public Node getReplacementNode(Node replacedNode) {

		// BC Find replacement node

		Node replacementParent = replacedNode;
		Node replacement = replacedNode;

		Node focusNode = replacedNode.rChild;

		while (focusNode != null) {

			replacementParent = replacement;

			replacement = focusNode;

			focusNode = focusNode.lChild;

		}

		if (replacement != replacedNode.rChild) {

			replacementParent.lChild = replacement.rChild;
			replacement.rChild = replacedNode.rChild;

		}

		return replacement;

	}

	class Node {

		// BC Define node

		String fullName;
		String eMail;
		String phone;

		Node lChild;
		Node rChild;

		Node(String fullName, String eMail, String phone) {
			this.fullName = fullName;
			this.eMail = eMail;
			this.phone = phone;

		}

		public String toString() {

			return "name: " + fullName + ", e-mail:  " + eMail + ", phone: " + phone;

		}

	}
}