package m11d10.project5.util;

/**
 * 测试工具类，提供简单的断言方法
 */
public class TestUtils {
    
    /**
     * 断言两个对象相等
     * @param expected 期望值
     * @param actual 实际值
     * @param message 失败时的错误消息
     * @return 如果断言通过返回true，否则打印错误信息并返回false
     */
    public static boolean assertEquals(Object expected, Object actual, String message) {
        boolean equal = (expected == null && actual == null) || 
                       (expected != null && expected.equals(actual));
        
        if (!equal) {
            System.out.println("FAIL: " + message);
            System.out.println("  Expected: " + expected);
            System.out.println("  Actual: " + actual);
        }
        
        return equal;
    }
    
    /**
     * 断言布尔值为true
     * @param condition 要测试的条件
     * @param message 失败时的错误消息
     * @return 如果断言通过返回true，否则打印错误信息并返回false
     */
    public static boolean assertTrue(boolean condition, String message) {
        if (!condition) {
            System.out.println("FAIL: " + message);
        }
        
        return condition;
    }
    
    /**
     * 断言布尔值为false
     * @param condition 要测试的条件
     * @param message 失败时的错误消息
     * @return 如果断言通过返回true，否则打印错误信息并返回false
     */
    public static boolean assertFalse(boolean condition, String message) {
        return assertTrue(!condition, message);
    }
    
    /**
     * 断言对象为null
     * @param object 要测试的对象
     * @param message 失败时的错误消息
     * @return 如果断言通过返回true，否则打印错误信息并返回false
     */
    public static boolean assertNull(Object object, String message) {
        return assertTrue(object == null, message);
    }
    
    /**
     * 断言对象不为null
     * @param object 要测试的对象
     * @param message 失败时的错误消息
     * @return 如果断言通过返回true，否则打印错误信息并返回false
     */
    public static boolean assertNotNull(Object object, String message) {
        return assertFalse(object == null, message);
    }
    
    /**
     * 开始测试用例
     * @param testName 测试用例名称
     */
    public static void startTest(String testName) {
        System.out.println("\n=== Running Test: " + testName + " ===");
    }
    
    /**
     * 结束测试用例
     * @param testName 测试用例名称
     * @param passedTests 通过的测试数量
     * @param totalTests 总测试数量
     */
    public static void endTest(String testName, int passedTests, int totalTests) {
        System.out.println("=== Test " + testName + " Completed: " + 
                          passedTests + "/" + totalTests + " passed ===\n");
    }
}