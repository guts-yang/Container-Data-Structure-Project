package m11d10.project5.impl;

import m11d10.project5.core.Map;
import m11d10.project5.core.Set;
import m11d10.project5.core.List;
import m11d10.project5.util.Iterator;
import m11d10.project5.util.TestUtils;

/**
 * HashMap类的单元测试
 */
public class HashMapTest {
    
    public static void main(String[] args) {
        System.out.println("Running HashMap Tests...");
        
        testBasicOperations();
        testEntryOperations();
        testCollectionViews();
        testIterator();
        testBoundaryConditions();
        
        System.out.println("All HashMap tests completed!");
    }
    
    /**
     * 测试基本操作：添加、获取、删除、大小
     */
    public static void testBasicOperations() {
        String testName = "testBasicOperations";
        TestUtils.startTest(testName);
        
        int passedTests = 0;
        int totalTests = 0;
        
        Map map = new HashMap();
        
        // 测试空映射
        totalTests++;
        if (TestUtils.assertTrue(map.isEmpty(), "New map should be empty")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertEquals(0, map.size(), "New map size should be 0")) {
            passedTests++;
        }
        
        // 测试添加键值对
        totalTests++;
        if (TestUtils.assertNull(map.put("key1", "value1"), "put should return null for new key")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertNull(map.put("key2", "value2"), "put should return null for new key")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertNull(map.put(100, 200), "put should return null for new key")) {
            passedTests++;
        }
        
        // 测试更新值
        totalTests++;
        if (TestUtils.assertEquals("value1", map.put("key1", "newValue1"), 
                               "put should return old value when updating existing key")) {
            passedTests++;
        }
        
        // 测试大小和非空
        totalTests++;
        if (TestUtils.assertEquals(3, map.size(), "Map size should be 3 after adding 3 unique keys")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertFalse(map.isEmpty(), "Map should not be empty after adding key-value pairs")) {
            passedTests++;
        }
        
        // 测试获取值
        totalTests++;
        if (TestUtils.assertEquals("newValue1", map.get("key1"), "get should return updated value")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertEquals("value2", map.get("key2"), "get should return correct value")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertEquals(200, map.get(100), "get should return correct value for integer key")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertNull(map.get("nonExistentKey"), "get should return null for non-existent key")) {
            passedTests++;
        }
        
        // 测试包含键和值
        totalTests++;
        if (TestUtils.assertTrue(map.containsKey("key1"), "Map should contain key1")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertTrue(map.containsValue("value2"), "Map should contain value2")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertFalse(map.containsKey("key3"), "Map should not contain key3")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertFalse(map.containsValue("value3"), "Map should not contain value3")) {
            passedTests++;
        }
        
        // 测试删除
        totalTests++;
        if (TestUtils.assertEquals("newValue1", map.remove("key1"), "remove should return removed value")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertFalse(map.containsKey("key1"), "Map should not contain key1 after removal")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertEquals(2, map.size(), "Map size should be 2 after removal")) {
            passedTests++;
        }
        
        // 测试删除不存在的键
        totalTests++;
        if (TestUtils.assertNull(map.remove("key3"), "remove should return null for non-existent key")) {
            passedTests++;
        }
        
        // 测试清空
        map.clear();
        totalTests++;
        if (TestUtils.assertTrue(map.isEmpty(), "Map should be empty after clear")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertEquals(0, map.size(), "Map size should be 0 after clear")) {
            passedTests++;
        }
        
        TestUtils.endTest(testName, passedTests, totalTests);
    }
    
    /**
     * 测试Entry相关操作
     */
    public static void testEntryOperations() {
        String testName = "testEntryOperations";
        TestUtils.startTest(testName);
        
        int passedTests = 0;
        int totalTests = 0;
        
        Map map = new HashMap();
        map.put("key1", "value1");
        map.put("key2", "value2");
        
        // 测试entrySet
        Set entrySet = map.entrySet();
        totalTests++;
        if (TestUtils.assertEquals(2, entrySet.size(), "entrySet should have 2 entries")) {
            passedTests++;
        }
        
        // 测试Entry对象
        Iterator<?> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String) entry.getKey();
            
