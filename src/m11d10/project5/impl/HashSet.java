package m11d10.project5.impl;

import m11d10.project5.core.Set;
import m11d10.project5.util.Iterator;

/**
 * HashSet类，基于哈希表实现的Set集合
 * 使用数组+链表的方式处理哈希冲突
 */
public class HashSet implements Set {
    // 默认初始容量
    private static final int DEFAULT_CAPACITY = 16;
    // 负载因子阈值，超过该值时进行扩容
    private static final float LOAD_FACTOR = 0.75f;
    // 存储元素的数组，每个元素是一个链表头节点
    private Node[] table;
    // 集合中的元素数量
    private int size;
    
    /**
     * 内部节点类，用于构建链表处理哈希冲突
     */
    private static class Node {
        Object element;
        Node next;
        
        Node(Object element, Node next) {
            this.element = element;
            this.next = next;
        }
    }
    
    /**
     * 构造一个空的HashSet，初始容量为16
     */
    public HashSet() {
        table = new Node[DEFAULT_CAPACITY];
        size = 0;
    }
    
    /**
     * 构造一个空的HashSet，指定初始容量
     * @param initialCapacity 初始容量
     */
    public HashSet(int initialCapacity) {
        int capacity = 1;
        while (capacity < initialCapacity) {
            capacity <<= 1; // 确保容量是2的幂
        }
        table = new Node[capacity];
        size = 0;
    }
    
    @Override
    public boolean add(Object element) {
        if (element == null) {
            throw new NullPointerException("Cannot add null element to HashSet");
        }
        
        // 检查是否需要扩容
        if (size >= table.length * LOAD_FACTOR) {
            resize();
        }
        
        int index = hash(element) & (table.length - 1);
        
        // 检查元素是否已存在
        Node current = table[index];
        while (current != null) {
            if (current.element.equals(element)) {
                return false; // 元素已存在，不添加
            }
            current = current.next;
        }
        
        // 添加新元素到链表头部
        table[index] = new Node(element, table[index]);
        size++;
        return true;
    }
    
    @Override
    public boolean remove(Object element) {
        if (element == null) {
            return false;
        }
        
        int index = hash(element) & (table.length - 1);
        Node current = table[index];
        Node previous = null;
        
        while (current != null) {
            if (current.element.equals(element)) {
                if (previous == null) {
                    // 移除链表头节点
                    table[index] = current.next;
                } else {
                    // 移除链表中间节点
                    previous.next = current.next;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        
        return false; // 元素不存在
    }
    
    @Override
    public boolean contains(Object element) {
        if (element == null) {
            return false;
        }
        
        int index = hash(element) & (table.length - 1);
        Node current = table[index];
        
        while (current != null) {
            if (current.element.equals(element)) {
                return true;
            }
            current = current.next;
        }
        
        return false;
    }
    
    @Override
    public int size() {
        return size;
    }
    
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    @Override
    public void clear() {
        table = new Node[DEFAULT_CAPACITY];
        size = 0;
    }
    
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        
        for (int i = 0; i < table.length; i++) {
            Node current = table[i];
            while (current != null) {
                array[index++] = current.element;
                current = current.next;
            }
        }
        
        return array;
    }
    
    @Override
    public Iterator<?> iterator() {
        return new HashSetIterator();
    }
    
    @Override
    public boolean isSubsetOf(Set other) {
        if (other == null) {
            throw new NullPointerException("Cannot compare with null set");
        }
        
        Iterator<?> iterator = this.iterator();
        while (iterator.hasNext()) {
            if (!other.contains(iterator.next())) {
                return false;
            }
        }
        
        return true;
    }
    
    @Override
    public Set union(Set other) {
        if (other == null) {
            throw new NullPointerException("Cannot union with null set");
        }
        
        HashSet result = new HashSet(size + other.size());
        
        // 添加当前集合的所有元素
        Iterator<?> iterator = this.iterator();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        
        // 添加另一个集合的所有元素
        Iterator<?> otherIterator = other.iterator();
        while (otherIterator.hasNext()) {
            result.add(otherIterator.next());
        }
        
        return result;
    }
    
    @Override
    public Set intersection(Set other) {
        if (other == null) {
            throw new NullPointerException("Cannot intersect with null set");
        }
        
        HashSet result = new HashSet(Math.min(size, ((HashSet)other).size));
        
        // 遍历较小的集合，减少查找次数
        Iterator<?> iterator = size < ((HashSet)other).size ? 
                                    this.iterator() : other.iterator();
        Set largerSet = size < ((HashSet)other).size ? other : this;
        
        while (iterator.hasNext()) {
            Object element = iterator.next();
            if (largerSet.contains(element)) {
                result.add(element);
            }
        }
        
        return result;
    }
    
    /**
     * 计算对象的哈希值
     * @param element 要计算哈希值的对象
     * @return 哈希值
     */
    private int hash(Object element) {
        int h = element.hashCode();
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
        
        // 重新哈希所有元素
        for (int i = 0; i < oldTable.length; i++) {
            Node current = oldTable[i];
            while (current != null) {
                add(current.element);
                current = current.next;
            }
        }
    }
    
    /**
     * HashSet的迭代器实现
     */
    private class HashSetIterator implements Iterator<Object> {
        private int currentIndex;
        private Node currentNode;
        private int count;
        
        public HashSetIterator() {
            currentIndex = 0;
            currentNode = null;
            count = 0;
            
            // 找到第一个非空的链表
            while (currentIndex < table.length && table[currentIndex] == null) {
                currentIndex++;
            }
            
            if (currentIndex < table.length) {
                currentNode = table[currentIndex];
            }
        }
        
        @Override
        public boolean hasNext() {
            return count < size;
        }
        
        @Override
        public Object next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException("No more elements");
            }
            
            Object element = currentNode.element;
            currentNode = currentNode.next;
            count++;
            
            // 如果当前链表遍历完了，找下一个非空链表
            if (currentNode == null) {
                currentIndex++;
                while (currentIndex < table.length && table[currentIndex] == null) {
                    currentIndex++;
                }
                
                if (currentIndex < table.length) {
                    currentNode = table[currentIndex];
                }
            }
            
            return element;
        }
    }
}