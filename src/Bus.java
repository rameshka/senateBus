

public class Bus extends Thread {
    private SharedData sharedData;

    public Bus(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    public void run(){
        try {
        sharedData.getMutex().acquire();
        if (sharedData.getRiders() > 0){
            System.out.println("waiting passengers...*********************");
            sharedData.getBusArrival().release();
            sharedData.getAllAboard().acquire();
         }
        } catch (InterruptedException e) {
             e.printStackTrace();
         }finally {
            sharedData.getMutex().release();
        }

        depart();
    }

    private void depart(){
        System.out.println("bus departing...");
    }
}
