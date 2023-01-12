/** 
 *******************************************************
 **   File:   	TwoThreadsTest.java
 **   Author:	P. Howells
 **   Lecture:	Introduction to Threads
 **   Modified:	6/11/22
 **
 ** Tutorial 7: Introduction to Java Threads 
 ** Exercise 7.1
 ** 
 ******************************************************* 
**/

class TwoThreadsTest    // the ``main'' class (program)
{

   public static void main ( String args[] ) 
   {
      // Declare 2 thread variables

      Thread firstThrd ;
      Thread secondThrd ;
      
      // Create the 2 threads

      firstThrd  = new SimpleThread("FirstThread") ;
      secondThrd = new SimpleThread("SecondThread") ;


      // Ex 7.1 (b)
      System.out.println( "new - firstThrd in State: " + firstThrd.getState() ) ;
      System.out.println( "new - secondThrd in State: " + secondThrd.getState() ) ;

      // Start the 2 threads executing

      firstThrd.start() ;
      secondThrd.start() ;

      // Ex 7.1 (b)
      System.out.println( "start - firstThrd in State: " + firstThrd.getState() ) ;
      System.out.println( "start - secondThrd in State: " + secondThrd.getState() ) ;

   }

} // TwoThreadsTest
