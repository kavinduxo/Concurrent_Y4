/** 
 *******************************************************
 ** File:   	Sharing.java
 ** Author:	P. Howells
 ** Tutorial:   7: Introduction to Java Threads 
 ** Exercise:   7.5
 ** Modified:	6/11/22
 ******************************************************* 
**/

class Sharing   
{

   public static void main ( String args[] ) 
   {

      SharedVariable X = new SharedVariable( 0 ) ;


      Reader reader1 = new Reader( "Reader-1", X ) ; 

      Writer writer1 = new Writer( "Writer-1", X ) ; 

  
      // Start the 2 threads executing

      reader1.start() ;
      writer1.start() ;

   }

} // Sharing
