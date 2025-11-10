package m11d10.project5.impl;

import m11d10.project5.core.Set;
import m11d10.project5.util.Iterator;
import m11d10.project5.util.TestUtils;

/**
 * HashSet类的单元测试
 */
public class HashSetTest {
    
    public static void main(String[] args) {
        System.out.println("Running HashSet Tests...");
        
        testBasicOperations();
        testIterator();
        testSetOperations();
        testBoundaryConditions();
        
        System.out.println("All HashSet tests completed!");
    }
    
    /**
     * 测试基本操作：添加、删除、查询、大小
     */
    public static void testBasicOperations() {
        String testName = "testBasicOperations";
        TestUtils.startTest(testName);
        
        int passedTests = 0;
        int totalTests = 0;
        
        Set set = new HashSet();
        
        // 测试空集合
        totalTests++;
        if (TestUtils.assertTrue(set.isEmpty(), "New set should be empty")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertEquals(0, set.size(), "New set size should be 0")) {
            passedTests++;
        }
        
        // 测试添加元素
        totalTests++;
        if (TestUtils.assertTrue(set.add("element1"), "Should add element1 successfully")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertTrue(set.add("element2"), "Should add element2 successfully")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertTrue(set.add(100), "Should add integer 100 successfully")) {
            passedTests++;
        }
        
        // 测试重复添加
        totalTests++;
        if (TestUtils.assertFalse(set.add("element1"), "Should not add duplicate element")) {
            passedTests++;
        }
        
        // 测试大小和非空
        totalTests++;
        if (TestUtils.assertEquals(3, set.size(), "Set size should be 3 after adding 3 unique elements")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertFalse(set.isEmpty(), "Set should not be empty after adding elements")) {
            passedTests++;
        }
        
        // 测试包含
        totalTests++;
        if (TestUtils.assertTrue(set.contains("element1"), "Set should contain element1")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertTrue(set.contains(100), "Set should contain 100")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertFalse(set.contains("element3"), "Set should not contain element3")) {
            passedTests++;
        }
        
        // 测试删除
        totalTests++;
        if (TestUtils.assertTrue(set.remove("element1"), "Should remove element1 successfully")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertFalse(set.contains("element1"), "Set should not contain element1 after removal")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertEquals(2, set.size(), "Set size should be 2 after removal")) {
            passedTests++;
        }
        
        // 测试删除不存在的元素
        totalTests++;
        if (TestUtils.assertFalse(set.remove("element3"), "Should not remove non-existent element")) {
            passedTests++;
        }
        
        // 测试清空
        set.clear();
        totalTests++;
        if (TestUtils.assertTrue(set.isEmpty(), "Set should be empty after clear")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertEquals(0, set.size(), "Set size should be 0 after clear")) {
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
        
        Set set = new HashSet();
        set.add("A");
        set.add("B");
        set.add("C");
        
        // 测试迭代器遍历
        int count = 0;
        Iterator<?> iterator = set.iterator();
        
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        
        totalTests++;
        if (TestUtils.assertEquals(3, count, "Iterator should iterate 3 elements")) {
            passedTests++;
        }
        
        // 测试空集合的迭代器
        set.clear();
        iterator = set.iterator();
        
        totalTests++;
        if (TestUtils.assertFalse(iterator.hasNext(), "Iterator of empty set should have no next element")) {
            passedTests++;
        }
        
        TestUtils.endTest(testName, passedTests, totalTests);
    }
    
    /**
     * 测试集合操作：子集、并集、交集
     */
    public static void testSetOperations() {
        String testName = "testSetOperations";
        TestUtils.startTest(testName);
        
        int passedTests = 0;
        int totalTests = 0;
        
        Set set1 = new HashSet();
        set1.add(1);
        set1.add(2);
        set1.add(3);
        
        Set set2 = new HashSet();
        set2.add(2);
        set2.add(3);
        set2.add(4);
        
        Set set3 = new HashSet();
        set3.add(2);
        set3.add(3);
        
        // 测试子集
        totalTests++;
        if (TestUtils.assertTrue(set3.isSubsetOf(set1), "{2,3} should be subset of {1,2,3}")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertFalse(set1.isSubsetOf(set3), "{1,2,3} should not be subset of {2,3}")) {
            passedTests++;
        }
        
        // 测试并集
        Set union = set1.union(set2);
        totalTests++;
        if (TestUtils.assertEquals(4, union.size(), "Union of {1,2,3} and {2,3,4} should have 4 elements")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertTrue(union.contains(1) && union.contains(4), 
                               "Union should contain elements from both sets")) {
            passedTests++;
        }
        
        // 测试交集
        Set intersection = set1.intersection(set2);
        totalTests++;
        if (TestUtils.assertEquals(2, intersection.size(), "Intersection of {1,2,3} and {2,3,4} should have 2 elements")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertTrue(intersection.contains(2) && intersection.contains(3) && !intersection.contains(1), 
                               "Intersection should contain only common elements")) {
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
        
        Set set = new HashSet();
        
        // 测试添加大量元素，触发扩容
        for (int i = 0; i < 100; i++) {
            set.add("element" + i);
        }
        
        totalTests++;
        if (TestUtils.assertEquals(100, set.size(), "Set should contain 100 elements after adding 100 unique elements")) {
            passedTests++;
        }
        
        // 测试toArray方法
        Object[] array = set.toArray();
        totalTests++;
        if (TestUtils.assertEquals(100, array.length, "toArray should return array with 100 elements")) {
            passedTests++;
        }
        
        TestUtils.endTest(testName, passedTests, totalTests);
    }
}