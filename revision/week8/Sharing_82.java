/** 
 *******************************************************
 ** File:   	Sharing_82.java [WAS: Sharing_81.java]
 ** Author:	P. Howells
 ** Tutorial:   8: Java Threads States etc 
 ** Exercise:   8.2, based on 8.1
 ** Modified:   13/11/2022
 ******************************************************* 
**/

/**
  Ex 8.2  
  Question: Does this effect the various states output?  

  Answer:  Any call to getState() inside a threads run() method will always returns 
           RUNNABLE because it only ever executes code when its in the RUNNABLE state.
**/


class Sharing_82
{

   private static final int NumbThreads = 5 ;
   
   private static Thread[] allThreads = new Thread[ NumbThreads ] ;

   public static void main ( String args[] ) 
   {

      // Ex 8.2
      final int OneSecond = 1000 ;
      final int time      = 2 ;    

      allThreads[0] = Thread.currentThread() ;  // the "main" thread

      // Create the 2 shared variables X & Y

      SharedVariable X = new SharedVariable( 0 ) ;

      SharedVariable Y = new SharedVariable( 0 ) ;


      // Create the 4 threads

      allThreads[ 1 ]  = new Reader_82( "Reader-1", X, Y, 5 ) ; 

      allThreads[ 2 ]  = new Reader_82( "Reader-2", X, Y, 5 ) ; 

      allThreads[ 3 ]  = new Writer_82( "Writer-1", X, Y, 5, 0 ) ; 

      allThreads[ 4 ]  = new Writer_82( "Writer-2", X, Y, 5, 100 ) ; 


      // Start the 2 threads executing
      StartReadersWriters() ;

      // Ex 8.2
      // Wait a few seconds before getting their states
      try {
	       allThreads[0].sleep( OneSecond * time ) ;  
          } 
      catch (InterruptedException e) {}
      
      PrintThreadStates( "Reader & Writer Thread States after exiting run()" ) ;

      // Ex 8.1
      // Wait for the threads to terminate.
      JoinReadersWriters() ;

      // Ex 8.1
      PrintThreadStates( "Reader & Writer Thread States after exiting run()" ) ;

   }



   public static void StartReadersWriters()
    {
        for ( int i = 1 ; i < NumbThreads ; i++ )
	{
	     allThreads[i].start() ;
	}
    }



   public static void JoinReadersWriters()
   {
      try {
		for ( int i = 1 ; i < NumbThreads ; i++ )
		{
		   allThreads[i].join() ;
		}
          }
      catch ( InterruptedException ie ) {} ;
   }


   public static void PrintThreadStates( String heading )
    {
	
	System.out.println( "___________________________________________" ) ;
	System.out.println( heading + " THREAD STATES:" ) ;
	System.out.println() ;

        for ( int i = 0 ; i < NumbThreads ; i++ )
	{
	     System.out.println( allThreads[i].getName() + " is: " + allThreads[i].getState​() ) ;
	}

	System.out.println( "___________________________________________" ) ;
	
    } // PrintThreadStates


} // Sharing_82
