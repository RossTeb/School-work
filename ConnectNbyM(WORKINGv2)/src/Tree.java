import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Mean Machine on 3/7/2016.
 */
public class Tree {

    String xmlFile = "";
    Node root = new Node();

    public Node getRoot() {
        return root;
    }

    public Tree(Board current,boolean player,int depth) {
        createTree(current,player,depth);
    }
    public String breadthFirst(String behavior)
    {
        Node current = root;
        boolean found = false;
        ArrayList<Node> responses = new ArrayList<Node>();
        LinkedList<Node> queue = new LinkedList<Node>();
        Node foundBehavior = new Node();
        boolean stop = false;
        while (found == false)
        {
            if(current.toString().equals(behavior))
            {
                queue.clear();
                found = true;
                foundBehavior=current;
                break;
            }
            if(!current.hasNextChild())
            {
                if(current.getParent() != null)
                {
                    if (current.hasChild())
                    {
                        queue.addLast(current);
                        current = queue.remove().getChild(0);
                        continue;
                    }
                    else if (!queue.isEmpty())
                    {
                        current = queue.remove().getChild(0);
                        continue;
                    }
                    else
                    {
                        return "";
                    }
                }
                else if(current == root)
                {
                    current = current.getChild(0);
                    continue;
                }
                else
                {
                    current = queue.remove().getChild(0);
                    continue;
                }
            }
            else
            {
                if(current.hasChild())
                {
                    queue.add(current);
                }
                current = current.nextChild();
            }



        }
        //node has been found in list now we need to find the responses

        while(stop == false)
        {
            if(current == foundBehavior)
            {
                if(current.hasChild()) {
                    current = current.getChild(0);
                    continue;
                }
                else {
                    return "No Responses to Node " + current+". "+current+" is a response to the behavior " + current.getParent();
                }
            }
            if(!current.hasNextChild()) {

                if (current.getParent() != null) {
                    if (current.hasChild()) {
                        queue.addLast(current);
                        current = queue.remove().getChild(0);
                        continue;
                    } else if (!queue.isEmpty()) {
                        responses.add(current);
                        current = queue.remove().getChild(0);
                        continue;
                    } else {
                        responses.add(current);
                        stop = true;
                        continue;
                    }
                } else if (current == root) {
                    current = current.getChild(0);
                    continue;
                } else {
                    current = queue.remove().getChild(0);
                    continue;
                }
            }
            else
            {
                if(current.hasChild())
                {
                    queue.add(current);
                }
                else
                {
                    responses.add(current);
                }
                current = current.nextChild();
            }
        }
        int x = (int)(Math.random()*responses.size());
        return responses.get(x).toString();

    }


    public String depthFirst(String behavior) {
        Node current = root;
        boolean found = false;
        ArrayList<Node> responses = new ArrayList<Node>();
        Node foundBehavior = new Node();
        boolean stop = false;
        while(found == false)
        {
            if(current.toString().equals(behavior))
            {
                found = true;
                foundBehavior=current;
                break;
            }
            if(current.hasChild())
            {
                current = current.getChild(0);
                continue;
            }
            else if(current.hasNextChild())
            {
                current = current.nextChild();
                continue;
            }
            else if (current.getParent().hasNextChild())
            {
                current =  current.getParent().nextChild();
                continue;
            }
            else
            {
                while(current.getParent().hasNextChild() == false)
                {
                    if(current.getParent() == root)
                    {
                        return"";
                    }
                    else
                    {
                        current = current.getParent();
                    }

                }
                current = current.getParent().nextChild();
                continue;
            }
        }
        if(foundBehavior.hasChild()==false)
        {
            return "No Responses to Node " + current+". "+current+" is a response to the behavior " + current.getParent();
        }

        //node has been found in list now we need to find the responses
        while(stop == false)
        {
            while (current.hasChild()) {
                current = current.getChild(0);
            }
            while (current.hasNextChild()) {
                responses.add(current);
                if (current.hasChild()) {
                    current = current.getChild(0);
                    continue;
                }
                current= current.nextChild();
            }
            if(current.hasChild())
            {
                continue;
            }
            responses.add(current);
            if(current.getParent() == foundBehavior)
            {
                break;
            }
            if(current.getParent().hasNextChild())
            {
                current = current.getParent().nextChild();
            }
            else
            {
                while(current.getParent().hasNextChild()== false)
                {
                    if(current.getParent() == foundBehavior)
                    {
                        stop = true;
                        break;
                    }
                    current = current.getParent();
                }
                current = current.getParent().nextChild();
                if(current == foundBehavior)
                {
                    break;
                }
                continue;
            }
        }
        int x = (int)(Math.random()*responses.size());
        return responses.get(x).toString();
    }











































