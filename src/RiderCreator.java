
/**
 * RiderCreator class is responsible for scheduling {@link Rider} for specified inter-arrival time.
 **/

public class RiderCreator extends Thread{

    private final int RIDER_MEAN_ARRIVAL_TIME = 300;
    private SharedData sharedData;

    public RiderCreator(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    public void run(){
        while (!Thread.currentThread().isInterrupted()) {

            try {
                // Initializing and starting the rider threads
                Rider rider = new Rider(sharedData);
                rider.start();

                // Sleep rider creator thread until next rider creation time arrives.
                Thread.sleep(getRiderInterArrivalTime());

            } catch (InterruptedException e) {
                System.out.println("Rider Creator Interupted : "+ e);
            }
        }
    }

    private long getRiderInterArrivalTime(){

        return (long)Math.abs(RIDER_MEAN_ARRIVAL_TIME* Math.log(Math.random()));
    }

}
