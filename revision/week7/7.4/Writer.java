/** 
 *******************************************************
 ** File:   	Writer.java
 ** Author:	P. Howells
 ** Tutorial:   7: Introduction to Java Threads 
 ** Exercise:   7.4
 ** Modified:	6/11/22
 **
 ** ******************************************************* 
**/


class Writer extends Thread 
{

   private final SharedVariable sharedVar ;

   private final int OneSecond = 1000 ;
   private int   time = 1 ; 


   public Writer( String name, SharedVariable sharedVar ) 
   {
      super( name ) ;        
      this.sharedVar = sharedVar ;     
   }


   public void run() 
   {

      System.out.println( getName() + ": STARTED" ) ;

      for (int i = 0; i < 10; i++) 
      {
         sharedVar.assign( i ) ;

        try {
               sleep( 2000 ) ;   /*  Reader-1 reads first few numbers several times & 
                                     then terminates, "Writer-1 takes much longer to 
                                     write numbers ot X, & only a few are read.        */
	    } 
        catch (InterruptedException e) {}

         System.out.println( getName() + " assigned: " + i ) ;

         try {
                sleep( (int) ( Math.random() * OneSecond * time ) ) ;
             } 
         catch (InterruptedException e) {}

      }

      System.out.println( getName() + ": TERMINATING" ) ;

   }

} // Writer
