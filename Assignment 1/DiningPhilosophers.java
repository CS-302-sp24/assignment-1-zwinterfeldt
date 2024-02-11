public class DiningPhilosophers {

  public static void main(String[] args) throws InterruptedException {

    int numPhilosophers = Integer.parseInt(args[0]);
    int thinkingTime = Integer.parseInt(args[1]);
    int maxEatingTime = Integer.parseInt(args[2]);
    int numCycles = Integer.parseInt(args[3]);
    int rightLeft = Integer.parseInt(args[4]);

    Philosopher[] philosophers = new Philosopher[numPhilosophers];
    Chopstick[] chopsticks = new Chopstick[numPhilosophers];

    // Create new chopsticks
    for (int i = 0; i < numPhilosophers; ++i) {
      chopsticks[i] = new Chopstick(i);
    }
    // Create new Philosophers
    for (int i = 0; i < numPhilosophers; ++i) {
      philosophers[i] = new Philosopher(chopsticks[i], chopsticks[(i + 1) % numPhilosophers], thinkingTime, maxEatingTime, numCycles, i, rightLeft);
      philosophers[i].start();
    }

    // Waits for Philosophers (threads) to die
    for (int i = 0; i < numPhilosophers; ++i) {
      philosophers[i].join();
    }
  }
}