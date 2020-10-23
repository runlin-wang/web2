# web2

南通职业大学 软件技术 Web2 班 动态网页设计 代码



[TOC]







## 所需环境

- JDK 1.8 及以上
- MySQL 8 以上
- jdbc  数据库连接 API （与MySQL版本对应）
- tomcat 大版本 8 或者 9
- servlet   （tools 文件夹📂中）



### JDK 及 MySQL确认

![java_version](https://raw.githubusercontent.com/runlin-wang/img/master/img/java_version.png)

>  确保 JDK 环境在 1.8 及以上

![image-20201022194549868](W:\web2\image\mysql_version.png)

> 确保 MySQL 环境在 8.0 以上 确保能够通过命令行进入 MySQL 环境



### JDBC 数据库 API

- tools 文件夹📂内自带 8.0.19 及 最新 8.0.22 版本的 jdbc 的 jar包
- 按需选择合适版本解压缩
- 如需其他版本 可至 Oracle（MySQL） [官网](https://dev.mysql.com/downloads/connector/j/)下载
- 下载使用可参考此篇[文章](https://www.cnblogs.com/NyanKoSenSei/p/11510438.html)



### Tomcat

- tools 内包含有 8.* 及 9.* 版本的 tomcat 
- 解压即可使用
- tomcat [官网](https://tomcat.apache.org/index.html)



### Servlet

- tools 文件夹📂内自带 3.1.0 的 jar 文件
- 来源：微信应用开发群文件



## 配置项目并进行使用

- 修改 LoginService.java 的 19 - 21 行 的 数据库名称及连接用户名和密码
  - 默认名称为
    - 数据库名 web2
    - 用户名     root
    - 密码        123456
- ![image-20201022201732197](https://raw.githubusercontent.com/runlin-wang/img/master/img/config.png)



- 项目结构中添加 servlet 及 jdbc 的 jar 包
- ![image-20201022202931141](https://raw.githubusercontent.com/runlin-wang/img/master/img/config2.png)

- Problems 中如含有问题 请点击 fix -> add *** 添加到项目结构中

## 运行

- 添加 Tomcat 服务器
- 选择 解压后的 Tomcat 根目录，并点击 fix
- ![image-20201022203519778](https://raw.githubusercontent.com/runlin-wang/img/master/img/config3.png)

- 将框内内容删除并点击 OK
- ![image-20201022203827590](https://raw.githubusercontent.com/runlin-wang/img/master/img/config4.png)
- 点击小绿点 运行 （run）