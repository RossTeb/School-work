public class TestBSTDelete {
  public static void main(String[] args) {
    BST<String> tree = new BST<>();
    tree.insert("Blue");
    tree.insert("Orange");
    tree.insert("Yellow");
    tree.insert("Green");
    tree.insert("Red");
    tree.insert("Purple");
    tree.insert("White");
    printTree(tree);

    System.out.println("\nAfter delete Blue:");
    tree.delete("Blue");
    printTree(tree);
    System.out.println("\nAfter delete Green:");
    tree.delete("Green");
    printTree(tree);
    System.out.println("\nAfter delete Orange:");
    tree.delete("Orange");
    printTree(tree);
  }

  public static void printTree(BST tree) 
  {
    System.out.print("Inorder (sorted): ");
    tree.inorder();
    System.out.print("\nPostorder: ");
    tree.postorder();
    System.out.print("\nPreorder: ");
    tree.preorder();
    System.out.print("\nThe number of nodes is " + tree.getSize());
    System.out.println();
  }
}