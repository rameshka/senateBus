import java.util.concurrent.Semaphore;

public class SharedData {
    private Semaphore allAboard = new Semaphore(0);
    private Semaphore multiplex = new Semaphore(50);
    private Semaphore mutex = new Semaphore(1);
    private Semaphore busArrival = new Semaphore(0);


    private int riders = 0;

    public Semaphore getBusArrival() {
        return busArrival;
    }

    public void setBusArrival(Semaphore busArrival) {
        this.busArrival = busArrival;
    }

    public Semaphore getAllAboard() {
        return allAboard;
    }

    public Semaphore getMutex() {
        return mutex;
    }

    public void setMutex(Semaphore mutex) {
        this.mutex = mutex;
    }

    public void setAllAboard(Semaphore allAboard) {
        this.allAboard = allAboard;
    }

    public Semaphore getMultiplex() {
        return multiplex;
    }

    public void setMultiplex(Semaphore multiplex) {
        this.multiplex = multiplex;
    }

    public int getRiders() {
        return riders;

    }

    public void setRiders() {
        riders = riders + 1;
    }

    public void decrementRiders(){
        riders = riders - 1;
    }
}
