package com.benedykt_package;

public class Turbulence extends MeanWindVelocity {

    Turbulence(){
        A = 0;
        zone = 1;
        z = 0;
        groundCategory = 0;
    };

    Turbulence(double A, int zone, double z, int groundCategory){
        super(A, zone, z, groundCategory);
    }


    //wybor z0 na podst kat terenu
    private double chooseZ0 () {
        double z0 = 1;
        switch (groundCategory) {
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
        return Constants.kl / (Constants.Co * Math.log(z / chooseZ0()));
    }
}
