package m11d10.project5.impl;

import m11d10.project5.core.Map;
import m11d10.project5.core.Set;
import m11d10.project5.core.List;
import m11d10.project5.util.Iterator;

/**
 * HashMap实现了Map接口，使用哈希表存储键值对
 * 支持高效的添加、删除、查找操作
 */
public class HashMap implements Map {
    // 默认初始容量
    private static final int DEFAULT_CAPACITY = 16;
    // 负载因子阈值，超过该值时进行扩容
    private static final float LOAD_FACTOR = 0.75f;
    // 存储键值对的数组，每个元素是一个链表头节点
    private Node[] table;
    // 映射中的键值对数量
    private int size;
    
    /**
     * 内部节点类，用于存储键值对并构建链表
     */
    private static class Node implements Map.Entry {
        Object key;
        Object value;
        Node next;
        
        Node(Object key, Object value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
        
        @Override
        public Object getKey() {
            return key;
        }
        
        @Override
        public Object getValue() {
            return value;
        }
        
        @Override
        public Object setValue(Object value) {
            Object oldValue = this.value;
            this.value = value;
            return oldValue;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            
            Node node = (Node) o;
            
            if (!key.equals(node.key)) return false;
            return value != null ? value.equals(node.value) : node.value == null;
        }
    }
    
    /**
     * 构造一个空的HashMap，初始容量为16
     */
    public HashMap() {
        table = new Node[DEFAULT_CAPACITY];
        size = 0;
    }
    
    /**
     * 构造一个空的HashMap，指定初始容量
     * @param initialCapacity 初始容量
     */
    public HashMap(int initialCapacity) {
        int capacity = 1;
        while (capacity < initialCapacity) {
            capacity <<= 1; // 确保容量是2的幂
        }
        table = new Node[capacity];
        size = 0;
    }
    
    public Object put(Object key, Object value) {
        if (key == null) {
            throw new NullPointerException("Cannot put null key to HashMap");
        }
        
        // 检查是否需要扩容
        if (size >= table.length * LOAD_FACTOR) {
            resize();
        }
        
        int index = hash(key) & (table.length - 1);
        
        // 检查键是否已存在
        Node current = table[index];
        while (current != null) {
            if (current.key.equals(key)) {
                // 键已存在，更新值
                Object oldValue = current.value;
                current.value = value;
                return oldValue;
            }
            current = current.next;
        }
        
        // 添加新的键值对到链表头部
        table[index] = new Node(key, value, table[index]);
        size++;
        return null;
    }
    
    public Object get(Object key) {
        if (key == null) {
            return null;
        }
        
        int index = hash(key) & (table.length - 1);
        Node current = table[index];
        
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        
        return null; // 键不存在
    }
    
    public Object remove(Object key) {
        if (key == null) {
            return null;
        }
        
        int index = hash(key) & (table.length - 1);
        Node current = table[index];
        Node previous = null;
        
        while (current != null) {
            if (current.key.equals(key)) {
                Object oldValue = current.value;
                
                if (previous == null) {
                    // 移除链表头节点
                    table[index] = current.next;
                } else {
                    // 移除链表中间节点
                    previous.next = current.next;
                }
                
                size--;
                return oldValue;
            }
            
            previous = current;
            current = current.next;
        }
        
        return null; // 键不存在
    }
    
    public boolean containsKey(Object key) {
        if (key == null) {
            return false;
        }
        
        int index = hash(key) & (table.length - 1);
        Node current = table[index];
        
        while (current != null) {
            if (current.key.equals(key)) {
                return true;
            }
            current = current.next;
        }
        
        return false;
    }
    public boolean containsValue(Object value) {
        // 遍历整个哈希表查找值
        for (int i = 0; i < table.length; i++) {
            Node current = table[i];
            
            while (current != null) {
                if (value == null ? current.value == null : value.equals(current.value)) {
                    return true;
                }
                current = current.next;
            }
        }
        
        return false;
    }
    
    public Set keySet() {
        HashSet keySet = new HashSet(size);
        
        for (int i = 0; i < table.length; i++) {
            Node current = table[i];
            
            while (current != null) {
                keySet.add(current.key);
                current = current.next;
            }
        }
        
        return keySet;
    }
    
    public List values() {
        ArrayList valuesList = new ArrayList(size);
        
        for (int i = 0; i < table.length; i++) {
            Node current = table[i];
            
            while (current != null) {
                valuesList.add(current.value);
                current = current.next;
            }
        }
        
        return valuesList;
    }
    
    public Set entrySet() {
        HashSet entrySet = new HashSet(size);
        
        for (int i = 0; i < table.length; i++) {
            Node current = table[i];
            
            while (current != null) {
                entrySet.add(current);
                current = current.next;
            }
        }
        
        return entrySet;
    }
    
    @Override
    public boolean contains(Object element) {
        // Map接口从Container继承的方法，检查是否包含指定元素
        return containsValue(element);
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public void clear() {
        table = new Node[DEFAULT_CAPACITY];
        size = 0;
    }
    
    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        
        for (int i = 0; i < table.length; i++) {
            Node current = table[i];
            
            while (current != null) {
                array[index++] = current;
                current = current.next;
            }
        }
        
        return array;
    }
    
    public Iterator iterator() {
        return new HashMapIterator();
    }
    
    /**
     * HashMap迭代器实现，遍历所有键值对
     */
    private class HashMapIterator implements Iterator {
        private int currentIndex = 0;
        private Node currentNode = null;
        private Node nextNode = null;
        private int traversedCount = 0;
        
        public HashMapIterator() {
            // 找到第一个非空节点作为起始点
            for (int i = 0; i < table.length; i++) {
                if (table[i] != null) {
                    nextNode = table[i];
                    currentIndex = i;
                    break;
                }
            }
        }
        
        @Override
        public boolean hasNext() {
            return traversedCount < size;
        }
        
        @Override
        public Object next() {
            if (!hasNext()) {
                throw new IllegalStateException("No more elements");
            }
            
            currentNode = nextNode;
            traversedCount++;
            
            // 查找下一个要访问的节点
            // 首先检查当前链表是否还有下一个节点
            if (currentNode.next != null) {
                nextNode = currentNode.next;
            } else {
                // 否则查找下一个非空桶
                nextNode = null;
                currentIndex++;
                for (; currentIndex < table.length; currentIndex++) {
                    if (table[currentIndex] != null) {
                        nextNode = table[currentIndex];
                        break;
                    }
                }
            }
            
            return currentNode;
        }
    }
    
    /**
     * 计算键的哈希值
     * @param key 要计算哈希值的键
     * @return 哈希值
     */
    private int hash(Object key) {
        int h = key.hashCode();
        // 扰动函数，减少哈希冲突
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }
    
    /**
     * 扩容哈希表
     */
    private void resize() {
        Node[] oldTable = table;
        int newCapacity = table.length * 2;
        table = new Node[newCapacity];
        size = 0;
        
        // 重新哈希所有键值对
        for (int i = 0; i < oldTable.length; i++) {
            Node current = oldTable[i];
            
            while (current != null) {
                put(current.key, current.value);
                current = current.next;
            }
        }
    }
}