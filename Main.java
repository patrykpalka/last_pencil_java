package lastpencil;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random random = new Random();

        System.out.println("How many pencils would you like to use:");
        int pencils = getPencils(scan);

        System.out.println("Who will be the first (John, Jack):");
        String roundPlayer = scan.nextLine();
        while (!roundPlayer.matches("John|Jack")) {
            System.out.println("Choose between 'John' and 'Jack'");
            roundPlayer = scan.nextLine();
        }

        while (pencils > 0) {
            for (int i = 0; i < pencils; i++) {
                System.out.print("|");
            }

            System.out.println("\n" + roundPlayer + "'s turn!");
            int pencilsDeduction;
            if (roundPlayer.equals("John")) {
                pencilsDeduction = getPencilsDeduction(scan, pencils);
            } else {
                if (pencils == 1) {
                    pencilsDeduction = 1;
                } else if ((pencils + 2) % 4 == 0) {
                    pencilsDeduction = 1;
                } else if ((pencils + 1) % 4 == 0) {
                    pencilsDeduction = 2;
                } else if (pencils % 4 == 0) {
                    pencilsDeduction = 3;
                } else {
                    pencilsDeduction = random.nextInt(3) + 1;
                }
                System.out.println(pencilsDeduction);
            }

            pencils -= pencilsDeduction;
            if (roundPlayer.equals("John")) {
                roundPlayer = "Jack";
            } else {
                roundPlayer = "John";
            }
            if (pencils == 0) {
                System.out.println(roundPlayer + " won!");
            }
        }
    }

    static int getPencils(Scanner scan) {
        int pencils;
        while (true) {
            try {
                pencils = Integer.valueOf(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("The number of pencils should be numeric");
                continue;
            }
            if (pencils == 0) {
                System.out.println("The number of pencils should be positive");
                continue;
            } else if (pencils < 0) {
                System.out.println("The number of pencils should be numeric");
                continue;
            } else {
                break;
            }
        }
        return pencils;
    }

    static int getPencilsDeduction(Scanner scan, int pencils) {
        int pencilsDeduction;
        while (true) {
            try {
                pencilsDeduction = Integer.valueOf(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Possible values: '1', '2' or '3'");
                continue;
            }
            if (pencilsDeduction > 3 || pencilsDeduction < 1) {
                System.out.println("Possible values: '1', '2' or '3'");
                continue;
            } else if (pencils - pencilsDeduction < 0) {
                System.out.println("Too many pencils were taken");
                continue;
            } else {
                break;
            }
        }
        return pencilsDeduction;
    }
}