            if ("key1".equals(key)) {
                totalTests++;
                if (TestUtils.assertEquals("value1", entry.getValue(), "Entry with key1 should have value1")) {
                    passedTests++;
                }
                
                // 测试setValue方法
                totalTests++;
                if (TestUtils.assertEquals("value1", entry.setValue("updatedValue1"), 
                                       "setValue should return old value")) {
                    passedTests++;
                }
                
                totalTests++;
                if (TestUtils.assertEquals("updatedValue1", entry.getValue(), "Entry value should be updated")) {
                    passedTests++;
                }
            }
        }
        
        // 验证映射中的值也已更新
        totalTests++;
        if (TestUtils.assertEquals("updatedValue1", map.get("key1"), "Map value should be updated through Entry")) {
            passedTests++;
        }
        
        TestUtils.endTest(testName, passedTests, totalTests);
    }
    
    /**
     * 测试集合视图：keySet、values
     */
    public static void testCollectionViews() {
        String testName = "testCollectionViews";
        TestUtils.startTest(testName);
        
        int passedTests = 0;
        int totalTests = 0;
        
        Map map = new HashMap();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        
        // 测试keySet
        Set keySet = map.keySet();
        totalTests++;
        if (TestUtils.assertEquals(3, keySet.size(), "keySet should have 3 keys")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertTrue(keySet.contains("key1") && keySet.contains("key2") && keySet.contains("key3"), 
                               "keySet should contain all keys")) {
            passedTests++;
        }
        
        // 测试values
        List values = map.values();
        totalTests++;
        if (TestUtils.assertEquals(3, values.size(), "values should have 3 values")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertTrue(values.contains("value1") && values.contains("value2") && values.contains("value3"), 
                               "values should contain all values")) {
            passedTests++;
        }
        
        TestUtils.endTest(testName, passedTests, totalTests);
    }
    
    /**
     * 测试迭代器功能
     */
    public static void testIterator() {
        String testName = "testIterator";
        TestUtils.startTest(testName);
        
        int passedTests = 0;
        int totalTests = 0;
        
        Map map = new HashMap();
        map.put("key1", "value1");
        map.put("key2", "value2");
        
        // 测试迭代器遍历
        int count = 0;
        Iterator<?> iterator = map.iterator();
        
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            assertNotNull(entry.getKey(), "Entry key should not be null");
            assertNotNull(entry.getValue(), "Entry value should not be null");
            count++;
        }
        
        totalTests++;
        if (TestUtils.assertEquals(2, count, "Iterator should iterate 2 entries")) {
            passedTests++;
        }
        
        // 测试空映射的迭代器
        map.clear();
        iterator = map.iterator();
        
        totalTests++;
        if (TestUtils.assertFalse(iterator.hasNext(), "Iterator of empty map should have no next element")) {
            passedTests++;
        }
        
        TestUtils.endTest(testName, passedTests, totalTests);
    }
    
    /**
     * 测试边界条件
     */
    public static void testBoundaryConditions() {
        String testName = "testBoundaryConditions";
        TestUtils.startTest(testName);
        
        int passedTests = 0;
        int totalTests = 0;
        
        Map map = new HashMap();
        
        // 测试添加大量键值对，触发扩容
        for (int i = 0; i < 100; i++) {
            map.put("key" + i, "value" + i);
        }
        
        totalTests++;
        if (TestUtils.assertEquals(100, map.size(), "Map should contain 100 entries after adding 100 key-value pairs")) {
            passedTests++;
        }
        
        // 验证所有键值对都存在
        totalTests++;
        boolean allFound = true;
        for (int i = 0; i < 100; i++) {
            if (!"value" + i.equals(map.get("key" + i))) {
                allFound = false;
                break;
            }
        }
        if (TestUtils.assertTrue(allFound, "All key-value pairs should be accessible after resizing")) {
            passedTests++;
        }
        
        // 测试toArray方法
        Object[] array = map.toArray();
        totalTests++;
        if (TestUtils.assertEquals(100, array.length, "toArray should return array with 100 elements")) {
            passedTests++;
        }
        
        TestUtils.endTest(testName, passedTests, totalTests);
    }
    
    // 辅助方法，避免TestUtils的静态导入问题
    private static boolean assertNotNull(Object object, String message) {
        if (object == null) {
            System.out.println("FAIL: " + message);
            return false;
        }
        return true;
    }
}