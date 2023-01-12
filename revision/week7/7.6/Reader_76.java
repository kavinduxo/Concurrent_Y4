/** 
 *******************************************************
 ** File:   	Reader_76.java
 ** Author:	P. Howells
 ** Tutorial:   7: Introduction to Java Threads 
 ** Exercise:   7.6
 ** Modified:	6/11/22
 **
 ** ******************************************************* 
**/


class Reader_76 extends Thread 
{

   private final SharedVariable sharedVar ;

   private int   count = 1 ; 

   private final int OneSecond = 1000 ;
   private       int time      = 1    ; 



   public Reader_76( String name, SharedVariable sharedVar, int count ) 
   {
      super( name ) ;        

      this.sharedVar = sharedVar ;     

      this.count = count ;     
   }


   public void run() 
   {

      System.out.println( getName() + ": STARTED" ) ;

      for ( int i = 0 ; i < count ; i++ ) 
      {
         System.out.println( getName() + " read: " + sharedVar.value( ) ) ;

         try {
                sleep( (int) ( Math.random() * OneSecond * time ) ) ;
             } 
         catch (InterruptedException e) {}

      }

      System.out.println( getName() + ": TERMINATING" ) ;

   }

} // Reader
