## 一、JavaScript概述

#### 1. 简介

JavaScript（LiveScript）是一种解释性脚本语言，是一种动态类型、弱类型、基于原型继承的语言，内置支持类型。它的解释器被称为JavaScript引擎，为浏览器的一部分，广泛用于客户端的脚本语言，最早是在HTML网页上使用，用来给HTML网页增加动态功能。

## 二、基本语法

入门程序：<script></script>标签可以写在任意位置，也没有分号要求

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title></title>
	</head>
	<body>
	</body>
	<script type="text/javascript">
		document.write("hello world"); // 页面中显示
		// alert("hello world") // 弹出提示
	</script>
</html>
```

- 使用方式

  1. 在<script>标签中，可以放在网页中任意位置

  ```javascript
  <script type="text/javascript">
  		document.write("hello world"); // 页面中显示
  		// alert("hello world") // 弹出提示
  </script>
  ```

  2. 使用外部JavaScript文件，把js代码单独放入文件.js中

  ```javascript
  <script type="text/javascript" src="js/myjs.js"></script>
  ```

  3. 放在标签中的事件属性，常见事件：onclick

  ```javascript
  <button onclick="alert('!!!!')">点击</button>
  ```

#### 1. 声明变量

JavaScript中，任何变量都要用var关键字声明，分号可以省略

```javascript
var a = 2147;
document.write(typeof(a)); // number类型
var s = "abc"; // 不区分单双引号，string类型
var b = true;
```

- 注释

```javascript
单行： //
多行： /* */
```

#### 2. 基本类型

number、string、boolean、undefined、null

- 当一个变量未被初始化，它的值为undefined
- 当一个引用不存在时，为null

#### 3. 引用类型

- 类似java创建

```javascript
		// 创建对象方法
		function student() {
			this.name;
			this.age;
			
			this.show = function(){
				document.write(this.name+"\t"+this.age)
			}
		}
		
		var x1 = new student()
		x1.name = "simple"
		x1.age = 20
		document.write(x1.age)
		
		x1.show() // 类似toString方法打印
		
		var lisi = new student()
		lisi.name = 'xxx'
		lisi.age = 18
		lisi.show()
```

- json创建

```javascript
var zhangsan = {
			name:"zhang", 
			age:22,
			show:function() {
				alert("json方法")
			},
			
			arr : [1, 2, 3, 4] // 数组
		}
document.write(zhangsan) // [object Object]
document.write(zhangsan.age) // 22
```

#### 4. 数组创建

```javascript
var a = [1,2,3]
// document.write(typeof(a))
var b = new Array(3) // 可以追加赋值，但是若直接访问未赋值下标，返回undefined
b[0] = "a"
document.write(b.length+"<br>") // 打印为3
b[1] = "v"
b[2] = "x"
b[3] = 'c'
b[4] = 'd'
document.write(b.length) // 数组长度，打印为5
```

## 三、运算

- `+,-,*,/,%`

- `+=,-=,/=,*=,%`

- `&&,||,!`

**===**：全相等，类型、内容都比较

==：只比较的是内容

```javascript
var num1 = 5
var num2 = "5"

num1 == num2 // 结果为true
num1 === num2 // 结果为false
```

## 四、注意foreach循环的不同

```javascript
var a = {9, 8, 7}
for (var i in a) {
				document.write(i+"\t")
				document.write(a[i]+"<br/>")
			}
```

这里变量i拿到的是数组a的递增下标，而不是值

## 五、函数

#### 1. 函数

包含一段功能的代码。目的：重复使用

#### 2. 函数定义

用function关键字声明，后面是函数名字，参数列表里不写var。整个方法不写返回值类型

```javascript
function fun(paras) {
    // 执行
}
```

例如：

```javascript
	<script type="text/javascript">
		function method() {
			document.write("无参方法")
			document.write("<br/>")
		}
		
		method() // 调用方法
		
		// 有参方法不用写var
		function add(a, b) {
			document.write("有参方法"+a)
			document.write("<br/>")
			return a+b
		}
		var result = add(1,2)
		document.write(result+"<br/>")
	</script>
