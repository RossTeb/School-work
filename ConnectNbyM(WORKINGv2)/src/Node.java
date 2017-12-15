import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by Mean Machine on 3/7/2016.
 */
public class Node {

    Node parent = null;
    ArrayList<Node> children = new ArrayList<Node>();
    int alpha = 0;
    int beta= 0;
    int tier;
    Board board;
    boolean player;
    BigInteger pBoard= new BigInteger("0");
    Node Chosen= null;
    int value;
    public Node getChosen() {
        return Chosen;
    }

    public void setChosen(Node chosen) {
        Chosen = chosen;
    }





    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }




    public int getEvaluation() {
        return evaluation;
    }

    int evaluation;

    public int getTier() {
        return tier;
    }
    public boolean getPlayer() {
        return player;
    }

    public Board getBoard() {
        return board;
    }

    public BigInteger getPBoard() {
        return pBoard;
    }

    public Node() {}

    public void evaluate(boolean turn)
    {
            evaluation = board.evaluate(turn);
    }

    public Node(Node parent, int tier, Board current,boolean player)
    {
        this.parent = parent;
        this.tier = tier;
        board = current;
        this.player = player;
        if(player == true)
        {
            pBoard = current.getP1();
        }
        else if(player == false)
        {
            pBoard = current.getP2();
        }
        else
        {
            System.out.println("something went wrong");
        }
    }
    public void move(int col)
    {

        board.move(player,col);
    }

    public Node getParent() {
        return parent;
    }

    public boolean hasNextChild()
    {
        if(this.getParent() == null)
        {
            return false;
        }
        if(this.getParent() != null) {
            ArrayList<Node> temp = this.getParent().getChildren();
            for (int i = 0; i < temp.size(); i++) {
                if (temp.get(i) == this) {
                    if (i == temp.size() - 1)
                        return false;
                }
            }
        }
        return true;
    }

    public boolean hasChild()
    {
        if(children.size()>0)
        {
            return true;
        }
        return false;
    }

    public Node nextChild()
    {
        if(this.getParent()  != null) {
            ArrayList<Node> temp = this.getParent().getChildren();
            for (int i = 0; i < temp.size() - 1; i++) {
                if (temp.get(i) == this) {
                    return temp.get(i + 1);
                }
            }
        }
        return this;
    }

    public void setParent(Node parent)
    {
        this.parent = parent;
    }

    public Node getChild(int child)
    {
        return this.children.get(child);
    }

    public ArrayList<Node> getChildren()
    {
        return this.children;
    }

    public void addChild(Node child)
    {
        children.add(child);
    }

    public void addChild(Node child,int index)
    {
        children.add(index,child);
    }

}
