package com.rms.tax.hra;

import java.util.Scanner;

/**
 * Utility class for calculate maximum house rent allowance you can avail
 *
 * @author ramesh.battula
 */
public class HouseRentAllowance {

    private static boolean stop_flag = false;

    public static void main(String[] args) {
        while (!stop_flag) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter range of your basic salary..");
            double minBaseSalary = scanner.nextDouble();
            double maxBaseSalary = scanner.nextDouble();

            System.out.println("Enter range of your HRA received from your employer");
            double minHRA = scanner.nextDouble();
            double maxHRA = scanner.nextDouble();

            System.out.println("Enter range of rent you paid to house");
            double minHRAPaid = scanner.nextDouble();
            double maxHRAPaid = scanner.nextDouble();

            System.out.println("Are you living in Metro city (Delhi, Kolkata, Mumbai, Chennai)");
            boolean isMetro = scanner.nextBoolean();

            System.out.println("You're entered following details ...........");
            System.out.println("Basic salary range from  " + minBaseSalary + " to " + maxBaseSalary);
            System.out.println("Basic HRA received from  " + minHRA + " to " + maxHRA);
            System.out.println("Basic Paid HRA range from  " + minHRAPaid + " to " + maxHRAPaid);

            /******** Actual HRA calculation start here **********/
            Double maxTaxExemption = Double.MIN_VALUE;
            double[] requiredValues = new double[3];

            double basic = minBaseSalary;
            while (basic <= maxBaseSalary) {
                double hra = minHRA;
                while (hra <= maxHRA && basic >= (hra * 2)) {
                    double paid = minHRAPaid;
                    while (paid <= maxHRAPaid) {
                        double firstExemption = (paid - 0.1 * basic);
                        double secondExemption = ((isMetro) ? 0.5 : 0.4) * basic;
                        double thirdExemption = hra;

                        double taxExemption = Math.min(firstExemption, Math.min(secondExemption, thirdExemption));
                        if (maxTaxExemption < taxExemption) {
                            maxTaxExemption = taxExemption;
                            requiredValues[0] = basic;
                            requiredValues[1] = hra;
                            requiredValues[2] = paid;
                        }

                        paid += 1000;

                    }
                    hra += 1000;
                }
                basic += 1000;
            }

            System.out.println();
            System.out.println("***************************************************");
            System.out.println("Maximum house rent exemption is " + maxTaxExemption);
            System.out.println("Desire basic salary is " + requiredValues[0]);
            System.out.println("Desire HRA component value is " + requiredValues[1]);
            System.out.println("Desire HRA paid value is " + requiredValues[2]);
            System.out.println("***************************************************");
            System.out.println();

            System.out.println("You want continue ... Please enter true for continue... ");
            stop_flag = !scanner.nextBoolean();
        }

    }
}
