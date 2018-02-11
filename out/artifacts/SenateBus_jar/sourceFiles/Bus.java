
/**
 * This class function as an arriving bus
 * */

public class Bus extends Thread {
    private SharedData sharedData;
    private int id;

    public Bus(SharedData sharedData, int id) {
        this.sharedData = sharedData;
        this.id = id;
    }

    public void run(){
        try {
        sharedData.getMutex().acquire();// acquires mutex before reading rider count
            System.out.println("----------Bus "+ id +" Arrived----------");
        if (sharedData.getRiders() > 0){

            //signals arrival of the bus to a passenger so that they can board
            sharedData.getBusArrival().release();
            System.out.println("----------Bus "+ id +" Waiting----------");

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

    //bus departing
    private void depart(){
        System.out.println("----------Bus "+id+" departing----------");
    }
}
