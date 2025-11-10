package m11d10.project5.core;

import m11d10.project5.util.Iterator;

/**
 * Set接口，定义集合数据结构的操作
 * 集合是不允许重复元素的无序容器
 */
public interface Set extends Container {
    
    /**
     * 添加元素到集合中
     * 如果集合中已存在该元素，则不会添加并返回false
     * @param element 要添加的元素
     * @return 如果元素被成功添加返回true，否则返回false
     */
    boolean add(Object element);
    
    /**
     * 从集合中移除指定元素
     * @param element 要移除的元素
     * @return 如果元素被成功移除返回true，否则返回false
     */
    boolean remove(Object element);
    
    /**
     * 获取集合的迭代器，用于遍历集合中的元素
     * @return 集合的迭代器
     */
    Iterator<?> iterator();
    
    /**
     * 检查当前集合是否是指定集合的子集
     * @param other 另一个集合
     * @return 如果当前集合是指定集合的子集返回true，否则返回false
     */
    boolean isSubsetOf(Set other);
    
    /**
     * 计算两个集合的并集
     * @param other 另一个集合
     * @return 包含两个集合所有元素的新集合
     */
    Set union(Set other);
    
    /**
     * 计算两个集合的交集
     * @param other 另一个集合
     * @return 包含两个集合共有元素的新集合
     */
    Set intersection(Set other);
}