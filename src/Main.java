

public class Main {

    private final static int RIDER_MEAN_ARRIVAL_TIME = 30000;
    private final static int BUS_MEAN_ARRIVAL_TIME = 1200000;

    public static void main(String[] args) {

        SharedData sharedData = new SharedData();
        long currentTime;
        long riderDiffTime, riderPreviousTime = System.currentTimeMillis();
        long busDiffTime, busPreviousTime = System.currentTimeMillis();
        double busInterArrivalTime = BUS_MEAN_ARRIVAL_TIME;
        double riderInterArrivalTime = RIDER_MEAN_ARRIVAL_TIME;
        //20 min and 30 sec


        while(true){
            currentTime = System.currentTimeMillis();
            riderDiffTime = currentTime - riderPreviousTime;

            if (riderDiffTime >= riderInterArrivalTime){
             new Rider(sharedData).start();
             riderPreviousTime =currentTime;
             riderInterArrivalTime = Math.abs(RIDER_MEAN_ARRIVAL_TIME *Math.log(Math.random())) ;
                System.out.println("Rider interarrival time" + riderInterArrivalTime);
            }

            busDiffTime = currentTime - busPreviousTime;
            if (busDiffTime >= busInterArrivalTime){
                new Bus(sharedData).start();
                busPreviousTime = currentTime;
                busInterArrivalTime = Math.abs(BUS_MEAN_ARRIVAL_TIME* Math.log(Math.random()));
            }

        }

    }



}