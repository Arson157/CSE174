//Karson Riley
//CSE 174 Sec G
//Norm Krumpe
//11-28-16

import java.util.Scanner;

public class YahtzeeGame{
  
  public static Scanner in = new Scanner(System.in); //universal scanner
  
  public static int gameState = 0; //used to exit play if no reroll
  
  public static void main(String[] args){
    
    int numberOfRolls = 1; //used to exit after 3 rolls
    int[] dice = new int[5]; //initiate array
    
    roll(dice);
    
    while(gameState == 0){
      sort(dice); //inital sort, could be redundant
      
      String firstRoll =  toString(dice);
      
      System.out.println("Roll #" + numberOfRolls + ":" + firstRoll); //print the dice
      scoreChoices(dice);
      if(numberOfRolls < 3) //check how many rerolls
        reroll(dice);
      else
        break;
      numberOfRolls++;
    }
    System.out.println("Your score is " + maxScore(dice) + ".   Goodbye.");
    gameState = 0; //needed for rerunning the program
    
  }//end main
  
  public static void roll(int[] dice){
    
    //put a random number 1-6 in all five spots in the array
    for(int i = 0; i < dice.length; i++){
      dice[i] = (int)(Math.random()*6)+1;
    }
    
  }//end roll
  
  public static void sort(int[] dice){
    
    //bubble sort
    boolean swapped = true;
    while(swapped){
      swapped = false;
      for(int i = 0; i < dice.length - 1; i++){
        if(dice[i] > dice[i+1]){
          int temp = dice[i];
          dice[i] = dice[i+1];
          dice[i+1] = temp;
          swapped = true;
        }
        
      }
    }
  }//end sort
  
  public static String toString(int[] dice){
    String done = "";
    for(int num: dice){
      done += " ";
      done += num;
    }
    return done;
  }//end toString
  
  public static void reroll(int[] dice){
    
    int[] which = new int[5]; //array to store which dice to change
    int valid = 0;
    
    //ask if the user wants to change any die
    while(valid == 0){
      System.out.print("Roll again y/n? ");
      String reroll = in.next();
      if(reroll.equals("y")){
        which = ask();//get which string
        valid = 1;
        for(int i = 0; i < dice.length; i++){
          //change the dice the user wants
          if(which[i] == 1)
            dice[i] = (int)(Math.random()*6)+1;
        }
      }
      else if(reroll.equals("n")){
        valid = 1;
        gameState = 1;
      }
    }
  }//end reroll
  
  //ask user which dice to change 
  public static int[] ask(){
    int valid = 0;
    int[] which = new int[5];
    while(valid == 0){
      System.out.print("Indicate which dice to roll using y and n:  ");
      String choice = in.next();
      //http://tutorials.jenkov.com/java-regex/syntax.html told me how to regex
      if(choice.matches("[yn]{5}")){ 
        for(int i = 0; i <choice.length(); i++){
          if(choice.charAt(i) == 'y'){
            valid = 1;
            which[i] = 1;
          }
        }
      }
      
      else;
    }
    return(which);
  }//end ask
  
  public static int yahtzee(int[] dice){
    
    int first = dice[0];
    for(int num: dice){
      if(num != first){
        return 0;
      }
    }
    return 50;
    
  }//end yahtzee
  
  public static int fourOfAKind(int[] dice){
    int temp = 0;
    int count = 0;
    int sum = 0;
    for(int num: dice){
      if(num == temp)
        count++;
      temp = num;
      sum += num;
    }
    if(count == 4)
      return sum;
    return 0;
     
  }//end fourOfAKind
  
  public static int threeOfAKind(int[] dice){
    int temp = 0;
    int count = 0;
    int sum = 0;
    for(int num: dice){
      if(num == temp)
        count++;
      temp = num;
      sum += num;
    }
    if(count == 3)
      return sum;
    return 0;
  }//end threeOfAKind
  
  public static int largeStraight(int[] dice){
    int temp = -5;
    int count = 0;
    for(int num: dice){
      if(num > temp){
        if(num-temp ==1)
          count++;
      }
      temp = num;
    }
    if(count == 4)
      return 40;
    return 0;
  }//end largeStraight
  
  public static int smallStraight(int[] dice){
    int temp = -5;
    int count = 0;
    for(int num: dice){
      if(num > temp){
        if(num-temp ==1)
          count++;
      }
      temp = num;
    }
    if(count == 3)
      return 30;
    return 0;
  }//end smallStraight
  
  public static int fullHouse(int[] dice){
    int firstNum = dice[0];
    int secondNum = dice[4];
    for (int num: dice){
      if(num != firstNum && num != secondNum)
        return 0;
    }
    return 25;    
  }//end fullHouse
  
  public static int sum(int[] dice, int key){
    int sum = 0;
    for (int num: dice){
      if(num == key)
        sum+=num;
    }
    return sum;
  }//end sum
  
  public static int chance(int[] dice){
    int sum = 0;
    for (int num : dice){
      sum+= num;
    }
    return sum;
  }//end chance
  
  public static void scoreChoices(int[] dice){
    sort(dice);
    for(int i = 1; i <= 6; i++){
      if(sum(dice, i) > 0)
        System.out.println(i + " total: " + sum(dice, i));
    }
    if(threeOfAKind(dice) > 0)
      System.out.println("3 of a kind: " + threeOfAKind(dice));
    if(fourOfAKind(dice) > 0)
      System.out.println("4 of a kind: " + fourOfAKind(dice));
    if(fullHouse(dice) > 0)
      System.out.println("Full House: " + fullHouse(dice));
    if(smallStraight(dice) > 0)
      System.out.println("Small Straight: " + smallStraight(dice));
    if(largeStraight(dice) > 0)
      System.out.println("Large Straight: " + largeStraight(dice));
    if(yahtzee(dice) > 0)
      System.out.println("Yahtzee: " + yahtzee(dice));
    if(chance(dice) > 0)
      System.out.println("Chance: " + chance(dice));
  }//end scoreChoices
  
  public static int maxScore(int[] dice){
    sort(dice);
    int max = 0;
    for(int i = 1; i <= 6; i++){
      if(sum(dice, i) > max)
        max = sum(dice, i);
    }
    if(chance(dice) > max)
      max = chance(dice);
    if(threeOfAKind(dice) > max)
      max = threeOfAKind(dice);
    if(fourOfAKind(dice) > max)
      max = fourOfAKind(dice);
    if(fullHouse(dice) > max)
      max = fullHouse(dice);
    if(smallStraight(dice) > max)
      max = smallStraight(dice);
    if(largeStraight(dice) > max)
      max = largeStraight(dice);
    if(yahtzee(dice) > max)
      max = yahtzee(dice);
    return max;
  }//end maxScore
  
}//end class