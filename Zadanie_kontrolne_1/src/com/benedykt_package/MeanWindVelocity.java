package com.benedykt_package;

public class MeanWindVelocity extends EurocodeTableVb0{

    //wysokosc dachu na poziomem terenu
    protected double z;
    //kategoria terenu 0-4
    protected int groundCategory;

    MeanWindVelocity(){
        z = 0;
        groundCategory = 0;
    };

    MeanWindVelocity(double A, int zone, double z_, int groundCategory_){
        super(A, zone);
        if(z_ < 0) {
            z = Constants.zmin;
        } else if(z_ > 200){
            z = Constants.zmax;
        }else {
            z = z_;
        }
        groundCategory = groundCategory_;
    }


    EurocodeTableVb0 eurocodeTableVb0 = new EurocodeTableVb0(A, zone);


    //PRZECIAZENIE FUNKCJI
    //obliczenie wartości Vb
    public double vb(){
        return Constants.Cdir * Constants.Cseason * eurocodeTableVb0.countVb0();
    }

    public double vb(double Cdir, double Cseason){
        return Cdir * Cseason * eurocodeTableVb0.countVb0();
    }

    //obliczenie wartości Vm(z) - serednia predkosc wiatru
    public double vmz () {
        double z0 = 1;
        double zmin = 1;

        //wybór wartości z0 i zmin w zależmości od kategorii terenu
        switch (groundCategory) {
            case 0:
                z0 = Constants.z0_zminTable[0][0];
                zmin = Constants.z0_zminTable[0][1];
                break;
            case 1:
                z0 = Constants.z0_zminTable[1][0];
                zmin = Constants.z0_zminTable[1][1];
                break;
            case 2:
                z0 = Constants.z0_zminTable[2][0];
                zmin = Constants.z0_zminTable[2][1];
                break;
            case 3:
                z0 = Constants.z0_zminTable[3][0];
                zmin = Constants.z0_zminTable[3][1];
                break;
            case 4:
                z0 = Constants.z0_zminTable[4][0];
                zmin = Constants.z0_zminTable[4][1];
                break;
            default:
                z0 = 1;
                zmin = 1;
                break;

        }
        double krz = 0.19 * Math.pow((z0 / Constants.z0II), 0.07);

        //wartosc sredniej predkosci wiatru
        double vmz = krz * Math.log(z / z0) * Constants.Co * vb();
        return  vmz;
    }

    public double tellZ(){
        return z;
    }

    public double tellGroundCategory() {
        return groundCategory;
    }


    public double tellA() {
        return A;
    }
}
