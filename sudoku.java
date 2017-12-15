

class Sudoku
{  
   private int[][] board = new int[9][9];
   public Sudoku() {
   
     // Clear all cells
        for( int row = 0; row < 9; row++ )
            for( int col = 0; col < 9; col++ )
                board[row][col] = 0 ;

        // Create the initial situation
        board[0][0] = 9 ;
        board[0][4] = 2 ;
        board[0][6] = 7 ;
        board[0][7] = 5 ;

        board[1][0] = 6 ;
        board[1][4] = 5 ;
        board[1][7] = 4 ;

        board[2][1] = 2 ;
        board[2][3] = 4 ;
        board[2][7] = 1 ;

        board[3][0] = 2 ;
        board[3][2] = 8 ;

        board[4][1] = 7 ;
        board[4][3] = 5 ;
        board[4][5] = 9 ;
        board[4][7] = 6 ;

        board[5][6] = 4 ;
        board[5][8] = 1 ;

        board[6][1] = 1 ;
        board[6][5] = 5 ;
        board[6][7] = 8 ;

        board[7][1] = 9 ;
        board[7][4] = 7 ;
        board[7][8] = 4 ;

        board[8][1] = 8 ;
        board[8][2] = 2 ;
        board[8][4] = 4 ;
        board[8][8] = 6 ;
   
   }
   
        public static void main(String args[])
        {
         Sudoku s = new Sudoku();
        try {
            s.solve(0, 0);
        }
        catch(Exception e) {
            s.printBoard();
        }         }
          
          
         
           //for loop for printing out organized game puzzle
         public void printBoard()
         { 
           for(int i = 0; i < board.length; i++)
           {

               for(int o = 0; o < board[0].length; o++)
               {
                  if(o%3==0)
                  {
                      System.out.print(" | ");
                  }
                  else
                  { 
                      System.out.print(" "); 
                  }
                  System.out.print(board[i][o]);  
               }
               if(i % 3 == 2)
               {
                  System.out.println();
                  System.out.println(" -----------------------");
               } 
               else
               {
                  System.out.println();
               }

           }
         }
  
       
       
        public boolean checkBox(int x, int y, int val)
        {
            int blockX = (x/3)*3;
            int blockY = (y/3)*3;
            
            for(int row = 0; row < Math.sqrt(board.length); row++)
            {
               for(int col = 0; col < Math.sqrt(board[0].length); col++)
               {
                  if(val == board[blockX+row][blockY+col])
                  {
                     return false;
                  }
               }
            } 
            return true;
        }
        
        public boolean checkRow(int row, int val)
        {
            for(int col = 0;col < board.length; col++)
            {
               if(val == board[row][col])
               {
                  return false;
               }
            }
            return true;
        }
        
        public boolean checkCol(int col, int val)
        {
            for(int row = 0;row < board[0].length; row++)
            {
               if(val == board[row][col])
               {
                  return false;
               }
            }
            return true;
        }
        
        public  void solve( int x, int y) throws Exception
        {   
        
            if(x>8)
            {
               throw new Exception("solution found!@#!@#");
            }
            if(board[x][y] != 0)
            {
               solveNext(x,y);
            }
            else
            {          
                for(int count = 1;count < 10; count++) 
                {
                   if(checkBox(x,y,count) && checkRow(x,count) && checkCol(y,count))
                   {
                       board[x][y] = count;
                       solveNext(x,y);
                   } 
                } 
                board[x][y] = 0;
            }
                   
        }
        
        public void solveNext( int x, int y) throws Exception
        {
            
               
            if(y<8)
            {
               solve(x,y+1);
            }
            else
            {
               solve(x+1,0);
            }
        }
        
}
