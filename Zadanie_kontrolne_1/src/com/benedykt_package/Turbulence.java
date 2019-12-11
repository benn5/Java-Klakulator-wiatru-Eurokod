package com.benedykt_package;

public class Turbulence extends MeanWindVelocity {

    Turbulence(){};

    Turbulence(double A, int zone, double z, int groundCategory){
        super(A, zone, z, groundCategory);
    }

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


    //wybor z0 na podst kat terenu
    public double chooseZ0 () {
        double z0 = 1;
        switch (tellGroundCategory()) {
            case 0:
                z0 = Constants.z0_zminTable[0][0];
                break;
            case 1:
                z0 = Constants.z0_zminTable[1][0];
                break;
            case 2:
                z0 = Constants.z0_zminTable[2][0];
                break;
            case 3:
                z0 = Constants.z0_zminTable[3][0];
                break;
            case 4:
                z0 = Constants.z0_zminTable[4][0];
                break;
            default:
                z0 = 1;
                break;
        }
        return  z0;
    }

    public double Ivz(){
        return Constants.kl / (Constants.Co * Math.log(tellZ() / chooseZ0()));
    }
}
