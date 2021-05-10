import java.util.*;

public class DreidelGameDriver
{

   public static void main (String[] args)
   {
      //Set Scanner and Random instances
      Scanner in = new Scanner (System.in);
      Random r = new Random();
      
      //Set variables
      int randomNum = 0;
      int potTokens = 0;
      int winnings = 0;
      String continuePlay = "";
      boolean error = false;
      
      //Set Player array to store 2 Player instances
      Player[] player = new Player[2];
      
      //Ask and set Player names
      System.out.println("Enter Player 1 name: ");
      player[0] = new Player(in.nextLine());
      
      System.out.println("Enter Player 2 name: ");
      player[1] = new Player(in.nextLine());
      
      //Contiue play until player inputs "N"
      while (!continuePlay.equalsIgnoreCase("N"))
      {
      
         System.out.println("Ante Up: Everyone put a token in the pot");
         
         //Loop through each player
         for (int i = 0; i < player.length; i++)
         {
            //Players each ante 1 token
            player[i].lostToken();
            //Add pot tokens
            potTokens++;
         }
         
         //Print game info
         player[0].printInfo();
         player[1].printInfo();
         
         //Print number of pot tokens
         System.out.println("Pot tokens: " + potTokens);
         
         //Loop through each player
         for (int i = 0; i < player.length; i++)
         {
            //Set random number for Dreidel
            randomNum = r.nextInt(4) + 1;
            System.out.print(player[i].getPlayerName() + "'s turn: Dreidel falls on...");
            
            //Compare Dreidel spin with random number
            if (randomNum == 1)
            {
               System.out.println("Nun: nothing happens");
               player[i].printInfo();
            }
            else if (randomNum == 2)
            {
               System.out.println("Gimmel: you get the pot");
               player[i].recieveTokens(potTokens);
               potTokens = 0;
               player[i].printInfo();
            }
            else if (randomNum == 3)
            {
               //Reset winnings variable
               winnings = 0;
               
               System.out.println("Hey, you recieve half the pot");
               
               //Compare odds and evens
               if (potTokens % 2 == 0)
               {
                  winnings = potTokens / 2;
               }
               else if (potTokens % 2 != 0)
               {
                  winnings = potTokens / 2;
                  //Add 1 if odd
                  if (winnings % 2 != 0)
                  {
                     winnings++;
                  }
               }
               player[i].recieveTokens(winnings);
               potTokens -= winnings;
               
               player[i].printInfo();
            }
            else if (randomNum == 4)
            {
               System.out.println("Shin: Lose a token");
               player[i].lostToken();
               potTokens++;
               player[i].printInfo();
            }
            else
            {
               System.out.println("Something went terribly wrong. Please restart the program.");
            }
         }
         
         //Ask to continue
         System.out.println("Continue: (y/n)");
         
         //Do while loop and try, catch for valid input
         do
         {
            try
            {
               error = false;
               
               continuePlay = in.nextLine();
               if (!continuePlay.equalsIgnoreCase("N") && !continuePlay.equalsIgnoreCase("Y"))
               {
                  throw new IllegalArgumentException();
               }
            }
            catch (IllegalArgumentException e)
            {
               System.out.println("Please enter y or n: ");
               
               error = true;
            }
         } while (error);
               
      }//End While Loop
      
      //Print game results
      player[0].printInfo();
      player[1].printInfo();
      
      System.out.println("Good game!");
      
   }//End Main

}//End Program