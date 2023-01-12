/** 
 *******************************************************
 ** File:   	Sharing_84.java [WAS: Sharing_82.java]
 ** Author:	P. Howells
 ** Tutorial:   8: Java Threads States etc 
 ** Exercise:   8.4, based on 8.2
 ** Modified:   13/11/2022
 ******************************************************* 
**/

/**
  Ex 8.4  
  Question: If this is the case, what are the "new" states?

  Answer: sometimes shows the non-runnable state BLOCKED.
**/


class Sharing_84
{

   public static void main ( String args[] ) 
   {

      // Ex 8.2
      final int OneSecond = 1000 ;
      final int time      = 2 ;    


      // Create the 2 shared variables X & Y

      SharedVariable X = new SharedVariable( 0 ) ;

      SharedVariable Y = new SharedVariable( 0 ) ;


      // Create the 4 threads

      Reader_84 reader1 = new Reader_84( "Reader-1", X, Y, 5 ) ; 

      Reader_84 reader2 = new Reader_84( "Reader-2", X, Y, 5 ) ; 

      Writer_84 writer1 = new Writer_84( "Writer-1", X, Y, 5, 0 ) ; 

      Writer_84 writer2 = new Writer_84( "Writer-2", X, Y, 5, 100 ) ; 


      // Ex 8.3
      System.out.println( "Main tests reader1 isAlive: " + reader1.isAlive() ) ;
      System.out.println( "Main gets state of reader1: " + reader1.getState() ) ;

      System.out.println( "Main tests reader2 isAlive: " + reader2.isAlive() ) ;
      System.out.println( "Main gets state of reader2: " + reader2.getState() ) ;

      System.out.println( "Main tests writer1 isAlive: " + writer1.isAlive() ) ;
      System.out.println( "Main gets state of writer1: " + writer1.getState() ) ;

      System.out.println( "Main tests writer2 isAlive: " + writer2.isAlive() ) ;
      System.out.println( "Main gets state of writer2: " + writer2.getState() ) ;

 
      // Start the 2 threads executing

      reader1.start() ;
      reader2.start() ;

      writer1.start() ;
      writer2.start() ;

      // Ex 8.2
      // Wait a few seconds before getting their states
      try {
	       Thread mainThread = Thread.currentThread() ;
               mainThread.sleep( (int) ( OneSecond * time ) ) ;  
          } 
      catch (InterruptedException e) {}
      
      System.out.println( "Main Method: Reader & Writer Thread States after start()" ) ;
      System.out.println( reader1.getName() + " is: " + reader1.getState​() ) ;
      System.out.println( reader2.getName() + " is: " + reader2.getState​() ) ;
      System.out.println( writer1.getName() + " is: " + writer1.getState​() ) ;
      System.out.println( writer2.getName() + " is: " + writer2.getState​() ) ;
      System.out.println() ;

      // Ex 8.3
      System.out.println( "Main tests reader1 isAlive: "  + reader1.isAlive() ) ;
      System.out.println( "Main tests reader2 isAlive: "  + reader2.isAlive() ) ;
      System.out.println( "Main tests writer1 isAlive: "  + writer1.isAlive() ) ;
      System.out.println( "Main tests writer2 isAlive: "  + writer2.isAlive() ) ;


      // Ex 8.1
      // Wait for the threads to terminate.
      try {
		reader1.join() ;
  		reader2.join() ;

      		writer1.join() ;
      		writer2.join() ;
	  }
      catch (InterruptedException ie) {} ;

      // Ex 8.1
      System.out.println( "Main Method: Reader & Writer Thread States after exiting run()" ) ;
      System.out.println( reader1.getName() + " is: " + reader1.getState​() ) ;
      System.out.println( reader2.getName() + " is: " + reader2.getState​() ) ;
      System.out.println( writer1.getName() + " is: " + writer1.getState​() ) ;
      System.out.println( writer2.getName() + " is: " + writer2.getState​() ) ;

      // Ex 8.3
      System.out.println( "Main tests reader1 isAlive: "  + reader1.isAlive() ) ;
      System.out.println( "Main tests reader2 isAlive: "  + reader2.isAlive() ) ;
      System.out.println( "Main tests writer1 isAlive: "  + writer1.isAlive() ) ;
      System.out.println( "Main tests writer2 isAlive: "  + writer2.isAlive() ) ;

   }

} // Sharing_84
