/** 
 *******************************************************
 ** File:   	Writer_78.java
 ** Author:	P. Howells
 ** Tutorial:   7: Introduction to Java Threads 
 ** Exercise:   7.8
 ** Modified:	6/11/22
 **
 ** ******************************************************* 
**/


class Writer_78 extends Thread 
{

   private final SharedVariable X ;
   private final SharedVariable Y ;

   private final int count ; 
   private final int lowerLimit ; 


   private final int OneSecond = 1000 ;
   private int   time = 1 ; 


   public Writer_78( String name,  SharedVariable sv1, SharedVariable sv2, 
                     int count, int lowerLimit  ) 
   {
      super( name ) ;  
      
      this.X = sv1 ;     
      this.Y = sv2 ;     

      this.count = count ;     
      this.lowerLimit = lowerLimit ;     
    }


   public void run() 
   {

      System.out.println( getName() + ": STARTED" ) ;

      for ( int i = 0 ; i < count ; i++ ) 
      {
         int newValue = lowerLimit + i ;

         X.assign( newValue ) ;

         System.out.println( getName() + " assigned X: " + newValue) ;

         Y.assign( newValue ) ;

         System.out.println( getName() + " assigned Y: " + newValue) ;

         try {
                sleep( (int) ( Math.random() * OneSecond * time ) ) ;
             } 
         catch (InterruptedException e) {}

      }

      System.out.println( getName() + ": TERMINATING" ) ;

   }

} // Writer_78
