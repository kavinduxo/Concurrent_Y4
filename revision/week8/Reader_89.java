/** 
 *******************************************************
 ** File:   	Reader_89  [Was: Reader_88.java]
 ** Author:	P. Howells
 ** Tutorial:   Week 08: Java Threads States 
 ** Exercise:   8.9, Based on Ex 8.8
 ** Modified:   13/11/2022
 ** ******************************************************* 
**/


class Reader_89 extends Thread 
{

   private final SharedVariable X ;
   private final SharedVariable Y ;

   private int   count = 1 ; 

   private final int OneSecond = 1000 ;
   private int   time  = 1 ; 


   public volatile boolean terminate = false ;   // Tutorial Exercise 8.9 


   public Reader_89( String name, SharedVariable sv1, SharedVariable sv2, int count ) 
   {
      super( name ) ;        

      System.out.println( getName() + " Thread State in Constructor is: " + getState​() ) ;

      this.X = sv1 ;     
      this.Y = sv2 ;     

      this.count = count ;     
   }


   public void run() 
   {

      System.out.println( getName() + ": STARTED" ) ;

      // Ex 8.1
      System.out.println( getName() + " Thread State in run() is: " + getState​() ) ;

      while ( ! terminate )        // Tutorial Exercise 8.8
      {
         System.out.println( getName() + " read X: " + X.value() ) ;
 
         System.out.println( getName() + " read Y: " + Y.value() ) ;

         try {
                sleep( (int) ( Math.random() * OneSecond * time ) ) ;
             } 
         catch (InterruptedException e) {}

      }

      // Tutorial Exercise 8.8
      System.out.println( getName() + ": TERMINATING, terminate = " + terminate ) ;

   }

} // Reader_89
