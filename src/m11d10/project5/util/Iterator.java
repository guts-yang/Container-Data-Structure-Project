package m11d10.project5.util;

/**
 * 迭代器接口，用于遍历容器中的元素
 * 泛型T表示元素类型
 */
public interface Iterator<T> {
    
    /**
     * 判断是否还有下一个元素
     * @return 如果还有下一个元素返回true，否则返回false
     */
    boolean hasNext();
    
    /**
     * 获取下一个元素并将迭代器前进一位
     * @return 下一个元素
     */
    T next();
}