package org.lxy;

import lombok.extern.slf4j.Slf4j;

/**
 * @author liuxinyi
 * @date 2019-07-11
 */
@Slf4j
public class TestReg2 {

//    public static void main(String[] args) throws Exception{
//        Main.main(args);
//    }

//    @State(Scope.Benchmark)
//    public static class ExecutionPlan {

//        @Param({ "100", "200", "300", "500", "1000" })
//        public int iterations;
//
//        public Hasher murmur3;
//
//        public String password = "4v3rys3kur3p455w0rd";
//
//        @Setup(Level.Invocation)
//        public void setUp() {
//            murmur3 = Hashing.murmur3_128().newHasher();
//        }
//    }


//    @Fork(value = 1, warmups = 1)
//    @Benchmark
//    @BenchmarkMode(Mode.Throughput)
//    @Warmup(iterations = 5)
//    public void benchMurmur3_128(ExecutionPlan plan) {
//
//        for (int i = plan.iterations; i > 0; i--) {
//            plan.murmur3.putString(plan.password, Charset.defaultCharset());
//        }
//
//        plan.murmur3.hash();
//    }


}
