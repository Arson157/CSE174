// Karson Riley
//CSE 174 sec G Norm Krumpe
//11/13/16

import java.util.Scanner;

public class PrimeStuff{
  
  //allows Scanner to be accessed in all methods:
  static Scanner in = new Scanner(System.in);
  
  public static void main(String[] args){
    //System.out.println(888868985488456218%10);
    int choice = 0;
    
    while(choice !=4){
      choice = menu();
      if(choice == 1)
        prime();
      else if(choice == 2)
        factor();
      else if(choice == 3)
        listPrime();
      
    }
    
  }//end main
  
  public static int menu(){
    
    System.out.println("What would you like to do?");
    System.out.println("1) Check if a number is prime");
    System.out.println("2) Factor a number");
    System.out.println("3) List prime numbers");
    System.out.println("4) Quit");
    System.out.print("Choice:  ");
    int choice = in.nextInt();
    
    if(choice <= 4 &&  choice >=1)
      return choice;
    else{
      System.out.println("**** INVALID OPTION ****");
      return 0;
    }
    
  }//end menu
  
  public static void prime(){
    System.out.print("Enter a number between 1 and " + Long.MAX_VALUE +": ");
    long choice = in.nextLong();
    
    
    if(choice <= Long.MAX_VALUE && choice >= 1){
      
      if(isPrime(choice))
        System.out.println("---> "+choice+ " is prime.");
      else
        System.out.println("---> "+choice + " is not prime.");
      
    }
    
    else
      prime();
    
    
  }//end prime
  
  public static boolean isPrime(long num){
    boolean prime = true;
    
    if(num%2 == 0){
      return(prime = false);
    }
    
    for(int i = 3; i*i <= num; i+=2){
      
      if (num%i == 0)
        return (prime = false);
    }
    
    return prime;
  }//end isPrime
  
  public static void factor(){
    System.out.print("Enter a number between 1 and " + Long.MAX_VALUE +": ");
    long choice = in.nextLong();
    while(choice <= 0){
      System.out.print("Enter a number between 1 and " + Long.MAX_VALUE +": ");
      choice = in.nextLong();
    }
    System.out.print("---> "+ choice+ " = ");
    if(choice == 1)
    System.out.print("1");
    
    while(!isPrime(choice)){
      
      if(choice%2 == 0){
        if(choice > 2)
          System.out.print("2 * ");
        else
          System.out.print("2");
        choice /= 2;
      }
      else {
        for(int i = 3; i<=choice; i += 2){
          if(i>3)
            if(choice%i == 0){
            System.out.print(i + " * ");
            choice /= i;
          }
        }
      }
    }
    if(choice > 1)
      System.out.println(choice);
    else
      System.out.println();
    
    
  }//end factor
  
  public static void listPrime(){
    System.out.print("Emter a starting number between 1 and " + Long.MAX_VALUE + ": ");
    long start = in.nextLong();
    
    while(start <= 0){
      System.out.print("Enter a starting number between 1 and " + Long.MAX_VALUE +": ");
      start = in.nextLong();
    }
    
    System.out.print("How many primes (1-1000): ");
    int count = in.nextInt();
    
    while(count <= 0 || count > 1000){
      System.out.print("How many primes (1-1000): ");
      count = in.nextInt();
    }
    
    System.out.print("How many primes per row (1-20): ");
    int row = in.nextInt();
    
    while(row <= 0 || row > 20){
      System.out.print("How many primes per row (1-20): ");
      row = in.nextInt();
    }
    int c = 0;
    int r = 0;
    
    for (long i = start; i < Long.MAX_VALUE; i++){
      if(isPrime(i)){
        System.out.printf("%6d", i);
        c++;
        r++;
      }
      if(r == row){
        System.out.println();
        r = 0;
      }
      if(c == count){
        c = 0;
        break;
      }
    }
  }//end listPrime
  
}//end class