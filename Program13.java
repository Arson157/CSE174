//Karson Riley
//CSE 174 Sec G Norm Krumpe
//Program 13 locker array

import java.util.Scanner;

public class Program13 {
  
  //public Scanner, used in ask and stages methods
  public static Scanner in = new Scanner(System.in);
  
  public static void main(String[] args){
    
    //gather all nececary information, number of lockers, show stages.
    boolean[] lockers = ask();
    
    //preform the act of opening and closing lockers
    lockers = activity(lockers);
    
    //print the final open lockers
    result(lockers);
    
  }//end main
  
  public static boolean[] ask(){
    System.out.print("How Many Lockers? ");
    int howMany = in.nextInt() + 1; //accounts for zero index
    boolean[] lockers = new boolean[howMany];
    
    //use zero index to store whether or not to show stages
    lockers[0] = stages(); 
    
    return lockers;
  }//end ask
  
  public static boolean stages(){
    boolean show = false;
    int i = 0;
    //only accept y or n
    while(i == 0){
      System.out.print("Show Stages? (y/n) ");
      String stages = in.next();
      if(stages.equals("y")){
        show = true;
        i = 1;
      }
      else if(stages.equals("n"))
        i = 1;
    }
    return show;
  }//end stages
  
  public static boolean[] activity(boolean[] lockers){
    boolean show = lockers[0];
    for (int i = 1; i < lockers.length; i++){
      for (int j = i; j < lockers.length; j+=i){
        if((j)%(i) == 0)
          lockers[j] = !lockers[j];
      }
      if(show)
        print(lockers);
    }
    return lockers;
  }//end activity
  
  public static void print(boolean[] lockers){
    for(int i = 1; i < lockers.length; i++){
      if(lockers[i])
        System.out.print("O");
      else
        System.out.print("-");
    }
    System.out.println();
  }//end print
  
  public static void result(boolean[] lockers){
    System.out.print("Open: 1");
    for(int i = 2; i < lockers.length; i++){
      if(lockers[i])
        System.out.print(", "+ i);
    }
    System.out.println();
  }//end result
}//end class