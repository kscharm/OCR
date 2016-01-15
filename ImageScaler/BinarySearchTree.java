import java.util.*;

/**
* This BinarySearchTree object defines a reference based binary search tree
* 
* @author Kenny Scharm
* @version 9/30/14
*/
@SuppressWarnings("unchecked")
public class BinarySearchTree<T extends Comparable<T>> implements BSTInterface<T>
{
    protected BinaryNode<T> root;      // reference to the root of this BST

    // Creates an empty Binary Search Tree object
    public BinarySearchTree()
    {
        root= null;
    }

    // Returns true if this BST is empty; otherwise, returns false.
    public boolean isEmpty()
    {
        return root== null;
    }

    // Returns the number of elements in this BST.
    public int size()
    {
        return size(root);
    }

    private int size(BinaryNode<T> tree)
    {
        if (tree== null)   
            return 0;
        else
            return size(tree.getLeft())+ size(tree.getRight()) +1;
    }

    // Adds element to this BST. The tree retains its BST property.
    public void add (T element)
    {
        root= add(element, root);
    }

    private BinaryNode<T> add(T element, BinaryNode<T> tree)
    {
        if (tree== null)
            tree= new BinaryNode<T>(element);
        else if (element.compareTo(tree.getInfo())<= 0)
            tree.setLeft(add(element, tree.getLeft()));
        else    
            tree.setRight(add(element, tree.getRight()));
        return tree;  
    }

    // Returns true if this BST contains an element e such that 
    // e.compareTo(element) == 0; otherwise, returns false.
    public boolean contains (T element)
    {
        return contains(element, root);
    }

    private boolean contains(T element, BinaryNode<T> tree)
    {
        if (tree== null)   
            return false;
        else if (element.compareTo(tree.getInfo())<0)
            return contains(element, tree.getLeft());
        else if (element.compareTo(tree.getInfo())>0)
            return contains(element, tree.getRight());
        else
            return true;
    }

    // Returns a graphical representation of the tree
    public String toString()
    {
        return toString(root, 0);
    }

    private String toString(BinaryNode<T> tree, int level)
    {
        String str = "";
        if (tree != null)
        {
            str += toString(tree.getRight(), level + 1);
            for (int i = 1; i <= level; ++i)
                str = str + "| ";
            str += tree.getInfo().toString() + "\n";
            str += toString(tree.getLeft(), level + 1);
        }
        return str;
    }

    // Returns a list of elements from a preorder traversal
    public List<T> preorderTraverse()
    {
        List list= new ArrayList();
        preorderTraverse(root, list);
        return list;
    }

    private List<T> preorderTraverse(BinaryNode tree, List list)
    {
        if (tree!= null)
        {
            list.add((T)tree.getInfo());
            preorderTraverse(tree.getLeft(), list);
            preorderTraverse(tree.getRight(), list);
        }
        return list;
    }

    // Returns a list of elements from an inorder traversal
    public List<T> inorderTraverse()
    {
        List list= new ArrayList();
        inorderTraverse(root, list);
        return list;
    }

    private List<T> inorderTraverse(BinaryNode<T> tree, List list)
    {
        if (tree!= null)
        {
            inorderTraverse(tree.getLeft(), list);
            list.add((T)tree.getInfo());
            inorderTraverse(tree.getRight(), list);
        }
        return list;
    }

    // Returns a list of elements from a postorder traversal
    public List<T> postorderTraverse()
    {
        List list= new ArrayList();
        postorderTraverse(root, list);
        return list;
    }

    private List<T> postorderTraverse(BinaryNode<T> tree, List list)
    {
        if (tree!= null)
        {
            postorderTraverse(tree.getLeft(), list);
            postorderTraverse(tree.getRight(), list);
            list.add((T)tree.getInfo());
        }
        return list;
    }

    // Removes an element e from this BST such that e.compareTo(element) == 0
    public void remove (T element)
    {
        root= remove(element, root);
    }
    
    private BinaryNode<T> remove(T element, BinaryNode<T> tree)
    {
        if (element.compareTo(tree.getInfo())< 0)
            tree.setLeft(remove(element, tree.getLeft()));
        else if (element.compareTo(tree.getInfo())> 0)
            tree.setRight(remove(element, tree.getRight()));
        else 
            tree= removeNode(tree);
        return tree;    
    }
    
    private BinaryNode<T> removeNode(BinaryNode<T> tree)
    {
        if (tree.getLeft()== null && tree.getRight()== null)
            return null;
        else if (tree.getLeft()== null)
            return tree.getRight();
        else if (tree.getRight()== null)
            return tree.getLeft();
        else
        {
            T predecessor= getPredecessor(tree);
            tree.setInfo(predecessor);
            tree.setLeft(remove(predecessor, tree.getLeft()));
        }
        return tree;
    }
    
    private T getPredecessor(BinaryNode<T> tree)
    {
        while (tree.getRight()!= null)
            tree= tree.getRight();
        return tree.getInfo();    
    }

    // Restructures this BST to be optimally balanced
    public void balance()
    {
        List originalTree= inorderTraverse();
        root= null;
        refillTree(0, originalTree.size()-1, originalTree);
    }
    
    public void refillTree(int low, int high, List list)
    {
        if (low== high)
            add((T)list.get(low));
        else if ((low+1)==high)
        {
            add((T)list.get(low));
            add((T)list.get(high));
        }
        else{
            int mid= (low+high)/2;
            add((T)list.get(mid));
            refillTree(low, mid-1, list);
            refillTree(mid+1, high, list);
        }     
    }
}
