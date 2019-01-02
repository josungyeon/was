package wooa;

import java.util.function.Function;

/**
 * Created by sungyeon on 27/12/2018.
 */
public enum CalculateType {
    CALC_A(value -> value),
    CALC_B(value -> value * 10),
    CALC_C(value -> value * 3),
    CALC_D(value -> value * 0L);

    private Function<Long, Long> expression;

    CalculateType(Function<Long, Long> expression) {
        this.expression = expression;
    }

    public long calculator(long value) {
        return expression.apply(value);
    }
}
