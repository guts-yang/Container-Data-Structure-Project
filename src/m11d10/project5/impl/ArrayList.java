package m11d10.project5.impl;

import m11d10.project5.core.List;
import m11d10.project5.util.Iterator;

/**
 * ArrayList类，基于动态数组实现的List列表
 * 提供高效的随机访问能力
 */
public class ArrayList implements List {
    // 默认初始容量
    private static final int DEFAULT_CAPACITY = 10;
    // 存储元素的数组
    private Object[] elementData;
    // 列表中的元素数量
    private int size;
    
    /**
     * 构造一个空的ArrayList，初始容量为10
     */
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }
    
    /**
     * 构造一个空的ArrayList，指定初始容量
     * @param initialCapacity 初始容量
     */
    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        elementData = new Object[initialCapacity];
        size = 0;
    }
    
    /**
     * 确保数组容量足够，如果不足则进行扩容
     * @param minCapacity 所需的最小容量
     */
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elementData.length) {
            // 扩容为原来的1.5倍
            int newCapacity = elementData.length * 3 / 2 + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            
            // 创建新数组并复制元素
            Object[] newElementData = new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newElementData[i] = elementData[i];
            }
            elementData = newElementData;
        }
    }
    
    /**
     * 检查索引是否在有效范围内
     * @param index 要检查的索引
     * @throws IndexOutOfBoundsException 如果索引超出范围
     */
    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
    
    @Override
    public boolean add(Object element) {
        ensureCapacity(size + 1);
        elementData[size++] = element;
        return true;
    }
    
    @Override
    public void add(int index, Object element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        ensureCapacity(size + 1);
        
        // 移动元素，为新元素腾出位置
        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        
        elementData[index] = element;
        size++;
    }
    
    @Override
    public Object remove(int index) {
        rangeCheck(index);
        
        Object oldValue = elementData[index];
        
        // 计算需要移动的元素数量
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            // 移动元素，覆盖被删除的元素
            for (int i = index; i < size - 1; i++) {
                elementData[i] = elementData[i + 1];
            }
        }
        
        // 将末尾元素置为null，便于垃圾回收
        elementData[--size] = null;
        
        return oldValue;
    }
    
    @Override
    public boolean remove(Object element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    remove(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elementData[i])) {
                    remove(i);
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public Object get(int index) {
        rangeCheck(index);
        return elementData[index];
    }
    
    @Override
    public Object set(int index, Object element) {
        rangeCheck(index);
        
        Object oldValue = elementData[index];
        elementData[index] = element;
        return oldValue;
    }
    
    @Override
    public int indexOf(Object element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    @Override
    public int lastIndexOf(Object element) {
        if (element == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (element.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    @Override
    public boolean contains(Object element) {
        return indexOf(element) != -1;
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
        // 将所有元素置为null，便于垃圾回收
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }
    
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        for (int i = 0; i < size; i++) {
            array[i] = elementData[i];
        }
        return array;
    }
    
    @Override
    public Iterator<?> iterator() {
        return new ArrayListIterator();
    }
    
    @Override
    public List subList(int fromIndex, int toIndex) {
        if (fromIndex < 0) {
            throw new IndexOutOfBoundsException("From index: " + fromIndex);
        }
        if (toIndex > size) {
            throw new IndexOutOfBoundsException("To index: " + toIndex);
        }
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("From index " + fromIndex + " greater than to index " + toIndex);
        }
        
        ArrayList subList = new ArrayList(toIndex - fromIndex);
        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(elementData[i]);
        }
        return subList;
    }
    
    /**
     * ArrayList的迭代器实现
     */
    private class ArrayListIterator implements Iterator<Object> {
        private int cursor;
        private int lastRet = -1; // 最后返回元素的索引
        
        @Override
        public boolean hasNext() {
            return cursor < size;
        }
        
        @Override
        public Object next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException("No more elements");
            }
            
            Object element = elementData[cursor];
            lastRet = cursor++;
            return element;
        }
    }
}