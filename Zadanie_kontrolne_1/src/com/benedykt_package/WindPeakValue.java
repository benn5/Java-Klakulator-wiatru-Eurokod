package com.benedykt_package;

public class WindPeakValue extends Turbulence{

    WindPeakValue(){
        A = 0;
        zone = 1;
        z = 0;
        groundCategory = 0;
    };

    WindPeakValue(double A, int zone, double z, int groundCategory){
        super(A, zone, z, groundCategory);
    };


    //PRZECIAZONE FUNKCJE
    //wartosc szczytowego cisnienia predkosci wiatru
    public double qpz(){
        return (1 + 7*Ivz()) * 0.5 * Constants.airDensity * vmz()*vmz() * 0.001;
    }

    public double qpz(double airDensity){
        return (1 + 7*Ivz()) * 0.5 * airDensity * vmz()*vmz() * 0.001;
    }

    //wartosc sczytowego cisnienia wiatru
    public double qb(){
        return 0.5 * Constants.airDensity * vb()*vb() * 0.001;
    }

    public double qb(double airDensity){
        return 0.5 * airDensity * vb()*vb() * 0.001;
    }
}
