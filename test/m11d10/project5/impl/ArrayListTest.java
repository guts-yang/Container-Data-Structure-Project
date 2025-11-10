package m11d10.project5.impl;

import m11d10.project5.core.List;
import m11d10.project5.util.Iterator;
import m11d10.project5.util.TestUtils;

/**
 * ArrayList类的单元测试
 */
public class ArrayListTest {
    
    public static void main(String[] args) {
        System.out.println("Running ArrayList Tests...");
        
        testBasicOperations();
        testIndexedOperations();
        testIterator();
        testSearchOperations();
        testBoundaryConditions();
        
        System.out.println("All ArrayList tests completed!");
    }
    
    /**
     * 测试基本操作：添加、删除、清空、大小
     */
    public static void testBasicOperations() {
        String testName = "testBasicOperations";
        TestUtils.startTest(testName);
        
        int passedTests = 0;
        int totalTests = 0;
        
        List list = new ArrayList();
        
        // 测试空集合
        totalTests++;
        if (TestUtils.assertTrue(list.isEmpty(), "New list should be empty")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertEquals(0, list.size(), "New list size should be 0")) {
            passedTests++;
        }
        
        // 测试添加元素
        totalTests++;
        if (TestUtils.assertTrue(list.add("element1"), "Should add element1 successfully")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertTrue(list.add("element2"), "Should add element2 successfully")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertTrue(list.add(100), "Should add integer 100 successfully")) {
            passedTests++;
        }
        
        // 测试允许重复元素
        totalTests++;
        if (TestUtils.assertTrue(list.add("element1"), "Should add duplicate element")) {
            passedTests++;
        }
        
        // 测试大小和非空
        totalTests++;
        if (TestUtils.assertEquals(4, list.size(), "List size should be 4 after adding 4 elements (including duplicate)")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertFalse(list.isEmpty(), "List should not be empty after adding elements")) {
            passedTests++;
        }
        
        // 测试包含
        totalTests++;
        if (TestUtils.assertTrue(list.contains("element1"), "List should contain element1")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertTrue(list.contains(100), "List should contain 100")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertFalse(list.contains("element3"), "List should not contain element3")) {
            passedTests++;
        }
        
        // 测试删除
        totalTests++;
        if (TestUtils.assertTrue(list.remove("element1"), "Should remove first occurrence of element1")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertEquals(3, list.size(), "List size should be 3 after removal")) {
            passedTests++;
        }
        
        // 测试删除不存在的元素
        totalTests++;
        if (TestUtils.assertFalse(list.remove("element3"), "Should not remove non-existent element")) {
            passedTests++;
        }
        
        // 测试清空
        list.clear();
        totalTests++;
        if (TestUtils.assertTrue(list.isEmpty(), "List should be empty after clear")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertEquals(0, list.size(), "List size should be 0 after clear")) {
            passedTests++;
        }
        
        TestUtils.endTest(testName, passedTests, totalTests);
    }
    
    /**
     * 测试索引操作：根据索引添加、获取、设置、删除
     */
    public static void testIndexedOperations() {
        String testName = "testIndexedOperations";
        TestUtils.startTest(testName);
        
        int passedTests = 0;
        int totalTests = 0;
        
        List list = new ArrayList();
        list.add("A");
        list.add("B");
        list.add("C");
        
        // 测试获取元素
        totalTests++;
        if (TestUtils.assertEquals("A", list.get(0), "list.get(0) should return \"A\"")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertEquals("B", list.get(1), "list.get(1) should return \"B\"")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertEquals("C", list.get(2), "list.get(2) should return \"C\"")) {
            passedTests++;
        }
        
        // 测试设置元素
        totalTests++;
        if (TestUtils.assertEquals("B", list.set(1, "X"), "set(1, \"X\") should return old value \"B\"")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertEquals("X", list.get(1), "After set, list.get(1) should return \"X\"")) {
            passedTests++;
        }
        
        // 测试在指定位置添加元素
        list.add(1, "Y");
        totalTests++;
        if (TestUtils.assertEquals("Y", list.get(1), "After add(1, \"Y\"), list.get(1) should return \"Y\"")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertEquals("X", list.get(2), "After add, list.get(2) should return \"X\"")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertEquals(4, list.size(), "List size should be 4 after inserting element")) {
            passedTests++;
        }
        
        // 测试根据索引删除元素
        totalTests++;
        if (TestUtils.assertEquals("Y", list.remove(1), "remove(1) should return \"Y\"")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertEquals(3, list.size(), "List size should be 3 after indexed removal")) {
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
        
        List list = new ArrayList();
        list.add("A");
        list.add("B");
        list.add("C");
        
        // 测试迭代器遍历
        int count = 0;
        Iterator<?> iterator = list.iterator();
        
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        
        totalTests++;
        if (TestUtils.assertEquals(3, count, "Iterator should iterate 3 elements")) {
            passedTests++;
        }
        
        // 测试空集合的迭代器
        list.clear();
        iterator = list.iterator();
        
        totalTests++;
        if (TestUtils.assertFalse(iterator.hasNext(), "Iterator of empty list should have no next element")) {
            passedTests++;
        }
        
        TestUtils.endTest(testName, passedTests, totalTests);
    }
    
    /**
     * 测试搜索操作：indexOf、lastIndexOf
     */
    public static void testSearchOperations() {
        String testName = "testSearchOperations";
        TestUtils.startTest(testName);
        
        int passedTests = 0;
        int totalTests = 0;
        
        List list = new ArrayList();
        list.add("A");
        list.add("B");
        list.add("A");
        list.add("C");
        
        // 测试indexOf
        totalTests++;
        if (TestUtils.assertEquals(0, list.indexOf("A"), "indexOf(\"A\") should return 0")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertEquals(1, list.indexOf("B"), "indexOf(\"B\") should return 1")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertEquals(-1, list.indexOf("D"), "indexOf(\"D\") should return -1")) {
            passedTests++;
        }
        
        // 测试lastIndexOf
        totalTests++;
        if (TestUtils.assertEquals(2, list.lastIndexOf("A"), "lastIndexOf(\"A\") should return 2")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertEquals(3, list.lastIndexOf("C"), "lastIndexOf(\"C\") should return 3")) {
            passedTests++;
        }
        
        // 测试subList
        List subList = list.subList(1, 3);
        totalTests++;
        if (TestUtils.assertEquals(2, subList.size(), "subList(1,3) should have size 2")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertEquals("B", subList.get(0), "subList.get(0) should be \"B\"")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertEquals("A", subList.get(1), "subList.get(1) should be \"A\"")) {
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
        
        List list = new ArrayList();
        
        // 测试添加大量元素，触发扩容
        for (int i = 0; i < 100; i++) {
            list.add("element" + i);
        }
        
        totalTests++;
        if (TestUtils.assertEquals(100, list.size(), "List should contain 100 elements after adding 100 elements")) {
            passedTests++;
        }
        
        // 测试toArray方法
        Object[] array = list.toArray();
        totalTests++;
        if (TestUtils.assertEquals(100, array.length, "toArray should return array with 100 elements")) {
            passedTests++;
        }
        
        // 测试在边界位置添加和删除
        list.add(0, "FIRST");
        list.add(list.size(), "LAST");
        
        totalTests++;
        if (TestUtils.assertEquals("FIRST", list.get(0), "First element should be \"FIRST\"")) {
            passedTests++;
        }
        
        totalTests++;
        if (TestUtils.assertEquals("LAST", list.get(list.size() - 1), "Last element should be \"LAST\"")) {
            passedTests++;
        }
        
        TestUtils.endTest(testName, passedTests, totalTests);
    }
}