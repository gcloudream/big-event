# big-event

项目教程：[黑马程序员SpringBoot3+Vue3全套视频教程，springboot+vue企业级全栈开发从基础、实战到面试一套通关_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV14z4y1N7pg?spm_id_from=333.788.videopod.episodes&vd_source=4c0aea890482c1b00f67058cdbb9c26e)

技术栈：springboot、mybatis-plus、mysql、redis、maven、vue、elemrnt-plus

---

## 项目预览

![image-20241109115139293](https://test-3c.oss-cn-hangzhou.aliyuncs.com/image-20241109115139293.png)

![image-20241109115733966](https://test-3c.oss-cn-hangzhou.aliyuncs.com/image-20241109115733966.png)

---

## 项目本地配置修改

1、utils目录下AliOSSUtil文件，修改OSS配置信息

![image-20241109113011452](https://test-3c.oss-cn-hangzhou.aliyuncs.com/image-20241109113011452.png)

​	在阿里云申请OSS，建立仓库，将下面修改为自己的仓库信息

![image-20241109113051765](https://test-3c.oss-cn-hangzhou.aliyuncs.com/image-20241109113051765.png)

2、application.yml 修改mysql配置

​	本地安装mysql，项目配置信息中修改账号密码

![image-20241109113319990](https://test-3c.oss-cn-hangzhou.aliyuncs.com/image-20241109113319990.png)

3、本地安装redis，项目运行前运行redis.server

4、将big_event.sql导入本地mysql

![image-20241109113750357](https://test-3c.oss-cn-hangzhou.aliyuncs.com/image-20241109113750357.png)

5、本地安装maven

6、安装npm、node

---



## 项目运行步骤

### 后端

1、使用idea打开后端文件，进入pom文件夹，刷新mavenm，下载依赖包

2、jdk使用java17以上

3、运行配置如下

![image-20241109114216254](https://test-3c.oss-cn-hangzhou.aliyuncs.com/image-20241109114216254.png)

### 前端

1、进入vue-project，路径栏输入cmd回车，进入命令行窗口

![image-20241109114507548](https://test-3c.oss-cn-hangzhou.aliyuncs.com/image-20241109114507548.png)

2、输入下面命令后访问 http://localhost:5173/

```bat
npm install   
npm run dev
```

![image-20241109114721104](https://test-3c.oss-cn-hangzhou.aliyuncs.com/image-20241109114721104.png)
