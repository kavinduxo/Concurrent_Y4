/** 
 *******************************************************
 ** File:   	Writer_89.java [WAS: Writer_88.java]
 ** Author:	P. Howells
 ** Tutorial:   8: Java Threads States 
 ** Exercise:   8.9 , Based on Tutorial 8, Ex 8.8
 ** Modified:   13/11/2022
 ** ******************************************************* 
**/


class Writer_89 extends Thread 
{

   private final SharedVariable X ;
   private final SharedVariable Y ;

   private final int count ; 
   private final int lowerLimit ; 

   private final int OneSecond = 1000 ;
   private int   time = 1 ; 

   public volatile boolean terminate = false ;   // Tutorial Exercise 8.9 

   public Writer_89( String name,  SharedVariable sv1, SharedVariable sv2, 
                     int count, int lowerLimit  ) 
   {
      super( name ) ;  

      // Ex 8.1      
      System.out.println( getName() + " Thread State in Constructor is: " + getState​() ) ;

      this.X = sv1 ;     
      this.Y = sv2 ;     

      this.count = count ;     
      this.lowerLimit = lowerLimit ;     
    }


   public void run() 
   {

      System.out.println( getName() + ": STARTED" ) ;

      // Ex 8.1
      System.out.println( getName() + " Thread State in run() is: " + getState​() ) ;


      int i = 0 ;                  // Tutorial Exercise 8.8
      while ( ! terminate )        // Tutorial Exercise 8.9
      {
         int newValue = lowerLimit + i++ ;        // Tutorial Exercise 8.8

         X.assign( newValue ) ;

         System.out.println( getName() + " assigned X: " + newValue) ;

         Y.assign( newValue ) ;

         System.out.println( getName() + " assigned Y: " + newValue) ;

         try {
                sleep( (int) ( Math.random() * OneSecond * time ) ) ;
             } 
         catch (InterruptedException e) {}

      }

      // Tutorial Exercise 8.8
      System.out.println( getName() + ": TERMINATING, terminate = " + terminate ) ;

   }

} // Writer_810
