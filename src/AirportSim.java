//YOUR NAME HERE

import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeSet;


public class AirportSim {
    static Random ran = new Random();


    public static void printhm(Airport ar){
        System.out.println("THIS IS AIRPORT " + ar.getName());
        HashMap<Double,Integer> hm = new HashMap<>();
        hm = ar.getHm();
        for (Double key:hm.keySet()){
            System.out.println(hm.get(key));
        }
    }

    public static void main(String[] args) {
        String[] airportlist = {"LAX","SFO","IAD","DOH","PVG"};
        int[][] distanceMatrix = {{0,600,2500,8000,5800},{600,0,2500,7500,5500},{2500,2500,0,6000,8300},
                {8000,7500,5800,0,4000},{5800,5500,8300,4000,0}};
        /*
        Initialize airport information: THESE AIRPLANE INFORMATION ARE SELECTED FROM THE INTERNET.
        Some airline companies will assign more than 2 international flights within 1 day. Like PVG <-> SFO.
         */

        Airport lax = new Airport("LAX", 0.1, 2);
        Airport pvg = new Airport("PVG",0.1,2);
        Airport iad = new Airport("IAD",0.1,1.5);
        Airport doh = new Airport("DOH",0.05,3);
        Airport sfo = new Airport("SFO",0.1,1.5);

        /*
        Initialize airplane information.
         */

        Airplane MU586 = new Airplane("MU586", 500.0,"A340"); //PVG->LAX
        Airplane MU587 = new Airplane("MU587",500,"A340"); //LAX->PVG
        Airplane MU533 = new Airplane("MU533", 550,"A330"); //PVG->SFO
        Airplane MU534 = new Airplane("MU534", 550,"A330"); //SFO->PVG
        Airplane QR871 = new Airplane("QR871",550,"777"); //DOH -> PVG
        Airplane QR870 = new Airplane("QR870",550,"777"); //PVG -> DOH
        Airplane QR739 = new Airplane("QR739",550,"777"); //DOH -> LAX
        Airplane QR740 = new Airplane("QR740",550,"777"); //LAX -> DOH
        Airplane QR777 = new Airplane("QR777",550,"777");//DOH -> IAD
        Airplane VA925 = new Airplane("VA925",450,"A320"); //SFO->LAX
        Airplane VA926 = new Airplane("VA926",450,"A320"); //SFO->LAX
        Airplane VA927 = new Airplane("VA925",450,"A320"); //SFO->LAX
        Airplane DL1859 = new Airplane("DL1859",450,"757"); //SFO->IAD
        Airplane DL4031 = new Airplane("DL4031",450,"737"); //SFO->IAD
        Airplane DL5762 = new Airplane("DL5762",450,"737"); //IAD->SFO
        Airplane DL1938 = new Airplane("DL1938",450,"757"); //IAD ->SFO
        Airplane UA281 = new Airplane("UA281",450,"A320"); //IAD ->LAX
        Airplane UA282 = new Airplane("UA282",450,"757"); //IAD ->LAX

        /*
        Initialize events
         */

        AirportEvent landingEventmu586 = new AirportEvent(0.2, pvg, AirportEvent.PLANE_ARRIVES, MU586, lax,pvg );
        AirportEvent landingEventmu587 = new AirportEvent(0.2, pvg, AirportEvent.PLANE_ARRIVES, MU587,lax,pvg );
        AirportEvent landingEventmu533 = new AirportEvent(0.2, pvg, AirportEvent.PLANE_ARRIVES, MU533,sfo,pvg );
        AirportEvent landingEventmu534 = new AirportEvent(0.2, pvg, AirportEvent.PLANE_ARRIVES, MU534,sfo,pvg );
        AirportEvent landingEventqr871 = new AirportEvent(0.2, pvg, AirportEvent.PLANE_ARRIVES, QR871,doh,pvg );
        AirportEvent landingEventqr870 = new AirportEvent(0.2, doh, AirportEvent.PLANE_ARRIVES, QR870,pvg,doh );
        AirportEvent landingEventqr739 = new AirportEvent(0.2, doh, AirportEvent.PLANE_ARRIVES, QR739,lax,doh );
        AirportEvent landingEventqr740 = new AirportEvent(0.2, lax, AirportEvent.PLANE_ARRIVES, QR740,doh,lax );
        AirportEvent landingEventqr777 = new AirportEvent(0.2, iad, AirportEvent.PLANE_ARRIVES, QR777,doh,iad );
        AirportEvent landingEventva925 = new AirportEvent(0.2, sfo, AirportEvent.PLANE_ARRIVES, VA925,lax,sfo );
        AirportEvent landingEventva926 = new AirportEvent(0.2, lax, AirportEvent.PLANE_ARRIVES, VA926,sfo,lax );
        AirportEvent landingEventva927 = new AirportEvent(0.2, sfo, AirportEvent.PLANE_ARRIVES, VA927,lax,sfo );
        AirportEvent landingEventdl1859 = new AirportEvent(0.2, iad, AirportEvent.PLANE_ARRIVES, DL1859,sfo,iad );
        AirportEvent landingEventdl4031 = new AirportEvent(0.2, sfo, AirportEvent.PLANE_ARRIVES, DL4031,iad,sfo );
        AirportEvent landingEventdl5762 = new AirportEvent(0.2, sfo, AirportEvent.PLANE_ARRIVES, DL5762,iad,sfo );
        AirportEvent landingEventdl1938 = new AirportEvent(0.2, iad, AirportEvent.PLANE_ARRIVES, DL1938,sfo,iad );
        AirportEvent landingEventua281 = new AirportEvent(0.2, lax, AirportEvent.PLANE_ARRIVES, UA281,iad,lax );
        AirportEvent landingEventua282 = new AirportEvent(0.2, lax, AirportEvent.PLANE_ARRIVES, UA282,lax,iad );

        /*
        Schedule events
         */

        Simulator.schedule(landingEventmu586);
        Simulator.schedule(landingEventmu587);
        Simulator.schedule(landingEventmu533);
        Simulator.schedule(landingEventmu534);
        Simulator.schedule(landingEventqr871);
        Simulator.schedule(landingEventqr870);
        Simulator.schedule(landingEventqr739);
        Simulator.schedule(landingEventqr740);
        Simulator.schedule(landingEventqr777);
        Simulator.schedule(landingEventva925);
        Simulator.schedule(landingEventva926);
        Simulator.schedule(landingEventva927);
        Simulator.schedule(landingEventdl1859);
        Simulator.schedule(landingEventdl4031);
        Simulator.schedule(landingEventdl5762);
        Simulator.schedule(landingEventdl1938);
        Simulator.schedule(landingEventua281);
        Simulator.schedule(landingEventua282);


        Simulator.stopAt(200);
        Simulator.run();
        lax.getCirclingtime();
        pvg.getCirclingtime();
        doh.getCirclingtime();
        sfo.getCirclingtime();
        iad.getCirclingtime();

        //printhm(lax);
        //printhm(pvg);
        //printhm(iad);
        //printhm(sfo);
        //printhm(doh);
    }
}
