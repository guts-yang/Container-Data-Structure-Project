package m11d10.project5;

import m11d10.project5.core.Set;
import m11d10.project5.core.List;
import m11d10.project5.core.Map;
import m11d10.project5.impl.HashSet;
import m11d10.project5.impl.ArrayList;
import m11d10.project5.impl.HashMap;
import m11d10.project5.util.Iterator;

/**
 * 容器使用演示主程序
 * 展示三种核心容器的基本使用方法和常见操作
 */
public class ContainerDemo {
    
    public static void main(String[] args) {
        System.out.println("===== 容器数据结构演示程序 =====\n");
        
        // 演示Set容器的使用
        System.out.println("1. Set容器使用演示:");
        demoSet();
        System.out.println();
        
        // 演示List容器的使用
        System.out.println("2. List容器使用演示:");
        demoList();
        System.out.println();
        
        // 演示Map容器的使用
        System.out.println("3. Map容器使用演示:");
        demoMap();
        System.out.println();
        
        // 演示容器综合应用
        System.out.println("4. 容器综合应用演示:");
        demoCompositeUsage();
        
        System.out.println("\n===== 容器数据结构演示完成 =====");
    }
    
    /**
     * 演示Set容器的使用
     */
    private static void demoSet() {
        // 创建HashSet实例
        Set set = new HashSet();
        
        // 添加元素
        System.out.println("添加元素到Set...");
        set.add("apple");
        set.add("banana");
        set.add("orange");
        set.add("grape");
        set.add("pear");
        
        // 尝试添加重复元素
        boolean added = set.add("apple");
        System.out.println("添加重复元素'apple': " + added);
        
        // 显示Set大小
        System.out.println("Set大小: " + set.size());
        
        // 检查元素是否存在
        System.out.println("Set包含'orange': " + set.contains("orange"));
        System.out.println("Set包含'watermelon': " + set.contains("watermelon"));
        
        // 使用迭代器遍历Set
        System.out.print("遍历Set: ");
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        
        // 集合操作 - 创建第二个Set
        Set set2 = new HashSet();
        set2.add("orange");
        set2.add("grape");
        set2.add("watermelon");
        
        // 测试子集关系
        System.out.println("set2是set的子集: " + set2.isSubsetOf(set));
        
        // 测试并集
        Set union = set.union(set2);
        System.out.print("set和set2的并集: ");
        iterator = union.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        
        // 测试交集
        Set intersection = set.intersection(set2);
        System.out.print("set和set2的交集: ");
        iterator = intersection.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        
        // 删除元素
        set.remove("banana");
        System.out.println("删除'banana'后的Set大小: " + set.size());
        
        // 清空Set
        set.clear();
        System.out.println("清空后Set是否为空: " + set.isEmpty());
    }
    
    /**
     * 演示List容器的使用
     */
    private static void demoList() {
        // 创建ArrayList实例
        List list = new ArrayList();
        
        // 添加元素
        System.out.println("添加元素到List...");
        list.add("Monday");
        list.add("Tuesday");
        list.add("Wednesday");
        
        // 在指定位置插入元素
        list.add(1, "Special Day");
        
        // 通过索引访问元素
        System.out.println("List中索引1的元素: " + list.get(1));
        
        // 修改元素
        list.set(2, "Modified Tuesday");
        
        // 显示List大小
        System.out.println("List大小: " + list.size());
        
        // 使用索引遍历List
        System.out.println("使用索引遍历List:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("索引" + i + ": " + list.get(i));
        }
        
        // 查找元素位置
        int index = list.indexOf("Wednesday");
        System.out.println("'Wednesday'在List中的位置: " + index);
        
        // 创建包含重复元素的List，测试lastIndexOf
        List dupList = new ArrayList();
        dupList.add("A");
        dupList.add("B");
        dupList.add("A");
        dupList.add("C");
        System.out.println("在包含重复元素的List中，'A'最后出现的位置: " + dupList.lastIndexOf("A"));
        
        // 使用迭代器遍历
        System.out.print("使用迭代器遍历List: ");
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        
        // 创建子列表
        List subList = list.subList(1, 3);
        System.out.print("子列表(索引1到3): ");
        iterator = subList.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        
        // 删除元素
        list.remove(1);  // 通过索引删除
        System.out.println("删除索引1的元素后，List大小: " + list.size());
        
        boolean removed = list.remove("Wednesday");  // 通过元素删除
        System.out.println("删除'Wednesday'元素: " + removed);
        
        // 清空List
        list.clear();
        System.out.println("清空后List是否为空: " + list.isEmpty());
    }
    
