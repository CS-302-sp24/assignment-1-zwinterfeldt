public class DiningPhilosophers {

  public static void main(String[] args) throws InterruptedException {
    Philosopher[] philosophers = new Philosopher[5];
    Chopstick[] chopsticks = new Chopstick[5];

    // Create 5 new chopsticks
    for (int i = 0; i < 5; ++i)
      chopsticks[i] = new Chopstick(i);

    // Creare 5 new Philosophers
    for (int i = 0; i < 5; ++i) {
      philosophers[i] = new Philosopher(chopsticks[i], chopsticks[(i + 1) % 5], 12);
      philosophers[i].start();
    }

    // Waits for Philosophers (threads) to die
    for (int i = 0; i < 5; ++i)
      philosophers[i].join();
  }
}
