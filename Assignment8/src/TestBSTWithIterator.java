public class TestBSTWithIterator {
  public static void main(String[] args) {
    BST<String> tree = new BST<>();
    
    tree.insert("Atlanta");
    tree.insert("New York");
    tree.insert("San Francisco");
    tree.insert("Dallas");
    tree.insert("Salt Lake City");
    tree.insert("Seattle");
    
    tree.insert("Philadelphia");
    
    for (String s: tree)
    {
      System.out.print(s.toUpperCase() + " ");
    }
  }
}