package zf.demo;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.BitSet;

/**
 * @auther: zf
 * @date: 2020-07-06 18:23
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class TestBitSet {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 利用 redisTemplate.opsForValue().setBit 提供的方法设置发现有问题
     * redisTemplate.opsForValue().getBit 可以得到某key的某偏移量上的具体值，但是如果bitCount 统计不到不知道为什么所以用方式二
     */
    @org.junit.Test
    public void testBit() {
        redisTemplate.opsForValue().set("abcd111", "test");
        System.out.println(redisTemplate.opsForValue().get("abcd111"));
        //以前不不知道用的比较少原来bit操作在模版操作的opsForValue 下 举例子是统计用户连续11天登录的情况, wangwu 1,3,5,7,9,10,11天 登录了
        redisTemplate.opsForValue().setBit("wangwu", 1, true);
        redisTemplate.opsForValue().setBit("wangwu", 3, true);
        redisTemplate.opsForValue().setBit("wangwu", 5, true);
        redisTemplate.opsForValue().setBit("wangwu", 7, true);
        redisTemplate.opsForValue().setBit("wangwu", 9, true);
        redisTemplate.opsForValue().setBit("wangwu", 10, true);
        redisTemplate.opsForValue().setBit("wangwu", 11, true);
        //获取某key wangwu的某一天的具体情况
        System.out.println("-----" + redisTemplate.opsForValue().getBit("wangwu", 2L));
        System.out.println("-----" + redisTemplate.opsForValue().getBit("wangwu", 11L));
    }

    /**
     * 功能描述: 经过查阅资料发现位图操作复杂的redisTemplate没有直接提供相关方法,的使用 redisTemplate.execute
     * 示例统计lisi 1周登录的情况
     *
     * @param:
     * @return:
     * @auther:
     */
    @org.junit.Test
    public void testBit2() {
        //假设lisi 1,3,5,6,7登录
        setBit("lisi", 1, true);
        setBit("lisi", 3, true);
        setBit("lisi", 5, true);
        setBit("lisi", 6, true);
        setBit("lisi", 7, true);
        //统计lisi登录了几天
        long wangwu = bitCount("lisi");

        System.out.println("count" + wangwu);

    }

    /**
     * 查询某key 范围
     */
    @Test
    public void  testBitCount(){
        System.out.println("count" + bitCount("lisi"));
        //0代表从开始-1代表最后，如果想统计倒数第二个就是-2
        System.out.println("统计某key 某范围的位图值"+bitCount("lisi", 0, -1));
    }

    /**
     * 位运算
     */
    @Test
    public void  testbitOp(){
//        System.out.println("bits1" + redisTemplate.opsForValue().get("lisi"));
        String saveKey="saveKey";
        System.out.println("统计某key 某范围的位图值"+bitOp( RedisStringCommands.BitOperation.AND, saveKey,"bits1", "bits2"));
        System.out.println("统计某key saveKey:"+redisTemplate.opsForValue().getBit(saveKey,1L));
    }




    public Boolean getBit(String key,Long offSet) {
        Object execute = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.getBit(key.getBytes(),offSet);
            }
        });
        return (Boolean) execute;
    }

    public Long bitCount(String key) {
        Object execute = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                long result = 0;
                result = connection.bitCount(key.getBytes());
                return result;
            }
        });
        return (Long) execute;
    }

    public Boolean setBit(String key, long offset, boolean value) {
        Object execute = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {

                return connection.setBit(key.getBytes(), offset, value);

            }
        });
        return (Boolean) execute;
    }

    /**
     * 统计某key 某范围的位图值
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Long bitCount(String key, int start, int end) {
        return (Long) redisTemplate.execute((RedisCallback<Long>) con -> con.bitCount(key.getBytes(), start, end));
    }

    /**
     * 按位运算
     * @param op
     * @param saveKey
     * @param desKey
     * @return
     */
    public Long bitOp(RedisStringCommands.BitOperation op, String saveKey, String... desKey) {
        byte[][] bytes = new byte[desKey.length][];
        for (int i = 0; i < desKey.length; i++) {
            bytes[i] = desKey[i].getBytes();
        }
        return (Long) redisTemplate.execute((RedisCallback<Long>) con -> con.bitOp(op, saveKey.getBytes(), bytes));
    }


    /**
     * java的位图
     * @param args
     */
    public static void main(String[] args) {
//        BitSet bitSet = new BitSet();
//        for (int i = 0; i < 10; i++) {
//            if (i % 2 == 0) {
//                bitSet.set(i, 0);
//            } else {
//                bitSet.set(i, 1);
//            }
//        }

        BitSet bits1 = new BitSet(16);
        BitSet bits2 = new BitSet(16);

        // set some bits
        for (int i = 0; i < 16; i++) {
            if ((i % 2) == 0) bits1.set(i);
            if ((i % 5) != 0) bits2.set(i);
        }
        System.out.println("Initial pattern in bits1: ");
        System.out.println(bits1);
        System.out.println("\nInitial pattern in bits2: ");
        System.out.println(bits2);

        // AND bits
        bits2.and(bits1);
        System.out.println("\nbits2 AND bits1: ");
        System.out.println(bits2);

        // OR bits
        bits2.or(bits1);
        System.out.println("\nbits2 OR bits1: ");
        System.out.println(bits2);

        // XOR bits
        bits2.xor(bits1);
        System.out.println("\nbits2 XOR bits1: ");
        System.out.println(bits2);
    }




}
