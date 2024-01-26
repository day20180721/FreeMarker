* Meta中，每個template都如同定義了一個變數，變數名稱為configName
例如 : daoInterface(configName).packageName
* mybatis-plus在FreeMarker使用的變數列表 
``` 
名称	含义
tableClass.fullClassName	类的全称(包括包名)
tableClass.shortClassName	类的简称
tableClass.tableName	表名
tableClass.pkFields	表的所有主键字段
tableClass.allFields	表的所有字段
tableClass.baseFields	排除主键和 blob 的所有字段
tableClass.baseBlobFields	排除主键的所有字段
tableClass.remark	表注释
字段信息

名称	含义
field.fieldName	字段名称
field.columnName	列名称
field.jdbcType	jdbc 类型
field.columnLength	列段长度
field.columnScale	列的精度
field.columnIsArray	字段类型是不是数组类型
field.shortTypeName	java 类型短名称, 通常用于定义字段
field.fullTypeName	java 类型的长名称, 通常用于导入
field.remark	字段注释
field.autoIncrement	是否自增
field.nullable	是否允许为空
配置信息

名称	含义
baseInfo.shortClassName	配置名称
baseInfo.tableName	配置文件名称
baseInfo.pkFields	配置名称
baseInfo.allFields	后缀
baseInfo.baseFields	包名
baseInfo.baseBlobFields	模板内容
baseInfo.remark	相对模块的资源文件路径
————————————————
版权声明：本文为CSDN博主「我是Superman丶」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/G971005287W/article/details/123072551
```




     
