import java.util.Random;

class Philosopher extends Thread {
  private Chopstick left, right;
  private Random random;
  private int thinkCount;

  // Add our instance variables from command line arguments
  // private int numCycles;

  // TODO might not need thinkingTime
  // private int thinkingTime;
  private int eatingTime;

  // TODO Might not need this one either
  // private int rightLeft;

  // Constructor
  // int eatingTime, int rightLeft, int numCycles,

  public Philosopher(Chopstick left, Chopstick right, int thinkingTime) {
    this.left = left;
    this.right = right;

    // set instance variables
    // this.numCycles = numCycles;
    // this.thinkingTime = thinkingTime;
    this.eatingTime = eatingTime;
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
        synchronized (left) { // Grab left chopstick
          synchronized (right) { // Grab right chopstick
            Thread.sleep(random.nextInt(1000)); // Eat for a while
            ++eatingTime;
            if (thinkCount % 10 == 0)
              System.out.println("Philosopher " + this + " has eaten for " + eatingTime + " ms");
          }
        }
      }
    } catch (InterruptedException e) {
    }
  }
}
