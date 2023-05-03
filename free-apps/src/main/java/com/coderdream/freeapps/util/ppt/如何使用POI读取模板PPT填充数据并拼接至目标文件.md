POI是一个用于操作Microsoft Office格式文件的Java API库。它可用于读取、写入和操作Word、Excel和PowerPoint等Microsoft Office格式文件。在日常工作中，我们经常需要处理PPT文件，例如制作报告、演示文稿等。而对于一些重复性工作，使用POI库读取模板PPT并填充数据，再拼接至目标文件可以大大提高工作效率，减少手动操作的时间和错误率。
在本篇博客中，我们将分享如何使用POI库读取模板PPT并填充数据，最终生成目标文件。我们参考了CSDN上的其他博客，结合具体代码进行演示，展示使用POI库操作PPT文件的方法和技巧。

在使用POI库读取模板PPT并填充数据的过程中，我们需要先导入POI的相关jar包，然后通过SlideShow类读取模板文件，使用Slide类对PPT中的幻灯片进行操作，例如获取文本框、图片等元素，并使用TextRun类对文本框进行文本替换样式调整。在数据填充完成后，我们可以通过SlideShow类将填充好的幻灯片拼接至目标文件中，最终得到我们想要的PPT文件。在整个操作过程中，我们需要注意异常处理和代码优化，以保证代码的可靠性和效率。

通过本篇博客的学习和实践，我们可以掌握使用POI库读取模板PPT并填充数据的方法和技巧，提高工作效率，减少手动操作的时间和错误率。

接下来，我们将结合具体代码进行演示，展示如何使用POI库读取模板PPT并填充数据，最终生成目标文件。