    private Node createTree(Board boardCurrent,boolean turn,int depth)
    {

        root = new Node(null,0,boardCurrent.copy(),turn);
        Node current = root;
        LinkedList<Node> queue = new LinkedList<Node>();

        System.out.println("root");
        System.out.println("tier: " + current.getTier());
        current.getBoard().printPlayer(turn);



        while (current.getTier() <depth) {
                int curTier = current.getTier();

                //else {
                    int invalidMoves = 0;
                    for (int i = 0; i < boardCurrent.getN(); i++)
                    {

                        if(current.getBoard().validMove(!turn,i))
                        {
                            current.addChild(new Node(current, current.getTier() + 1, current.getBoard().move(!turn,i), !current.getPlayer()));
                           //current.getChild(i).evaluate(!turn);
                        }
                       else
                        {
                            System.out.println("invalid Move");

                            invalidMoves++;
                            continue;
                        }
                        //System.out.println("move: " + (i));
                        //System.out.println("tier: " + (current.getChild(i-invalidMoves).getTier()));
                       // current.getChild(i).getBoard().move(!turn, i);
                        current.getChild(i-invalidMoves).getBoard().printPlayer(!turn);

                        queue.addLast(current.getChild(i-invalidMoves));
                       // queue.addLast(current);
                    }

                    /*if(current.hasNextChild())
                    {
                        current = queue.remove();
                    }*/
                    if(current.getTier()!=queue.peek().getTier())
                    {
                        turn = !turn;

                            current = queue.remove();

                    }
                    else
                    {

                            current = queue.remove();

                    }



        }
        System.out.println("Q size: "+queue.size());
        LinkedList<Node> tempQ = new LinkedList<Node>();
        current.evaluate(turn);
        tempQ.addFirst(current);
        if(current.getTier()%2==0)
        {
            current.evaluate(!turn);
//
            while (!queue.isEmpty())
            {

                    //System.out.println("tier: " + (current.getTier()));
                    current = queue.remove();
                    current.evaluate(!turn);
                    tempQ.add(current);
                    //current.getBoard().printPlayer(true);
                    System.out.println("turn : " + !turn);
                    System.out.println("evaluation: " + current.getBoard().evaluate(!turn));
                    // current.getBoard().printPlayer(false);

                    current.setValue(current.getEvaluation());

            }
        }
        else
        {
            current.evaluate(!turn);
            while (!queue.isEmpty())
            {

                //System.out.println("tier: " + (current.getTier()));
                current = queue.remove();
                current.evaluate(!turn);

                tempQ.add(current);
                //current.getBoard().printPlayer(true);
                System.out.println("turn : " + turn);
                System.out.println("evaluation: " + current.getBoard().evaluate(turn));
                // current.getBoard().printPlayer(false);

                current.setValue(current.getEvaluation());

            }
        }

        int min =99;
        int max = -99;
        Node kid = null;
        tempQ.peek().setValue(tempQ.peek().getEvaluation());
        while(!tempQ.isEmpty())
        {

            current = tempQ.remove();
            if(current == root)
            {
                break;
            }
            if((current.getTier()%2)!= 0)
            {
                //current.setValue(current.getEvaluation());

                if(current.getValue()<=min)
                {
                   // current.evaluate(turn);
                    min = current.getValue();
                    System.out.println("min" +min);
                    if(min==current.getValue())
                    {
                        int x = (int)Math.random()*2;
                        if (x==1)
                        {
                            current.getParent().setValue(min);
                            //current.getParent().setChosen(current);
                        }
                    }
                    else {
                        current.getParent().setValue(min);
                        //current.getParent().setChosen(current);
                    }


                    //kid = current;
                    if(current.getTier()==depth&&current.hasNextChild())
                    {
                        tempQ.peek().setValue(tempQ.peek().getEvaluation());
                    }
                }
                if(current.hasNextChild())
                {
                    continue;
                }
                else
                {
                    if (current.getParent() != null)
                    {
                        tempQ.addLast(current.getParent());
                        current.getParent().setValue(min);
                    }
                    if (current.getTier() != tempQ.peek().getTier())
                    {
                        min = 99;

                        max = -99;
                        kid = null;
                    }
                }
                continue;


            }
            else
            {
                //current.setValue(current.getEvaluation());
                if(current.getValue()>max)
                {
                    // current.evaluate(turn);
                    max = current.getValue();
                    System.out.println("max" +max);

                    if(min==current.getValue())
                    {
                        int x = (int)Math.random()*2;
                        if (x==1)
                        {
                            current.getParent().setValue(max);
                         //   current.getParent().setChosen(current);
                        }
                    }
                    else {
                        current.getParent().setValue(max);
                        //current.getParent().setChosen(current);
                    }

                    if(current.getTier()==depth&&current.hasNextChild())
                    {
                        tempQ.peek().setValue(tempQ.peek().getEvaluation());
                    }
                }
                if(current.hasNextChild())
                {
                    continue;
                }
                else
                {
                     if(current.getParent()!= null)
                    {
                       tempQ.addLast(current.getParent());
                        current.getParent().setValue(max);
                       // current.get
                    }
                   if(current.getTier()!= tempQ.peek().getTier())
                    {
                        min =99;
                        max = -99;
                       kid = null;
                    }
                }continue;
            }
        }

        if(current == root)
        {
            int Max = current.getChild(0).getValue();
            current.setChosen(current.getChild(0));
            for(int i =0;i<current.getChildren().size();i++)
            {
                if(current.getChild(i).getValue()>Max)
                {
                    Max = current.getChild(i).getValue();
                    current.setChosen(current.getChild(i));
                }
                else if(current.getChild(i).getValue()==Max)
                {
                    int x = (int) Math.random() * 2;
                    if (x == 1) {
                        //current.getParent().setValue(Max);
                        current.setChosen(current.getChild(i));
                        //   current.getParent().setChosen(current);
                    }
                }
            }
        }


        System.out.println("End");
        return root;
    }
}
