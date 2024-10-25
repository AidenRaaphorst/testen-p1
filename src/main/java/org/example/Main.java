package org.example;

import org.example.utils.PostalCodeUtils;
import org.example.utils.InterestUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BigDecimal annualIncome;
        boolean hasStudentDebt;
        String postalCode;
        boolean hasPartner;
        int fixedInterestPeriod;
        // BigDecimal loanAmount;

        do {
            System.out.println("\n");
            annualIncome = askAnnualIncome(sc, false);
            hasStudentDebt = askStudentDebt(sc);
            postalCode = askPostalCode(sc);
            hasPartner = askPartner(sc);
            if (hasPartner) {
                annualIncome = annualIncome.add(askAnnualIncome(sc, true));
            }
            fixedInterestPeriod = askFixedInterestPeriod(sc);

            // TODO: Do something with this
            // loanAmount = askLoanAmount(sc);

            displayData(annualIncome, hasStudentDebt, postalCode, hasPartner, fixedInterestPeriod);
        } while (!askConfirm(sc));

        displayCalculations(annualIncome, hasStudentDebt, fixedInterestPeriod);
        sc.close();
    }

    public static void displayData(BigDecimal annualIncome, boolean hasStudentDebt, String postalCode,
                                   boolean hasPartner, int fixedInterestPeriod) {
        System.out.println("\n");
        System.out.println("============");
        System.out.println("  Gegevens  ");
        System.out.println("============");
        System.out.println("Totaal jaarinkomen: " + annualIncome);
        System.out.println("Heeft studieschuld: " + (hasStudentDebt ? "Ja" : "Nee"));
        System.out.println("Postcode: " + postalCode);
        System.out.println("Heeft partner: " + (hasPartner ? "Ja" : "Nee"));
        System.out.println("Rentevaste periode: " + fixedInterestPeriod + " jaar");
    }

    public static void displayCalculations(BigDecimal annualIncome, boolean hasStudentDebt, int fixedInterestPeriod) {
        BigDecimal maxLoan = InterestUtils.calculateMaxLoan(annualIncome, hasStudentDebt);
        BigDecimal interestPerMonth = InterestUtils.calculateInterestPerMonth(maxLoan, fixedInterestPeriod);
        BigDecimal repaymentPerMonth = InterestUtils.calculateRepaymentPerMonth(maxLoan, fixedInterestPeriod);
        BigDecimal totalMonthlyCharge = InterestUtils.calculateTotalMonthlyCharge(maxLoan, fixedInterestPeriod);
        BigDecimal totalPayment = InterestUtils.calculateTotalPayment(maxLoan, fixedInterestPeriod);

        System.out.println("\n");
        System.out.println("============");
        System.out.println("  Uitkomst  ");
        System.out.println("============");
        System.out.println("Maximale hypotheek: " + maxLoan);
        System.out.println("Rente per maand: " + interestPerMonth);
        System.out.println("Aflossing per maand: " + repaymentPerMonth);
        System.out.println("Totale maandbedrag: " + totalMonthlyCharge);
        System.out.println("Totale betaling: " + totalPayment);
    }

    public static BigDecimal askAnnualIncome(Scanner sc, boolean hasPartner) {
        BigDecimal annualIncome;

        while (true) {
            try {
                System.out.print("Wat is het jaarinkomen van " + (hasPartner ? "de partner van " : "") + "de klant: ");
                annualIncome = sc.nextBigDecimal();
                sc.nextLine();
            } catch (NumberFormatException ignored) {
                System.out.println("Dat is niet een geldig jaarinkomen.");
                continue;
            }
            break;
        }

        return annualIncome;
    }

    public static BigDecimal askLoanAmount(Scanner sc) {
        BigDecimal annualIncome;

        while (true) {
            try {
                System.out.print("Hoeveel wil de klant lenen?: ");
                annualIncome = sc.nextBigDecimal();
                sc.nextLine();
            } catch (NumberFormatException ignored) {
                System.out.println("Dat is niet een geldig jaarinkomen.");
                continue;
            }
            break;
        }

        return annualIncome;
    }

    public static boolean askStudentDebt(Scanner sc) {
        System.out.print("Heeft de klant studieschuld (y/N): ");
        return sc.nextLine().equalsIgnoreCase("y");
    }

    public static String askPostalCode(Scanner sc) {
        String postalCode;

        while (true) {
            System.out.print("Wat is de postcode van de klant: ");
            postalCode = PostalCodeUtils.formatPostalCode(sc.nextLine());

            if (!PostalCodeUtils.isValidPostalCode(postalCode)) {
                System.out.println("Dat is geen geldige postcode.");
                continue;
            } else if (!PostalCodeUtils.isValidPostalCodeRegion(postalCode)) {
                System.out.println("Dat is geen geldige postcode regio.");
                continue;
            }

            break;
        }

        return postalCode;
    }

    public static boolean askPartner(Scanner sc) {
        System.out.print("Heeft de klant een partner (y/N): ");
        return sc.nextLine().equalsIgnoreCase("y");
    }

    public static int askFixedInterestPeriod(Scanner sc) {
        int fixedInterestPeriod;

        while (true) {
            try {
                Object[] fixedInterestPeriods = InterestUtils.FIXED_INTEREST_PERIODS.keySet().toArray();
                Arrays.sort(fixedInterestPeriods);
                System.out.print("Wat wil de klant als rentevaste jaar periode %s: ".formatted(Arrays.toString(fixedInterestPeriods)));
                fixedInterestPeriod = sc.nextInt();
                sc.nextLine();

                if (!InterestUtils.FIXED_INTEREST_PERIODS.containsKey(fixedInterestPeriod)) {
                    System.out.println("Dat is geen geldige rentevaste jaar periode.");
                    continue;
                }
                break;
            } catch (NumberFormatException ignored) {}
        }

        return fixedInterestPeriod;
    }

    public static boolean askConfirm(Scanner sc) {
        System.out.print("\nKloppen de gegevens? (Y/n): ");
        return !sc.nextLine().equalsIgnoreCase("n");
    }
}
