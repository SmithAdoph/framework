# [Framework后台管理](https://github.com/SmithAdoph/framework)
## 基于spring-boot和ExtJs的权限管理和基础架构
>#### `com.adoph.framework`
>>#### `algorithm` 算法
>>>##### `sort.SortUtils.java` 简单排序工具类
+++++++++++++++++++++++++++++++++++++++++++
>>#### `core` 框架核心
>>>##### `core.cache` 缓存封装
>>>##### `lock` 锁
>>>>##### `DistributedLockManager.java` 基于Redis的分布式锁 
+++++++++++++++++++++++++++++++++++++++++++
>>##### `dao.jpa` 基于JPA封装BaseDao
+++++++++++++++++++++++++++++++++++++++++++
>>##### `exception` 异常
>>>##### `bean` 异常相关bean
>>>>##### `Error.java` 错误消息
>>>##### `handler` 异常handler
>>>>##### `WebExceptionHandler.java` web统一异常处理器
>>>##### `UtilException.java` 工具类异常
>>>##### `WebException.java` Web异常
+++++++++++++++++++++++++++++++++++++++++++
>>##### `permission` 权限
+++++++++++++++++++++++++++++++++++++++++++
>>##### `pojo`
>>>##### `BasePojo.java` 公共pojo
>>>##### `Page.java` 分页
+++++++++++++++++++++++++++++++++++++++++++
>>##### `test` 测试
+++++++++++++++++++++++++++++++++++++++++++
>>##### `util` 工具包
+++++++++++++++++++++++++++++++++++++++++++
>>##### `web` web相关