package wooa;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by sungyeon on 27/12/2018.
 */
public enum PayGroupType {
    CASH(Arrays.asList("ACCOUNT_TRANSFER", "REMITTANCE", "ON_SITE_PAYMENT", "TOSS")),
    CARD(Arrays.asList("PAYCO", "CARD", "KAKAO_PAY", "BAEMIN_PAY")),
    ETC(Arrays.asList("POINT", "COUPON")),
    EMPTY(Collections.EMPTY_LIST);

    private List<String> payCodeList;

    PayGroupType(List<String> payCodeList) {
        this.payCodeList = payCodeList;
    }

    public static PayGroupType findByPayCode(String payCode) {
        for (PayGroupType type: PayGroupType.values()) {
            if (type.payCodeList.contains(payCode)) {
                return type;
            }
        }

        Arrays.stream(PayGroupType.values());

        return EMPTY;
    }

    public static void pushByPayGroup(String payGroup) {
        for (PayGroupType type: PayGroupType.values()) {
            if (type.toString().equals(payGroup))
                System.out.println(type.toString());
        }
    }

    public void printByPayGroup(String payGroup) {

    }
}
