/** 
 *******************************************************
 **   File:   	SimpleThread.java
 **   Author:	P. Howells
 **   Lecture:	Introduction to Threads
 **   Modified:	6/11/22
 **
 ** Tutorial 7: Introduction to Java Threads 
 ** Exercise 7.1
 ** ******************************************************* 
**/


class SimpleThread extends Thread 
{

   private final int OneSecond = 1000 ;

   // Ex 7.1 (c)
   public SimpleThread( String str ) 
   {
      super(str) ;             // calling ``Thread( String )'' 
                               // constructor
   }



   public void run()           // ``body'' of the thread
   {
      // Ex 7.1 (a)
      System.out.println( getName() + ": Started Running" ) ;


      // Ex 7.1 (b): will only ever print "RUNNABLE", as that is the only 
      //             thread state where its run() method is executed

      System.out.println( getName() + " in State: " + getState() ) ;



      for (int i = 0; i < 10; i++) 
      {
         System.out.println( getName() + ": " + i ) ;

         try {
                sleep( (int) ( Math.random() * OneSecond ) ) ;   
             } 
         catch (InterruptedException e) {}

      }

      System.out.println( getName() + ": TERMINATING" ) ;

   }

} // SimpleThread
