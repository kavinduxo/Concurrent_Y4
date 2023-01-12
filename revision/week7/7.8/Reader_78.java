/** 
 *******************************************************
 ** File:   	Reader_78.java
 ** Author:	P. Howells
 ** Tutorial:   7: Introduction to Java Threads 
 ** Exercise:   7.8
 ** Modified:	6/11/22
 **
 ** ******************************************************* 
**/


class Reader_78 extends Thread 
{

   private final SharedVariable X ;
   private final SharedVariable Y ;

   private int   count = 1 ; 

   private final int OneSecond = 1000 ;
   private int   time  = 1 ; 



   public Reader_78( String name, SharedVariable sv1, SharedVariable sv2, int count ) 
   {
      super( name ) ;        

      this.X = sv1 ;     
      this.Y = sv2 ;     

      this.count = count ;     
   }


   public void run() 
   {

      System.out.println( getName() + ": STARTED" ) ;

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

} // Reader_78
