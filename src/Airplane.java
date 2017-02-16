//YOUR NAME HERE

//TODO add number of passengers, speed

import java.util.Random;

public class Airplane {
    private int m_maxPassengerNum;
    private String m_name;
    private int m_numberPassengers;
    private double m_speed;
    private String m_airplaneModelType;
    static Random ran = new Random();

    public Airplane(String name) {
        m_name = name;
    }
    public static int radnumgen(int max){
        //System.out.println(max);
        int s = ran.nextInt(max)%(1+max);
        return s;
    }
    public void resetNumPassenger(){
        m_numberPassengers = radnumgen(m_maxPassengerNum);
    }
    public Airplane(String name, double speed, String type){
        this(name);
        this.m_speed = speed;
        this.m_airplaneModelType = type;
        switch (type){
            case("747"):
                this.m_maxPassengerNum = 500;
                break;
            case("777"):
                this.m_maxPassengerNum = 450;
                break;
            case("A380"):
                this.m_maxPassengerNum = 500;
                break;
            case("767"):
                this.m_maxPassengerNum = 300;
                break;
            case("757"):
                this.m_maxPassengerNum = 200;
                break;
            case("737"):
                this.m_maxPassengerNum = 150;
                break;
            case("A330"):
                this.m_maxPassengerNum = 300;
                break;
            case("A320"):
                this.m_maxPassengerNum = 150;
                break;
            case("A350"):
                this.m_maxPassengerNum = 300;
                break;
            case("A340"):
                this.m_maxPassengerNum = 400;
                break;
        }

        this.m_numberPassengers = radnumgen(this.m_maxPassengerNum);
    }


    public String getM_Name() {
        return m_name;
    }
    public double getM_speed(){
        return m_speed;
    }
    public int getM_numberPassengers(){
        return m_numberPassengers;
    }


}
