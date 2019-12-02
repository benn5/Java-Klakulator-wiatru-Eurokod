package com.benedykt_package;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        ImageIcon icon = new ImageIcon("src/images/mapaPolski.png");
        JLabel label = new JLabel(icon);
        frame.add(label);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setTitle("Mapa Polski");


        //DANE WSTĘPNE
        double A = 0;
        int zone = 1;
        double z = 0;
        int groundCategory = 0;

        Scanner scanner = new Scanner(System.in);

        //WPROWADZENIE STREFY WIATROWEJ
        boolean againZone = true;
        while(againZone || zone < 1 || zone > 3) {
            try {
                System.out.println("Strefa witrowa (1-3)");
                zone = scanner.nextInt();

                if(zone < 1 || zone > 3){
                    System.out.println("Podaj strefę wiatrową w zakresie od 1 do 3");
                }

                againZone = false;
            } catch (Exception ex) {
                System.out.println("Wprowadź liczbę");
                scanner.next();
            }
        }

        //WPROWADZENIE WYSOKOSCI NAD POZIOMEM MORZA
        boolean againA = true;
        while(againA || A < Constants.Amin) {
            try {
                System.out.println("Wysokość ponad poziomem morza (> 0 m):");
                A = scanner.nextDouble();

                if(A < Constants.Amin){
                    System.out.println("Podaj wysokość większą równą 0");
                }

                againA = false;
            } catch (Exception ex) {
                System.out.println("Wprowadź liczbę");
                scanner.next();
            }
        }

        //WPROWADZENIE WYSOKOŚĆI DACHU NAD POZIOMEM TERENU
        boolean againZ = true;
        while (againZ || z < Constants.zmin || z > Constants.zmax) {
            try {
                System.out.println("Wysokość dachu nad poziomem terenu [m]:");
                z = scanner.nextDouble();

                if(z < Constants.zmin || z > Constants.zmax){
                    System.out.println("podaj wartość w zakresie od 1 do 200 m");
                }

                againZ = false;
            } catch (InputMismatchException ex) {
                System.out.println("Wprowadź liczbę");
                scanner.next();
            }
        }

        JFrame frame2 = new JFrame();
        ImageIcon icon2 = new ImageIcon("src/images/kategorieTerenu.png");
        JLabel label2 = new JLabel(icon2);
        frame2.add(label2);
        //frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.pack();
        frame2.setVisible(true);
        frame2.setTitle("Kategorie terenu");


        //WPROWADZENIE KATEGORII TERENU
        boolean againGroudCat = true;
        while (againGroudCat || groundCategory < 0 || groundCategory > 4) {
            try {
                System.out.println("Kategoria terenu (0-4)");
                groundCategory = scanner.nextInt();

                if (groundCategory < 0 || groundCategory > 4){
                    System.out.println("Podaj kategorie terenu w zakresie od 0 do 4");
                }

                againGroudCat = false;
            } catch (InputMismatchException ex) {
                System.out.println("Wprowadź liczbę");
                scanner.next();
            }
        }


        //Przykładowe dane do usunięcia później
        /*
        double A = -100;
        int zone = 5;
        double z = 1;
        int groundCategory = -3;
         */

        //INFORMACJE O BŁĘDNIE WPROWADZONYCH DANYCH - OKIENKA DIALOGOWE
        /*
        if(A < 0){
            A = 0;
            JOptionPane.showMessageDialog(null,
                    "Wysokość na poziomem morza musi być wieksza lub równa 0 m \n" +
                            "Przyjęto wysokość 0 m",
                    "Błąd", JOptionPane.ERROR_MESSAGE);
        }

        if(zone > 3 || zone < 1) {
            JOptionPane.showMessageDialog(null,
                    "Podaj strefę wiatrową w zakresie od 1 do 3",
                    "Błąd", JOptionPane.ERROR_MESSAGE);

        }

        if(groundCategory > 4 || groundCategory < 0) {
            JOptionPane.showMessageDialog(null,
                    "Podaj kategorię terenu w zakresie od 0 do 4",
                    "Błąd", JOptionPane.ERROR_MESSAGE);

        }
        */


        //OBLICZENIA
        EurocodeTableVb0 eurocodeTableVb0 = new EurocodeTableVb0(A, zone);
        MeanWindVelocity meanWindVelocity = new MeanWindVelocity(A,zone,z, groundCategory);
        Turbulence turbulence = new Turbulence(A,zone,z,groundCategory);
        WindPeakValue windPeakValue = new WindPeakValue(A,zone,z,groundCategory);



        //WYNIKI - DWA MIEJSCA PO PRZECINKU
        System.out.println("Prędkość bazowa wynosi: " +
                String.format("%.2f",eurocodeTableVb0.countVb0()) + " [m/s]");

        System.out.println("Srednia prędkość wiatru wynosi: " +
                String.format("%.2f",meanWindVelocity.vmz()) + " [m/s]");

        System.out.println("Turbulencja wiatru wynosi: " +
                String.format("%.2f",turbulence.Ivz()) + " [-]");

        System.out.println("Wartość szczytowego ciśnienia wiatru: " +
                String.format("%.2f",windPeakValue.qb()) + " [kPa]");

        System.out.println("wartość szczytowego ciścnienia prędkości wiatru wynosi: " +
                String.format("%.2f", windPeakValue.qpz()) + " [kPa]");

    }
}
