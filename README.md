# 容器数据结构项目

本项目实现了三种核心数据结构容器：Set（集合）、List（列表）和Map（映射），并提供了完整的功能实现、文档和单元测试。

## 项目结构

```
m11d10/project5/
├── src/
│   └── m11d10/
│       └── project5/
│           ├── core/         # 核心接口定义
│           │   ├── Container.java    # 容器通用接口
│           │   ├── Set.java          # Set接口
│           │   ├── List.java         # List接口
│           │   └── Map.java          # Map接口
│           ├── impl/         # 接口实现
│           │   ├── MySet.java        # Set实现
│           │   ├── MyList.java       # List实现
│           │   └── MyMap.java        # Map实现
│           ├── util/         # 工具类
│           │   └── Iterator.java     # 迭代器接口
│           └── Main.java     # 主程序演示
├── test/
│   └── m11d10/
│       └── project5/
│           ├── SetTest.java          # Set单元测试
│           ├── ListTest.java         # List单元测试
│           └── MapTest.java          # Map单元测试
└── README.md                 # 项目说明文档
```

## 功能概述

### Set（集合）
- 不允许重复元素的无序集合
- 支持元素添加、删除、查找和遍历
- 基于哈希表实现以获得良好的性能

### List（列表）
- 允许重复元素的有序集合
- 支持按索引访问、添加、删除和修改元素
- 基于动态数组实现

### Map（映射）
- 存储键值对的容器
- 键不允许重复，值可以重复
- 支持根据键查找、添加、删除和修改值

## 编译与运行

### 编译项目

```bash
d:\coding\java\class_1\m11d10\project5> javac -d d:\coding\java\class_1 src\m11d10\project5\*.java src\m11d10\project5\core\*.java src\m11d10\project5\impl\*.java src\m11d10\project5\util\*.java
```

### 运行主程序

```bash
d:\coding\java\class_1> java m11d10.project5.Main
```

### 运行测试

```bash
d:\coding\java\class_1\m11d10\project5> javac -d d:\coding\java\class_1 test\m11d10\project5\*.java
d:\coding\java\class_1> java m11d10.project5.SetTest
d:\coding\java\class_1> java m11d10.project5.ListTest
d:\coding\java\class_1> java m11d10.project5.MapTest
```