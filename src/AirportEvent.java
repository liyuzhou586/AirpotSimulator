//YOUR NAME HERE

public class AirportEvent extends Event {
    public static final int PLANE_ARRIVES = 0;
    public static final int PLANE_LANDED = 1;
    public static final int PLANE_DEPARTS = 2;
    private Airplane m_airplaneobj;
    private Airport depatureAirport;
    private Airport destinationAirport;
    private int[][] distanceMatrix = {{0,600,2500,8000,5800},{600,0,2500,7500,5500},{2500,2500,0,6000,8300},
            {8000,7500,5800,0,4000},{5800,5500,8300,4000,0}};
    private double flightduration;
    private double computeTime(int[][] distmatrix, int c1, int c2, Airplane model ){
        //System.out.println("THISIS FROM COMPUTE TIME"+c1+"  "+c2);
        double speed = model.getM_speed();
        int dist =  distmatrix[c1][c2];
        double time;
        time = dist/speed;
        return time;
    }
    AirportEvent(double delay, EventHandler handler, int eventType) {

        super(delay, handler, eventType);
    }

    /**
     * Constructor
     * @param delay time period;
     * @param handler
     * @param eventType
     * @param modelplane
     * @param start
     * @param end
     */
    AirportEvent(double delay,EventHandler handler, int eventType, Airplane modelplane, Airport start, Airport end){
        this(delay,handler,eventType);
        this.m_airplaneobj = modelplane;
        this.depatureAirport = start;
        this.destinationAirport = end;
        this.flightduration = computeTime(distanceMatrix,depatureAirport.getM_airportnum(),destinationAirport.getM_airportnum(),modelplane);
    }
    public Airplane getM_airplaneobj(){
        return this.m_airplaneobj;
    }
    public double getFlightduration(){
        return this.flightduration;
    }
    public Airport getDepatureAirport(){
        return this.depatureAirport;
    }
    public Airport getDestinationAirport(){
        return this.destinationAirport;
    }

}