    /**
     * 演示Map容器的使用
     */
    private static void demoMap() {
        // 创建HashMap实例
        Map map = new HashMap();
        
        // 添加键值对
        System.out.println("添加键值对到Map...");
        map.put("name", "Alice");
        map.put("age", 25);
        map.put("city", "New York");
        map.put("email", "alice@example.com");
        
        // 获取值
        String name = (String) map.get("name");
        int age = (Integer) map.get("age");
        System.out.println("姓名: " + name + ", 年龄: " + age);
        
        // 显示Map大小
        System.out.println("Map大小: " + map.size());
        
        // 检查键是否存在
        System.out.println("Map包含键'city': " + map.containsKey("city"));
        System.out.println("Map包含键'phone': " + map.containsKey("phone"));
        
        // 检查值是否存在
        System.out.println("Map包含值'New York': " + map.containsValue("New York"));
        
        // 更新值
        String oldEmail = (String) map.put("email", "alice_new@example.com");
        System.out.println("更新email，旧值: " + oldEmail);
        System.out.println("新email值: " + map.get("email"));
        
        // 遍历Map - 使用keySet
        System.out.println("使用keySet遍历Map:");
        Set keySet = map.keySet();
        Iterator keyIterator = keySet.iterator();
        while (keyIterator.hasNext()) {
            Object key = keyIterator.next();
            System.out.println(key + ": " + map.get(key));
        }
        
        // 遍历Map - 使用entrySet
        System.out.println("使用entrySet遍历Map:");
        Set entrySet = map.entrySet();
        Iterator entryIterator = entrySet.iterator();
        while (entryIterator.hasNext()) {
            Map.Entry entry = (Map.Entry) entryIterator.next();
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
        // 遍历Map - 使用直接迭代器
        System.out.println("使用Map迭代器遍历:");
        Iterator iterator = map.iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
        // 获取所有值
        List values = map.values();
        System.out.print("Map中所有的值: ");
        Iterator valueIterator = values.iterator();
        while (valueIterator.hasNext()) {
            System.out.print(valueIterator.next() + " ");
        }
        System.out.println();
        
        // 删除键值对
        Object removedValue = map.remove("age");
        System.out.println("删除'age'键值对，移除的值: " + removedValue);
        System.out.println("删除后Map大小: " + map.size());
        
        // 清空Map
        map.clear();
        System.out.println("清空后Map是否为空: " + map.isEmpty());
    }
    
    /**
     * 演示容器综合应用
     */
    private static void demoCompositeUsage() {
        // 模拟一个通讯录应用
        System.out.println("通讯录应用示例:");
        
        // 创建联系人映射，键为联系人ID，值为联系人信息（Map）
        Map contacts = new HashMap();
        
        // 创建联系人1
        Map contact1 = new HashMap();
        contact1.put("name", "张三");
        contact1.put("phone", "13800138001");
        
        // 创建联系人1的邮箱列表
        List emails1 = new ArrayList();
        emails1.add("zhangsan@example.com");
        emails1.add("zhangsan_work@example.com");
        contact1.put("emails", emails1);
        
        // 添加联系人1到通讯录
        contacts.put("001", contact1);
        
        // 创建联系人2
        Map contact2 = new HashMap();
        contact2.put("name", "李四");
        contact2.put("phone", "13900139002");
        
        // 创建联系人2的邮箱列表
        List emails2 = new ArrayList();
        emails2.add("lisi@example.com");
        contact2.put("emails", emails2);
        
        // 添加联系人2到通讯录
        contacts.put("002", contact2);
        
        // 显示通讯录中的所有联系人
        System.out.println("通讯录中的联系人:");
        Iterator contactIterator = contacts.iterator();
        while (contactIterator.hasNext()) {
            Map.Entry contactEntry = (Map.Entry) contactIterator.next();
            String contactId = (String) contactEntry.getKey();
            Map contact = (Map) contactEntry.getValue();
            
            System.out.println("联系人ID: " + contactId);
            System.out.println("  姓名: " + contact.get("name"));
            System.out.println("  电话: " + contact.get("phone"));
            
            // 显示邮箱列表
            List emails = (List) contact.get("emails");
            System.out.print("  邮箱: ");
            Iterator emailIterator = emails.iterator();
            while (emailIterator.hasNext()) {
                System.out.print(emailIterator.next());
                if (emailIterator.hasNext()) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
        
        // 找出所有有多个邮箱的联系人
        System.out.println("\n有多个邮箱的联系人:");
        contactIterator = contacts.iterator();
        while (contactIterator.hasNext()) {
            Map.Entry contactEntry = (Map.Entry) contactIterator.next();
            Map contact = (Map) contactEntry.getValue();
            List emails = (List) contact.get("emails");
            
            if (emails.size() > 1) {
                System.out.println(contact.get("name") + " 有 " + emails.size() + " 个邮箱地址");
            }
        }
    }
}