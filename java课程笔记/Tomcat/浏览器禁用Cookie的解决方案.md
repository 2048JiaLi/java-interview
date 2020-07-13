### 1. 浏览器禁用Cookie的后果

如果浏览器禁用Cookie、Session还能用吗？

- 不能，但是有其他解决方案

服务器在默认情况下，会使用Cookie的方式将sessionID发送给浏览器，如果用户禁用Cookie，则该sessionID不会被浏览器保存，此时，服务器可以使用如URL重写这样的方式来发送sessionID。

- 使用Session区分每个用户的方式
  - 使用Cookie
  - 作为隐藏域嵌入HTML表单中，附加在主体URL中，通常作为指向其他应用程序页面的链接，即URL重写

### 2. 什么是URL重写

浏览器在访问服务器上的某个地址时，不再使用原来的那个地址，而是使用经过改写的地址（即，在原来的地址后面加上了sessionID）

### 3. 如何使用URL重写

- 如果是链接地址和表单提交，使用`response.encodeURL(String url)`生成重写后的URL
- 如果是重定向，使用`response.encodeRedirectURL(String url)`生成重写的URL