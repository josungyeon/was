import wooa.CalculateType;
import org.junit.Test;

/**
 * Created by sungyeon on 27/12/2018.
 */
public class CalculateTypeTest {

    @Test
    public void testType() {
        CalculateType type;

        long result = 1000L;
        System.out.println(CalculateType.CALC_C.calculator(result));
    }
}
