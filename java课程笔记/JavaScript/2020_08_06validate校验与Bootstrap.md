## validate插件

### 1. 概述

```
validate：jQuery的一个插件，依赖jQuery使用，validation是历史最悠久的jQuery插件之一，经过了全球范围内不同项目的验证，并得到了许多web开发者的好评。作为一个标准的验证方法库，validation拥有如下特点：

1. 内置验证规则：拥有必填、数字、Email、url和信用卡号码等19类内置验证规则
2. 自定义验证规则：很方便的自定义规则
3. 简单强大的验证信息提示：默认了验证信息提示，并提供自定义覆盖默认的提示信息功能
4. 实时验证：可能通过keyup与blur事件触发验证，而不仅仅在表单提交的时候验证
```

### 2. 使用步骤

```
1. 导入jQuery文件
2. 导入validate.js
3. 页面加载成功后，对表单进行验证： 
	$(function () {
		#("选择器").validate()
	}
4. 在validate中编写校验规则
	$("选择器").validate({
            // 规则，对谁做验证，指定验证的规则
            rules : {},
            // 覆盖默认的提示信息，可省略（使用默认）
            messages : {

            }
        })
```

### 3. 默认规则

| 属性        | 默认值 | 类型    | 说明                                                         |
| :---------- | :----- | :------ | :----------------------------------------------------------- |
| required    | false  | boolean | 是否必填                                                     |
| remote      | -      | string  | 使用 ajax 方法调用 check.php 验证输入值。                    |
| email       | -      | boolean | 电子邮箱格式校验。                                           |
| url         | -      | boolean | 网址格式校验。                                               |
| date        | -      | boolean | 日期格式校验 ie6 出错，慎用。                                |
| dateISO     | -      | boolean | 必须输入正确格式的日期（ISO），例如：2009-06-23，1998/01/22。只验证格式，不验证有效性。 |
| number      | -      | boolean | 校验数字（负数，小数）。                                     |
| digits      | -      | boolean | 校验整数。                                                   |
| creditcard  | -      | boolean | 校验信用卡号。                                               |
| equalTo     | -      | string  | 如："#password"。输入值必须和 #password 相同,通常用于密码验证。 |
| accept      | -      | string  | 输入拥有合法后缀名的字符串（常用来校验上传的文件格式）。     |
| min         | -      | number  | 如：10，输入值不能小于 10。                                  |
| minlength   | -      | number  | 如：10，输入长度最小是 10 的字符串（汉字算一个字符）。       |
| max         | -      | number  | 如：5，输入值不能大于 5。                                    |
| maxlength   | -      | number  | 如：5，输入长度最多是 5 的字符串（汉字算一个字符）。         |
| range       | -      | array   | 如：[5,10]，输入值必须介于 5 和 10 之间。                    |
| rangelength | -      | array   | 如：[5,10]，输入长度必须介于 5 和 10 之间的字符串（汉字算一个字符）。 |

### 4. 例

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="js/jquery-1.8.3.js" type="text/javascript" charset="UTF-8"></script>
    <!--validate校验库-->
    <script src="js/jquery.validate.js"></script>
    <!--国际化库，汉化提示， dist/localization文件夹下各种中文包-->
    <script src="js/messages_zh.js"></script>

    <script type="text/javascript">
        // 页面加载
        $(function () {
            $("#formID").validate({
                // 规则，对谁做验证，指定验证的规则
                rules : {
                    username : "required",
                    password : {
                        required : true,
                        digits : true
                    },
                    repassword : {
                        required : true,
                        // equalTo : "#password" // 这是用id查找
                        // 或
                        equalTo : "[name=password]" // 这是name查找
                    },
                    minvalue : {
                        min : 10
                    },
                    interval : {
                        range : [5,10]
                    }
                },


                // 覆盖默认的提示信息，可省略（使用默认）
                messages : {
                    // 修改username默认提醒（默认为：这是必填字段）
                    username : "请输入用户名！",
                    password : "您必须输入数字内容",
                    repassword : "两次密码输入不一致",
                    minvalue : "输入的年龄不能低于{0}岁", // 用{0}通用占位，随着规则中的值变化
                }
            })
        })

    </script>
</head>
<body>
    <form id="formID" action="">
        必填：<input type="text" name="username"><br/>
        必填数字：<input type="text" id="password" name="password"><br/>
        必填重复：<input type="text" name="repassword"><br/>
        最小值：<input type="text" name="minvalue"><br/>
        区间：<input type="text" name="interval"><br/>
        <input type="submit" name="提交"><br/>
    </form>
</body>
</html>
```

## Bootstrap入门

### 1. 概述

Bootstrap 是一个用于快速开发 Web 应用程序和网站的前端框架。Bootstrap 是基于 HTML、CSS、JAVASCRIPT 的。Bootstrap 是由 *Twitter* 的 *Mark Otto* 和 *Jacob Thornton* 开发的。Bootstrap 是 2011 年八月在 GitHub 上发布的开源产品。

- 特点

**移动设备优先**：自 Bootstrap 3 起，框架包含了贯穿于整个库的移动设备优先的样式。

**浏览器支持**：所有的主流浏览器都支持 Bootstrap。

**容易上手**：只要您具备 HTML 和 CSS 的基础知识，您就可以开始学习 Bootstrap。

**响应式设计**：Bootstrap 的响应式 CSS 能够自适应于台式机、平板电脑和手机。更多有关响应式设计的内容详见 [Bootstrap 响应式设计](https://www.runoob.com/bootstrap/bootstrap-v2-responsive-design.html)。

>  Bootstrap中文网：https://v3.bootcss.com/

### 2. 使用

> https://v3.bootcss.com/getting-started/#grunt

先安装并导入相关包

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--导入bootstrap的css文件-->
    <link rel="stylesheet" href="css/bootstrap.css">
    <title>Title</title>
</head>
<body>
    <h1>你好，世界！</h1>

    <!--先导入jQuery的js文件-->
    <script src="js/jquery-1.12.4.js"></script>
    <script src="js/bootstrap.js"></script>
</body>

</html>
```

### 3. 全局css

> https://v3.bootcss.com/css/

- 布局容器

Bootstrap 需要为页面内容和栅格系统包裹一个 `.container` 容器。我们提供了两个作此用处的类。注意，由于 `padding` 等属性的原因，这两种 容器类不能互相嵌套。

`.container` 类用于固定宽度并支持响应式布局的容器。

```
<div class="container">
  ...
</div>
```

`.container-fluid` 类用于 100% 宽度，占据全部视口（viewport）的容器。

```
<div class="container-fluid">
  ...
</div>
```

- 等等，参见中文网教程