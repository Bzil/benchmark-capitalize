package bz.benchmark.runner.capitatize;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CapitalizeTest {

    Capitalize capitalize = new Capitalize();

    public static List<CapitalizeConstant.Holder> data() {
        return CapitalizeConstant.TO_CAPITALIZE;
    }

    @ParameterizedTest
    @MethodSource("data")
    void capitalizeOld(CapitalizeConstant.Holder holder) {
        assertThat(capitalize.capitalizeOld(holder.getInput())).isEqualTo(holder.getOutput());
    }

    @ParameterizedTest
    @MethodSource("data")
    void capitalizeOldNewRegex(CapitalizeConstant.Holder holder) {
        assertThat(capitalize.capitalizeOldNewRegex(holder.getInput())).isEqualTo(holder.getOutput());
    }

    @ParameterizedTest
    @MethodSource("data")
    void capitalizeNewLikeOldWay(CapitalizeConstant.Holder holder) {
        assertThat(capitalize.capitalizeNewLikeOldWay(holder.getInput())).isEqualTo(holder.getOutput());
    }

    @ParameterizedTest
    @MethodSource("data")
    void capitalizeNewLikeOldWayGuillaume(CapitalizeConstant.Holder holder) {
        assertThat(capitalize.capitalizeNewLikeOldWayGuillaume(holder.getInput())).isEqualTo(holder.getOutput());
    }
}
