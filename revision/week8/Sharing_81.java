/** 
 *******************************************************
 ** File:   	Sharing_81.java [WAS: Sharing_78.java]
 ** Author:	P. Howells
 ** Tutorial:   8: Java Threads States etc 
 ** Exercise:   8.1, based on Week 07 Tutorial, Ex 7.8
 ** Modified:   13/11/2022
 ******************************************************* 
**/

/**
  Ex 8.1  
  Questions: What do you notice about all of the thread states that are output? 
             Why do you think this is happening? 

  Answer:  Any call to getState() inside a threads run() method will always 
	   return RUNNABLE because it only ever executes code when its in the 
	   RUNNABLE state.
**/


class Sharing_81
{

   private static final int NumbThreads = 5 ;
   
   private static Thread[] allThreads = new Thread[ NumbThreads ] ;

   public static void main ( String args[] ) 
   {

     allThreads[0] = Thread.currentThread() ;  // the "main" thread

      // Create the 2 shared variables X & Y

      SharedVariable X = new SharedVariable( 0 ) ;

      SharedVariable Y = new SharedVariable( 0 ) ;


      // Create the 4 threads

      allThreads[ 1 ] = new Reader_81( "Reader-1", X, Y, 5 ) ; 

      allThreads[ 2 ] = new Reader_81( "Reader-2", X, Y, 5 ) ; 

      allThreads[ 3 ] = new Writer_81( "Writer-1", X, Y, 5, 0 ) ; 

      allThreads[ 4 ] = new Writer_81( "Writer-2", X, Y, 5, 100 ) ; 


      PrintThreadStates( "CREATED - Reader & Writer threads" ) ;

      // Start the threads executing
      StartReadersWriters() ;

      PrintThreadStates( "STARTED - Reader & Writer threads" ) ;

      for( int i = 1 ; i <= 5 ; i++ )
      {
	  PrintThreadStates( "(" + i +") Running - Reader & Writer threads" ) ;

          try {
	          allThreads[0].sleep( 500 ) ;
              }
          catch( Exception e ) {}
      }

      // Ex 8.1
      // Wait for the threads to terminate.
      JoinReadersWriters() ;

      // Ex 8.1
      PrintThreadStates( "Reader & Writer Thread States after exiting run()" ) ;

   } // main



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


} // Sharing_81
