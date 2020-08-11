## jQuery Ajax

### 1. `$.ajax()`

jQuery底层Ajax实现。简单易用的高层实现get和post方法。`$.ajax()` 返回其创建的 XMLHttpRequest 对象。大多数情况下你无需直接操作该函数，除非你需要操作不常用的选项，以获得更多的灵活性。

> API：https://jquery.cuishifeng.cn/jQuery.Ajax.html

常用参数

- contentType：发送数据到服务器时所使用的内容类型。默认是："application/x-www-form-urlencoded"。

- data：规定要发送到服务器的数据。（如果是GET传输，则可不用该参数，直接在地址拼接数据）
- dataType：预期的服务器响应的数据类型。

```
"xml": 返回 XML 文档，可用 jQuery 处理。

"html": 返回纯文本 HTML 信息；包含的script标签会在插入dom时执行。

"script": 返回纯文本 JavaScript 代码。不会自动缓存结果。除非设置了"cache"参数。'''注意：'''在远程请求时(不在同一个域下)，所有POST请求都将转为GET请求。(因为将使用DOM的script标签来加载)

"json": 返回 JSON 数据 。

"jsonp": JSONP 格式。使用 JSONP 形式调用函数时，如 "myurl?callback=?" jQuery 将自动替换 ? 为正确的函数名，以执行回调函数。

"text": 返回纯文本字符串
```

- success：请求成功后的回调函数。参数：由服务器返回，并根据dataType参数进行处理后的数据；描述状态的字符串。

例：

jQueryAjax.html

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="js/jquery.js"></script>
</head>
<body>
    <!--onblur() 失去焦点-->
    <input type="text" id="username" name="username" onblur="ck()">
    <span id="sp"></span>
</body>
    <script type="text/javascript">
        function ck() {
            var value = $("#username").val();
            $.ajax({
                // 请求方式
                type : "GET",
                // 请求地址！不用写项目名称
                url : "check",
                // 发送的数据！GET可以在url直接拼接（不拼接也会根据data数据自动拼接）
                data : "username="+value,
                // 服务器返回的数据类型。 直接对其解析
                dataType : "text",
                // 成功之后的回调函数 ====  readystate==4&&status==200
                success : function (data) { // 参数随意命名，是返回的数据
                    if (data == "1") {
                        $("#sp").html(value+"已存在！请重新输入").css("color","red")
                        // alert(value+"不可用")
                    } else {
                        $("#sp").html(value+"可以注册").css("color","green")
                        // alert(value+"可以注册")
                    }
                }
            })
        }
    </script>
</html>
```

CheckServlet.java

```java
package com.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/8/5 10:16
 */
@WebServlet(name = "CheckServlet", value = "/check")
public class CheckServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        PrintWriter writer = response.getWriter();
        if (username.equals("admin")) {
            writer.print("1");
        } else {
            writer.print("0");
        }
    }
}
```

### 2. `$.get()`

这是一个简单的 GET 请求功能以取代复杂` $.ajax` 。请求成功时可调用回调函数。如果需要在出错时执行函数，请使用` $.ajax`。

```js
function ckGet() {
    var value = $("#username").val();
    // 请求地址   参数   回调，预期返回的类型
    $.get("check","username="+value,function (data) {
        if (data == "1") {
            $("#sp").html(value+"已存在！请重新输入").css("color","red")
        } else {
            $("#sp").htmkl(value + "可以注册").css("color", "green")
        }
    },"json")
}
```

- 注意：与`.ajax()`不同的是，`.get()`的参数是有序的，且不能以json方式传递

### 3. `$.post()`

与`$.get()`的操作基本没有区别