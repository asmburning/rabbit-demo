package org.lxy;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

/**
 * @author liuxinyi
 * @date 2019-07-12
 */
@Slf4j
public class TestStreamLazy {

    private long counter;

    private void wasCalled() {
        log.info("wasCalled");
        counter++;
    }

    @Test
    public void testLazy() {
        List<String> list = Arrays.asList("abc1", "abc2", "abc3");
        counter = 0;
        Stream<String> stream = list.stream().filter(element -> {
            wasCalled();
            return element.contains("2");
        });
    }

    //    intermediate operations which reduce the size of the stream
    //     should be placed before operations which are applying to each element.
    //    don’t leave an instantiated streams unconsumed as that will lead to memory leaks.

    // This can be simplified with Optional:
    //
    //Optional<User> user = Optional.ofNullable(getUser());
    //String result = user
    //  .map(User::getAddress)
    //  .map(Address::getStreet)
    //  .orElse("not specified");
    //In this example we used the map() method to convert results of calling the getAdress() to the Optional<Address> and getStreet() to Optional<String>. If any of these methods returned null the map() method would return an empty Optional.
    //
    //Imagine that our getters return Optional<T>. So, we should use the flatMap() method instead of the map():
    //
    //Optional<OptionalUser> optionalUser = Optional.ofNullable(getOptionalUser());
    //String result = optionalUser
    //  .flatMap(OptionalUser::getAddress)
    //  .flatMap(OptionalAddress::getStreet)
    //  .orElse("not specified");


    @Test
    public void testVertically() {
        List<String> list = Arrays.asList("abc1", "abc2", "abc3");
        Optional<String> stringOptional = list.stream().filter(element -> {
            log.info("filter() was called");
            return element.contains("2");
        }).map(element -> {
            log.info("map() was called");
            return element.toUpperCase();
        }).findFirst();

    }

    @Test
    public void testFunc() {
        BiFunction<String, Job, String> sf = (prefix, job) -> job.randomName(prefix);
        String applyResult = sf.apply("prefix:", new Job());
        log.info(applyResult);
    }


    public static class Job {

        public String randomName() {
            return RandomStringUtils.randomAlphanumeric(20);
        }

        public String randomName(String prefix) {
            return prefix + RandomStringUtils.randomAlphanumeric(20);
        }
    }


    /**
     * or else if always called no matter null or not
     */
    @Test
    public void testOp() {
        Optional<String> op1 = Optional.empty();
        String s = op1.orElse(orElse(10));
        log.info(s);

        Optional<String> op2 = Optional.of("abc");
        String s2 = op2.orElse(orElse(10));
        log.info(s2);
    }

    /**
     * orElseGet is only called when null
     */
    @Test
    public void testOpG() {
        Optional<String> op = Optional.of("abc");
        String s1 = op.orElseGet(() -> orElseGet(10));
        log.info(s1);

        Optional<String> op3 = Optional.empty();
        String s3 = op3.orElseGet(() -> orElseGet(10));
        log.info(s3);
    }

    private String orElse(int n) {
        log.info("method or else");
        return RandomStringUtils.randomAlphanumeric(n);
    }

    private String orElseGet(int n) {
        log.info("method or else get");
        return RandomStringUtils.randomAlphanumeric(n + 5);
    }
}
