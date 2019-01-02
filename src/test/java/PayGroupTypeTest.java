import org.junit.Test;
import wooa.PayGroupType;

import static org.junit.Assert.*;

/**
 * Created by sungyeon on 27/12/2018.
 */
public class PayGroupTypeTest {
    @Test
    public void testPayGroup() {
        PayGroupType.pushByPayGroup("");
        assertEquals(PayGroupType.findByPayCode("REMITTANCE").name(), "CASH");
    }
}
