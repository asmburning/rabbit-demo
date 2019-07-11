import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @author liuxinyi
 * @date 2019-07-11
 */
@Slf4j
public class TestReg {

    public static void main(String[] args) {
        testReg1();
    }

    public static void testReg1() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line.length());
            log.info("result:{}, line:{}", line.matches("^[a-zA-Z]([a-z0-9A-Z_]|-){0,29}"), line);
        }
    }
}
