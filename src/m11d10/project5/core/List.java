package m11d10.project5.core;

import m11d10.project5.util.Iterator;

/**
 * List接口，定义列表数据结构的操作
 * 列表是允许重复元素的有序容器，支持通过索引访问元素
 */
public interface List extends Container {
    
    /**
     * 添加元素到列表末尾
     * @param element 要添加的元素
     * @return 如果元素被成功添加返回true
     */
    boolean add(Object element);
    
    /**
     * 在指定索引位置插入元素
     * @param index 插入位置的索引
     * @param element 要插入的元素
     * @throws IndexOutOfBoundsException 如果索引超出范围
     */
    void add(int index, Object element);
    
    /**
     * 移除指定索引位置的元素
     * @param index 要移除元素的索引
     * @return 被移除的元素
     * @throws IndexOutOfBoundsException 如果索引超出范围
     */
    Object remove(int index);
    
    /**
     * 移除指定的元素
     * @param element 要移除的元素
     * @return 如果元素被成功移除返回true，否则返回false
     */
    boolean remove(Object element);
    
    /**
     * 获取指定索引位置的元素
     * @param index 元素的索引
     * @return 指定索引位置的元素
     * @throws IndexOutOfBoundsException 如果索引超出范围
     */
    Object get(int index);
    
    /**
     * 设置指定索引位置的元素
     * @param index 要设置的位置索引
     * @param element 新的元素值
     * @return 被替换的旧元素
     * @throws IndexOutOfBoundsException 如果索引超出范围
     */
    Object set(int index, Object element);
    
    /**
     * 获取指定元素在列表中第一次出现的索引
     * @param element 要查找的元素
     * @return 元素第一次出现的索引，如果元素不存在返回-1
     */
    int indexOf(Object element);
    
    /**
     * 获取指定元素在列表中最后一次出现的索引
     * @param element 要查找的元素
     * @return 元素最后一次出现的索引，如果元素不存在返回-1
     */
    int lastIndexOf(Object element);
    
    /**
     * 获取列表的迭代器，用于遍历列表中的元素
     * @return 列表的迭代器
     */
    Iterator<?> iterator();
    
    /**
     * 截取列表的子列表
     * @param fromIndex 子列表的起始索引（包含）
     * @param toIndex 子列表的结束索引（不包含）
     * @return 子列表
     * @throws IndexOutOfBoundsException 如果索引超出范围
     */
    List subList(int fromIndex, int toIndex);
}