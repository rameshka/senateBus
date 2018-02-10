
public class Rider extends Thread {
    private SharedData sharedData;

    public Rider(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    public void run() {
        try {
            //Multiplex enables only maximum of 50 riders are able to wait
            sharedData.getMultiplex().acquire();

            //rider entering critical region to increment rider count
            //A rider arriving after bus arrival can not increment the rider count
            System.out.println("Passenger: "+ getName() + " waiting till bus arrives");
            sharedData.getMutex().acquire();
            sharedData.incrementRiders();
            sharedData.getMutex().release();

            sharedData.getBusArrival().acquire(); //first rider can continue once bus has arrived
            sharedData.getMultiplex().release();

            boardBus();

            //decrementing the waiting rider are not synchronize as only one rider can board at a time (no concurrent issues)
            sharedData.decrementRiders();

            if (sharedData.getRiders() == 0) {
                //last rider informs the bus, that all eligible waiting passengers are boarded
                sharedData.getAllAboard().release();
            } else {
                //each rider informs one another eligible rider that bus has arrived
                sharedData.getBusArrival().release();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void boardBus() {
        System.out.println("Passenger: "+ getName() + " boarding to the bus");
    }
}
