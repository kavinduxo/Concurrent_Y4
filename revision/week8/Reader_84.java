/** 
 *******************************************************
 ** File:   	Reader_84  [Was: Reader_82.java]
 ** Author:	P. Howells
 ** Tutorial:   Week 08: Java Threads States 
 ** Exercise:   8.4 Based on Ex 8.2
 ** Modified:   13/11/2022
 **
 ** ******************************************************* 
**/


class Reader_84 extends Thread 
{

   private final SharedVariable X ;
   private final SharedVariable Y ;

   private int   count = 1 ; 

   private final int OneSecond = 1000 ;
   private int   time  = 2 ;             // Ex8.2



   public Reader_84( String name, SharedVariable sv1, SharedVariable sv2, int count ) 
   {
      super( name ) ;        

      // Ex 8.1      
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

      for ( int i = 0 ; i < count ; i++ ) 
      {
           synchronized ( X )  // the "X" object acts as the LOCK 
           {
                // ENTERED critical section     

		System.out.println( getName() + " read X: " + X.value() ) ;
 
		System.out.println( getName() + " read Y: " + Y.value() ) ;

        	try {
			sleep( (int) ( Math.random() * OneSecond * time ) ) ;   // Ex 8.1, 8.4
                	// sleep( (int) ( OneSecond * time ) ) ;                // Ex 8.2
             	    } 
         	catch (InterruptedException e) {}

	   } // sync - EXITED critical section

      }

      System.out.println( getName() + ": TERMINATING" ) ;

   }

} // Reader_84
