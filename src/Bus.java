
public class Bus extends Thread {
    private SharedData sharedData;

    public Bus(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    public void run(){
        try {
        sharedData.getMutex().acquire();
            System.out.println("----------Bus Arrived----------");
        if (sharedData.getRiders() > 0){

            //signals arrival of the bus so that passengers are able to
            sharedData.getBusArrival().release();
            System.out.println("----------Bus Waiting----------");

            //bus waiting till all the passengers start to board.
            sharedData.getAllAboard().acquire();
         }
        } catch (InterruptedException e) {
             System.out.println("Unexpected Error occurred" +e);
         }finally {
            sharedData.getMutex().release();
            //Releasing the mutex at the end enable no rider arrived after bus arrived is eligible to board
        }

        depart();
    }

    private void depart(){
        System.out.println("----------Bus departing----------");
    }
}
