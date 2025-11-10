package m11d10.project5.core;

import m11d10.project5.util.Iterator;

/**
 * Map接口定义了键值对映射的基本操作
 */
public interface Map extends Container {
    /**
     * 将指定的值与此映射中的指定键关联
     * 
     * @param key 键
     * @param value 值
     * @return 与指定键关联的旧值，如果没有，则返回null
     */
    Object put(Object key, Object value);
    
    /**
     * 返回指定键映射到的值
     * 
     * @param key 键
     * @return 指定键映射到的值，如果没有此键的映射关系，则返回null
     */
    Object get(Object key);
    
    /**
     * 从此映射中移除指定键的映射关系
     * 
     * @param key 键
     * @return 与指定键关联的旧值，如果没有此键的映射关系，则返回null
     */
    Object remove(Object key);
    
    /**
     * 检查此映射是否包含指定键的映射关系
     * 
     * @param key 键
     * @return 如果此映射包含指定键的映射关系，则返回true
     */
    boolean containsKey(Object key);
    
    /**
     * 检查此映射是否将一个或多个键映射到指定值
     * 
     * @param value 值
     * @return 如果此映射将一个或多个键映射到指定值，则返回true
     */
    boolean containsValue(Object value);
    
    /**
     * 返回此映射中所有键的Set视图
     * 
     * @return 此映射中所有键的Set视图
     */
    Set keySet();
    
    /**
     * 返回此映射中所有值的List视图
     * 
     * @return 此映射中所有值的List视图
     */
    List values();
    
    /**
     * 返回此映射中所有键值对的Set视图
     * 
     * @return 此映射中所有键值对的Set视图
     */
    Set entrySet();
    
    /**
     * 返回此映射中所有键值对的迭代器
     * 
     * @return 键值对迭代器
     */
    Iterator iterator();
    
    /**
     * Entry接口表示映射中的键值对
     */
    interface Entry {
        /**
         * 返回此项的键
         * 
         * @return 此项的键
         */
        Object getKey();
        
        /**
         * 返回此项的值
         * 
         * @return 此项的值
         */
        Object getValue();
        
        /**
         * 将此项的值设置为指定值
         * 
         * @param value 新值
         * @return 旧值
         */
        Object setValue(Object value);
    }
}