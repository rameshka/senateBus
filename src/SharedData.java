import java.util.concurrent.Semaphore;

/**
 * SharedDate class contains data shared between {@link Bus} and {@link Rider}
 * */
public class SharedData {

    //variable to store rider count
    private int riders = 0;

    //semaphore indicating the maximum number of riders allowed to board to the bus
    private Semaphore multiplex = new Semaphore(50);

    //semaphore used in place of the mutex controlling access to rider count
    private Semaphore mutex = new Semaphore(1);

    //semaphore used to indicate bus arrival
    private Semaphore busArrival = new Semaphore(0);

    //semaphore used to indicate all passengers have boarded to the bus
    private Semaphore allAboard = new Semaphore(0);


    public Semaphore getBusArrival() {
        return busArrival;
    }

    public Semaphore getAllAboard() {
        return allAboard;
    }

    public Semaphore getMutex() {
        return mutex;
    }


    public Semaphore getMultiplex() {
        return multiplex;
    }

    public int getRiders() {
        return riders;

    }

    public void incrementRiders() {
        riders = riders + 1;
    }

    public void decrementRiders(){
        riders = riders - 1;
    }
}
