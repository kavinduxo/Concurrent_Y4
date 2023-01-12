/** 
 *******************************************************
 **   File:   	SimpleThread_Time.java
 **   Author:	P. Howells
 **   Lecture:	Introduction to Threads
 **   Modified:	6/11/22
 **
 ** Tutorial 7: Introduction to Java Threads 
 ** Exercise 7.1  (c)
 ** ******************************************************* 
**/


class SimpleThread_Time extends Thread 
{

   private final int OneSecond = 1000 ;
   private int time = 1 ;                     // Ex 7.1 (c)


   // Ex 7.1 (c)
   public SimpleThread_Time( String str, int time ) 
   {
      super(str) ;             // calling ``Thread( String )'' 
                               // constructor
      this.time = time ;       // Ex 7.1 (c)
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
                sleep( (int) ( Math.random() * OneSecond * time ) ) ;   // Ex 7.1 (c)
             } 
         catch (InterruptedException e) {}

      }

      System.out.println( getName() + ": TERMINATING" ) ;

   }

} // SimpleThread_Time
