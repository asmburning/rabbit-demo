package org.lxy;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Warmup;

import java.util.regex.Pattern;

/**
 * @author liuxinyi
 * @date 2019-07-11
 */
@Slf4j
public class TestReg3 {

    private static final String reg = "^[a-zA-Z]([\\w_]|-){0,29}";

    public static void main(String[] args) throws Exception {
        Main.main(args);
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 3)
    public void testPattern() {
        String s = RandomStringUtils.randomAlphanumeric(30);
        boolean matches = Pattern.matches(reg, s);
        log.info("s:{}, matches:{}", s, matches);
    }

}
