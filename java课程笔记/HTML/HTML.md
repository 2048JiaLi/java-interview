[TOC]

## HTML简介

### 概述：超文本标记语言（Hyper Text Markup Language）

是标准通用标记语言（SGML）的一个应用，也是一种规范，通过标记符号来标记要显示的网页中各个部分。

HTML是一门用户创建网页文档的标记语言，网页文件本身是一种文本文件，通过在文本文件中添加标记符。

HTML可以告诉浏览器如何显示其中的内容，如：文字处理、画面安排、图片显示、音频及视频的播放等

> HTML是一门用来创建网页的标记语言

###  特点

1. 简易性
2. 可扩展性
3. 平台无关性
4. 通用性

### 基本结构

```html
<!--文档声明：告诉浏览器使用HTML5版本-->
<!DOCTYPE html>
<html>
    <!--网页头部-->
    <head>
        <!--设置页面的字符集编码-->
        <meta charset="utf-8"/>
        <!--设置页面的标题-->
        <title>第一页</title>
    </head>
     <!--网页主体-->
    <body>
        <!--需要展示的信息-->
        页面内容
    </body>
</html>      
```

1. HTML页面包含头部head和主体body
2. HTML标签通常是成对出现的，有开始就有结束，这样的标签称为成对标签、没有结束标签称为空标签
3. HTML通常都有属性，格式：属性名=“属性值”（多个属性之间空格隔开）
4. HTML标签不区分大小写，建议小写
5. 文件名后缀为`.hetml`或`.htm`

### 基本标签

#### 结构标签

```html
<html><html>:根标签
<head>：头标签
    <title></title>:页面的标题
</head>
    <body></body>:主体标签-网页内容
```

```html
属性：
color:文本颜色		<font color="red">内容</font>
bgcolor:背景颜色 	<body bgcolor="bisque">text</body>
background:背景图片 <body background=".\a.png"></body>

颜色表示方式：
一、使用颜色名称：red, green, blue等
二、RGB格式，#000000, #ffffff等
```

#### 排版标签

```html
1. 注释标签：<!--注释-->
2. 换行标签：<br/>
3. 段落标签：<p>文本</p>
		特点：段与段之间有行高（行间距），自带换行
		属性：align对齐方式（left左对齐，center居中，right：右对齐）
4. 水平标签：<hr/>
		属性：
			width:水平线的长度（两种：1、像素表示；2、百分比表示）
			size：水平线的粗细
			color：水平线颜色
			align：对齐方式
```

#### 标题标签

```html
六级标题：<h1></h1> -- <h6></h6>
```

#### 容器标签

```html
<div></div>:块级标签，独占一行，换行
<span></span>:行级标签，所有内容都在同一行
	作用： <div></div>主要是结合css页面分块布局
		  <span></span>进行友好提示信息
```

#### 列表标签

- 无序标签：ul（unorder list）

  ```html
  <ul></ul>
  
  <li></li>
  ```

  > 类似markdown的`-、+、*`等列表

- 有序标签：ol（order list）

  ```html
  <ol></ol>
  ```

列表之间可以相互嵌套

#### 图片标签

```html
<img/>独立标签
属性：
	src：图片地址
	width：宽度
	height：高度
	border：边框
	align：对齐方式（top、middle、bottom）
	alt：图片的文字说明，当图片未能正确加载才显示
	title：图片的悬停显示
	hspace和vspace设定图片边沿上下左右空白，以免文字或其他图片过于贴近
```

#### 链接标签

```html
<a>文本或图片</a>
属性：
	herf：跳转页面的地址（外网需要加http）
	target：_self（自己），_blank（新页面，之前页面还存在），_parent，_top，默认_self
			_search相等于给页面起一个名字，再次打开时，如果页面存在，则不再打开新页面
	name：名称，锚点（回到锚点：顶部，底部，中间），在访问锚点的书写格式：#mame的值
	每一个a标签有name书写，给定值，需要点击跳转的herf="#name"
```

#### 表格标签

```html
<form></form>
```

