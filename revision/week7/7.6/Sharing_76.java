/** 
 *******************************************************
 ** File:   	Sharing.java
 ** Author:	P. Howells
 ** Tutorial:   7: Introduction to Java Threads 
 ** Exercise:   7.6
 ** Modified:	6/11/22
 ******************************************************* 
**/

class Sharing_76  
{

   public static void main ( String args[] ) 
   {

      SharedVariable X = new SharedVariable( 0 ) ;


      Reader_76 reader1 = new Reader_76( "Reader-1", X, 10 ) ; 

      Writer_76 writer1 = new Writer_76( "Writer-1", X, 7 ) ; 

     // Ex7.7

      Reader_76 reader2 = new Reader_76( "Reader-2", X, 10 ) ; 

      Writer_76 writer2 = new Writer_76( "Writer-2", X, 7 ) ; 



  
      // Start the 2 threads executing

      reader1.start() ;
      reader2.start() ;

      writer1.start() ;
      writer2.start() ;

   }

} // Sharing_76
