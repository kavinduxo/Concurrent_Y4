/** 
 *******************************************************
 ** File:   	Sharing_77.java
 ** Author:	P. Howells
 ** Tutorial:   7: Introduction to Java Threads 
 ** Exercise:   7.7
 ** Modified:	6/11/22
 ******************************************************* 
**/

class Sharing_77  
{

   public static void main ( String args[] ) 
   {

      SharedVariable X = new SharedVariable( 0 ) ;


      Reader_76 reader1 = new Reader_76( "Reader-1", X, 10 ) ; 

      Writer_77 writer1 = new Writer_77( "Writer-1", X, 7, 0 ) ; 

     // Ex7.7

      Reader_76 reader2 = new Reader_76( "Reader-2", X, 10 ) ; 

      Writer_77 writer2 = new Writer_77( "Writer-2", X, 7, 100 ) ; 

  
      // Start the 2 threads executing

      reader1.start() ;
      reader2.start() ;

      writer1.start() ;
      writer2.start() ;

   }

} // Sharing_77

