

public class Main {

    public static void main(String[] args) {

        SharedData sharedData = new SharedData();


        RiderCreator riderCreator = new RiderCreator(sharedData);
        riderCreator.start();

        BusCreator busCreator = new BusCreator(sharedData);
        busCreator.start();

    }


}