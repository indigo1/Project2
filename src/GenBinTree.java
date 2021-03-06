public class GenBinTree
{
	Node root = new Node(null); // Initialize root

	public class Node
	{
		Integer data;
		Node left, right;

		Node(Integer d)
		{
			this.data = d;
			this.left = null;
			this.right = null;
		}
	}

	public void add(String d) // Creates root.
	{
		root.data = Integer.parseInt(d);
	}

	public void add(int d) // Creates root.
	{
		root.data = d;
	}

	public boolean add(String lr, String m)
	{
		Node temp, newNode;
		Integer d = Integer.parseInt(m);

		if (find(d, root) == true) // Check for duplicates.
			return false;
		else
		{
			// Check if first parameter is string of only L and R.
			char arr[] = lr.toCharArray();
			for (char c : arr)
				if (c != 'L' && c != 'R')
					return false;
			// Traverse to node to be added.
			temp = root;
			newNode = new Node(d);
			
			for (char k : arr)
			{
				if (k == 'L' && temp.left == null)
				{
					temp.left = newNode;
					break;
				}
				if (k == 'L')
					temp = temp.left;
				if (k == 'R' && temp.right == null)
				{
					temp.right = newNode;
					break;
				}
				if (k == 'R')
					temp = temp.right;
			}
		}
		return true;
	}

	public boolean add(String lr, Integer d)
	{
		Node temp, newNode;

		if (find(d, root) == true) // Check for duplicates.
			return false;
		else
		{
			// Check if first parameter is string of only L and R.
			char arr[] = lr.toCharArray();
			for (char c : arr)
				if (c != 'L' && c != 'R')
					return false;
			// Traverse to node to be added.
			temp = root;
			newNode = new Node(d);
			
			for (char k : arr)
			{
				if (k == 'L' && temp.left == null)
				{
					temp.left = newNode;
					break;
				}
				
				if (k == 'L')
					temp = temp.left;
				
				if (k == 'R' && temp.right == null)
				{
					temp.right = newNode;
					break;
				}
				
				if (k == 'R')
					temp = temp.right;
			}
		}
		return true;
	}

	public boolean find(int val, Node cur)
	{
		boolean result = false;

		if (cur.left != null)
			result = find(val, cur.left);

		if (cur.data.intValue() == val)
			result = true;

		if (result == false && cur.right != null)
			result = find(val, cur.right);

		return result;
	}

	public boolean find(int val)
	{
		return find(val, root);
	}


	public boolean hasChild(Node n)
	{
		if (n.left != null && n.right != null)
			return false;
		else
			return true;
	}

	public void remove(Integer d, Node n)
	{
		if (n == null)
			n = root;
		if (n.left.data == d && hasChild(n.left) == false)
			n.left = null;
		else if (n.right.data == d && hasChild(n.right) == false)
			n.right = null;
		else if (hasChild(n.left) == true)
			remove(d, n.left);
		else if (hasChild(n.right) == true)
			remove(d, n.right);
	}

	public Node locate(Integer d, Node x)
	{
		// Start at x and return node with matching data d.
		if (x.data == d)
			return x;
		else
		{
			locate(d, x.left);
			locate(d, x.right);
		}
		return null;
	}

	@SuppressWarnings("null")
	public void swap(Integer d)
	{
		Node p, temp = null;

		// Select parent of nodes to be swapped.
		p = locate(d, root);

		temp.left = p.left;
		p.left = p.right;
		p.right = temp.left;
	}

	public void mirror()
	{
		mirrorTree(root);
	}
	
	public void mirrorTree(Node r)
	{
		
		if (r != null)
		{
			Node temp = r.left;
			r.left = r.right;
			r.right = temp;
			mirrorTree(r.right);
			mirrorTree(r.left);
		}
		return;
	}

	public Node locateR(Integer d, Node x)
	{
		// Start at x and return node with matching data d.
		if (x.right.data == d)
			return x;
		else
		{
			locate(d, x.left);
			locate(d, x.right);
		}
		return null;
	}

	public Node locateL(Integer d, Node x)
	{
		// Start at x and return node with matching data d.
		if (x.left.data == d)
			return x;
		else
		{
			locate(d, x.left);
			locate(d, x.right);
		}
		return null;
	}

	public void rotateRight(Integer data)
	{
		Node p = locateR(data, root);
		Node x = locate(data, root);
		Node y = x.left;

		p.right = y;
		x.left = y.right;
		y.right = x;
	}

	public void rotateLeft(Integer data)
	{
		Node p = locateL(data, root);
		Node x = locate(data, root);
		Node y = x.right;

		p.left = y;
		x.right = y.left;
		y.left = x;
	}

	public int countRecur(Node x)
	{
		Node right = x.right;
		Node left = x.left;
		int c = 1;

		if (right != null)
			c += countRecur(right);
		if (left != null)
			c += countRecur(left);
		return c;
	}
	
	public int countNode()
	{
		return countRecur(root);
	}

	public void printRecur(Node x)
	{
		if (x != null)
		{
			printRecur(x.left);
			System.out.println(x.data);
			printRecur(x.right);
		}
	}
	
	public void print()
	{
		printRecur(root);
	}

	public static void main(String args[])
	{
		GenBinTree myTree = new GenBinTree();
		
		//Make Tree
		myTree.add(100);
		myTree.add("L", "50");
		myTree.add("R", 150);
		myTree.add("LL", 40);
		myTree.add("LLR", 45);
		
		//Find test
		System.out.println(myTree.find(50));
		
		//Count nodes
		System.out.println(myTree.countNode());
		
		//print tree
		myTree.print();
		
		//swap test
		myTree.swap(100);
		myTree.print();
		
//		//Mirror test
//		myTree.mirror();
//		myTree.print();
		
		
	}
}