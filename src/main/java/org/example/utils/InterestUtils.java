package org.example.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class InterestUtils {
    public static final HashMap<Integer, BigDecimal> FIXED_INTEREST_PERIODS = new HashMap<>(Map.of(
            1, BigDecimal.valueOf(0.02),
            5, BigDecimal.valueOf(0.03),
            10, BigDecimal.valueOf(0.035),
            20, BigDecimal.valueOf(0.045),
            30, BigDecimal.valueOf(0.05)
    ));
    private static final BigDecimal MAX_MORTGAGE = BigDecimal.valueOf(4.25);
    private static final BigDecimal MAX_MORTGAGE_WITH_STUDENT_DEBT = MAX_MORTGAGE.multiply(BigDecimal.valueOf(0.75));

    public static Set<Integer> getAllInterestPeriods() {
        return FIXED_INTEREST_PERIODS.keySet();
    }

    public static Collection<BigDecimal> getAllInterestPercentages() {
        return FIXED_INTEREST_PERIODS.values();
    }

    public static BigDecimal calculateMaxLoan(BigDecimal annualIncome, boolean hasStudentDebt) {
        return format(annualIncome.multiply(hasStudentDebt ? MAX_MORTGAGE_WITH_STUDENT_DEBT : MAX_MORTGAGE));
    }

    public static BigDecimal calculateInterestPerMonth(BigDecimal loanAmount, int fixedInterestPeriod) {
        BigDecimal interestPerMonth = loanAmount
                .multiply(FIXED_INTEREST_PERIODS.get(fixedInterestPeriod))
                .divide(BigDecimal.valueOf(12), RoundingMode.HALF_EVEN);

        return format(interestPerMonth);
    }

    public static BigDecimal calculateRepaymentPerMonth(BigDecimal loanAmount, int fixedInterestPeriod) {
        MathContext mc = new MathContext(5, RoundingMode.HALF_EVEN);
        BigDecimal repaymentPerMonth = loanAmount.divide(BigDecimal.valueOf(fixedInterestPeriod * 12L), mc);

        return format(repaymentPerMonth);
    }

    public static BigDecimal calculateTotalMonthlyCharge(BigDecimal loanAmount, int fixedInterestPeriod) {
        BigDecimal interest = calculateInterestPerMonth(loanAmount, fixedInterestPeriod);
        BigDecimal repayment = calculateRepaymentPerMonth(loanAmount, fixedInterestPeriod);
        return format(interest.add(repayment));
    }

    public static BigDecimal calculateTotalPayment(BigDecimal loanAmount, int fixedInterestPeriod) {
        BigDecimal totalPayment = calculateTotalMonthlyCharge(loanAmount, fixedInterestPeriod)
                .multiply(BigDecimal.valueOf(fixedInterestPeriod * 12L));
        return format(totalPayment);
    }

    public static BigDecimal format(BigDecimal bd) {
        return bd.setScale(2, RoundingMode.HALF_EVEN);
    }
}
