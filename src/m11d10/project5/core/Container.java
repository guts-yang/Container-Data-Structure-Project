package m11d10.project5.core;

import m11d10.project5.util.Iterator;

/**
 * 容器基础接口，定义所有容器共有的操作
 */
public interface Container {
    
    /**
     * 获取容器中元素的数量
     * @return 容器中元素的数量
     */
    int size();
    
    /**
     * 判断容器是否为空
     * @return 如果容器为空返回true，否则返回false
     */
    boolean isEmpty();
    
    /**
     * 清空容器中的所有元素
     */
    void clear();
    
    /**
     * 检查容器是否包含指定元素
     * @param element 要检查的元素
     * @return 如果包含指定元素返回true，否则返回false
     */
    boolean contains(Object element);
    
    /**
     * 将容器转换为数组
     * @return 包含容器中所有元素的数组
     */
    Object[] toArray();
}