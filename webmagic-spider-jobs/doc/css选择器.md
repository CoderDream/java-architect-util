


`
//XPath
String result = page.getHtml().xpath("//tr[@class='tr-third']/td/p/text()").all();

//正则表达式
String result = page.getHtml().regex("<p>(.*)+</p>").all();

//CSS选择器
String result = page.getHtml().css("tr.tr-third td p","text").all();
String result = page.getHtml().$("tr.tr-third td p","text").all();

`
https://www.cnblogs.com/juddy/p/13141582.html
抽取元素Selectable
在webmagic中主要使用了三种抽取技术：Xpath、正则表达式和CSS选择器。另外对JSON格式的内容可以使用JsonPath进行解析

### Xpath：详情了解查看w3cschool

下面是一个例子，获取属性class=mt的div标签，里面的h1标签的内容

page.getHtml().Xpath("//div[@class=mt]/h1/text()")

### CSS选择器

CSS选择器是与Xpath相似的语言，在前面的博客中已经总结了Jsoup的选择器，它比Xpath要简单一些，但是写复杂一点的抽取规则，就相对要麻烦一些

div.mt>h1表示class为mt的div标签下的直接子元素h1标签

page.getHtml().css("div.mt h1").toString();
可是使用：nth-child(n)选择第几个元素，如下选择第一个元素

page.getHtml().css("div#news_div>ul>li:nth-child(1) a").toString();
注意：需要使用>，就是直接子元素才可以选择第几个元素

### 正则表达式

正则表达式是一种通过的文本抽取语言。在这里一般用于获取url地址
