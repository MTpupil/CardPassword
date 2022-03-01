[卡密后台管理系统](#卡密后台管理系统)
- [创作原因](#创作原因)
- [技术栈](#技术栈)
- [模块分解](#模块分解)
  - [登录功能](#登录功能)


# 卡密后台管理系统

> @auther 木瞳

## 创作原因

> 某日一时兴起，简单思考了下卡密管理的系统结构，然后画了一张草图，于是就动手实践起来。

<img src="https://s4.ax1x.com/2022/03/01/blShJH.png" alt="草图" style="zoom:30%;" />

## 技术栈

- java
- html
- springboot
- mysql
- css
- JavaScript
- ....

## 模块分解

### 登录功能

登录的实现是接收用户的账号和密码，通过与数据库中的数据进行比对，判断账号是否存在以及是否正确。

登录失败会判断失败原因：

1. 用户权限不足（仅admin用户才可以登录后台）
2. 账号或密码错误

失败后自动返回登录界面，并提示用户失败原因。

登录成功后跳转到后台首页，并将用户信息存入session中。

### 用户验证

为了防止用户直接访问后台，需要在后台首页添加一个验证用户是否登录的功能。

主要的验证方法是通过session中的用户信息判断用户是否登录，如果没有登录则跳转到登录界面，并提示还未登陆的信息。





## 总结