HSLF教程
[XSLF教程](https://poi.apache.org/components/slideshow/xslf-cookbook.html)
poi4.1文档

## 一、PPT文件格式介绍

### 1、PPT文件格式的概述

PPT文件格式是Microsoft PowerPoint演示文稿的格式，用于存储幻灯片、文本、图形、图像、音频和视频等多媒体元素。PPT文件格式的扩展名为 **.ppt（PowerPoint 97-2003格式）**和 .pptx（PowerPoint 2007及以后版本格式）。

PPT文件格式的基本结构由一系列XML文件和二进制数据组成。

* 在.ppt文件中，每个幻灯片都被存储为一个二进制数据流，PPT文件的二进制数据包含了PPT文件的所有元素和布局信息。
* 而在.pptx文件中，每个幻灯片都被存储为一个XML文件，PPTX文件的XML文件则将这些元素和布局信息以标记语言的形式存储。

PPT文件格式的版本和兼容性是需要注意的问题。在Microsoft PowerPoint 97至2003版本中，PPT文件格式使用二进制文件格式，而在Microsoft PowerPoint 2007及以后版本中，PPTX文件格式使用XML文件格式。由于这两个版本的PPT文件格式不兼容，使用不同版本的Microsoft PowerPoint软件打开PPT文件可能会导致文件格式错误或元素丢失等问题。

为了实现对PPT文件的操作和处理，可以使用POI类库。POI类库是一个Java类库，可以读取、写入和操作Microsoft Office格式的文档，包括PPT文件。POI类库支持HSLF和XSLF两种PPT文件格式的操作，提供了丰富的API和工具，可以方便地读取、修改、创建和保存PPT文件。

### 2、HSLF和XSLF的区别

HSLF（Horrible Slide Layout Format）和 XSLF（XML Slide Layout Format）是POI类库中用于操作PPT文件的两种不同的API。HSLF是用于操作PPT文件的旧API，而XSLF是用于操作PPTX文件的新API。

以下是HSLF和XSLF的区别：

* 文件格式：HSLF是用于操作PPT文件的API，而XSLF是用于操作PPTX文件的API。PPT文件是二进制格式，而PPTX文件是基于XML的文件格式。
* API：HSLF提供了对PPT文件的操作和处理，包括读取、修改和创建等；而XSLF则提供了对PPTX文件的操作和处理，也包括读取、修改和创建等。
* 功能：XSLF相比HSLF提供了更多的功能和特性，如更好的文本和形状控制、更高的图像质量和更灵活的布局控制等。
* 兼容性：XSLF可以处理PPTX文件的所有特性和功能，而HSLF则有一些限制，无法处理PPTX文件中的一些新特性和功能。
* 性能：XSLF相比HSLF需要更多的内存和CPU资源，因为PPTX文件是基于XML的格式，需要解析和处理XML文件，而PPT文件则是二进制格式，相对来说更加高效。

总的来说，如果需要操作PPT文件，可以使用HSLF API，如果需要操作PPTX文件，可以使用XSLF API。然而，如果需要同时处理PPT和PPTX文件，则需要考虑两种API的差异性和兼容性，以确保代码的正确性和稳定性。

### 3、如何选择合适的POI类库
选择合适的POI类库取决于需要操作的文档类型和所需的功能。以下是选择适合的POI类库的一些考虑因素：

* 文档类型：首先需要确定需要操作的文档类型是PPT还是XLS或DOC等。如果需要操作PPT文件，则需要使用HSLF或XSLF API，如果需要操作XLS文件，则需要使用HSSF API，如果需要操作DOC文件，则需要使用HWPF API等。
* 功能需求：需要确定需要实现哪些功能，POI类库提供了丰富的API和工具，可以用于读取、写入、修改、创建和保存文档，包括文本、图像、图表、表格、公式等元素的操作。根据具体的需求选择相应的API。
  兼容性：需要考虑POI类库的兼容性，POI类库支持的文档类型和版本可能有所不同，需要根据实际情况选择相应的API。
* 性能：需要考虑POI类库的性能，POI类库的性能可能会受到文档大小、复杂度和计算机配置等因素的影响。对于大型文档和复杂操作，需要选择具有更好性能的API。
  学习曲线：需要考虑POI类库的学习曲线，POI类库提供了丰富的API和工具，但也需要一定的学习和实践才能熟练掌握。

综上所述，需要根据文档类型、功能需求、兼容性、性能和学习曲线等因素来选择适合的POI类库。同时，也需要考虑代码的可维护性、稳定性和可扩展性等方面，以确保代码质量和效率。

##  二、SlideShow

SlideShow 接口定义了一组方法，用于处理幻灯片演示文稿的数据。为了使用 SlideShow 接口，需要使用具体的实现类XMLSlideShow和HSLFSlideShow。这些实现类提供了 SlideShow 接口中定义的所有方法，并且可以用来读取、创建和编辑幻灯片演示文稿。它们分别用于打开PPT、pptX和老版本的PPT文件。

* HSLFSlideShow 类用于处理 PowerPoint 97-2003 幻灯片演示文稿，即 PPT 文件。它使用 HSLF 模型来处理幻灯片演示文稿，HSLF 模型是基于 Microsoft OLE 2 Compound Document Format 的解析器，它可以解析幻灯片演示文稿中的所有元素，如幻灯片、文本框、形状、图像、音频、视频等等。HSLFSlideShow 类提供了一组方法，用于读取、创建和编辑 PPT 文件中的幻灯片。
* XMLSlideShow 类用于处理 PowerPoint 2007 或更高版本的幻灯片演示文稿，即 PPTX 文件。它使用 XML 模型来处理幻灯片演示文稿，XML 模型是基于 OpenXML 文件格式的解析器，它可以解析幻灯片演示文稿中的所有元素，如幻灯片、文本框、形状、图像、音频、视频等等。XMLSlideShow 类提供了一组方法，用于读取、创建和编辑 PPTX 文件中的幻灯片。

总的来说，HSLFSlideShow 用于处理 PPT 文件，XMLSlideShow 用于处理 PPTX 文件。它们都提供了类似的方法，如 getSlides()、createSlide()、getPageSize() 等等，但是具体的实现略有不同。如果需要处理 PPT 文件，就应该使用 HSLFSlideShow 类；如果需要处理 PPTX 文件，就应该使用 XMLSlideShow 类。

引入依赖：本文版本5.2.2（pms引入的easyExcel依赖的poi版本是5.2.2）

```xml
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi</artifactId>
    <version>5.2.2</version>
    <scope>compile</scope>
</dependency>
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>5.2.2</version>
    <scope>compile</scope>
</dependency>
<!-- 读取PPTX格式的依赖 -->
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml-schemas</artifactId>
    <version>5.2.2</version>
    <scope>compile</scope>
</dependency>
<!-- 读取PPT 97-2003格式的依赖 -->
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-scratchpad</artifactId>
    <version>5.2.2</version>
</dependency>
```

## 三、读取PPT文件

### 1、加载PPT文件

在使用这些类打开PPT文件之前，需要先使用Java IO类库中的FileInputStream类打开文件流，然后将文件流传递给相关的类来读取PPT文件。下面是一个简单的Java示例代码，演示如何使用HSLFSlideShow类和XMLSlideShow类来打开PPT和pptX文件：

```java
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;

public class OpenPPTX {
    public static void main(String[] args) {
      	try {
            FileInputStream fis = new FileInputStream("example.ppt"); // 打开ppt文件流
            HSLFSlideShow ppt = new HSLFSlideShow(fis); // 创建HSLFSlideShow对象
            fis.close(); // 关闭文件流
            // 处理pptX文件
        } catch (IOException e) {
            e.printStackTrace();
        }  	
        
        try {
            FileInputStream fis = new FileInputStream("example.pptx"); // 打开pptX文件流
            XMLSlideShow pptx = new XMLSlideShow(fis); // 创建XMLSlideShow对象
            fis.close(); // 关闭文件流
            // 处理pptX文件
        } catch (IOException e) {
            e.printStackTrace();
        }

	}
}  
```


上述代码中，使用FileInputStream类打开PPT和pptX文件流，然后分别使用 HSLFSlideShow类 和 XMLSlideShow类 创建ppt和pptX文件的对象，并在文件处理完之后关闭文件流。需要注意的是，读取ppt和pptX文件时都需要使用try-catch语句来捕获IOException异常。

总的来说，HSLFSlideShow和XMLSlideShow是Apache POI库中用于处理PPT文件的类，它们分别用于打开PPT、pptX和老版本的PPT文件，使用时需要先使用Java IO类库中的FileInputStream类打开文件流，然后将文件流传递给相关的类来读取PPT文件。

### 2、获取PPT文件中的幻灯片

要获取PPT文件中的幻灯片，可以使用SlideShow、XMLSlideShow和HSLFSlideShow类的getSlides() 方法来获取PPT文件中的所有幻灯片。

* HSLFSlide

```
HSLFSlideShow ppt = new HSLFSlideShow(fis); // 创建HSLFSlideShow对象
for (HSLFSlide slide : ppt.getSlides()) {
		// 处理每个幻灯片
}
```

* XSLFSlide

```
XMLSlideShow pptx = new XMLSlideShow(fis); // 创建XMLSlideShow对象
for (XSLFSlide slide : pptx.getSlides()) {
    // 处理每个幻灯片
}
```

### 3、获取幻灯片中的形状元素

HSLFSlide 和 XSLFSlide 类都提供了一组方法来获取幻灯片中的形状元素。可以使用 getShapes() 方法来获取幻灯片中的所有形状，然后判断形状的类型进行相关类型的操作。下面分别介绍如何使用这两个类来获取这些元素。

* HSLFShape

```java
// 如获取第一张幻灯片
HSLFSlide slide = ppt.getSlides().get(0);
// 获取幻灯片中的所有形状遍历
for (HSLFShape shape : slide.getShapes()) {
    if (shape instanceof HSLFTextBox) {
        // 文本框
    }
    if (shape instanceof HSLFPictureShape) {
        // 图片
    }
    if (shape instanceof HSLFTable) {
        // 表格
    }
  	//……
}
```

* XSLFShape

```java
// 如获取第一张幻灯片
XSLFSlide slide = ppt.getSlides().get(0);
// 获取幻灯片中的所有形状遍历
for (XSLFShape shape : slide.getShapes()) {
    if (shape instanceof XSLFTextBox) {
        // 文本框
    }
    if (shape instanceof XSLFPictureShape) {
        // 图片
    }
    if (shape instanceof XSLFTable) {
        // 表格
    }
    //……
}
```

## 四、修改PPT文件内容

### 1、幻灯片操作

在使用POI读取模板PPT填充数据并拼接至目标文件时，您可能需要对幻灯片进行操作。如新增一张幻灯片、删除某张幻灯片、移动幻灯片到指定位置以及复制幻灯片。

#### 1.1 新增幻灯片

HSLFSlideShow 和 XSLFSlideShow 类都提供了 createSlide() 方法，用于在最后新增一张空白的幻灯片。

```java
HSLFSlide slide = ppt.createSlide();
XSLFSlide slide = pptx.createSlide();
```

#### 1.2 删除幻灯片

HSLFSlideShow 和 XSLFSlideShow 类都提供了 removeSlide() 方法，用于删除指定位置或对象的幻灯片。

例如，要删除第二个幻灯片，可以使用以下代码：

```java
// 删除第二个幻灯片
ppt.removeSlide(1);注意，幻灯片的索引从 0 开始，因此第二个幻灯片的索引是 1。
```

#### 1.3 移动幻灯片

使用 HSLFSlideShow 或 XSLFSlideShow 类的 getSlides() 方法获取幻灯片列表，然后使用 List 类的 add() 和 remove() 方法来实现幻灯片的移动。

例如，将第二个幻灯片移动到第四个位置，可以使用以下代码：

```java
List<XSLFSlide> slides = pptx.getSlides();
slides.add(3, slides.get(1));
slides.remove(1);
```

在上面的代码中，我们首先使用 HSLFSlideShow 的 getSlides() 方法获取幻灯片列表，然后获取第二个幻灯片对象，使用 List 类的 add() 方法将该幻灯片添加到第四个位置，最后使用 List 类的 remove() 方法将原来的第二个幻灯片删除。

#### 1.4 复制幻灯片

使用 HSLFSlide 或 XSLFSlide 类的 createSlide() 方法新增一张幻灯片，并将需要复制的幻灯片中所有的形状复制至新增的幻灯片，即完成复制操作。

* HSLF

```java
/**
 * 复制ppt单页
 *
 * @param template 模板页
 * @param ppt      HSLFSlideShow ppt
 * @param index    移动到指定下标
 * @return 复制页
 */
public static HSLFSlide copySlide(HSLFSlide template, HSLFSlideShow ppt, int index) {
    // 创建新的一页PPT，按模板的布局母页
    HSLFSlide newSlide = ppt.createSlide();
    // 复制模板页中的shapes
    List<HSLFShape> shapes = template.getShapes();
    shapes.forEach(shape -> {
        newSlide.addShape(shape);
    });
    // 排序	
    List<HSLFSlide> slides = ppt.getSlides();
    ppt.reorderSlide(slides.size(), newIndex);
    return newSlide;
}
```

* XSLF

```java
/**
 * 复制ppt单页
 *
 * @param template 模板页
 * @param ppt      ppt
 * @param newIndex 复制页放置位置
 * @return 复制页
 */
private XSLFSlide copySlide(XSLFSlide template, XMLSlideShow ppt, int newIndex) {
    // 创建新幻灯片且填充内容
    XSLFSlide newSlide = ppt.createSlide().importContent(template);
    // 排序（在PPT中的第几页）
    ppt.setSlideOrder(newSlide, newIndex);
    return newSlide;
}
```

### 2、设置幻灯片中的形状元素的属性

在使用POI读取模板PPT填充数据并拼接至目标文件时，您可能需要对幻灯片中的形状元素进行属性修改。这些属性包括形状大小、形状文本、形状填充和形状边框等。您可以使用POI的相应方法来修改这些属性，以满足您的实际需求。

注：以下内容基于XSLF

#### 2.1 形状的位置以及大小

可以使用POI的setAnchor方法来修改形状的大小和位置。例如：

```java
XSLFSlide slide = ...; // 获取幻灯片
XSLFTextShape shape = ...; // 获取文本框形状
Rectangle2D anchor = new Rectangle2D.Double(x, y, width, height); // 新的位置和大小
shape.setAnchor(anchor); // 设置新的位置和大小
```

#### 2.2 形状的文本

在POI中，用于表示PPT中的文本框的类主要包括以下几个类：

* XSLFTextShape：表示PPT中的形状中的文本框，是XSLFShape的子类。
* XSLFTextBox：表示PPT中的文本框，是XSLFTextShape的子类。
* XSLFTextParagraph：表示文本框中的段落，每个段落可以包含多个文本片段。
* XSLFTextRun：表示文本框中的文本片段，每个文本片段可以设置不同的字体、字号、颜色等属性。
* XSLFTextShapeAutoFit：用于设置文本框的自适应大小方式。
* XSLFTextShape.TextDirection：用于设置文本框中文本的方向。
* XSLFTextShape.TextDecoration：用于设置文本框中文本的装饰效果，例如下划线、删除线等。
* XSLFTextShape.TextPlaceholder：用于设置文本框的占位符属性，例如标题、正文、页脚等。

其中，XSLFTextShape和XSLFTextBox都是用于表示PPT中的文本框的类。它们的主要区别在于它们继承的类不同，以及它们可以应用的场景不同。

* XSLFTextShape是XSLFShape的子类，可以表示PPT中的各种形状（包括文本框）以及组合形状等。它具有更广泛的应用场景，可以用于表示任何形状中的文本内容，例如矩形、梯形、圆形等。相对于XSLFTextBox，XSLFTextShape的API更加丰富，可以设置更多的属性，例如字体、字号、颜色等。
* XSLFTextBox是XSLFTextShape的子类，专门用于表示PPT中的文本框。它可以包含多行文本，并且可以自动调整大小以适应文本内容。相对于XSLFTextShape，XSLFTextBox的API更加简洁，可以更方便地操作文本框的内容和属性。

因此，当您需要在PPT中添加文本框时，可以选择使用XSLFTextShape或XSLFTextBox，具体取决于您的实际需求。如果您需要在形状中添加文本内容，可以选择XSLFTextShape；如果您需要添加一个自适应大小的文本框，可以选择XSLFTextBox。

##### 1、XSLFTextShape设置新的文本内容：

```java
XSLFTextShape shape = ...; // 获取文本框形状
String text = "新的文本"; // 新的文本内容
shape.setText(text); // 设置新的文本内容
```

##### 2、XSLFTextBox 设置新的文本内容

```java
XSLFTextBox textBox = ...; 						// 获取文本框形状
String text = "新的文本"; 						 // 新的文本内容
textBox.setText(text);								// 设置新的文本内容
textBox.setVerticalAlignment(VerticalAlignment.MIDDLE);		// 设置垂直对齐方式（例如：垂直居中）
```

#### 2.3 形状文本属性

在POI中，可以通过XSLFTextRun类来设置形状文本的字体颜色、加粗、字号等属性。XSLFTextRun类表示文本框中的文本片段，每个文本片段可以设置不同的字体、字号、颜色等属性。

##### 1、设置字体颜色：使用setFontColor方法设置文本片段的字体颜色，参数为一个XSLFColor对象。

```java
XSLFTextRun textRun = ...;
textRun.setFontColor(new XSLFColor(Color.RED));
```

##### 2、设置加粗：使用setBold方法设置文本片段是否加粗，参数为true或false。

```java
XSLFTextRun textRun = ...;
textRun.setBold(true);
```

##### 3、设置字号：使用setFontSize方法设置文本片段的字号，参数为一个float类型的数值。

```java
XSLFTextRun textRun = ...;
textRun.setFontSize(14.0);
```

##### 4、设置字体：使用setFontFamily方法设置文本片段的字体，参数为字体名称字符串。

```java
XSLFTextRun textRun = ...;
textRun.setFontFamily("Arial");
```

#### 2.4 形状填充

可以使用POI的setFillColor方法来修改形状的填充颜色。例如：

* XSLFTextShape

```java
XSLFTextShape shape = ...; // 获取形状
Color fillColor = Color.RED; // 新的填充颜色
shape.setFillColor(fillColor); // 设置新的填充颜色
```

* XSLFTextBox

```java
XSLFTextBox textBox = ...; 						// 获取文本框形状
Color color = new Color(0, 0, 0, 40); // 新的填充颜色(如透明色)
textBox.setFillColor(color);					// 设置新的填充颜色
```

#### 2.5 形状边框

可以使用POI的setLineColor方法来修改形状的边框颜色，以及使用setLineWidth方法来修改边框的宽度。例如：

* XSLFTextShape

```java
XSLFTextShape shape = ...; // 获取形状
Color lineColor = Color.BLUE; // 新的边框颜色
int lineWidth = 2; // 新的边框宽度
shape.setLineColor(lineColor); // 设置新的边框颜色
shape.setLineWidth(lineWidth); // 设置新的边框宽度
```

* XSLFTextBox

```java
XSLFTextBox textBox = ...; // 获取形状
Color lineColor = Color.BLUE; // 新的边框颜色
int lineWidth = 2; // 新的边框宽度
textBox.setLineColor(lineColor); // 设置新的边框颜色
textBox.setLineWidth(lineWidth); // 设置新的边框宽度
```

#### 2.6 形状对齐方式

在POI中，可以通过XSLFTextShape、XSLFTextBox类的setVerticalAlignment和setHorizontalCentered方法来设置形状中内容的垂直对齐方式和水平对齐方式。

* 垂直对齐方式：setTextVerticalAlignment方法可以设置文本框中文本的垂直对齐方式，方法的参数是VerticalAlignment枚举类，常用的对齐方式包括：
  * VerticalAlignment.TOP：文本框中文本向上对齐；
  * VerticalAlignment.MIDDLE：文本框中文本垂直居中对齐；
  * VerticalAlignment.BOTTOM：文本框中文本向下对齐。
  * VerticalAlignment.JUSTIFIED：文本框中文本两端对齐
  * VerticalAlignment.DISTRIBUTED：文本框中文本分散对齐

前三个值是常用的对齐方式。JUSTIFY和DISTRIBUTED一般用于多行文本的对齐，可以使文本在每行末尾对齐或者分散对齐。

* 水平对齐方式：setTextHorizontalCentered方法可以设置文本框中文本的水平对齐方式，常用的对齐方式包括：
  * true：文本框中文本水平居中对齐。
  * false：文本框中文本左对齐。

* XSLFTextShape

```java
XSLFTextShape shape =  ...; 													// 获取形状
shape.setVerticalAlignment(VerticalAlignment.TOP);		// 垂直对齐方式
shape.setHorizontalCentered(true);												// 设置水平对齐方式
```

* XSLFTextBox

```java
XSLFTextBox textBox =  ...; 														// 获取形状
textBox.setVerticalAlignment(VerticalAlignment.MIDDLE); // 垂直对齐方式
textBox.setHorizontalCentered(false);										// 设置水平对齐方式
```

#### 实战：在生成招商PPT需求时为展位图片添加指定标题的方法

```java
/**
 * 指定幻灯片案例图片加上标题
 *
 * @param slide          XSLFSlide 幻灯片
 * @param x              新建矩形的左上角的X坐标
 * @param y              新建矩形的左上角的y坐标
 * @param w              新建矩形的宽度
 * @param h              新建矩形的高度
 * @param fontSize       字号
 * @param transparentRed 背景色
 * @param context        内容
 */
private void addActivityPictureTitle(XSLFSlide slide, double x, double y, double w, double h, Double fontSize, Color transparentRed, String context) {
    XSLFTextBox textBox = slide.createTextBox();
    textBox.setText(context);
    textBox.setFillColor(transparentRed);
    textBox.setVerticalAlignment(VerticalAlignment.MIDDLE);
    Rectangle2D anchor = new Rectangle2D.Double(x, y, w, h);
    textBox.setAnchor(anchor);
    XSLFTextParagraph paragraph = textBox.getTextParagraphs().get(0);
    paragraph.setTextAlign(TextParagraph.TextAlign.CENTER);
    XSLFTextRun textRun = paragraph.getTextRuns().get(0);
    textRun.setFontColor(Color.white);
    textRun.setBold(true);
    textRun.setFontSize(fontSize);
}
```

### 3、添加新的形状

#### 3.1 添加新的文本框

使用createTextBox方法在幻灯片中添加新的文本框。可以通过setAnchor方法设置文本框的位置和大小，通过setText方法设置文本框中的文本内容。

```java
XSLFSlide slide = ...;
XSLFTextBox textBox = slide.createTextBox();		//创建新的文本框
textBox.setAnchor(new Rectangle2D.Double(x, y, width, height));
textBox.setText("Hello, World!");
```

#### 3.2 添加新的图片

使用createPicture方法在幻灯片中添加新的图像。

```java
XSLFSlide slide = ...;
InputStream pictureData = ...; // 图像的输入流
XSLFPictureData pictureData =  slide.addPicture(pictureData), PictureData.PictureType.PNG);	//给幻灯片添加图片
pictureData.setAnchor(new Rectangle(470, 54, 386, 236));	// 指定图片的位置和大小
```

#### 3.3 添加新的表格

添加表格：使用createTable方法在幻灯片中添加新的表格。可以通过setAnchor方法设置表格的位置和大小，通过getCell方法获取单元格，并通过setText方法设置单元格中的文本内容。

```java
XSLFSlide slide = ...;
XSLFTable table = slide.createTable();
table.setAnchor(new Rectangle2D.Double(x, y, width, height));
XSLFTableCell cell = table.getCell(0, 0);
cell.setText("Cell 1,1");
```

## 五、拼接PPT文件以及导出和保存

### 1、拼接PPT文件

#### 1.1 将填充好的幻灯片拼接至目标文件

```java
XMLSlideShow ppt = new XMLSlideShow();	// 需要拼接至目标文件
XSLFSlide slide = ...;	// 填充好的幻灯片
ppt.createSlide().importContent(slide);	// 拼接至目标文件
```

#### 1.2 实现PPT文件的合并和拼接

```java
// 目标ppt
XMLSlideShow targetPpt = ...;
// 来源ppt
XMLSlideShow sourcePpt = ...;
for (XSLFSlide slide : sourcePpt.getSlides()) {
     targetPpt.createSlide().importContent(slide);
}
```


#### 1.3 将多个演示文稿合并在一起

```java
// 目标ppt
XMLSlideShow ppt = new XMLSlideShow();
// 需要合并的ppt文件路径
String[] inputs = {"presentations1.pptx", "presentation2.pptx"};
for(String arg : inputs){
 FileInputStream is = new FileInputStream(arg);
 XMLSlideShow src = new XMLSlideShow(is);
 is.close();

  	// 遍历ppt文件中的每张幻灯片
  	for(XSLFSlide srcSlide : src.getSlides()){
  	 		// 目标ppt新建幻灯片且导入幻灯片内容
  	    ppt.createSlide().importContent(srcSlide);
  	}

}
FileOutputStream out = new FileOutputStream("merged.pptx");
ppt.write(out);
out.close();
```

### 2、PPT文件的导出和保存

#### 2.1 将PPT文件导出为PDF、图片等格式

将PPT文件导出为PDF

Apache POI 库主要用于读写 Microsoft Office 格式的文档，例如 Word、Excel 和 PowerPoint 文件，不支持直接操作 PDF 文件。如果您需要操作 PDF 文件，可以使用其他的 Java PDF 库，例如 iText、PDFBox 等。

##### 1、引入依赖

iText 和 Apache POI 的兼容版本依赖关系如下：

iText 5.x 与 POI 3.x 兼容。
iText 7.x 与 POI 4.x 兼容。

```xml
<dependency>
    <groupId>com.itextpdf</groupId>
    <artifactId>itext7-core</artifactId>
    <version>7.1.16</version>
</dependency>
```


##### 2、编写方法

```java
public static byte[] pptxToPdf(InputStream src) {
    Document document = null;
    XMLSlideShow slideShow = null;
    FileOutputStream fileOutputStream = null;
    PdfWriter pdfWriter = null;
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    byte[] resBytes = null;
    try {
      //使用输入流pptx文件
      slideShow = new XMLSlideShow(src);
      //获取幻灯片的尺寸
      Dimension dimension = slideShow.getPageSize();
      //创建一个写内容的容器
      document = new Document(PageSize.A3, 72, 72, 72, 72);
      //使用输出流写入
      pdfWriter = PdfWriter.getInstance(document, baos);
      //使用之前必须打开
      document.open();
      pdfWriter.open();
      PdfPTable pdfPTable = new PdfPTable(1);
      //获取幻灯片
      java.util.List<XSLFSlide> slideList = slideShow.getSlides();
      for (int i = 0, row = slideList.size(); i < row; i++) {
        //获取每一页幻灯片
        XSLFSlide slide = slideList.get(i);
        for (XSLFShape shape : slide.getShapes()) {
          //判断是否是文本
          if(shape instanceof XSLFTextShape){
            // 设置字体, 解决中文乱码
            XSLFTextShape textShape = (XSLFTextShape) shape;
            for (XSLFTextParagraph textParagraph : textShape.getTextParagraphs()) {
              for (XSLFTextRun textRun : textParagraph.getTextRuns()) {
                textRun.setFontFamily("宋体");
              }
            }
          }
        }
        //根据幻灯片尺寸创建图形对象
        BufferedImage bufferedImage = new BufferedImage((int)dimension.getWidth(), (int)dimension.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2d = bufferedImage.createGraphics();
        graphics2d.setPaint(Color.white);
        graphics2d.setFont(new java.awt.Font("宋体", java.awt.Font.PLAIN, 12));
        //把内容写入图形对象
        slide.draw(graphics2d);
        graphics2d.dispose();
        //封装到Image对象中
        Image image = Image.getInstance(bufferedImage, null);
        image.scalePercent(50f);
        // 写入单元格
        pdfPTable.addCell(new PdfPCell(image, true));
        document.add(image);
      }
      document.close();
      pdfWriter.close();
      resBytes = baos.toByteArray();
    } catch (Exception e) {
      log.error("pptx转pdf异常：{}",e);
    } finally {
      try {
        if (baos != null) {
          baos.close();
        }
      } catch (IOException e) {
        log.error("pptx转pdf关闭io流异常：{}",e);
      }
    }
    return resBytes;
}
```


将PPT文件导出为图片

```java
/**
 * 将后缀为.pptx的PPT转换为图片
 * @param pptFile PPT的路径
 * @param imgFile 将PPT转换为图片后的路径
 * @return
 */
public List<String> PptToPdfConverter(File pptFile, String imgFile) {
    List<String> list = new ArrayList<>();
    FileInputStream is = null ;
    try {
        is = new FileInputStream(pptFile);

        XMLSlideShow xmlSlideShow = new XMLSlideShow(is);
        is.close();
        // 获取大小
        Dimension pgsize = xmlSlideShow.getPageSize();
        // 获取幻灯片
        List<XSLFSlide> slides = xmlSlideShow.getSlides();

        for (int i = 0 ; i < slides.size() ; i++) {
            // 解决乱码问题
            List<XSLFShape> shapes1 = slides.get(i).getShapes();
            for (XSLFShape shape : shapes1) {

                if (shape instanceof XSLFTextShape) {
                    XSLFTextShape sh = (XSLFTextShape) shape;
                    List<XSLFTextParagraph> textParagraphs = sh.getTextParagraphs();

                    for (XSLFTextParagraph xslfTextParagraph : textParagraphs) {
                        List<XSLFTextRun> textRuns = xslfTextParagraph.getTextRuns();
                        for (XSLFTextRun xslfTextRun : textRuns) {
                            xslfTextRun.setFontFamily("宋体");
                        }
                    }
                }
            }
            //根据幻灯片大小生成图片
            BufferedImage img = new BufferedImage(pgsize.width,pgsize.height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = img.createGraphics();

            graphics.setPaint(Color.white);
            graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width,pgsize.height));

            // 最核心的代码
            slides.get(i).draw(graphics);

            //图片将要存放的路径
            String absolutePath = imgFile+"/"+DateUtils.datePath() +"/"+ (i + 1) + ".jpeg";
            File jpegFile = new File(absolutePath);
            // 图片路径存放
            list.add((i + 1) + ".jpeg");
            //如果图片存在，则不再生成
            if (jpegFile.exists()) {
                continue;
            }
            // 这里设置图片的存放路径和图片的格式(jpeg,png,bmp等等),注意生成文件路径
            FileOutputStream out = new FileOutputStream(jpegFile);

            // 写入到图片中去
            ImageIO.write(img, "jpeg", out);
            out.close();
        }
        return list;

    } catch (Exception e) {
    }
    return list;
}

```


#### 2.2 PPT文件保存
使用 FileOutputStream 类将 PPT 文件保存到本地磁盘、OSS中。

* 本地保存

```java
XMLSlideShow ppt = ...; 
FileOutputStream fos = new FileOutputStream("文件路径.ppptx");
ppt.write(fos);
fos.close();
```

* 上传七牛云

```java
QiNiuOSS service = SpringUtil.getBean(QiNiuOSS.class);
ByteArrayOutputStream bos = new ByteArrayOutputStream();
originalPpt.write(bos);
String key = LocalDateTimeUtil.format(LocalDate.now(), DatePattern.PURE_DATETIME_MS_PATTERN) + RandomStringUtils.randomAlphabetic(6) + ".pptx";
return service.uploadStream("bucketName", "dir", new ByteArrayInputStream(bos.toByteArray()), key);
```

## 六、实战Demo
使用POI读取模板PPT填充数据并拼接至目标文件，代码托管于gitee～

* 读取模板PPT文件、目标PPT文件（拼接至PPT文件）
* 复制模板幻灯片至目标PPT文件指定页面
* 替换幻灯片文本框、表格中的文本
* 幻灯片指定位置处插入图片以及图片标题
* 导出目标PPT文件至OSS
* 前置准备

首先提供目标PPT文件（注意我们在模板中的占位符）、模板PPT文件上传至七牛云或者存在本地。

核心代码

```java
/**
 * 根据模板导出展位和案例幻灯片，并拼接到PPT指定位置
 *
 * @param spotList          展位列表
 * @param templateLink      模板的链接
 * @param spotTempIndex     展位模板在链接中的下标
 * @param activityTempIndex 案例模板在链接中的下标
 * @param slideLink         幻灯片的链接
 * @param insertPosition    插入的位置（页码）
 * @return 无返回值，直接将拼接后的PPT保存到指定位置
 * @throws IOException
 */
public Boolean exportAndInsertPPT(List<Spot> spotList, String templateLink, Integer spotTempIndex, Integer activityTempIndex, String slideLink, Integer insertPosition) throws IOException {
    // 读取拼接至文件流
    InputStream slideFis = getClass().getClassLoader().getResourceAsStream(slideLink);
    XMLSlideShow slidePpt = new XMLSlideShow(slideFis);
    // 读取PPT模板文件流
    InputStream tempFis = getClass().getClassLoader().getResourceAsStream(templateLink);
    XMLSlideShow tempPpt = new XMLSlideShow(tempFis);

    AtomicInteger newSlideIndex = new AtomicInteger(insertPosition);
    // 获取所有幻灯片
    spotList.forEach(spot -> {
        XSLFSlide newSpotSlide = copySlide(tempPpt.getSlides().get(spotTempIndex), slidePpt, newSlideIndex.getAndIncrement());
        List<XSLFShape> shapes = newSpotSlide.getShapes();
        shapes.forEach(shape -> {
            // 填充文本框数据
            if (shape instanceof XSLFTextBox) {
                XSLFTextBox textBox = (XSLFTextBox) shape;
                if (textBox.getTextParagraphs() != null && textBox.getTextParagraphs().size() > 0 && textBox.getTextParagraphs().get(0).getTextRuns() != null && textBox.getTextParagraphs().get(0).getTextRuns().size() > 0) {
                    String text = textBox.getText();
                    if (text.equals("{boothTitle}")) {
                        textBox.setText(spot.getSpotName() + "-" + spot.getPropertyNumber());
                        XSLFTextRun textRun = textBox.getTextParagraphs().get(0).getTextRuns().get(0);
                        textRun.setFontColor(Color.WHITE);
                        textRun.setFontSize(24.00);
                        textRun.setBold(true);
                    }
                }
            }
            // 填充表格内数据
            if (shape instanceof XSLFTable) {
                XSLFTable table = (XSLFTable) shape;
                for (int row = 0; row < table.getNumberOfRows(); row++) {
                    for (int colum = 0; colum < table.getNumberOfColumns(); colum++) {
                        // 遍历单元格匹配修改内容
                        XSLFTableCell cell = table.getCell(row, colum);
                        String text = cell.getText();
                        if (text.contains("{spotFloor}")) {
                            text = text.replace("{spotFloor}", ObjectUtil.isNotEmpty(spot.getSpotFloor()) ? spot.getSpotFloor() : "/");
                        }
                        if (text.contains("{dailyFlow}")) {
                            text = text.replace("{dailyFlow}", ObjectUtil.isNotEmpty(spot.getDailyFlow()) ? spot.getDailyFlow() : "/");
                        }
                        if (text.contains("{dimension}")) {
                            text = text.replace("{dimension}", ObjectUtil.isNotEmpty(spot.getDimension()) ? spot.getDimension() : "/");
                        }
                        if (text.contains("{surroundingBrands}")) {
                            if (StringUtils.isBlank(spot.getSurroundingBrands())) {
                                text = "/";
                            } else if (spot.getSurroundingBrands().length() <= 32) {
                                text = text.replace("{surroundingBrands}", spot.getSurroundingBrands());
                            } else {
                                char c = spot.getSurroundingBrands().charAt(32);
                                if (isChinese(spot.getSurroundingBrands().charAt(32))) {
                                    text = text.replace("{surroundingBrands}", spot.getSurroundingBrands().substring(0, 33) + "……");
                                } else {
                                    text = text.replace("{surroundingBrands}", spot.getSurroundingBrands().substring(0, 32) + "……");
                                }
                            }
                        }
                        if (text.contains("{intendedIndustry}")) {
                            text = text.replace("{intendedIndustry}", spot.getIntendedIndustry());
                        }
                        if (text.contains("{weekdayPrice}")) {
                            text = text.replace("{weekdayPrice}", spot.getWeekdayPrice());
                        }
                        if (text.contains("{weekdayPrice}")) {
                            text = text.replace("{weekdayPrice}", spot.getWeekdayPrice());
                        }
                        if (text.contains("{weekendPrice}")) {
                            text = text.replace("{weekendPrice}", spot.getWeekendPrice());
                        }
                        if (text.contains("{spotDescription}")) {
                            if (StringUtils.isNotBlank(spot.getSpotDescription())) {
                                if (spot.getSpotDescription().length() <= 84) {
                                    text = text.replace("{spotDescription}", spot.getSpotDescription());
                                } else {
                                    if (isChinese(spot.getSpotDescription().charAt(84))) {
                                        text = text.replace("{spotDescription}", spot.getSpotDescription().substring(0, 85) + "……");
                                    } else {
                                        text = text.replace("{spotDescription}", spot.getSpotDescription().substring(0, 84) + "……");
                                    }
                                }
                            } else {
                                text = "/";
                            }
                            cell.setText(text);
                            List<XSLFTextParagraph> paragraphs = cell.getTextParagraphs();
                            paragraphs.forEach(paragraph -> {
                                paragraph.getTextRuns().forEach(textRun -> textRun.setFontSize(14.00));
                            });
                            continue;
                        }
                        cell.setText(text);
                    }
                }
            }
        });
        // 填充展位图片
        try {
            XSLFPictureData pictureData1 = slidePpt.addPicture(new FileInputStream("/Users/gwh/Documents/Linhuiba/demo/poi-ppt/src/main/resources/static/image1.png"), PictureData.PictureType.PNG);
            XSLFPictureShape pictureShape1 = newSpotSlide.createPicture(pictureData1);
            pictureShape1.setAnchor(new Rectangle(470, 54, 386, 236));

            XSLFPictureData pictureData2 = slidePpt.addPicture(new FileInputStream("/Users/gwh/Documents/Linhuiba/demo/poi-ppt/src/main/resources/static/imagef" + 2 + ".png"), PictureData.PictureType.PNG);
            XSLFPictureShape pictureShape2 = newSpotSlide.createPicture(pictureData2);
            pictureShape2.setAnchor(new Rectangle(470, 300, 413, 212));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 案例幻灯片
        if (CollUtil.isNotEmpty(spot.getActivityList())) {
            XSLFSlide addActivitySlide = copySlide(tempPpt.getSlides().get(activityTempIndex), slidePpt, newSlideIndex.getAndIncrement());
            List<XSLFShape> activityShapes = addActivitySlide.getShapes();
            activityShapes.forEach(shape -> {
                // 填充文本框数据
                if (shape instanceof XSLFTextBox) {
                    XSLFTextBox textBox = (XSLFTextBox) shape;
                    if (textBox.getTextParagraphs() != null && textBox.getTextParagraphs().size() > 0 && textBox.getTextParagraphs().get(0).getTextRuns() != null && textBox.getTextParagraphs().get(0).getTextRuns().size() > 0) {
                        String text = textBox.getText();
                        if (text.equals("{boothTitle}")) {
                            textBox.setText(spot.getSpotName() + "-" + spot.getPropertyNumber());
                            XSLFTextRun textRun = textBox.getTextParagraphs().get(0).getTextRuns().get(0);
                            textRun.setFontColor(Color.WHITE);
                            textRun.setFontSize(24.00);
                            textRun.setBold(true);
                        }
                    }
                }
            });
            // 根据案例图片数量不同排版
            Map<String, String> activityPictureUriMap = getActivityPictureUriList(spot.getActivityList());
            if (CollUtil.isNotEmpty(activityPictureUriMap)) {
                List<String> uriList = activityPictureUriMap.entrySet().stream().map(item -> item.getKey()).collect(Collectors.toList());
                Color fillColor = new Color(0, 0, 0, 40);
                try {
                    switch (activityPictureUriMap.keySet().size()) {
                        case 1: {
                            // 图片（为了方便演示这里图片全部固定写死，大家根据个人需求修改）
                            XSLFPictureData pictureData1 = slidePpt.addPicture(new FileInputStream("/Users/gwh/Documents/Linhuiba/demo/poi-ppt/src/main/resources/static/activity1.png"), PictureData.PictureType.PNG);
                            XSLFPictureShape pictureShape1 = addActivitySlide.createPicture(pictureData1);
                            pictureShape1.setAnchor(new Rectangle(107, 50, 727, 485));
                            // 图片标题
                            String context = activityPictureUriMap.get(uriList.get(0));
                            addActivityPictureTitle(addActivitySlide, 107, 50, getShapeW(context, 26, 290,727), 50, 24.00, fillColor, context);
                            break;
                        }
                        case 2: {
                            XSLFPictureData pictureData1 = slidePpt.addPicture(new FileInputStream("/Users/gwh/Documents/Linhuiba/demo/poi-ppt/src/main/resources/static/activity1.png"), PictureData.PictureType.PNG);
                            XSLFPictureShape pictureShape1 = addActivitySlide.createPicture(pictureData1);
                            pictureShape1.setAnchor(new Rectangle(20, 130, 455, 320));
                            XSLFPictureData pictureData2 = slidePpt.addPicture(new FileInputStream("/Users/gwh/Documents/Linhuiba/demo/poi-ppt/src/main/resources/static/activity2.png"), PictureData.PictureType.PNG);
                            XSLFPictureShape pictureShape2 = addActivitySlide.createPicture(pictureData2);
                            pictureShape2.setAnchor(new Rectangle(480, 130, 455, 320));
                            String context1 = activityPictureUriMap.get(uriList.get(0));
                            String context2 = activityPictureUriMap.get(uriList.get(1));
                            addActivityPictureTitle(addActivitySlide, 20, 130, getShapeW(context1, 22, 182,455), 32, 20.00, fillColor, context1);
                            addActivityPictureTitle(addActivitySlide, 480, 130, getShapeW(context1, 22, 182,455), 32, 20.00, fillColor, context2);
                            break;
                        }
                        case 3: {
                            XSLFPictureData pictureData1 = slidePpt.addPicture(new FileInputStream("/Users/gwh/Documents/Linhuiba/demo/poi-ppt/src/main/resources/static/activity1.png"), PictureData.PictureType.PNG);
                            XSLFPictureShape pictureShape1 = addActivitySlide.createPicture(pictureData1);
                            pictureShape1.setAnchor(new Rectangle(290, 44, 366, 246));
                            XSLFPictureData pictureData2 = slidePpt.addPicture(new FileInputStream("/Users/gwh/Documents/Linhuiba/demo/poi-ppt/src/main/resources/static/activity2.png"), PictureData.PictureType.PNG);
                            XSLFPictureShape pictureShape2 = addActivitySlide.createPicture(pictureData2);
                            pictureShape2.setAnchor(new Rectangle(97, 291, 366, 246));
                            XSLFPictureData pictureData3 = slidePpt.addPicture(new FileInputStream("/Users/gwh/Documents/Linhuiba/demo/poi-ppt/src/main/resources/static/activity3.png"), PictureData.PictureType.PNG);
                            XSLFPictureShape pictureShape3 = addActivitySlide.createPicture(pictureData3);
                            pictureShape3.setAnchor(new Rectangle(467, 291, 366, 246));
                            String context1 = activityPictureUriMap.get(uriList.get(0));
                            String context2 = activityPictureUriMap.get(uriList.get(1));
                            String context3 = activityPictureUriMap.get(uriList.get(2));
                            addActivityPictureTitle(addActivitySlide, 290, 44, getShapeW(context1, 18,146,366), 32, 16.00, fillColor, context1);
                            addActivityPictureTitle(addActivitySlide, 97, 291, getShapeW(context2, 18,146,366), 32, 16.00, fillColor, context2);
                            addActivityPictureTitle(addActivitySlide, 467, 291, getShapeW(context2, 18,146,366), 32, 16.00, fillColor, context3);
                            break;
                        }
                        case 4: {
                            XSLFPictureData pictureData1 = slidePpt.addPicture(new FileInputStream("/Users/gwh/Documents/Linhuiba/demo/poi-ppt/src/main/resources/static/activity1.png"), PictureData.PictureType.PNG);
                            XSLFPictureShape pictureShape1 = addActivitySlide.createPicture(pictureData1);
                            pictureShape1.setAnchor(new Rectangle(120, 60, 340, 230));
                            XSLFPictureData pictureData2 = slidePpt.addPicture(new FileInputStream("/Users/gwh/Documents/Linhuiba/demo/poi-ppt/src/main/resources/static/activity2.png"), PictureData.PictureType.PNG);
                            XSLFPictureShape pictureShape2 = addActivitySlide.createPicture(pictureData2);
                            pictureShape2.setAnchor(new Rectangle(470, 60, 340, 230));
                            XSLFPictureData pictureData3 = slidePpt.addPicture(new FileInputStream("/Users/gwh/Documents/Linhuiba/demo/poi-ppt/src/main/resources/static/activity3.png"), PictureData.PictureType.PNG);
                            XSLFPictureShape pictureShape3 = addActivitySlide.createPicture(pictureData3);
                            pictureShape3.setAnchor(new Rectangle(120, 300, 340, 230));
                            XSLFPictureData pictureData4 = slidePpt.addPicture(new FileInputStream("/Users/gwh/Documents/Linhuiba/demo/poi-ppt/src/main/resources/static/activity4.png"), PictureData.PictureType.PNG);
                            XSLFPictureShape pictureShape4 = addActivitySlide.createPicture(pictureData4);
                            pictureShape4.setAnchor(new Rectangle(470, 300, 340, 230));
                            String context1 = activityPictureUriMap.get(uriList.get(0));
                            String context2 = activityPictureUriMap.get(uriList.get(1));
                            String context3 = activityPictureUriMap.get(uriList.get(2));
                            String context4 = activityPictureUriMap.get(uriList.get(3));
                            addActivityPictureTitle(addActivitySlide, 120, 60, getShapeW(context1, 16,136,340), 23, 14.00, fillColor, context1);
                            addActivityPictureTitle(addActivitySlide, 470, 60, getShapeW(context2, 16,136,340), 23, 14.00, fillColor, context2);
                            addActivityPictureTitle(addActivitySlide, 120, 300, getShapeW(context3, 16,136,340), 23, 14.00, fillColor, context3);
                            addActivityPictureTitle(addActivitySlide, 470, 300, getShapeW(context4, 16,136,340), 23, 14.00, fillColor, context4);
                            break;
                        }
                        case 5: {
                            XSLFPictureData pictureData1 = slidePpt.addPicture(new FileInputStream("/Users/gwh/Documents/Linhuiba/demo/poi-ppt/src/main/resources/static/activity1.png"), PictureData.PictureType.PNG);
                            XSLFPictureShape pictureShape1 = addActivitySlide.createPicture(pictureData1);
                            pictureShape1.setAnchor(new Rectangle(150, 60, 310, 210));
                            XSLFPictureData pictureData2 = slidePpt.addPicture(new FileInputStream("/Users/gwh/Documents/Linhuiba/demo/poi-ppt/src/main/resources/static/activity2.png"), PictureData.PictureType.PNG);
                            XSLFPictureShape pictureShape2 = addActivitySlide.createPicture(pictureData2);
                            pictureShape2.setAnchor(new Rectangle(465, 60, 310, 210));
                            XSLFPictureData pictureData3 = slidePpt.addPicture(new FileInputStream("/Users/gwh/Documents/Linhuiba/demo/poi-ppt/src/main/resources/static/activity3.png"), PictureData.PictureType.PNG);
                            XSLFPictureShape pictureShape3 = addActivitySlide.createPicture(pictureData3);
                            pictureShape3.setAnchor(new Rectangle(10, 275, 310, 210));
                            XSLFPictureData pictureData4 = slidePpt.addPicture(new FileInputStream("/Users/gwh/Documents/Linhuiba/demo/poi-ppt/src/main/resources/static/activity4.png"), PictureData.PictureType.PNG);
                            XSLFPictureShape pictureShape4 = addActivitySlide.createPicture(pictureData4);
                            pictureShape4.setAnchor(new Rectangle(325, 275, 310, 210));
                            XSLFPictureData pictureData5 = slidePpt.addPicture(new FileInputStream("/Users/gwh/Documents/Linhuiba/demo/poi-ppt/src/main/resources/static/activity5.png"), PictureData.PictureType.PNG);
                            XSLFPictureShape pictureShape5 = addActivitySlide.createPicture(pictureData5);
                            pictureShape5.setAnchor(new Rectangle(640, 275, 310, 210));
                            String context1 = activityPictureUriMap.get(uriList.get(0));
                            String context2 = activityPictureUriMap.get(uriList.get(1));
                            String context3 = activityPictureUriMap.get(uriList.get(2));
                            String context4 = activityPictureUriMap.get(uriList.get(3));
                            String context5 = activityPictureUriMap.get(uriList.get(4));
                            addActivityPictureTitle(addActivitySlide, 150, 60, getShapeW(context1, 16,124,310), 23, 14.00, fillColor, context1);
                            addActivityPictureTitle(addActivitySlide, 465, 60, getShapeW(context2, 16,124,310), 23, 14.00, fillColor, context2);
                            addActivityPictureTitle(addActivitySlide, 10, 275, getShapeW(context3, 16,124,310), 23, 14.00, fillColor, context3);
                            addActivityPictureTitle(addActivitySlide, 325, 275, getShapeW(context4, 16,124,310), 23, 14.00, fillColor, context4);
                            addActivityPictureTitle(addActivitySlide, 640, 275, getShapeW(context5, 16,124,310), 23, 14.00, fillColor, context5);
                            break;
                        }
                        default: {
                            XSLFPictureData pictureData1 = slidePpt.addPicture(new FileInputStream("/Users/gwh/Documents/Linhuiba/demo/poi-ppt/src/main/resources/static/activity1.png"), PictureData.PictureType.PNG);
                            XSLFPictureShape pictureShape1 = addActivitySlide.createPicture(pictureData1);
                            pictureShape1.setAnchor(new Rectangle(10, 60, 305, 205));
                            XSLFPictureData pictureData2 = slidePpt.addPicture(new FileInputStream("/Users/gwh/Documents/Linhuiba/demo/poi-ppt/src/main/resources/static/activity2.png"), PictureData.PictureType.PNG);
                            XSLFPictureShape pictureShape2 = addActivitySlide.createPicture(pictureData2);
                            pictureShape2.setAnchor(new Rectangle(325, 60, 305, 205));
                            XSLFPictureData pictureData3 = slidePpt.addPicture(new FileInputStream("/Users/gwh/Documents/Linhuiba/demo/poi-ppt/src/main/resources/static/activity3.png"), PictureData.PictureType.PNG);
                            XSLFPictureShape pictureShape3 = addActivitySlide.createPicture(pictureData3);
                            pictureShape3.setAnchor(new Rectangle(640, 60, 305, 205));
                            XSLFPictureData pictureData4 = slidePpt.addPicture(new FileInputStream("/Users/gwh/Documents/Linhuiba/demo/poi-ppt/src/main/resources/static/activity4.png"), PictureData.PictureType.PNG);
                            XSLFPictureShape pictureShape4 = addActivitySlide.createPicture(pictureData4);
                            pictureShape4.setAnchor(new Rectangle(10, 280, 305, 205));
                            XSLFPictureData pictureData5 = slidePpt.addPicture(new FileInputStream("/Users/gwh/Documents/Linhuiba/demo/poi-ppt/src/main/resources/static/activity5.png"), PictureData.PictureType.PNG);
                            XSLFPictureShape pictureShape5 = addActivitySlide.createPicture(pictureData5);
                            pictureShape5.setAnchor(new Rectangle(325, 280, 305, 205));
                            XSLFPictureData pictureData6 = slidePpt.addPicture(new FileInputStream("/Users/gwh/Documents/Linhuiba/demo/poi-ppt/src/main/resources/static/activity6.png"), PictureData.PictureType.PNG);
                            XSLFPictureShape pictureShape6 = addActivitySlide.createPicture(pictureData6);
                            pictureShape6.setAnchor(new Rectangle(640, 280, 305, 205));
                            String context1 = activityPictureUriMap.get(uriList.get(0));
                            String context2 = activityPictureUriMap.get(uriList.get(1));
                            String context3 = activityPictureUriMap.get(uriList.get(2));
                            String context4 = activityPictureUriMap.get(uriList.get(3));
                            String context5 = activityPictureUriMap.get(uriList.get(4));
                            String context6 = activityPictureUriMap.get(uriList.get(5));
                            addActivityPictureTitle(addActivitySlide, 10, 60, getShapeW(context1, 16,122,305), 23, 14.00, fillColor, context1);
                            addActivityPictureTitle(addActivitySlide, 325, 60, getShapeW(context2, 16,122,305), 23, 14.00, fillColor, context2);
                            addActivityPictureTitle(addActivitySlide, 640, 60, getShapeW(context3, 16,122,305), 23, 14.00, fillColor, context3);
                            addActivityPictureTitle(addActivitySlide, 10, 280, getShapeW(context4, 16,122,305), 23, 14.00, fillColor, context4);
                            addActivityPictureTitle(addActivitySlide, 325, 280, getShapeW(context5, 16,122,305), 23, 14.00, fillColor, context5);
                            addActivityPictureTitle(addActivitySlide, 640, 280, getShapeW(context6, 16,122,305), 23, 14.00, fillColor, context6);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    });
    // 导出PPT文件
    File file = new File("/Users/gwh/Documents/Linhuiba/demo/poi-ppt/src/main/resources/static/" + "XXX场地" + "招商PPT.pptx");
    if (file != null) {
        file.delete();
    }
    FileOutputStream fos = new FileOutputStream("/Users/gwh/Documents/Linhuiba/demo/poi-ppt/src/main/resources/static/" + "XXX场地" + "招商PPT.pptx");
    slidePpt.write(fos);
    slideFis.close();
    tempFis.close();
    fos.close();
    return Boolean.TRUE;
}

/**

 * 获取合适新建矩形的宽度
 * @param context 内容
 * @param wordW 单字宽度
 * @param suitableSW 合适的矩形宽度
 * @param maxSW 最大的矩形宽度
 * @return
   */
private int getShapeW(String context, int wordW, int suitableSW, int maxSW) {
    return context.length() * wordW <= suitableSW ? suitableSW : (context.length() * wordW >= maxSW ? maxSW : context.length() * wordW);
}

/**
 * 指定幻灯片案例图片加上标题
 *
 * @param slide          XSLFSlide 幻灯片
 * @param x              新建矩形的左上角的X坐标
 * @param y              新建矩形的左上角的y坐标
 * @param w              新建矩形的宽度
 * @param h              新建矩形的高度
 * @param fontSize       字号
 * @param fillColor      背景色
 * @param context        内容
 */
private void addActivityPictureTitle(XSLFSlide slide, double x, double y, double w, double h, Double fontSize, Color fillColor, String context) {
    XSLFTextBox textBox = slide.createTextBox();
    textBox.setText(context);
    textBox.setFillColor(fillColor);
    textBox.setVerticalAlignment(VerticalAlignment.MIDDLE);
    Rectangle2D anchor = new Rectangle2D.Double(x, y, w, h);
    textBox.setAnchor(anchor);
    XSLFTextParagraph paragraph = textBox.getTextParagraphs().get(0);
    paragraph.setTextAlign(TextParagraph.TextAlign.CENTER);
    XSLFTextRun textRun = paragraph.getTextRuns().get(0);
    textRun.setFontColor(Color.white);
    textRun.setBold(true);
    textRun.setFontSize(fontSize);
}

/**
 * 竖着遍历案例集合中图片集合
 *
 * @param activityList 案例集合
 * @return 图片集合
 */
private Map<String, String> getActivityPictureUriList(List<Activity> activityList) {
    int rowLe = activityList.stream().mapToInt(item -> item.getPictureList().size()).max().orElse(0);
    int colLe = activityList.size();
    // 需要保证入列顺序，故使用LinkedHashMap
    Map<String, String> activityPictureUriMap = new LinkedHashMap<>();
    for (int i = 0; i < rowLe; i++) {
        for (int j = 0; j < colLe; j++) {
            if (activityList.size() > j && activityList.get(j).getPictureList().size() > i) {
                String url = activityList.get(j).getPictureList().get(i);
                String title = "";
                if (StringUtils.isNotBlank(activityList.get(j).getActivityName())) {
                    title += activityList.get(j).getActivityName() + "｜";
                }
                if (StringUtils.isNotBlank(activityList.get(j).getIndustryName())) {
                    title += activityList.get(j).getIndustryName() + "｜";
                }
                if (StringUtils.isNotBlank(activityList.get(j).getBrandName())) {
                    title += activityList.get(j).getBrandName() + "｜";
                }
                if (title.endsWith("｜")) {
                    title = title.substring(0, title.length() - 1);
                }
                activityPictureUriMap.put(url, title);
            }
        }
    }
    return activityPictureUriMap;
}

/**
 * 复制ppt单页
 *
 * @param template 模板页
 * @param ppt      ppt
 * @param newIndex 复制页放置位置
 * @return 复制页
 */
private XSLFSlide copySlide(XSLFSlide template, XMLSlideShow ppt, int newIndex) {
    // 创建新的一页PPT，按模板的布局
    XSLFSlide newSlide = ppt.createSlide().importContent(template);
    // 排序（在PPT中的第几页）
    ppt.setSlideOrder(newSlide, newIndex);
    return newSlide;
}

/**
 * 判断字符是否是中文汉字
 * - 使用 Unicode 范围来判断。汉字的 Unicode 范围是4E00 到 9FFF
 *
 * @param c char 字符
 * @return 是否是中文汉字
 */
private Boolean isChinese(char c) {
    return (c >= '\u4e00' && c <= '\u9fff');
}
```


demo数据run一下~

```java
@Test
public void demo() {
    // 获取测试数据
    List<Spot> spotList = getSpotList();
    try {
        Boolean aBoolean = exportAndInsertPPT(spotList, "static/template.pptx", 0, 1, "static/slide.pptx", 1);
        System.out.println(aBoolean);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

/**
 * 测试数据
 *
 * @return
 */
public List<com.hgw.poippt.DO.Spot> getSpotList() {
    com.hgw.poippt.DO.Activity activity1 = new com.hgw.poippt.DO.Activity(9001L, "特斯拉大甩卖1", "汽车", "比亚迪", Lists.list("1", "2", "3"));
    com.hgw.poippt.DO.Activity activity2 = new com.hgw.poippt.DO.Activity(9002L, "仔仔棒推广2", "食品", "旺旺食品", Lists.list("4", "5"));
    com.hgw.poippt.DO.Activity activity3 = new com.hgw.poippt.DO.Activity(9003L, "GPT宣讲3", "AI", "苹果", Lists.list("6"));
    List<com.hgw.poippt.DO.Activity> activityList1 = Lists.list(activity1);
    List<com.hgw.poippt.DO.Activity> activityList2 = Lists.list(activity1, activity2);
    List<Activity> activityList3 = Lists.list(activity1, activity2, activity3);
    com.hgw.poippt.DO.Spot spot1 = new com.hgw.poippt.DO.Spot(1L, "一楼中展厅", "L007", "1", "5-10", "6*6*7", "品牌1、品牌2、品牌3、品牌4、品牌5、品牌6、品牌7", "汽车、房产、地产", "8000", "10000", "我们的场地位于市中心，交通非常便利，周围有很多酒店和餐厅方便您的活动安排。", activityList1);
    com.hgw.poippt.DO.Spot spot2 = new com.hgw.poippt.DO.Spot(2L, "二楼楼梯转角", "L008", "2", "4-8", "6*6*7", "品牌1、品牌2、品牌3、品牌4、品牌5、品牌6、品牌7、品牌82321", "汽车、房产、地产", "8000", "10000", "活动厅内配备有先进的音响和投影设备，适合各种演讲、讲座、演出、婚礼、生日派对等活动。我们还提供了各。", activityList2);
    com.hgw.poippt.DO.Spot spot3 = new com.hgw.poippt.DO.Spot(3L, "大润发超市门口1", "L009", "3", "4-9", "6*6*7", "品牌1、品牌2、品牌3、品牌4、品牌5、品牌6、品牌7、品牌8、品牌9、品牌10", "食品", "8000", "10000", "此外，我们的场地也可以租用给企业和组织举办各种会议和培训活动。会议室可以容纳10到20人，配备有投影仪、白板等设备，可以提供各种会议需要的服务和支持。在场地租用方面，我们提供灵活的租用方式和价格，可以根据您的需求进行定制。租用场地的客户还可以享受我们提供的优质服务和设施，让您的活动更加顺利和成功。如果您正在寻找一个安全、便利、舒适和多功能的场地，欢迎来到我们的场地，我们将竭诚为您服务。", activityList3);
    com.hgw.poippt.DO.Spot spot4 = new com.hgw.poippt.DO.Spot(4L, "大润发超市门口2", "L009", "3", "4-9", "6*6*7", "品牌1、品牌2、品牌3、品牌4、品牌5、品牌6、品牌7、品牌8、品牌9、品牌10", "食品", "8000", "10000", "此外，我们的场地也可以租用给企业和组织举办各种会议和培训活动。会议室可以容纳10到20人，配备有投影仪、白板等设备，可以提供各种会议需要的服务和支持。在场地租用方面，我们提供灵活的租用方式和价格，可以根据您的需求进行定制。租用场地的客户还可以享受我们提供的优质服务和设施，让您的活动更加顺利和成功。如果您正在寻找一个安全、便利、舒适和多功能的场地，欢迎来到我们的场地，我们将竭诚为您服务。", Lists.newArrayList(activity3));
    com.hgw.poippt.DO.Spot spot5 = new com.hgw.poippt.DO.Spot(5L, "大润发超市门口3", "L009", "3", "4-9", "6*6*7", "品牌1、品牌2、品牌3、品牌4、品牌5、品牌6、品牌7、品牌8、品牌9、品牌10", "食品", "8000", "10000", "此外，我们的场地也可以租用给企业和组织举办各种会议和培训活动。会议室可以容纳10到20人，配备有投影仪、白板等设备，可以提供各种会议需要的服务和支持。在场地租用方面，我们提供灵活的租用方式和价格，可以根据您的需求进行定制。租用场地的客户还可以享受我们提供的优质服务和设施，让您的活动更加顺利和成功。如果您正在寻找一个安全、便利、舒适和多功能的场地，欢迎来到我们的场地，我们将竭诚为您服务。", Lists.newArrayList(activity2));
    com.hgw.poippt.DO.Spot spot6 = new com.hgw.poippt.DO.Spot(6L, "大润发超市门口4", "L009", "3", "4-9", "6*6*7", "品牌1、品牌2、品牌3、品牌4、品牌5、品牌6、品牌7、品牌8、品牌9、品牌10", "食品", "8000", "10000", "此外，我们的场地也可以租用给企业和组织举办各种会议和培训活动。会议室可以容纳10到20人，配备有投影仪、白板等设备，可以提供各种会议需要的服务和支持。在场地租用方面，我们提供灵活的租用方式和价格，可以根据您的需求进行定制。租用场地的客户还可以享受我们提供的优质服务和设施，让您的活动更加顺利和成功。如果您正在寻找一个安全、便利、舒适和多功能的场地，欢迎来到我们的场地，我们将竭诚为您服务。", Lists.newArrayList(activity1, activity3));
    return Lists.list(spot1, spot2, spot3, spot4, spot5, spot6);
    // return Lists.list(spot1);
}
```


最终生成效果

————————————————
版权声明：本文为CSDN博主「HGW689」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/m0_49183244/article/details/130109694