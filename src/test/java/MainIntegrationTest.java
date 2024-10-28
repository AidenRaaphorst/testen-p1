import org.example.Main;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class MainIntegrationTest {
    @Test
    public void main_integrationTest_normal() {
        // Create simulated user input
        String annualIncomeInput = "12000\n";
        String hasStudentDebtInput = "\n";
        String postalCodeInput = "1234AB\n";
        String hasPartnerInput = "\n";
        String fixedInterestPeriodInput = "30\n";
        String confirmDataInput = "\n";
        String simulatedInput = annualIncomeInput + hasStudentDebtInput + postalCodeInput + hasPartnerInput + fixedInterestPeriodInput + confirmDataInput;

        // Set the input to the simulated input
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        // Set the output to a different stream, so we can check the output with code
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        Main.main(null);
        String programOutput = out.toString();

        // Check the output
        assertTrue(programOutput.contains("Maximale hypotheek: 51000.00"));
        assertTrue(programOutput.contains("Aflossing per maand: 141.67"));
        assertTrue(programOutput.contains("Rente per maand: 212.50"));
        assertTrue(programOutput.contains("Totale maandbedrag: 354.17"));
        assertTrue(programOutput.contains("Totale betaling: 127501.20"));
    }
}