```
> 变量都是var，所以没有必要标明

#### 3. 匿名函数

```javascript
			// 匿名函数执行，需要赋值给一个变量
			var lambda = function(){
				document.write("lambda")
				document.write("<br/>")
			}
			lambda()
			
			// 匿名函数，自执行（不赋值给变量，只能执行一次）
			(function(a, b){
				document.write("自执行<br/>")
				document.write(a+b)
			}(1,2))
```
#### 4. 闭包

JS变量属于本地或全局作用域，全局变量能够通过闭包实现局部（私有）

```javascript
		var counter = 0 // 全局变量
		var add = (function(){
			var counter = 0 // 局部变量
			function add() { // 内部函数
				return counter+=1; // 访问外部自执行函数的局部变量
			}
			return add // 把add方法返回 
		}())
		document.write(add()+"<br/>")
		document.write(add()+"<br/>")
		document.write(add()+"<br/>")
		document.write(counter+"<br/>")
```
输出结果为

```
1
2
3
0
```

变量add的赋值是自调用函数的返回值

这个自调用函数只执行一次，counter初始化为0，并返回函数表达式

这样add成为了函数，它能访问父作用域中的counter

- 这样被称为JS闭包，它使函数拥有“私有”变量成为可能，即改变了局部变量的生命周期
- counter被这个匿名函数的作用域保护，并且只能使用add函数来修改
- 闭包指的是有权访问父作用域的函数，即使父函数已经关闭

#### 5. 回调

回调函数是一段可执行的代码段，它作为一个参数传递给其他的代码，其作用是在需要的时候方便调用这段函数代码

**现有使用者，后有实现者**

```js
		function add(a, b, callback) {
			document.write(a+b+"<br />")
			callback()
		}
		function test() {
			document.write("callback方法")
		}
		add(2,3,test)
```
#### 6. 系统函数

- 弹窗提示

```js
		var res = alert("弹窗") // 只是提醒
		document.write(res)
```
- 一次确定/否则的交互

```js
		var res = confirm("确定删除吗") // 接收一个返回的值, true/false
		if (res == true) {
			document.write("删除成功!")
		} else {
			document.write("删除失败!")
		}
```
- 接收一次输入

```js
		// 通过prompt输入的都是string类型
		var res = prompt("请输入姓名：")  // 接收控制台的一次输入
		document.write("输入为："+res)
```

- 类型转换解析

```js
		parseInt() // 把符合要求的进行转换
		parseFloat()
		isNaN() // 
```
## 六、事件

|    事件     | 描述                                   |
| :---------: | :------------------------------------- |
|  onchange   | HTML元素改变（离开光标触发）           |
|   onclick   | 用户点击HTML元素                       |
| onmouseover | 光标移动到HTML元素                     |
| onmouseout  | 光标离开HTML元素                       |
|  onkeydown  | 用户按下键盘按键                       |
|   onload    | 浏览器已完成页面加载                   |
|   onblur    | 元素失去焦点                           |
|   onfocus   | 元素获得焦点                           |
| ondblclick  | 当用户**双击**某个对象时调用的事件句柄 |
|  onsubmit   | 确认按钮被点击                         |
|  onselect   | 文本被选中                             |

语法

```html
事件名="函数名"
或
事件名="行内js"
```

## 七、字符串String对象

> 注意string与String的不同

- String对象用于处理文本
- 创建方法

```js
new String(s)
String(s)
```

参数s是要存储在String对象中或者转换成原始字符串的值

| 属性   | 描述       |
| ------ | ---------- |
| length | 字符串长度 |

#### 1. 方法

| 方法          | 描述                   |
| ------------- | ---------------------- |
| charAt()      | 返回指定位置的字符     |
| concat()      | 字符串连接             |
| indexOf()     | 检索                   |
| lastIndexOf() | 从后向前搜索           |
| match()       | 一个或多个正则表达匹配 |
| replace()     | 替换                   |
| search()      | 检索正则匹配           |
| slice()       | 字符串片段             |
| ...           | ...                    |

> https://www.w3school.com.cn/js/jsref_obj_string.asp

