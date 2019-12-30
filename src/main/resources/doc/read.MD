##项目使用到的技术
| 项目使用到的技术 | 
| :-----:| 
| spring boot | 
| mybatis | 
|spring data jpa|
|swagger文档自动生成技术|
|druid 监控|
|mysql数据库：主从复制策略|
|mybatis generator 自动生成策略|
|定义全局异常|
|密码采用AES加密方式|

project 访问地址: http://127.0.0.1:8081/ <br/>
swagger 访问地址: http://127.0.0.1:8081/swagger-ui.html <br/>
druid   访问地址: http://127.0.0.1:8081/druid/login.html <br/>

alipay 支付参考：https://www.cnblogs.com/xifengxiaoma/p/10107635.html

mybatis generator 如何在idea 中使用：
1.创建spring boot 项目
2.在resources 下创建generator 文件夹
3.在创建generator 文件夹的下面创建generatorConfig.xml 
4.在xml 文件夹中找到，location 即你的连接数据库jar 的本地地址 <classPathEntry  location="E:\JeeRTD\libs\mysql-connector-java-5.1.30.jar"/>
5.修改数据库连接
6.修改要自动生成的表
7.选择idea 工具栏的Run ---Edit configurations---选择 + ----选择maven---在Name 栏输入generator---Command line 输入:mybatis-generator:generate -e 
---apply---ok 即可

