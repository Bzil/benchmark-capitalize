package bz.benchmark.runner.capitatize;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

import static bz.benchmark.runner.capitatize.CapitalizeConstant.TO_CAPITALIZE;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 3, time = 3, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class CapitalizeRunner {

    @Benchmark
    public void bench_old_way(Capitalize capitalize, Blackhole blackhole) {
        for (CapitalizeConstant.Holder holder : TO_CAPITALIZE) {
            blackhole.consume(capitalize.capitalizeOld(holder.getInput()));
        }
    }

    @Benchmark
    public void bench_new_way_with_old_rules(Capitalize capitalize, Blackhole blackhole) {
        for (CapitalizeConstant.Holder holder : TO_CAPITALIZE) {
            blackhole.consume(capitalize.capitalizeNewLikeOldWay(holder.getInput()));
        }
    }

    @Benchmark
    public void bench_old_new_regex(Capitalize capitalize, Blackhole blackhole) {
        for (CapitalizeConstant.Holder holder : TO_CAPITALIZE) {
            blackhole.consume(capitalize.capitalizeOldNewRegex(holder.getInput()));
        }
    }

    @Benchmark
    public void trully_capitalize(Capitalize capitalize, Blackhole blackhole) {
        for (CapitalizeConstant.Holder holder : TO_CAPITALIZE) {
            blackhole.consume(capitalize.trullyCapitalize(holder.getInput()));
        }
    }

    @Benchmark
    public void bench_new_way_with_old_rules_guillaume(Capitalize capitalize, Blackhole blackhole) {
        for (CapitalizeConstant.Holder holder : TO_CAPITALIZE) {
            blackhole.consume(capitalize.capitalizeNewLikeOldWayGuillaume(holder.getInput()));
        }
    }
}
