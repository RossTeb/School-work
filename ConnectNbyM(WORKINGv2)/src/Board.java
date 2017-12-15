import java.math.BigInteger;

/**
 * Created by Mean Machine on 3/7/2016.
 */
public class Board {
    int m = 6;//rows
    int n = 7;//columns
    int r = 4;//winning
    int[] board;
    BigInteger P2 = new BigInteger("0");
    BigInteger P1 = new BigInteger("0");

    public int getR() {
        return r;
    }

    public void setP2(BigInteger p2) {
        P2 = p2;
    }
    public void setP1(BigInteger p1) {
        P1 = p1;
    }

    public BigInteger getP2() {
        return P2;
    }

    public BigInteger getP1() {
        return P1;
    }

    public int[] getBoard() {
        return board;
    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    public Board copy()
    {
        Board temp = new Board(m,n,r,P1,P2);
        return temp;
    }


    Board()
    {
        board = new int[m*n];
    }

    Board(int m, int n,int r, BigInteger Play1,BigInteger Play2)
    {
        this.P1 = Play1;
        this.P2 = Play2;
        this.m = m;
        this.n = n;
        this.r = r;
        P1.shiftLeft((m * n) + 1);
        P2.shiftLeft((m * n) + 1);
        board = new int[m*n];
    }

    Board(int m, int n)
    {
        this.m = m;
        this.n = n;
        P1.shiftLeft((m * n) + 1);
        P2.shiftLeft((m * n) + 1);
        board = new int[m*n];
    }


    public int evaluate(boolean turn)
    {
        int evaluation=0;

        //for player 1
        if(turn)
        {

            if(isWin(P1))
            {
                if(isWin(P2))
                {
                    return -98;
                }
                else
                {
                    return 99;
                }
            }
            else if(isWin(P2))
            {
                return -98;
            }
            else if(isDouble(true))
            {
                evaluation += ((m-1)-movesFromDouble(true))+r*2+4;
                System.out.println("current value1 :" +evaluation);
            }
            if(nearestWin(true)>r)
            {
                evaluation += wins1;
            }
            else {
                evaluation += (r - nearestWin(true))*2+wins1;
            }


            //evaluate other players board
            if(isWin(P2))
            {
                if(isWin(P1))
                {
                    return -98;
                }
                else
                {
                    return 99;
                }
            }
            else if(isWin(P1))
            {
                return -98;
            }
            else if(isDouble(false))
            {
                evaluation -= ((m-1)-movesFromDouble(false))+r*2;
                System.out.println("current value1x :" +evaluation);
            }
            else
            {
                if(nearestWin(false)>r)
                {
                    evaluation -= wins2;
                }
                else {
                    evaluation -= (r - nearestWin(false))*2+wins2;
                }
                System.out.println("current value2x :" +evaluation);
            }

        }

        // for p2
        else
        {

            if(isWin(P2))
            {
                if(isWin(P1))
                {
                    return -98;
                }
                else
                {
                    return 99;
                }
            }
            else if(isWin(P1))
            {
                return -98;
            }
            else if(isDouble(false))
            {
                evaluation += ((m-1)-movesFromDouble(false))+r*2+4;
                System.out.println("current value1x :" +evaluation);
            }
            else
            {
                if(nearestWin(false)>r)
                {
                    evaluation += wins2;
                }
                else {
                    evaluation += (r - nearestWin(false))*2+wins2;
                }
                System.out.println("current value2x :" +evaluation);
            }


            if(isWin(P1))
            {
                if(isWin(P2))
                {
                    return -98;
                }
                else
                {
                    return 99;
                }
            }
            else if(isWin(P2))
            {
                return -98;
            }
            else if(isDouble(true))
            {
                evaluation -= ((m-1)-movesFromDouble(true))+r*2;
                System.out.println("current value1 :" +evaluation);
            }
            if(nearestWin(true)>r)
            {
                evaluation -= wins1;
            }
            else {
                evaluation -= (r - nearestWin(true))*2+wins1;
            }

        }
    return evaluation;

    }

    public int movesFromDouble(boolean turn)
    {
        int moves = -1;
        if(!isDouble(turn))
        {
            return -1;
        }
        if(turn)
        {
            BigInteger temp= P1;
            for (int i = 1; i < r-1; i++)
            {
                temp = temp.and(temp.shiftLeft(m));
            }
            if(!temp.and(temp.shiftLeft(1)).equals(0))
            {
                temp = temp.and(temp.shiftLeft(1));
                moves++;

                while(temp.bitLength()%m!=0||!isPossibleMove(temp,turn))
                {
                    moves++;
                   temp = temp.shiftLeft(1);
                }
            }
            else
            {
                temp = P1;
                for(int i = 1;i<r-1;i++)
                {
                    temp = temp.and(temp.shiftRight(m));
                }
                if(!temp.and(temp.shiftLeft(1)).equals(0))
                {
                    temp = temp.and(temp.shiftLeft(1));
                    moves++;

                    while(temp.bitLength()%m!=0||!isPossibleMove(temp,turn))
                    {
                        moves++;
                        temp = temp.shiftLeft(1);
                    }
                }
            }
            return moves;

        }
        return 0;
    }

    //checks for stackable win
    public boolean isDouble(boolean turn)
    {

        if(turn)
        {
            if(this.isWin(turn))
            {
                //System.out.println("is Win");
                return false;
            }

            BigInteger temp= P1;
            for (int i = 1; i < r-1; i++)
            {
                temp = temp.and(temp.shiftLeft(m));
            }
            if(!temp.and(temp.shiftLeft(1)).equals(BigInteger.ZERO))
            {
                BigInteger temp2 = temp.and(temp.shiftLeft(1));
                for(int i = 1; i <= n;i++)
                {
                    temp2= temp2.clearBit((i*m)-1);
                }
                if(!temp2.equals(BigInteger.ZERO))
                {
                    if(this.isWin(P1.or(temp.shiftLeft(m)))&&temp.shiftLeft(m).and(P2).equals(BigInteger.ZERO))
                    {
                            //System.out.println("true 1");
                            return true;
                    }
                    else if(this.isWin(P1.or(temp.shiftRight(m*(r-1)))))
                    {
                        if(temp.shiftRight(m*(r-1)).and(P2).equals(BigInteger.ZERO))
                        {
                            //System.out.println("true 2");
                            return true;
                        }
                        else
                        {
                            //System.out.println("false 2");
                            return false;
                        }
                    }
                    else
                    {
                        //System.out.println("false 3");
                        return false;
                    }
                }
                else
                {
                    //System.out.println("false 4");
                    return false;
                }
            }
            else
            {
                //System.out.println("false 5");
                return false;
            }
        }
        else
        {
            if(this.isWin(turn))
            {
                return false;
            }

            BigInteger temp= P2;
            for (int i = 1; i < r-1; i++)
            {
                temp = temp.and(temp.shiftLeft(m));
            }
            if(!temp.and(temp.shiftLeft(1)).equals(BigInteger.ZERO))
            {
                BigInteger temp2 = temp.and(temp.shiftLeft(1));
                for(int i = 1; i <= n;i++)
                {
                    temp2= temp2.clearBit((i*m)-1);
                }

                if(!temp2.equals(BigInteger.ZERO))
                {
                    if(this.isWin(P2.or(temp.shiftLeft(m)))&&temp.shiftLeft(m).and(P1).equals(BigInteger.ZERO))
                    {
                        //System.out.println("true 1");
                        return true;
                    }
                    else if(this.isWin(P2.or(temp.shiftRight(m * (r - 1)))))
                    {
                        if(temp.shiftRight(m*(r-1)).and(P1).equals(BigInteger.ZERO))
                        {
                           // System.out.println("true 2");
                            return true;
                        }
                        else
                        {
                           // System.out.println("false 2");
                            return false;
                        }
                    }
                    else
                    {
                       // System.out.println("false 3");
                        return false;
                    }
                }
                else
                {
                   // System.out.println("false 4");
                    return false;
                }
            }
            else
            {
               // System.out.println("false 5");
                return false;
            }
        }
    }

    public boolean isWin(BigInteger board)
    {

            BigInteger temp= board;
            for (int i = 1; i < r; i++)
            {
                temp = temp.and(temp.shiftLeft(m));
            }
            if(!temp.equals(BigInteger.ZERO))
            {
                return true;
            }

            //vertical
            temp = board;
            for (int i = 1; i < r; i++)
            {
                temp = temp.and(temp.shiftLeft(1));
            }
            if(!temp.equals(BigInteger.ZERO))
            {
                return true;
            }

            //vertical
            temp = board;
            for (int i = 1; i < r; i++)
            {
                temp = temp.and(temp.shiftRight(1));
            }
            if(!temp.equals(BigInteger.ZERO))
            {
                return true;
            }

            //upstairs diagonal
            temp = board;
            for (int i = 1; i < r; i++)
            {
                temp = temp.and(temp.shiftLeft(m-1).clearBit(m * n - 1));
            }
            if(!temp.equals(BigInteger.ZERO))
            {
                return true;
            }

            //downstairs diagonal
            temp = board;
            for (int i = 1; i < r; i++)
            {
                temp = temp.and(temp.shiftRight(m + 1));
            }
            if(!temp.equals(BigInteger.ZERO))
            {
                return true;
            }

            return false;

    }


    public boolean isWin(boolean turn)
    {

        if(turn) //player 1
        {
            //horizontal

            BigInteger temp= P1;
            for (int i = 1; i < r; i++)
            {
                temp = temp.and(temp.shiftLeft(m));
            }
            if(!temp.equals(BigInteger.ZERO))
            {
                return true;
            }

            //vertical
            temp = P1;
            BigInteger temp2 =P1;
            for (int i = 1; i < r; i++)
            {
                for(int j = 1; j <= n;j++)
                {
                    temp2 = temp2.clearBit((j*m)-1);

                }
                temp2 = temp2.shiftLeft(1);
                temp = temp.and(temp2);
                temp2 = temp;
            }
            if(!temp.equals(BigInteger.ZERO))
            {

                return true;
            }

            //vertical
            temp = P1;
            temp2 = P1;
            for (int i = 1; i < r; i++)
            {
                for(int j = 1; j <= n;j++)
                {
                    temp2 = temp2.clearBit((j*m));

                }
                temp2 = temp2.shiftRight(1);
                temp = temp.and(temp2.shiftRight(1));
                temp2 = temp;
            }
            if(!temp.equals(BigInteger.ZERO))
            {
                return true;
            }

            //upstairs diagonal
            temp = P1;
            for (int i = 1; i < r; i++)
            {
                temp = temp.and(temp.shiftLeft(m-1).clearBit(m * n - 1));
            }
            if(!temp.equals(BigInteger.ZERO))
            {
                return true;
            }

            //downstairs diagonal
            temp = P1;
            for (int i = 1; i < r; i++)
            {
                temp = temp.and(temp.shiftRight(m + 1));
            }
            if(!temp.equals(BigInteger.ZERO))
            {
                return true;
            }

            return false;


        }
        else //player 2
        {
            //horizontal
            BigInteger temp= P2;
            for (int i = 1; i < r; i++)
            {
                temp = temp.and(temp.shiftLeft(m));
            }
            if(!temp.equals(BigInteger.ZERO))
            {
                return true;
            }

            //vertical
            temp = P2;
            BigInteger temp2 =P2;
            for (int i = 1; i < r; i++)
            {
                for(int j = 1; j <= n;j++)
                {
                    temp2 = temp2.clearBit((j*m)-1);

                }
                temp2 = temp2.shiftLeft(1);
                temp = temp.and(temp2);
                temp2 = temp;
            }
            if(!temp.equals(BigInteger.ZERO))
            {

                return true;
            }

            //vertical
            temp = P2;
            temp2 = P2;
            for (int i = 1; i < r; i++)
            {
                for(int j = 1; j <= n;j++)
                {
                    temp2 = temp2.clearBit((j*m));

                }
                temp2 = temp2.shiftRight(1);
                temp = temp.and(temp2.shiftRight(1));
                temp2 = temp;
            }
            if(!temp.equals(BigInteger.ZERO))
            {
                return true;
            }


            //upstairs diagonal
            temp = P2;
            for (int i = 1; i < r; i++)
            {
                temp = temp.and(temp.shiftLeft(m-1).clearBit(m * n - 1));
            }
            if(!temp.equals(BigInteger.ZERO))
            {
                return true;
            }

            //downstairs diagonal
            temp = P2;
            for (int i = 1; i < r; i++)
            {
                temp = temp.and(temp.shiftRight(m + 1));
            }
            if(!temp.equals(BigInteger.ZERO))
            {
                return true;
            }

            return false;

        }

    }

    public int getWins1() {
        return wins1;
    }
    public int getWins2() {
        return wins2;
    }

    // number of winning possibilitys
    int wins1=0;
    int wins2=0;
    //TODO
    public int nearestWin(boolean turn)
    {
        int Count=999;
        int bitcount=0;
        wins1 =0;
        wins2=0;
        if(turn)
        {
            BigInteger temp = P1;

            while(bitcount<=P1.bitCount())
            {
                for (int i = 0; i < P1.bitLength(); i++)
                {
                    if (P1.testBit(i))
                    {
                        bitcount++;


                        for(int a=0;a<7;a++)
                        {
                            temp = BigInteger.ONE.shiftLeft(i);
                            int turnCount =0;
                            BigInteger attempt = P1;
                            for (int j = 0; j < r - 1; j++)
                            {
                                if(a==0)
                                {
                                    if(temp.bitLength()>=m*n)
                                    {
                                        break;
                                    }
                                    temp = temp.shiftLeft(m);
                                }
                                if(a==1)
                                {
                                    if(temp.bitLength()%m==1&&temp.bitLength()<=m*n)
                                    {
                                        break;
                                    }
                                    temp = temp.shiftLeft(m-1);

                                }
                                if(a==2)
                                {
                                    if(temp.bitLength()%m==0&&temp.bitLength()<=m*n)
                                    {
                                        break;
                                    }
                                    temp = temp.shiftLeft(m+1);

                                }
                                if(a==3)
                                {

                                    if(temp.bitLength()%m==1||temp.bitLength()<=m)
                                    {
                                        break;
                                    }
                                    temp = temp.shiftRight(m+1);
                                }

                                if(a==4) {
                                    if (temp.bitLength() <= m)
                                    {
                                        break;
                                    }
                                    temp = temp.shiftRight(m);

                                }
                                if(a==5)
                                {

                                    if(temp.bitLength()%m==0||temp.bitLength()<=m)
                                    {
                                        break;
                                    }
                                    temp = temp.shiftRight(m-1);
                                }
                                if(a==6)
                                {
                                    if(temp.bitLength()%m==1)
                                    {
                                        break;
                                    }
                                    temp = temp.shiftRight(1);

                                }

                                if (!temp.and(P1).equals(BigInteger.ZERO))
                                {

                                    attempt = attempt.or(temp);
                                    if (this.isWin(attempt)) {
                                        if (turnCount < Count) {
                                            //System.out.println("bitcount: " + bitcount);
                                            //System.out.println("hoyo: " + a);
                                            //System.out.println("J: " +j);
                                            wins1++;
                                            Count = turnCount;
                                        }
                                    }
                                }
                                else if (!temp.and(P2).equals(BigInteger.ZERO))
                                {
                                    break;
                                }
                                else if (temp.and(P1.or(P2)).equals(BigInteger.ZERO))
                                {
                                    if(a==3&&j>0)
                                    {
                                        attempt = attempt.or(temp);
                                        if (this.isPossibleMove(attempt, turn)) {
                                            turnCount++;

                                            if (this.isWin(attempt)) {
                                                if (turnCount < Count) {
                                                    //System.out.println("bitcount: " + bitcount);
                                                    //System.out.println("hoyo: " + a);
                                                    //System.out.println("J: " + j);
                                                    wins1++;
                                                    Count = turnCount;
                                                    //System.out.println("Count: " + Count);
                                                }
                                            }
                                        }
                                    }
                                    else
                                    {
                                        if (this.isPossibleMove(temp, turn)) {
                                            turnCount++;
                                            attempt = attempt.or(temp);
                                            if (this.isWin(attempt)) {
                                                if (turnCount < Count) {
                                                    //System.out.println("bitcount: " + bitcount);
                                                    //System.out.println("hoyo: " + a);
                                                    //System.out.println("J: " + j);
                                                    wins1++;
                                                    Count = turnCount;
                                                    //System.out.println("Count: " + Count);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else
        {
            BigInteger temp = P2;

            while(bitcount<=P2.bitCount())
            {
                for (int i = 0; i < P2.bitLength(); i++)
                {
                    if (P2.testBit(i))
                    {
                        bitcount++;


                        for(int a=0;a<7;a++)
                        {
                            temp = BigInteger.ONE.shiftLeft(i);
                            int turnCount =0;
                            BigInteger attempt = P2;
                            for (int j = 0; j < r - 1; j++)
                            {
                                if(a==0)
                                {
                                    if(temp.bitLength()>=m*n)
                                    {
                                        break;
                                    }
                                    temp = temp.shiftLeft(m);
                                }
                                if(a==1)
                                {
                                    if(temp.bitLength()%m==1&&temp.bitLength()<=m*n)
                                    {
                                        break;
                                    }
                                    temp = temp.shiftLeft(m-1);

                                }
                                if(a==2)
                                {
                                    if(temp.bitLength()%m==0&&temp.bitLength()<=m*n)
                                    {
                                        break;
                                    }
                                    temp = temp.shiftLeft(m+1);

                                }
                                if(a==3)
                                {

                                    if(temp.bitLength()%m==1||temp.bitLength()<=m)
                                    {
                                        break;
                                    }
                                    temp = temp.shiftRight(m+1);
                                }
                                if(a==4) {
                                    if (temp.bitLength() <= m)
                                    {
                                        break;
                                    }
                                    temp = temp.shiftRight(m);

                                }
                                if(a==5)
                                {

                                    if(temp.bitLength()%m==0||temp.bitLength()<=m)
                                    {
                                        break;
                                    }
                                    temp = temp.shiftRight(m-1);
                                }

                                if(a==6)
                                {
                                    if(temp.bitLength()%m==1)
                                    {
                                        break;
                                    }
                                    temp = temp.shiftRight(1);

                                }

                                if (!temp.and(P2).equals(BigInteger.ZERO))
                                {
                                    /*
                                    turnCount++;
                                    if (turnCount < Count)
                                    {
                                        System.out.println("hoyo: " +a);
                                        Count = turnCount;
                                    }*/
                                    attempt = attempt.or(temp);
                                    if (this.isWin(attempt)) {
                                        if (turnCount < Count) {
                                            //System.out.println("bitcount: " + bitcount);
                                            //System.out.println("hoyo: " + a);
                                            //System.out.println("J: " +j);
                                            wins2++;
                                            Count = turnCount;
                                        }
                                    }
                                }
                                else if (!temp.and(P1).equals(BigInteger.ZERO))
                                {
                                    break;
                                }
                                else if (temp.and(P2.or(P1)).equals(BigInteger.ZERO))
                                {
                                    if(a==3&&j>0)
                                    {
                                        attempt = attempt.or(temp);
                                        if (this.isPossibleMove(attempt, turn)) {
                                            turnCount++;

                                            if (this.isWin(attempt)) {
                                                if (turnCount < Count) {
                                                    //System.out.println("bitcount: " + bitcount);
                                                    //System.out.println("hoyo: " + a);
                                                    //System.out.println("J: " + j);
                                                    wins2++;
                                                    Count = turnCount;
                                                    //System.out.println("Count: " + Count);
                                                }
                                            }
                                        }
                                    }
                                    else
                                    {
                                        if (this.isPossibleMove(temp, turn)) {
                                            turnCount++;
                                            attempt = attempt.or(temp);
                                            if (this.isWin(attempt)) {
                                                if (turnCount < Count) {
                                                    //System.out.println("bitcount: " + bitcount);
                                                    //System.out.println("hoyo: " + a);
                                                    //System.out.println("J: " + j);
                                                    wins2++;
                                                    Count = turnCount;
                                                    //System.out.println("Count: " + Count);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if(Count == 999)
        {
            //System.out.println("hoyo! ");
            return r+1;
        }
        else
        {
            return Count;
        }
    }

    public boolean isValidBoard()
    {
        BigInteger temp = P1.or(P2);
        if(!temp.equals(BigInteger.ZERO))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public boolean validMove(boolean player, int col)
    {
        BigInteger holder = new BigInteger("0");
        BigInteger both = P1.or(P2);
        if(player == true&&col <= n)
        {
            for(int j = 0; j <m;j++)
            {
                if((col*m)+j < both.bitLength())
                {
                    if(both.testBit((col*m)+j)== true)
                    {
                        if(j  == 0)
                        {
                            return false;
                        }
                        else
                        {
                            return true;
                        }
                    }
                    else if (j == m-1) {
                        return true;
                    }


                }
                else
                {
                    return true;
                }
            }
        }
        else if (player == false&&col <= n)
        {
            for (int j = 0; j < m; j++)
            {
                if ((col * m) + j < both.bitLength())
                {
                    if (both.testBit((col * m) + j) == true)
                    {
                        if (j == 0)
                        {
                            return false;
                        }
                        else
                        {
                            return true;
                        }
                    }
                    else if (j == m-1)
                    {

                        return true;
                    }
                }
                else
                {
                    return true;
                }
            }
        }
        System.out.println("something is wrong!");

        return false;
    }

    public boolean oldMove(boolean player, int col)
    {
        BigInteger holder = new BigInteger("0");
        BigInteger both = P1.or(P2);
        if(player == true&&col <= n)
        {
            for(int j = 0; j <m;j++)
            {
                if((col*m)+j < both.bitLength())
                {
                    if(both.testBit((col*m)+j)== true)
                    {
                        if(j  == 0)
                        {
                            return false;
                        }
                        else
                        {
                            P1 = P1.or(BigInteger.ONE.shiftLeft(((col*m)+j)-1));
                            return true;
                        }
                    }
                    else if (j == m-1) {
                        P1 = P1.or(BigInteger.ONE.shiftLeft((col * m) + j));
                        return true;
                    }


                }
                else
                {
                    P1 = P1.or(BigInteger.ONE.shiftLeft((col*m)+m+j-1));
                    return true;
                }
            }
        }
        else if (player == false&&col <= n)
        {
            for (int j = 0; j < m; j++)
            {
                if ((col * m) + j < both.bitLength())
                {
                    if (both.testBit((col * m) + j) == true)
                    {
                        if (j == 0)
                        {
                            return false;
                        }
                        else
                        {
                            P2 = P2.or(BigInteger.ONE.shiftLeft(((col*m)+j)-1));
                            return true;
                        }
                    }
                    else if (j == m-1)
                    {
                        P2 = P2.or(BigInteger.ONE.shiftLeft((col * m) + j));
                        return true;
                    }
                }
                else
                {
                    P2 = P2.or(BigInteger.ONE.shiftLeft((col*m)+m+j-1));
                    return true;
                }
            }
        }
        System.out.println("something is wrong!");

        return false;
    }

    public boolean isPossibleMove(BigInteger P, boolean turn)
    {
        if(turn)
        {
            if(P.bitLength()%m==0)
            {
                return true;
            }
            else if(!P.shiftLeft(1).and((P1.or(P2))).equals(BigInteger.ZERO))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            if(P.bitLength()%m==0)
            {
                return true;
            }
            else if(!P.shiftLeft(1).and((P2.or(P1))).equals(BigInteger.ZERO))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    public Board move(boolean player, int col)
    {
        BigInteger holder = new BigInteger("0");
        BigInteger both = P1.or(P2);
        Board Temp = this.copy();
        if(player == true&&col <= n)
        {
            for (int j = 0; j < m; j++)
            {
                if ((col * m) + j < both.bitLength())
                {
                    if (both.testBit((col * m) + j) == true)
                    {
                        if (j == 0)
                        {
                            System.out.println("something is wrong!");
                            return null;
                        }
                        else
                        {
                            Temp.setP1(P1.or(BigInteger.ONE.shiftLeft(((col * m) + j) - 1)));
                            return Temp;
                        }
                    }
                    else if (j == m-1)
                    {
                        Temp.setP1(P1.or(BigInteger.ONE.shiftLeft((col * m) + j)));
                        return Temp;
                    }

                }
                else
                {
                    Temp.setP1(P1.or(BigInteger.ONE.shiftLeft((col * m) + m + j - 1)));

                    return Temp;
                }
            }
        }
        else if (player == false&&col <= n)
        {
            for (int j = 0; j < m; j++)
            {
                if ((col * m) + j < both.bitLength())
                {
                    if (both.testBit((col * m) + j) == true)
                    {
                        if (j == 0)
                        {
                            System.out.println("something is wrong!");
                            return null;
                        }
                        else
                        {
                            Temp.setP2(P2.or(BigInteger.ONE.shiftLeft(((col * m) + j) - 1)));
                            return Temp;
                        }
                    }
                    else if (j == m-1)
                    {
                        Temp.setP2(P2.or(BigInteger.ONE.shiftLeft((col * m) + j)));
                        return Temp;
                    }

                }
                else
                {
                    Temp.setP2(P2.or(BigInteger.ONE.shiftLeft((col * m) + m + j - 1)));

                    return Temp;
                }
            }
        }
        System.out.println("something is wrong!");

        return null;
    }

    public void printPlayer(boolean p)
    {
        String boards;
        if(p == true)
        {
            System.out.println("Player:1");
            boards = P1.or(BigInteger.ONE.shiftLeft(m * n)).toString(2).substring(1);
            System.out.println(boards);
        }
        else if(p == false)
        {
            System.out.println("Player:2");
            boards = P2.or(BigInteger.ONE.shiftLeft(m * n)).toString(2).substring(1);
            System.out.println(boards);
        }
        else
        {
            System.out.println("both");
            boards = P1.or(P2).toString(2).substring(1);
        }
        for(int i = 0; i < m;i++)
        {
            System.out.print("| ");
            for(int j= 0; j<n; j++)
            {
                System.out.print(boards.charAt(boards.length()-1-(j*m+i)) + " | ");
            }
            System.out.println();
        }
    }

}

