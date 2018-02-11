/**
 * BusCreator class is responsible for scheduling {@link Bus} for inter-arrival time
 * */

public class BusCreator extends Thread{
    private final int BUS_MEAN_ARRIVAL_TIME = 120000; //mean arrival time of a bus
    private SharedData sharedData;
    private int busID = 0;

    public BusCreator(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    public void run(){
        while (!Thread.currentThread().isInterrupted()) {

            try {

                // Sleep rider creator thread until next rider creation time arrives.
                Thread.sleep(getBusInterArrivalTime());

                busID++;
                // Initializing and starting the rider threads
                Bus bus = new Bus(sharedData,busID);
                bus.start();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //calculates time to schedule next bus
    private long getBusInterArrivalTime(){

        return (long)Math.abs(BUS_MEAN_ARRIVAL_TIME* Math.log(Math.random()));
    }
}
