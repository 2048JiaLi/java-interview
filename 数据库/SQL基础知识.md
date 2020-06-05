# SELECT

- 定义：用于从表中选取数据。结果被存储在一个结果表中（称为结果集）。

- 语法：`SELECT 列名称 FROM 表名称;`

# DISTINCT用法
在表中，可能会包含重复值。关键词`DISTINCT`用于返回唯一不同的值。
- 语法：`SELECT DISTINCT 列名称 FROM 表名称;`

> 如果某个字段或某几个字段所对应的记录都重复了，使用DISTINCT就可以只显示一条记录，简言之就是**去重**。

# TOP用法
- 定义：用于规定要返回的记录的数目。
> 并非所有的数据库系统都支持TOP子句

- 语法：`SELECT TOP number|percent columns FROM 表名称;`

- MySQL类似TOP语法：`SELECT columns FROM 表名称 LIMIT number;`
> 例如：`selct * from Customers LIMIT 5;`

# WHERE用法
有条件地从表中选取数据
- 语法：`SELECT 列名称 FROM 表名称 WHERE 列 运算符 值;`

可在WHERE中使用的运算符如下：

|操作符|描述|
|:-|:-|
|=| 等于|
|<>|不等于|
|>|大于|
|<|小于|
|>=|大于等于|
|<=|小于等于|
|BETWEEN|在某个范围内|
|LIKE|搜索某种模式|
|IN|指定针对某个列的多个可能值|
|EXISTS|在子查询中匹配到符合条件的数据行|

> 例如：`select * from Customers where 城市='北京';`
>
> PS:SQL使用单引号环绕文本值