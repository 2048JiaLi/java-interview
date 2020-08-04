## 一、BOM（Browser Object Model）

浏览器对象模型（BOM）使JavaScript有能力与浏览器“对话”

### 1. window对象

- 所有浏览器都支持window对象，它表示浏览器窗口

- 所有JavaScript全局对象、函数以及变量均自动成为window对象的成员

- 全局变量是window对象的属性

- 全局函数是window对象的方法

甚至HTML DOM的document也是window对象的属性之一

```js
window.document.getElementById("header")
```

#### 1.1 window尺寸

三种方法能够确定浏览器窗口的尺寸

对于Internet Explorer、Chrome、Firefox、Opera及Safari

- window.innerHeight：浏览器窗口的内部高度（不包括滚动条、菜单栏、工具栏）
- window.innerWidth：浏览器窗口的内部宽度（不包括滚动条、菜单栏、工具栏）

对于Internet Explorer 8、7、6、5

- document.documentElement.clientHeight
- document.documentElement.clientWidth

或者

- document.body.clientHeight
- document.body.clientWidth

#### 1.2 window方法

- close
- open

```js
	<button onclick="newWindow()">打开新窗口</button>
	<button onclick="closeWindow()">关闭新窗口</button>
	<body>
		<script type="text/javascript">
			var mywindow
			function newWindow() {
				mywindow = window.open("http://www.baidu.com")
			}
			
			function closeWindow() {
				mywindow.close()
			}
		</script>
	</body>
```

#### 1.3 screen

- 可用宽度：screen.availWidth
- 可用高度：screen.availHeight

#### 1.4 location

```html
<button onclick="locas()">重定向</button>

<script type="text/javascript">
	function locas() {
		// window.location.href="http://www.baidu.com"
		console.log(location.href)
		console.log(location.hostname)
		console.log(location.pathname)
		console.log(location.port)
		console.log(location.protocol)
	}
</script>
```

#### 1.5 history

 可以用来实现页面的后退功能

### 2. 计时

#### 2.1 Date对象

```js
		var date = new Date()
		年： 当前年 - 1900
		document.write(date.getYear() + 1900 + "<br />")
		// 年
		document.write(date.getFullYear()+"<br />")
		// 月
		document.write(date.getMonth()+1+"<br />")
		// 日
		document.write(date.getDate()+"<br />")
		// 时，分，秒
		document.write(date.getHours()+"<br />")
		document.write(date.getMinutes()+"<br />")
		document.write(date.getSeconds()+"<br />")
```
#### 2.2 JS计时

- setInterval()

		<button onclick="stops()">停止</button>
		<button onclick="goon()">继续</button>
		<script type="text/javascript">
			function times() {
				location.reload()		
			}
			var date = new Date()
			document.write(date.getFullYear()+"-"+date.getMonth()+1 +"-" + date.getDate()+ " "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"<br />")
			
			// 函数每隔一秒执行一次
			var s = setInterval(times, 1000)
			function stops() {
				clearInterval(s)
			}
			
			function goon() {
				s = setInterval(times, 1000)
			}
			
		</script>
- setTimeout：延迟

```js
// 延迟5秒弹窗一次，只执行一次
setTimeout(function(){alert("您好")},5000)
```

## 二、DOM（Document Object Model）

### 1. 概述

通过HTML DOM，使用JS访问HTML文档的所有元素

当网页被加载时，浏览器会创建页面的文件对象模型DOM

- HTML DOM模型被构造为对象的树

![](image/htmltree.gif)

浏览器加载HTML时，会将所有的HTML标签封装成对象（标签对象），称为节点（Node），并悬挂在树状结构中

**在HTML中，一切都是节点**

- **元素节点**：HTML标签
- **本文节点**：标签中的文字（比如标签之间的空格、换行），注意换行也是一个单独的文本节点
- **属性节点**：标签的属性

通过可编程的对象模型，JS获得了足够的能力来创建动态的HTML

- JS能够改变页面中的所有HTML元素
- JS能够改变页面中的所有HTML属性
- JS能够改变页面中的所有CSS样式
- JS能够对页面中的所有事件做出反应

### 2. DOM的节点属性

- nodeName：名称
  1. 元素节点的 nodeName 与标签名相同
  2. 属性节点的 nodeName 是属性的名称
  3. 文本节点的 nodeName 永远是 #text
  4. 文档节点的 nodeName 永远是 #document

- nodeValue：值
  1. 元素节点的 nodeValue 是 undefined 或 null
  2. 文本节点的 nodeValue 是文本自身
  3. 属性节点的 nodeValue 是属性的值
- nodeType：类型，是只读的

| 元素类型 | 节点属性 |
| -------- | -------- |
| 元素     | 1        |
| 属性     | 2        |
| 文本     | 3        |
| 注释     | 8        |
| 文档     | 9        |

### 3. 常用的节点属性和方法

> https://blog.csdn.net/yiren_99/article/details/47843879

- 访问父节点parentNode

**语法：**

```
elementNode.parentNode
```

> **注意:父节点只能有一个**

- 访问子节点childNodes

访问选定元素节点下的所有子节点的列表，返回的值可以看作是一个数组，他具有length属性。

**语法：**

```
elementNode.childNodes
```

**注意：**

如果选定的节点没有子节点，则该属性返回不包含节点的 NodeList。

- 访问子节点的首个和最后一个

一、**`firstChild `**属性返回‘childNodes’数组的第一个子节点。如果选定的节点没有子节点，则该属性返回 NULL。

语法：

```
node.firstChild
```

**说明：**与`elementNode.childNodes[0]`是同样的效果。 

二、**` lastChild`** 属性返回‘childNodes’数组的最后一个子节点。如果选定的节点没有子节点，则该属性返回 NULL。

**语法：**

```
node.lastChild
```

**说明：**与`elementNode.childNodes[elementNode.childNodes.length-1]`是同样的效果。 

- 访问兄弟节点

1. nextSibling 属性可返回某个节点之后紧跟的节点（处于同一树层级中）。

语法：

```
nodeObject.nextSibling
```

**说明：**如果无此节点，则该属性返回 null。

2. previousSibling 属性可返回某个节点之前紧跟的节点（处于同一树层级中）。

**语法：**

```
nodeObject.previousSibling  
```

**说明：**如果无此节点，则该属性返回 null。

- 获取属性attributes：类似于childNodes