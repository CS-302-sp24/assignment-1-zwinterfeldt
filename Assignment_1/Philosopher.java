import java.util.Random;
import java.io.*;

class Philosopher extends Thread {
  private FileWriter fileWriter;
  private PrintWriter printWriter;
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
    try{
      fileWriter = new FileWriter("Trace.txt", true);
      printWriter = new PrintWriter(fileWriter);
    }
    catch (IOException e){
      e.printStackTrace();
    }

    random = new Random();
  }

  public void run() {
    try {
      // Set the current number of cycles
      // System.out.println(System.getProperty("user.dir"));
      int currCycles = 0;
      while (true) {
        while (currCycles<this.numCycles){
        if(numCycles>0){
          currCycles++;
        }
        ++thinkCount;
        // Every 10 seconds print out how many times a philosopher has thought

        // All philosophers are right-handed
          if (rightLeft==0){
            if (thinkCount % 10 == 0) {
              printWriter.println("Philosopher " + this.id + " has thought " + thinkCount + " times");
            }
            // Think for a time between 0 and 1000
            Thread.sleep(random.nextInt(1000)); // Think for a while
            printWriter.println("Philosopher " + this.id + " wants the right chopstick.");
            synchronized (right) { // Grab right chopstick
              printWriter.println("Philosopher " + this.id + " has the right chopstick.");
              printWriter.println("Philosopher " + this.id + " wants the left chopstick.");
              synchronized (left) { // Grab left chopstick
                printWriter.println("Philosopher " + this.id + " has the left chopstick.");
                eatCount = random.nextInt(maxEatingTime);
                Thread.sleep(eatCount); // Eat for a while

                // Redo this entire part
                /*while (eatCount < maxEatingTime) {
                  ++eatCount;
                }*/
                printWriter.println("Philosopher " + this.id + " has eaten for " + eatCount);
              }
          }
            // Reset the eat count
            this.eatCount = 0;
            // redo ^^
        }
          // Even philosophers are right-handed, odd are left-handed (hypothetically should produce a deadlock)
          if(rightLeft!=0) {
            if (thinkCount % 10 == 0) {
              printWriter.println("Philosopher " + this.id + " has thought " + thinkCount + " times");
            }
            // Think for a time between 0 and 1000
            Thread.sleep(random.nextInt(1000)); // Think for a while
            // Odd-numbered philosophers
            if(this.id % 2 != 0) {
              printWriter.println("Philosopher " + this.id + " wants the left chopstick.");
              // Grab the left chopstick first
              synchronized (left) {
                printWriter.println("Philosopher " + this.id + " has the left chopstick.");
                printWriter.println("Philosopher " + this.id + " wants the right chopstick.");
                synchronized (right) {
                  printWriter.println("Philosopher " + this.id + " has the right chopstick.");
                  eatCount = random.nextInt(maxEatingTime);
                  Thread.sleep(eatCount); // Eat for a while // Eat for awhile
                  /*while (eatCount < maxEatingTime) {
                    ++eatCount;
                  }*/
                  printWriter.println("Philosopher " + this.id + " has eaten for " + eatCount);
                }
              }
              // Reset the eat count
              this.eatCount = 0;
            }

            // Even-numbered philosophers
            else {
              printWriter.println("Philosopher " + this.id + " wants the right chopstick");
              synchronized (right){
                printWriter.println("Philosopher " + this.id + " has the right chopstick");
                printWriter.println("Philosopher " + this.id + " wants the left chopstick");
                synchronized (left){
                  printWriter.println("Philosopher " + this.id + " has the left chopstick");
                  eatCount = random.nextInt(maxEatingTime);
                  Thread.sleep(eatCount); // Eat for awhile
                  /*while (eatCount < maxEatingTime) {
                    ++eatCount;
                  }*/
                  printWriter.println("Philosopher " + this.id + " has eaten for " + eatCount);
                }
              }
              // Reset the eat count
              this.eatCount = 0;
            }
          }


        }
        break;
      }
      printWriter.close();
      //fileWriter.close();
    } catch (InterruptedException e) {
      // e.printStackTrace();
    }
  }
}
