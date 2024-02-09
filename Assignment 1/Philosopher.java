import java.util.Random;

class Philosopher extends Thread {
  private Chopstick left, right;
  private Random random;
  private int thinkCount;

  // Add our instance variables from command line arguments
  // private int numCycles;

  // TODO might not need thinkingTime
  // private int thinkingTime;
  private int maxEatingTime;

  // TODO Might not need this one either
  // private int rightLeft;

  // Constructor
  // int eatingTime, int rightLeft, int numCycles,

  public Philosopher(Chopstick left, Chopstick right, int thinkingTime, int maxEatingTime) {
    this.left = left;
    this.right = right;

    // set instance variables
    // this.numCycles = numCycles;
    // this.thinkingTime = thinkingTime;
    this.maxEatingTime = maxEatingTime;
    // this.rightLeft = rightLeft;

    random = new Random();
  }

  public void run() {
    try {
      while (true) {
        ++thinkCount;
        // Every 10 seconds print out how many times a philosopher has thought

        // TODO Potentially remove this and replace it with time
        if (thinkCount % 10 == 0)
          System.out.println("Philosopher " + this + " has thought " + thinkCount + " times");

        // Think for a time between 0 and 1000
        Thread.sleep(random.nextInt(1000)); // Think for a while
        System.out.println("Philosopher " + this + "wants the left chopstick.");
        synchronized (left) { // Grab left chopstick
          synchronized (right) { // Grab right chopstick
            System.out.println("Philosopher " + this + "wants the right chopstick.");
            Thread.sleep(random.nextInt(1000)); // Eat for a while

            // Redo this entire part
            while (thinkCount < maxEatingTime) {
              ++maxEatingTime;
              System.out.println("Philosopher " + this + " has eaten for " + maxEatingTime);
            }
            // redo ^^

          }
        }
      }
    } catch (InterruptedException e) {
    }
  }
}
