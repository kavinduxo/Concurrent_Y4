/** 
 *******************************************************
 ** File:   	ThreeThreadsTest.java
 ** Author:	P. Howells
 ** Tutorial:   7: Introduction to Java Threads 
 ** Exercise:    7.1
 ** Modified:	6/11/22
 ******************************************************* 
**/

class ThreeThreadsTest    // the ``main'' class (program)
{

   public static void main (String args[]) 
   {
      // Declare 3 thread variables

      Thread  firstThrd ;
      Thread  secondThrd ;
      Thread  thirdThrd ;


      // Create the 3 threads

       firstThrd  = new SimpleThread( "FirstThread" )  ;
       secondThrd = new SimpleThread( "SecondThread" ) ;
       thirdThrd  = new SimpleThread( "ThirdThread" )  ;

      // Ex 7.1 (b)
      System.out.println( "new - firstThrd  in State: " + firstThrd.getState() )  ;
      System.out.println( "new - secondThrd in State: " + secondThrd.getState() ) ;
      System.out.println( "new - thirdThrd  in State: " + secondThrd.getState() ) ;

      // Start the 3 threads executing

      firstThrd.start() ;
      secondThrd.start() ;
      thirdThrd.start() ;

      // Ex 7.1 (b)
      System.out.println( "start - firstThrd  in State: " + firstThrd.getState() )  ;
      System.out.println( "start - secondThrd in State: " + secondThrd.getState() ) ;
      System.out.println( "start - thirdThrd  in State: " + secondThrd.getState() ) ;

   }

}
