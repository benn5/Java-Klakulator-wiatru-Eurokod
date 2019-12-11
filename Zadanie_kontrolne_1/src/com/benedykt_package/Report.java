package com.benedykt_package;

import java.util.Formatter;

public class Report extends Turbulence {

    private Formatter f;
    private String fileName = "Calculations report.txt";

    Report(){};

    Report(double A, int zone, double z, int groundCategory){
        super(A, zone, z, groundCategory);
    };

    //FUNKCJE PRZEDEFINOWANE
    @Override
    public int tellGroundCategory() {
        return super.tellGroundCategory();
    }

    @Override
    public double tellZ() {
        return super.tellZ();
    }

    @Override
    public double tellA() {
        return super.tellA();
    }

    @Override
    public int tellZone() {
        return super.tellZone();
    }

    public String tellFileName(){return fileName;}


    public void openReport(){
        try {
            f = new Formatter(fileName);
        } catch (Exception e){
            System.out.println("File not opened");
        }
    }

    //OBLICZENIA
    EurocodeTableVb0 eurocodeTableVb0 = new EurocodeTableVb0(tellA(), tellZone());
    MeanWindVelocity meanWindVelocity = new MeanWindVelocity(tellA(),tellZone(),tellZ(), tellGroundCategory());
    Turbulence turbulence = new Turbulence(tellA(),tellZone(),tellZ(), tellGroundCategory());
    WindPeakValue windPeakValue = new WindPeakValue(tellA(),tellZone(),tellZ(), tellGroundCategory());


    public void addInformation(){
        //PODANIE DANYCH WEJSCIOWYCH
        f.format("Wysokość na poziomem morza wynosi: " + tellA() + " [m n.p.m.]\n" +
                "Strefa wiatrowa: " + tellZone() + "\n" +
                "Wysokość dachu nad poziom terenu: " + tellZ() + "\n" +
                "Kategoria terenu: " + tellGroundCategory() +"\n\n\n");

        //WYNIKI Z KLASY EUROCODETABLE
        f.format("Wartość podstawowa bazowej prędkości wiatru: " + eurocodeTableVb0.countVb0() + "[m/s]\n\n\n");

        //WYNIKI Z KLASY MEANWINDVELOCITY
        String Vb = String.valueOf(meanWindVelocity.countVb0());
        f.format("Cdir = " + Constants.Cdir + "[-]\n" +
                "Cseason = " + Constants.Cseason + "[-]\n" +
                "Prędkość bazowa Vb = " + Constants.Cdir + " * " +  Constants.Cseason +  " * " + Vb + "[m/s]\n" +
                "Srednia prędkość wiatru Vm(z) = " + meanWindVelocity.vmz() + "[m/s]\n\n\n");

        //WYNIKI Z KLASY TURBULENCE
        f.format("Współczynnik chropowatości terenu = " + turbulence.chooseZ0() + "[m]\n" +
                "Intensywność turbulencji = " + turbulence.Ivz() + "[-]\n");

        //WYNIKI Z KLASY WINDPEAKVALUE
        f.format("Wartość szczytowego ciśnienia wiatru: " + windPeakValue.qb() + " [kPa]\n" +
                "Wartość szczytowego ciścnienia prędkości wiatru wynosi: " + windPeakValue.qpz()+ " [kPa]\n");

    }

    public void closeReport(){
        f.close();
    }



}
