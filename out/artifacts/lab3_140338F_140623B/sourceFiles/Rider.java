
/**
 * This class functions similar to an arriving rider
 * */
public class Rider extends Thread {
    private SharedData sharedData;
    private int id;

    public Rider(SharedData sharedData,int id) {
        this.sharedData = sharedData;
        this.id = id;
    }

    public void run() {
        try {
            //Multiplex enables only maximum of 50 riders are able to board to an arriving bus
            sharedData.getMultiplex().acquire();

            System.out.println("Passenger: "+ id + " waiting till bus arrives");
            //A rider arriving after bus arrival can not increment the rider count
            sharedData.getMutex().acquire();
            sharedData.incrementRiders();    //rider enters critical region to mark him as a waiting passenger
            sharedData.getMutex().release();

            sharedData.getBusArrival().acquire(); //one rider can continue boarding once bus has arrived
            sharedData.getMultiplex().release();

            boardBus(); //boarding to the bus

            //decrementing the waiting rider count
            // waiting rider count is not synchronized as only one rider can board at a time (no concurrent issues)
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

    //boarding to the bus
    private void boardBus() {
        System.out.println("Passenger: "+ id + " boarding to the bus");
    }
}
