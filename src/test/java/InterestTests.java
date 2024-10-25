import org.example.utils.InterestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    public void calculateRepaymentPerMonth_with255k_withoutDebt() {

    }
}
