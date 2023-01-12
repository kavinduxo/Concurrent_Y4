/** 
 *******************************************************
 ** File:   	Writer_77.java
 ** Author:	P. Howells
 ** Tutorial:   7: Introduction to Java Threads 
 ** Exercise:   7.7
 ** Modified:	6/11/22
 ** ******************************************************* 
**/


class Writer_77 extends Thread 
{

   private final SharedVariable sharedVar ;

   private final int count ; 
   private final int lowerLimit ; 

   private final int OneSecond = 1000 ;
   private int   time = 1 ; 


   public Writer_77( String name, SharedVariable sharedVar, int count, int lowerLimit ) 
   {
      super( name ) ;  
      
      this.sharedVar = sharedVar ;     

      this.count = count ;     
      this.lowerLimit = lowerLimit ;     
    }


   public void run() 
   {

      System.out.println( getName() + ": STARTED" ) ;

      for ( int i = 0 ; i < count ; i++ ) 
      {

         int newValue = lowerLimit + i ;

         sharedVar.assign( newValue ) ;

         System.out.println( getName() + " assigned: " + newValue) ;

         try {
                sleep( (int) ( Math.random() * OneSecond * time ) ) ;
             } 
         catch (InterruptedException e) {}

      }

      System.out.println( getName() + ": TERMINATING" ) ;

   }

} // Writer_77
