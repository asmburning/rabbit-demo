import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author liuxinyi
 * @date 2019-05-14
 */
@Slf4j
public class TestLong {

    @Test
    public void testLong() {
        log.info("{}", Long.valueOf(1) == 1);
        log.info("{}", Long.valueOf(1).equals(1));
        log.info("{}", Integer.valueOf(1) == 1);
        log.info("{}", Integer.valueOf(1).equals(1));
    }

    /**
     * https://juejin.im/post/5ce0e550f265da1b897a9f55
     * <p>
     * # 136. 只出现一次的数字
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * <p>
     * 说明：
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     * 示例 1:
     * 输入: [2,2,1]
     * 输出: 1
     */
    @Test
    public void leetCode136() {
        int[] array = {2, 2, 5};
        log.info("{}", solution136(array));
    }

    private int solution136(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }

    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
     * <p>
     * 说明：
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     * <p>
     * 示例 1:
     * 输入: [2,2,3,2]
     * 输出: 3
     * <p>
     * 示例 2:
     * 输入: [0,1,0,1,0,1,99]
     * 输出: 99
     */
    @Test
    public void leetCode137() {
        int[] array = {0, 1, 0, 1, 0, 1, 99};
        log.info("{}", solution137(array));
    }

    public int solution137(int[] nums) {
        int a = 0, b = 0;
        int mask;
        for (int num : nums) {
            b ^= a & num;
            a ^= num;
            mask = ~(a & b);
            a &= mask;
            b &= mask;
        }
        return a;
    }

    /**
     * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。
     * <p>
     * 示例 :
     * 输入: [1,2,1,3,2,5]
     * 输出: [3,5]
     */
    @Test
    public void leetCode260() {
        int[] array = {1, 2, 1, 3, 2, 5};
        log.info("{}", solution260(array));
    }

    public int[] solution260(int[] nums) {
        int diff = 0;
        for (int num : nums) {
            diff ^= num;
        }
        // 得到最低的有效位，即两个数不同的那一位
        diff &= -diff;
        log.info("diff:{}", diff);
        int[] result = new int[2];
        for (int num : nums) {
            if ((num & diff) == 0) {
                log.info("diff 0:{}", num);
                result[0] ^= num;
            } else {
                log.info("diff 1:{}", num);
                result[1] ^= num;
            }
        }
        return result;
    }

}
