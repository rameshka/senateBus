
public class Rider extends Thread {
    private SharedData sharedData;

    public Rider(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    public void run() {
        try {
            System.out.println("thread name" + getName());
            sharedData.getMultiplex().acquire();
            sharedData.getMutex().acquire();    //similar to a lock
            sharedData.setRiders();
            sharedData.getMutex().release();

            System.out.println("wait Till Bus.... " + getName());
            sharedData.getBusArrival().acquire();
            sharedData.getMultiplex().release();

            boardBus();

            sharedData.decrementRiders();

            if (sharedData.getRiders() == 0) {
                sharedData.getAllAboard().release();
            } else {
                sharedData.getBusArrival().release();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void boardBus() {
        System.out.println("boarding to the bus "+ getName());
    }
}
