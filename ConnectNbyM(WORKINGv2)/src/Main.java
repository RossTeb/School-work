import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by Mean Machine on 3/2/2016.
 */
public class Main {
    public static void main(String args[])
    {
        int ok = 0;
        if(ok == 0)
        {
            int m = 6;
            int n =7;
            int r = 4;
            int turn = 1;
            //boolean turn = false;
            int moves=0;
            Scanner input = new Scanner(System.in);
           /* System.out.println("input the number of rows");
            m = input.nextInt();
            System.out.println("input the number of Columns");
            n = input.nextInt();
            System.out.println("input the number to connect");
            r = input.nextInt();

            System.out.println("who goes first you or the computer? 1:computer/0:you");
            int turn = input.nextInt();*/
            Board board = new Board(m,n,r,BigInteger.ZERO,BigInteger.ZERO);
            System.out.println("turn: " + turn);
            while(!board.isWin(true)&&!board.isWin(false))
            {
                if (turn==1) {
                    if(moves==0)
                    {
                        board = board.move(true,((n%2)+2));
                        turn =0;
                        board.printPlayer(true);
                        board.printPlayer(false);
                        moves++;
                        continue;
                    }
                    else
                    {
                        Tree tree = new Tree(board,false,2);
                        board.setP1(tree.getRoot().getChosen().getBoard().getP1());
                        board.printPlayer(true);
                        board.printPlayer(false);
                        turn = 0;
                        moves++;
                        continue;
                    }
                } else {
                    boolean go = true;
                    int col=-20;
                    while (go)
                    {
                        System.out.println("pick a column to play in: 0-" + (n - 1));
                        col = input.nextInt();
                        if (col >= 0 && col < n) {
                            if(board.validMove(false,col)) {
                                board = board.move(false, col);
                                go = false;
                            }

                        } else {
                            System.out.println("invalid move please try again");
                            continue;
                        }
                    }

                    moves++;
                    board.printPlayer(true);
                    board.printPlayer(false);
                    turn = 1;
                    continue;
                }
            }
            if(board.isWin(true))
            {
                System.out.println("You Lose!");
            }
            else if(board.isWin(false))
            {
                System.out.println("You Win!");
            }
            else
            {
                System.out.println("Something has gone horribly wrong!");
            }



          //  board = board.move(false,2);
          //  board = board.move(true,5);
          //  board = board.move(false,4);
           // board = board.move(true,3);

          //  board = board.move(false,5);
          //  board = board.move(true,4);
          //  board = board.move(false,3);

           // board = board.move(false,5);
           // board = board.move(true,4);
          //  board = board.move(false,3);



           // board = board.move(true,2);
           // board = board.move(true,5);
           // board = board.move(true,4);
           // board = board.move(true,3);

           // board = board.move(true,2);
          //  board = board.move(true,1);
           // board = board.move(true,3);
           // board = board.move(true,6);

          //  board = board.move(false,4);
           // board = board.move(false,4);
           // board = board.move(false,6);
           // board = board.move(false,2);
           // board = board.move(true,5);
            //board = board.move(false,5);
            //board = board.move(false,4);

            // Tree tree = new Tree(board,false);
           // board.printPlayer(false);
           // board.printPlayer(true);
           // System.out.println("evaluation: "+board.evaluate(false));
          //  System.out.println(board.isWin(true));
          //  System.out.println(board.nearestWin(true));
           // System.out.println(board.isDouble(true));
           // System.out.println(board.movesFromDouble(true));
            //System.out.println(board.getP1().toString(2));
            //board.printPlayer(false);

        }
        if(ok == 1) {
            Board board = new Board();

            board = board.move(true,2);
              board = board.move(false,2);
              board = board.move(false,2);
              board = board.move(false,2);
              board = board.move(false,2);

            //  board = board.move(false,5);
            //  board = board.move(true,4);
            //  board = board.move(false,3);

            // board = board.move(false,5);
            // board = board.move(true,4);
            //  board = board.move(false,3);



            //
            // board = board.move(true,5);
            // board = board.move(true,4);
            // board = board.move(true,3);

            // board = board.move(true,2);
            //  board = board.move(true,1);
            // board = board.move(true,3);
            // board = board.move(true,6);

            //  board = board.move(false,4);
            // board = board.move(false,4);
            // board = board.move(false,6);
            // board = board.move(false,2);
            // board = board.move(true,5);
            //board = board.move(false,5);
            //board = board.move(false,4);

            // Tree tree = new Tree(board,false);
             board.printPlayer(false);
             board.printPlayer(true);
            // System.out.println("evaluation: "+board.evaluate(false));
              System.out.println(board.isWin(false));
            //  System.out.println(board.nearestWin(true));
            // System.out.println(board.isDouble(true));
            // System.out.println(board.movesFromDouble(true));
            //System.out.println(board.getP1().toString(2));
            //board.printPlayer(false);


        }
    }
}