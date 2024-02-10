import java.util.Random;

class Philosopher extends Thread {
  private Chopstick left, right;
  private Random random;
  private int thinkCount;

  // Add our instance variables from command line arguments
  private int numCycles;

  // TODO might not need thinkingTime
  // private int thinkingTime;
  private int eatCount;
  private int maxEatingTime;
  private int id;

  // TODO Might not need this one either
  // private int rightLeft;

  // Constructor
  // int eatingTime, int rightLeft, int numCycles,

  public Philosopher(Chopstick left, Chopstick right, int thinkingTime, int maxEatingTime, int numCycles, int id) {
    this.left = left;
    this.right = right;
    // set instance variables
    // this.thinkingTime = thinkingTime;
    this.eatCount = 0;
    this.maxEatingTime = maxEatingTime;
    this.numCycles = numCycles;
    // this.rightLeft = rightLeft;
    this.id = id+1;

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

        // TODO Potentially remove this and replace it with time
        if (thinkCount % 10 == 0)
          System.out.println("Philosopher " + this + " has thought " + thinkCount + " times");

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
            this.eatCount = 0;
            // redo ^^
          }
        }
        }
        break;
      }
    } catch (InterruptedException e) {
    }
  }
}
