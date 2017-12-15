public interface Tree<E> extends Iterable<E> 
{
  //Return true if the element is in the tree 
  public boolean search(E e);

  public void inorder();

  public void preorder();

  public void breadthFirstTraversal();
  
  // Return true if the element is inserted successfully
  public boolean insert(E e);

  // Return true if the element is deleted successfully 
  public boolean delete(E e);
  
  public int height();

  public int getSize();

  public boolean isEmpty();

  public void postorder();
 
  public int getNumberOfNonLeaves();
}