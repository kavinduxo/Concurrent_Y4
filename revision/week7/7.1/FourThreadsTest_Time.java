/** 
 *******************************************************
 **   File:   	FourThreadsTest_Time.java
 **   Author:	P. Howells
 **   Lecture:	Introduction to Threads
 **   Modified:	6/11/22
 **
 ** Tutorial 7: Introduction to Java Threads 
 ** Exercise 7.1 (c) & (d)
 ** 
 ******************************************************* 
**/

class FourThreadsTest_Time    // the ``main'' class (program)
{

   public static void main ( String args[] ) 
   {
      // Declare 2 thread variables

      Thread firstThrd ;
      Thread secondThrd ;
      Thread thirdThrd ;
      Thread fourthThrd ;
      
      // Create the 4 threads

      firstThrd  = new SimpleThread_Time("FirstThread",  1 ) ;
      secondThrd = new SimpleThread_Time("SecondThread", 2 ) ;
      thirdThrd  = new SimpleThread_Time("ThirdThread",  3 ) ;
      fourthThrd = new SimpleThread_Time("FourthThread", 4 ) ;


      // Ex 7.1 (b)
      System.out.println( "new - firstThrd  in State: " + firstThrd.getState()  ) ;
      System.out.println( "new - secondThrd in State: " + secondThrd.getState() ) ;
      System.out.println( "new - thirdThrd  in State: " + thirdThrd.getState()  ) ;
      System.out.println( "new - fourthThrd in State: " + fourthThrd.getState() ) ;

      // Start the 4 threads executing

      firstThrd.start() ;
      secondThrd.start() ;
      thirdThrd.start() ;
      fourthThrd.start() ;


      // Ex 7.1 (b)
      System.out.println( "start - firstThrd  in State: " + firstThrd.getState() ) ;
      System.out.println( "start - secondThrd in State: " + secondThrd.getState() ) ;
      System.out.println( "start - thirdThrd  in State: " + thirdThrd.getState()  ) ;
      System.out.println( "start - fourthThrd in State: " + fourthThrd.getState() ) ;

   }

} // FourThreadsTest_Time
