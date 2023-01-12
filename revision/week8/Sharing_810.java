/** 
 *******************************************************
 ** File:   	Sharing_810.java [WAS: Sharing_88.java]
 ** Author:	P. Howells
 ** Tutorial:   8: Java Threads States  
 ** Exercise:   8.10, based on Tutorial 8, Ex 8.8
 ** Modified:   13/11/2022
 ******************************************************* 
**/

/**
  Ex 8.10
  Questions: Does this terminate all the threads? 

  Answer:  Yes, no difference.
**/


class Sharing_810
{

   private static final int NumbThreads = 5 ;

   private static Thread[] allThreads = new Thread[ NumbThreads ] ;

   public static void main ( String args[] ) 
   {
     final int OneSecond = 1000 ;

     allThreads[0] = Thread.currentThread() ;  // the "main" thread

      // Create the 2 shared variables X & Y

      SharedVariable X = new SharedVariable( 0 ) ;

      SharedVariable Y = new SharedVariable( 0 ) ;


      // Create the 4 threads

      allThreads[ 1 ] = new Reader_810( "Reader-1", X, Y, 5 ) ; 

      allThreads[ 2 ] = new Reader_810( "Reader-2", X, Y, 5 ) ; 

      allThreads[ 3 ] = new Writer_810( "Writer-1", X, Y, 5, 0 ) ; 

      allThreads[ 4 ] = new Writer_810( "Writer-2", X, Y, 5, 100 ) ; 

      // Start the threads executing
      StartReadersWriters() ;

      PrintThreadStates( "STARTED - Reader & Writer threads" ) ;

      try {
               allThreads[0].sleep( OneSecond * 2 ) ;
          }
      catch( Exception e ) {}

      TerminateReadersWriters() ;

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


   // Tutorial Exercise 8.10
   public static void TerminateReadersWriters()
    {
     
	System.out.println( "___________________________________________" ) ;
	System.out.println( " Main terminating all Readers & Writer:" ) ;
	     
        // Need to cast: Thread -> Reader_810 or Writer_810, as Thread doesn't have "terminate()"

        ( ( Reader_810) allThreads[1] ).terminate() ;     
	( ( Reader_810) allThreads[2] ).terminate() ;     

	( ( Writer_810) allThreads[3] ).terminate() ;     
	( ( Writer_810) allThreads[4] ).terminate() ; 

	System.out.println( "___________________________________________" ) ;
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


} // Sharing_810
