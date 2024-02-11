import java.util.Random;

class Philosopher extends Thread {
  private Chopstick left, right;
  private Random random;
  private int thinkCount;

  // Add our instance variables from command line arguments
  private int numCycles;
  private int eatCount;
  private int maxEatingTime;
  private int id;
  private int rightLeft;

  // Constructor
  public Philosopher(Chopstick left, Chopstick right, int thinkingTime, int maxEatingTime, int numCycles, int id, int rightLeft) {
    this.left = left;
    this.right = right;
    // set instance variables
    // this.thinkingTime = thinkingTime;
    this.eatCount = 0;
    this.maxEatingTime = maxEatingTime;
    this.numCycles = numCycles;
    // this.rightLeft = rightLeft;
    this.id = id+1;
    this.rightLeft = rightLeft;
    /*
    if((this.id)%2==0){

      this.right = Chopstick;
      this.left = null;
    }
    */


    random = new Random();
  }

  public void run() {
    try {
      int currCycles = 0;
      while (true) {
        while (currCycles<=this.numCycles){
        if(numCycles>0){
          currCycles++;
        }
        ++thinkCount;
        // Every 10 seconds print out how many times a philosopher has thought

        // All philosophers are right-handed
          if (rightLeft==0){
            if (thinkCount % 10 == 0) {
              System.out.println("Philosopher " + this.id + " has thought " + thinkCount + " times");
            }
            // Think for a time between 0 and 1000
            Thread.sleep(random.nextInt(1000)); // Think for a while
            System.out.println("Philosopher " + this.id + " wants the left chopstick.");
            synchronized (left) { // Grab left chopstick
              System.out.println("Philosopher " + this.id + " has the left chopstick.");
              System.out.println("Philosopher " + this.id + " wants the right chopstick.");
              synchronized (right) { // Grab right chopstick
                System.out.println("Philosopher " + this.id + " has the right chopstick.");
                Thread.sleep(random.nextInt(1000)); // Eat for a while

                // Redo this entire part
                while (eatCount < maxEatingTime) {
                  ++eatCount;
                }
                System.out.println("Philosopher " + this.id + " has eaten for " + eatCount);
                // Reset the eat count
                this.eatCount = 0;
                // redo ^^
              }
          }
        }
          // Even philosophers are right-handed, odd are left-handed (hypothetically should produce a deadlock)
          if(rightLeft!=0) {
            if (thinkCount % 10 == 0) {
              System.out.println("Philosopher " + this.id + " has thought " + thinkCount + " times");
            }
            // Think for a time between 0 and 1000
            Thread.sleep(random.nextInt(1000)); // Think for a while
            if(this.id % 2 != 0) {
              System.out.println("Philosopher " + this.id + " wants the left chopstick.");
              // Grab the left chopstick first
              synchronized (left) {
                System.out.println("Philosopher " + this.id + " has the left chopstick.");
                System.out.println("Philosopher " + this.id + " wants the right chopstick.");
                synchronized (right) {
                  System.out.println("Philosopher " + this.id + " has the right chopstick.");
                  while (eatCount < maxEatingTime) {
                    ++eatCount;
                  }
                  System.out.println("Philosopher " + this.id + " has eaten for " + eatCount);
                  // Reset the eat count
                  this.eatCount = 0;
                }
              }
            }
            else {
              System.out.println("Philosopher" + this.id + "wants the right chopstick");
              synchronized (left){
                System.out.println("Philosopher" + this.id + "has the right chopstick");
                System.out.println("Philosopher" + this.id + "wants the left chopstick");
                synchronized (right){
                  System.out.println("Philosopher" + this.id + "has the left chopstick");
                  while (eatCount < maxEatingTime) {
                    ++eatCount;
                  }
                  System.out.println("Philosopher " + this.id + " has eaten for " + eatCount);
                  // Reset the eat count
                  this.eatCount = 0;
                }
              }
            }
          }


        }
        break;
      }
    } catch (InterruptedException e) {
    }
  }
}
