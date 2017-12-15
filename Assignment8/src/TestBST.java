public class TestBST {
  public static void main(String[] args) {
    // Create a BST
    BST<String> tree = new BST<>();
    tree.insert("Apple");//1
    tree.insert("Pineapple");//2
    tree.insert("Banana");//3
    tree.insert("Peach");//4
    tree.insert("Cherry");//5
    tree.insert("Orange");//6
    tree.insert("Plum");//7

    // Traverse tree
    System.out.print("Inorder (sorted): ");
    tree.inorder();
    System.out.print("\nPostorder: ");
    tree.postorder();
    System.out.print("\nPreorder: ");
    tree.preorder();
    System.out.print("\nThe number of nodes is " + tree.getSize());

    // Search for an element
    System.out.print("\nIs Orange in the tree? " + tree.search("Orange")); //6

    // Get a path from the root to Peter
    System.out.print("\nA path from the root to Orange is: "); //6
    java.util.ArrayList<BST.TreeNode<String>> path 
      = tree.path("Orange");//6
    for (int i = 0; path != null && i < path.size(); i++)
      System.out.print(path.get(i).element + " ");

    Integer[] numbers = {};
    BST<Integer> intTree = new BST<>(numbers); 
    System.out.print("\nInorder (sorted): ");
    intTree.inorder();
    System.out.println();
    System.out.println("The height is " + intTree.height() + ".");
    System.out.println("The number of leaves is  " + intTree.getNumberOfNonLeaves() + ".");
    intTree.breadthFirstTraversal();
  }
}