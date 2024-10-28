import org.example.utils.InterestUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class InterestTests {
    @Test
    public void formatBigDecimal() {
        BigDecimal expected = new BigDecimal("1.00");
        BigDecimal actual = InterestUtils.format(BigDecimal.valueOf(1));

        assertEquals(expected, actual);
    }

    @Test
    public void calculateMaxLoan_with60k_withoutDebt() {
        BigDecimal expected = BigDecimal.valueOf(255_000).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal actual = InterestUtils.calculateMaxLoan(BigDecimal.valueOf(60_000), false);

        assertEquals(expected, actual);
    }

    @Test
    public void calculateMaxLoan_with60k_withDebt() {
        BigDecimal expected = BigDecimal.valueOf(191_250).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal actual = InterestUtils.calculateMaxLoan(BigDecimal.valueOf(60_000), true);

        assertEquals(expected, actual);
    }

    @Test
    public void calculateInterestPerMonth_with255k_30years() {
        BigDecimal expected = BigDecimal.valueOf(1062.50).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal actual = InterestUtils.calculateInterestPerMonth(BigDecimal.valueOf(255_000), 30);

        assertEquals(expected, actual);
    }

    @Test
    public void calculateRepaymentPerMonth_with255k_30years() {
        BigDecimal expected = BigDecimal.valueOf(708.33).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal actual = InterestUtils.calculateRepaymentPerMonth(BigDecimal.valueOf(255_000), 30);

        assertEquals(expected, actual);
    }

    @Test
    public void calculateTotalMonthlyCharge_with255k_30years() {
        BigDecimal expected = BigDecimal.valueOf(1770.83).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal actual = InterestUtils.calculateTotalMonthlyCharge(BigDecimal.valueOf(255_000), 30);

        assertEquals(expected, actual);
    }

    @Test
    public void calculateTotalPayment_with255k_30years() {
        BigDecimal expected = BigDecimal.valueOf(637_498.80).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal actual = InterestUtils.calculateTotalPayment(BigDecimal.valueOf(255_000), 30);

        assertEquals(expected, actual);
    }
}
