/**
 * BusCreator class is responsible for scheduling {@link Bus} for inter-arrival time
 * */

public class BusCreator extends Thread{
    private final int BUS_MEAN_ARRIVAL_TIME = 12000;
    private SharedData sharedData;

    public BusCreator(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    public void run(){
        while (!Thread.currentThread().isInterrupted()) {

            try {
                // Initializing and starting the rider threads
                Bus bus = new Bus(sharedData);
                bus.start();

                // Sleep rider creator thread until next rider creation time arrives.
                Thread.sleep(getBusInterArrivalTime());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private long getBusInterArrivalTime(){

        return (long)Math.abs(BUS_MEAN_ARRIVAL_TIME* Math.log(Math.random()));
    }
}
