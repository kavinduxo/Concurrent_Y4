/** 
 *******************************************************
 ** File:   	Sharing_78.java
 ** Author:	P. Howells
 ** Tutorial:   7: Introduction to Java Threads 
 ** Exercise:   7.8
 ** Modified:	6/11/22
 ******************************************************* 
**/

class Sharing_78 
{

   public static void main ( String args[] ) 
   {


      // Create the 2 shared variables X & Y

      SharedVariable X = new SharedVariable( 0 ) ;

      SharedVariable Y = new SharedVariable( 0 ) ;


      // Create the 4 threads

      Reader_78 reader1 = new Reader_78( "Reader-1", X, Y, 5 ) ; 

      Reader_78 reader2 = new Reader_78( "Reader-2", X, Y, 5 ) ; 

      Writer_78 writer1 = new Writer_78( "Writer-1", X, Y, 5, 0 ) ; 

      Writer_78 writer2 = new Writer_78( "Writer-2", X, Y, 5, 100 ) ; 


      // Start the 2 threads executing

      reader1.start() ;
      reader2.start() ;

      writer1.start() ;
      writer2.start() ;

   }

} // Sharing_78
