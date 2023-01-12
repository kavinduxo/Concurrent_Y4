/** 
 *******************************************************
 ** File:   	Reader.java
 ** Author:	P. Howells
 ** Tutorial:   7: Introduction to Java Threads 
 ** Exercise:   7.3
 ** Modified:	6/11/22
 **
 ** ******************************************************* 
**/


class Reader extends Thread 
{

   private final SharedVariable sharedVar ;

   private final int OneSecond = 1000 ;
   private int   time = 1 ; 


   public Reader( String name, SharedVariable sharedVar ) 
   {
      super( name ) ;        
      this.sharedVar = sharedVar ;     
   }


   public void run() 
   {

      System.out.println( getName() + ": STARTED" ) ;

      for (int i = 0; i < 10; i++) 
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
