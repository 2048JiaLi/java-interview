- 实现在users表中控制权限（1/2），在部门表dept中根据权限，提供不同的操作

- users表

|  id  | username | password |    phone    | accsee |
| :--: | :------: | :------: | :---------: | :----: |
|  1   | zhangsan |  123456  | 13899991234 |   1    |
|  2   |   lisi   |   6666   | 12112347412 |   2    |

- dept表

| deptno |   dname    | loc  |
| :----: | :--------: | :--: |
|   10   |   财务1    | 北京 |
|   20   | java开发部 | 天津 |
|   30   |   测试部   | 上海 |
|   4    |   销售部   | 深圳 |