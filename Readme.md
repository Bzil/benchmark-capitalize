Run me `mvn clean package && java -jar target/benchmark-0.0.1-SNAPSHOT-jar-with-dependencies.jar `

Result 
```
Benchmark                                      Mode  Cnt      Score     Error  Units
CapitalizeRunner.bench_old_new_regex           avgt    5   9957,313 ± 183,159  ns/op
CapitalizeRunner.bench_old_way                 avgt    5  15945,474 ± 197,439  ns/op
CapitalizeRunner.trully_capitalize             avgt    5   2425,971 ±  37,244  ns/op
```
