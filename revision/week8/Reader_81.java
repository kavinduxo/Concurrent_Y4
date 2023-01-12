/** 
 *******************************************************
 ** File:   	Reader_81  [Was: Reader_78.java]
 ** Author:	P. Howells
 ** Tutorial:   Week 08
 ** Exercise:   8.1 Based on Week 07 Tutorial, Ex 7.8
 ** Modified:	13/11/22
 **
 ** ******************************************************* 
**/


class Reader_81 extends Thread 
{

   private final SharedVariable X ;
   private final SharedVariable Y ;

   private int   count = 1 ; 

   private final int OneSecond = 1000 ;
   private int   time  = 1 ; 



   public Reader_81( String name, SharedVariable sv1, SharedVariable sv2, int count ) 
   {
      super( name ) ;        

      // Ex 8.1      
      System.out.println( getName() + " Thread State in Constructor is: " + getState​() ) ;

      this.X = sv1 ;     
      this.Y = sv2 ;     

      this.count = count ;     
   }


   public void run() 
   {

      System.out.println( getName() + ": STARTED" ) ;

      // Ex 8.1
      System.out.println( getName() + " Thread State in run() is: " + getState​() ) ;

      for ( int i = 0 ; i < count ; i++ ) 
      {
         System.out.println( getName() + " read X: " + X.value() ) ;
 
         System.out.println( getName() + " read Y: " + Y.value() ) ;

         try {
                sleep( (int) ( Math.random() * OneSecond * time ) ) ;
             } 
         catch (InterruptedException e) {}

      }

      System.out.println( getName() + ": TERMINATING" ) ;

   }

} // Reader_81
