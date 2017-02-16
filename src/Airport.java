//YOUR NAME HERE

import java.util.*;

public class Airport implements EventHandler {

    //TODO add landing and takeoff queues, random variables

    private int m_inTheAir;
    private int m_onTheGround;
    private int m_airportnum;
    private boolean m_freeToLand;
    private Queue<AirportEvent> m_eventQueue;
    private double circlingtime;
    private HashMap<Double,Integer> hm;
    private int m_currentpassenger;
    //private double m_flightTime;
    private double m_runwayTimeToLand;
    private double m_requiredTimeOnGround;


    private String m_airportName;
    public int getM_onTheGround(){
        return this.m_onTheGround;
    }

    public HashMap<Double,Integer> getHm(){
        return this.hm;
    }
    public Airport(String name, double runwayTimeToLand, double requiredTimeOnGround) {
        m_airportName = name;
        switch (name){
            case ("LAX") :
                m_airportnum = 0;
                break;
            case ("SFO") :
                m_airportnum = 1;
                break;
            case ("IAD") :
                m_airportnum = 2;
                break;
            case ("DOH") :
                m_airportnum = 3;
                break;
            case ("PVG") :
                m_airportnum = 4;
                break;
        }
        m_inTheAir =  0;
        m_onTheGround = 0;
        m_freeToLand = true;
        m_runwayTimeToLand = runwayTimeToLand;
        m_requiredTimeOnGround = requiredTimeOnGround;
        m_currentpassenger = 1000;
        m_eventQueue = new LinkedList<AirportEvent>();
        hm = new HashMap<Double,Integer>();

    }
    public void getCirclingtime(){
        System.out.println(this.getName()+" Airport has circling time "+ this.circlingtime);
    }


    public int getM_airportnum(){
        return m_airportnum;
    }
    public void addPassenger(int a){
        this.m_currentpassenger += a;
    }
    public int getPassenger(){
        return m_currentpassenger;
    }
    public int getM_inTheAir(){
        return this.m_inTheAir;
    }
    public double getM_runwayTimeToLand(){
        return this.m_runwayTimeToLand;
    }
    public AirportEvent queuepop(Queue<AirportEvent> queue){
        AirportEvent ae = queue.poll();
        double addt = 0.0;
        if(ae.getType()==AirportEvent.PLANE_LANDED){
            addt = ae.getDestinationAirport().getM_runwayTimeToLand();
            ae.getDestinationAirport().circlingtime=ae.getId()*addt;
        }
        if(ae.getType()==AirportEvent.PLANE_DEPARTS){
            addt = ae.getDepatureAirport().getM_runwayTimeToLand();
            ae.getDepatureAirport().circlingtime=ae.getId()*addt;
        }

        ae.setTime(ae.getId()*addt);
        return ae;
    }

    public String getName() {
        return m_airportName;
    }


    public void handle(Event event) {
        AirportEvent airEvent = (AirportEvent)event;
        /*
        if(airEvent.getDestinationAirport().getm_eventQueue().size()!=0){
            handle(queuepop(airEvent.getDestinationAirport().getm_eventQueue()));
        }
        */
        //System.out.println("AIR EVENT CHECK"+airEvent.getDepatureAirport().getM_airportnum()+"  "+airEvent.getDestinationAirport().getM_airportnum());
        String airname = airEvent.getM_airplaneobj().getM_Name();
        int nump = airEvent.getM_airplaneobj().getM_numberPassengers();
        double m_flightTime = (double)airEvent.getFlightduration();
        switch(airEvent.getType()) {
            case AirportEvent.PLANE_ARRIVES:
                m_inTheAir++;
                System.out.println(Simulator.getCurrentTime() + ": Plane "+airname+" is approaching "+this.getName()+ " airport with "+nump+" passengers");
                System.out.println("Currently in "+this.getName()+" airport has "+this.getM_onTheGround()+
                        " flights on the ground and "+this.getM_inTheAir()+" in the air and "+ this.getPassenger()+" passengers");
                airEvent.resetEventType(AirportEvent.PLANE_LANDED);

                //System.out.println("###CASE1###"+airEvent.getFlightduration());


                m_eventQueue.add(airEvent); //Add the airplane to the landing queue
                while(m_eventQueue.size()>0){
                    AirportEvent ae = queuepop(m_eventQueue);
                    Simulator.schedule(ae);
                    //m_freeToLand = false;
                }

                break;

            case AirportEvent.PLANE_DEPARTS:
                //m_freeToLand = true;
                m_onTheGround--;
                m_currentpassenger= m_currentpassenger-airEvent.getM_airplaneobj().getM_numberPassengers();
                hm.put(Simulator.getCurrentTime(),this.m_currentpassenger);
                System.out.println(Simulator.getCurrentTime() + ": Plane "+airname+ " departs from "+this.getName()+" airport with "+nump+" passengers");
                System.out.println("Currently in "+this.getName()+" airport has "+this.getM_onTheGround()+
                        " flights on the ground and "+this.getM_inTheAir()+" in the air and "+ this.getPassenger()+" passengers");

                AirportEvent arrivalEvent = new AirportEvent(m_flightTime, airEvent.getDepatureAirport(), AirportEvent.PLANE_ARRIVES,airEvent.getM_airplaneobj()
                        ,airEvent.getDestinationAirport(),airEvent.getDepatureAirport());
                Simulator.schedule(arrivalEvent);

                if(!m_eventQueue.isEmpty()){
                    AirportEvent ae = queuepop(m_eventQueue);
                    Simulator.schedule(ae);
                }


                break;

            case AirportEvent.PLANE_LANDED:
                m_inTheAir--;
                m_onTheGround++;
                m_currentpassenger += airEvent.getM_airplaneobj().getM_numberPassengers();
                hm.put(Simulator.getCurrentTime(),this.m_currentpassenger);
                System.out.println(Simulator.getCurrentTime() + ": Plane "+airname+" lands at "+ this.getName()+" airport with "+nump+" passengers");
                System.out.println("Currently in "+this.getName()+" airport has "+this.getM_onTheGround()+
                        " flights on the ground and "+this.getM_inTheAir()+" in the air and "+ this.getPassenger()+" passengers");
                //m_freeToLand = true;
                airEvent.getM_airplaneobj().resetNumPassenger();
                //System.out.println("###CASE3###"+airEvent.getFlightduration());
                AirportEvent departureEvent = new AirportEvent(m_requiredTimeOnGround, this, AirportEvent.PLANE_DEPARTS,
                        airEvent.getM_airplaneobj(),airEvent.getDepatureAirport(),airEvent.getDestinationAirport());

                m_eventQueue.add(departureEvent);
                if(!m_eventQueue.isEmpty()){
                    AirportEvent ae = queuepop(m_eventQueue);
                    Simulator.schedule(ae);
                }

                break;
        }
    }
}
