package com.benedykt_package;

import javax.swing.*;

public class EurocodeTableVb0 {

    protected double A;
    protected int zone;

    EurocodeTableVb0(double A_, int zone_){
        A = A_;
        zone = zone_;
    };

    EurocodeTableVb0(){
        A = 0;
        zone = 1;
    }

    public double tellA() {
        return A;
    }

    public int tellZone(){
        return zone;
    }


    public double countVb0(){
        double vb0 = 0;

        if(A <= 300 && A >= 0){
            switch(zone){
                case 1:
                case 3:
                    vb0 = 22;
                    break;
                case 2:
                    vb0 = 26;
                    break;
                default:
                    vb0 = 0.00;
                    break;
            }
        } else if(A > 300){
            switch(zone){
                case 1:
                case 3:
                    vb0 = 22*(1 + 0.0006*(A - 300));
                    break;
                case 2:
                    vb0 = 26;
                    break;
                default:
                    vb0 = 0;
                    break;
            }
        }
             return vb0;
    }

}
