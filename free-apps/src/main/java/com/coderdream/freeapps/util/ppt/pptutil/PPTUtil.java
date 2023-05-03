/**
 * @author: LV
 */

package com.coderdream.freeapps.util.ppt.pptutil;

import com.coderdream.freeapps.util.ppt.pptutil.entity.ParagraphTextStyle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.sl.usermodel.TextBox;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFAutoShape;
import org.apache.poi.xslf.usermodel.XSLFChart;
import org.apache.poi.xslf.usermodel.XSLFFreeformShape;
import org.apache.poi.xslf.usermodel.XSLFGroupShape;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTable;
import org.apache.poi.xslf.usermodel.XSLFTableCell;
import org.apache.poi.xslf.usermodel.XSLFTableRow;
import org.apache.poi.xslf.usermodel.XSLFTextBox;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLineChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLineSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLvl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTMultiLvlStrData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTMultiLvlStrRef;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumRef;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumVal;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPieChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPieSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrRef;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrVal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetLineDashProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableCell;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableCellProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextAutonumberBullet;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextField;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextLineBreak;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacingPercent;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacingPoint;
import org.openxmlformats.schemas.drawingml.x2006.main.STPresetLineDashVal;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAlignType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextFontAlignType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PPTUtil {

    private static final Logger logger = LoggerFactory.getLogger(PPTUtil.class);

    private XMLSlideShow pptx;

    public PPTUtil(String filePath) {
        this.readPPT(filePath);
    }

    public XMLSlideShow getPPTX() {
        return pptx;
    }

    // 读取 ppt
    private XMLSlideShow readPPT(String filePath) {
        try {
            this.pptx = new XMLSlideShow(new FileInputStream(new File(filePath)));
            if (logger.isDebugEnabled()) {
                logger.debug("已读取文件：" + filePath);
            }
            return this.pptx;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 写入 ppt
    public void writePPT(String exportPath) {
        try {
            File file = new File(exportPath);
            if (file.exists()) {
                file.delete();
            }
            this.pptx.write(new FileOutputStream(new File(exportPath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从幻灯片中获取图表
     *
     * @param slide
     * @return
     */
    public XSLFChart getChartFromSlide(XSLFSlide slide) {
        for (POIXMLDocumentPart relation : slide.getRelations()) {
            if (relation instanceof XSLFChart) {
                return (XSLFChart) relation;
            }
        }
        return null;
    }

    /**
     * 从幻灯片中获取图表列表
     *
     * @param slide
     * @return
     */
    public List<XSLFChart> getAllChartFromSlide(XSLFSlide slide) {
        List<XSLFChart> charts = new ArrayList<XSLFChart>();
        for (POIXMLDocumentPart relation : slide.getRelations()) {
            if (relation instanceof XSLFChart) {
                charts.add((XSLFChart) relation);
            }
        }
        return charts;
    }

    /**
     * 从幻灯片中获取表格
     *
     * @param slide
     * @return
     */
    public XSLFTable getTableFromSlide(XSLFSlide slide) {
        for (XSLFShape shape : slide.getShapes()) {
            if (shape instanceof XSLFTable) {
                return (XSLFTable) shape;
            }
        }
        return null;
    }

    /**
     * 从幻灯片中获取表格列表
     *
     * @param slide
     * @return
     */
    public List<XSLFTable> getAllTableFromSlide(XSLFSlide slide) {
        List<XSLFTable> tables = new ArrayList<XSLFTable>();
        for (XSLFShape shape : slide.getShapes()) {
            if (shape instanceof XSLFTable) {
                tables.add((XSLFTable) shape);
            }
        }
        return tables;
    }

    /**
     * 从幻灯片中获取文本框
     *
     * @param slide
     * @return
     */
    public XSLFTextBox getTextBoxFromSlide(XSLFSlide slide) {
        for (XSLFShape shape : slide.getShapes()) {
            if (shape instanceof XSLFTextBox) {
                return (XSLFTextBox) shape;
            }
        }
        return null;
    }

    /**
     * 从幻灯片中获取文本框列表
     *
     * @param slide
     * @return
     */
    public List<XSLFTextBox> getAllTextBoxFromSlide(XSLFSlide slide) {
        List<XSLFTextBox> textBoxes = new ArrayList<XSLFTextBox>();
        for (XSLFShape shape : slide.getShapes()) {
            if (shape instanceof XSLFTextBox) {
                textBoxes.add((XSLFTextBox) shape);
            }
        }
        return textBoxes;
    }

    public XSLFAutoShape getAutoShape(XSLFSlide slide) {
        for (XSLFShape shape : slide.getShapes()) {
            if (shape instanceof XSLFAutoShape) {
                return (XSLFAutoShape) shape;
            }
        }
        return null;
    }

    public List<XSLFAutoShape> getAllAutoShape(XSLFSlide slide) {
        List<XSLFAutoShape> autoShapes = new ArrayList<XSLFAutoShape>();
        for (XSLFShape shape : slide.getShapes()) {
            if (shape instanceof XSLFAutoShape) {
                autoShapes.add((XSLFAutoShape) shape);
            }
        }
        return autoShapes;
    }

    // 获取所有幻灯片
    public List<XSLFSlide> getSlides() {
        return pptx.getSlides();
    }

    // 获取所有幻灯片的获取所有图标
    public List<XSLFChart> getCharts() {
        return pptx.getCharts();
    }


    /**
     * 设置幻灯片段落垂直对齐方式
     *
     * @param paragraph
     * @param vertical
     */
    public void setParagraphVerticalAlign(XSLFTextParagraph paragraph, String vertical) {
        vertical = this.nullToDefault(vertical, "auto");

        setCTTextParagraphVerticalAlign(paragraph, vertical.toLowerCase());
    }

    // 设置段落垂直对齐
    private void setCTTextParagraphVerticalAlign(XSLFTextParagraph ctTextParagraph, String verticalStr) {
        CTTextParagraphProperties pPr = this.getPPR(ctTextParagraph);
        switch (verticalStr) {
            case "top":
                pPr.setFontAlgn(STTextFontAlignType.T);
                break;   // 顶部
            case "baseline":
                pPr.setFontAlgn(STTextFontAlignType.BASE);
                break;  // 基线对齐
            case "bottom":
                pPr.setFontAlgn(STTextFontAlignType.B);
                break;    // 底部
            case "center":
                pPr.setFontAlgn(STTextFontAlignType.CTR);
                break;    // 居中
            default:
                pPr.setFontAlgn(STTextFontAlignType.AUTO);  // 自动
        }
    }

    /**
     * 设置幻灯片段落水平对齐方式
     *
     * @param paragraph
     * @param horizontal
     */
    public void setParagraphHorizontalAlign(XSLFTextParagraph paragraph, String horizontal) {
        horizontal = this.nullToDefault(horizontal, "auto");

        setCTTextParagraphHorizonAlign(paragraph, horizontal.toLowerCase());
    }

    // 设置段落水平对齐方式
    private void setCTTextParagraphHorizonAlign(XSLFTextParagraph ctTextParagraph, String horizontalStr) {
        CTTextParagraphProperties pPr = this.getPPR(ctTextParagraph);

        switch (horizontalStr) {
            case "left":
                pPr.setAlgn(STTextAlignType.L);
                break;  // 左对齐
            case "right":
                pPr.setAlgn(STTextAlignType.R);
                break;  // 右对齐
            case "center":
                pPr.setAlgn(STTextAlignType.CTR);
                break;  // 居中
            case "disperse":
                pPr.setAlgn(STTextAlignType.DIST);
                break;  // 分散对齐
            default:
                pPr.setAlgn(STTextAlignType.JUST); // 两端对齐
        }
    }

    // 获取 pPr
    private CTTextParagraphProperties getPPR(XSLFTextParagraph ctTextParagraph) {
        return ctTextParagraph.getXmlObject().getPPr() == null ? ctTextParagraph.getXmlObject().addNewPPr()
            : ctTextParagraph.getXmlObject().getPPr();
    }

    /**
     * 设置项目符号的编号
     *
     * @param ctTextParagraph
     * @param lvl
     */
    public void setBulletLvl(XSLFTextParagraph ctTextParagraph, int lvl) {
        CTTextParagraphProperties pPr = this.getPPR(ctTextParagraph);
        pPr.setLvl(lvl);
    }

    /**
     * 设置段落自动编号
     *
     * @param ctTextParagraph
     * @param startAt
     */
    public void setAutoBullet(XSLFTextParagraph ctTextParagraph, int startAt) {
        CTTextParagraphProperties pPr = this.getPPR(ctTextParagraph);
        CTTextAutonumberBullet bullet = pPr.isSetBuAutoNum() ? pPr.getBuAutoNum() : pPr.addNewBuAutoNum();
        bullet.setStartAt(startAt);
    }

    /**
     * 设置缩进等级，即悬挂缩进
     *
     * @param ctTextParagraph
     * @param indentLevel
     */
    public void setIndentLevel(XSLFTextParagraph ctTextParagraph, int indentLevel) {
        ctTextParagraph.setIndentLevel(indentLevel);
    }

    /**
     * 设置项目符号的颜色
     *
     * @param ctTextParagraph
     * @param colorHex
     */
    public void setBulletColor(XSLFTextParagraph ctTextParagraph, String colorHex) {
        colorHex = this.nullToDefault(colorHex, "000000");

        CTTextParagraphProperties pPr = this.getPPR(ctTextParagraph);
        CTColor buClr = pPr.getBuClr();
        if (pPr.isSetBuClr()) {
            pPr.unsetBuClr();
        }
        CTTextParagraph xmlObject = ctTextParagraph.getXmlObject();
        CTColor ctColor = pPr.addNewBuClr();
        CTSRgbColor ctsRgbColor = ctColor.addNewSrgbClr();
        ctsRgbColor.setVal(hexToByteArray(colorHex.substring(1)));
    }

    /**
     * 设置段落的行距，单位 磅
     *
     * @param ctTextParagraph
     * @param pounts          磅
     */
    public void setLineSpacingPounts(XSLFTextParagraph ctTextParagraph, String pounts) {
        pounts = this.nullToDefault(pounts, "1");
        CTTextParagraphProperties pPr = this.getPPR(ctTextParagraph);
        CTTextSpacing lnSpc = pPr.getLnSpc() == null ? pPr.addNewLnSpc() : pPr.getLnSpc();
        if (lnSpc.isSetSpcPct()) {
            lnSpc.unsetSpcPct();
        }

        CTTextSpacingPoint spcPts = lnSpc.getSpcPts() == null ? lnSpc.addNewSpcPts() : lnSpc.getSpcPts();
        int pts = (int) (Double.valueOf(pounts) * 100);
        spcPts.setVal(pts);
    }

    /**
     * 设置段落的行距，单位倍数
     *
     * @param ctTextParagraph
     * @param multiple        倍数，几倍行距
     */
    public void setLineSpacing(XSLFTextParagraph ctTextParagraph, Double multiple) {

        CTTextParagraphProperties pPr = this.getPPR(ctTextParagraph);
        CTTextSpacing lnSpc = pPr.getLnSpc() == null ? pPr.addNewLnSpc() : pPr.getLnSpc();
        if (lnSpc.isSetSpcPct()) {
            lnSpc.unsetSpcPct();
        }

        CTTextSpacingPercent spcPct = lnSpc.getSpcPct() == null ? lnSpc.addNewSpcPct() : lnSpc.getSpcPct();

        spcPct.setVal(Double.valueOf(multiple * 100000).intValue());
    }

    /**
     * 设置段前间距，单位磅
     *
     * @param ctTextParagraph
     * @param pounts
     */
    public void setCTTextParagraphSpacingBefore(XSLFTextParagraph ctTextParagraph, String pounts) {
        pounts = this.nullToDefault(pounts, "1");
        CTTextParagraphProperties pPr = this.getPPR(ctTextParagraph);
        CTTextSpacing ctTextSpacing = pPr.isSetSpcBef() ? pPr.getSpcBef() : pPr.addNewSpcBef();

        if (ctTextSpacing.isSetSpcPct()) {
            ctTextSpacing.unsetSpcPct();
        }

        CTTextSpacingPoint spacing =
            ctTextSpacing.isSetSpcPts() ? ctTextSpacing.getSpcPts() : ctTextSpacing.addNewSpcPts();
        int pts = (int) (Double.valueOf(pounts) * 100);
        spacing.setVal(pts);
    }

    /**
     * 设置段后间距，单位磅
     *
     * @param ctTextParagraph
     * @param pounts
     */
    public void setCTTextParagraphSpacingAfter(XSLFTextParagraph ctTextParagraph, String pounts) {
        pounts = this.nullToDefault(pounts, "1");
        CTTextParagraphProperties pPr = this.getPPR(ctTextParagraph);
        CTTextSpacing ctTextSpacing = pPr.isSetSpcAft() ? pPr.getSpcAft() : pPr.addNewSpcAft();

        if (ctTextSpacing.isSetSpcPct()) {
            ctTextSpacing.unsetSpcPct();
        }

        CTTextSpacingPoint spacing =
            ctTextSpacing.isSetSpcPts() ? ctTextSpacing.getSpcPts() : ctTextSpacing.addNewSpcPts();
        int pts = (int) (Double.valueOf(pounts) * 100);
        spacing.setVal(pts);
    }

    /**
     * 设置缩进字符
     *
     * @param ctTextParagraph
     * @param charsNum
     */
    public void setCTTextParagraphIndent(XSLFTextParagraph ctTextParagraph, String charsNum) {
        CTTextParagraphProperties pPr = this.getPPR(ctTextParagraph);
        pPr.setIndent(Integer.valueOf(charsNum));
    }

    public XSLFTextRun addParagraphText(XSLFTextParagraph paragraph, Boolean appendText, String text) {
        XSLFTextRun textRun = getNewRun(paragraph, appendText);
        textRun.setText(text);  // 设置文本

        return textRun;
    }

    /**
     * 为一个段落添加文本，appendText 参数为 true 的话，就追加文本，false 的话就重开头设置文本
     *
     * @param paragraph  要操作的段落
     * @param text       文本
     * @param appendText 是否追加文本
     */
    public XSLFTextRun addParagraphText(XSLFTextParagraph paragraph, Boolean appendText, String text, Boolean bold) {
        XSLFTextRun textRun = getNewRun(paragraph, appendText);
        textRun.setText(text);
        textRun.setBold(bold);
        return textRun;
    }

    public XSLFTextRun addParagraphText(XSLFTextParagraph paragraph,
        Boolean appendText,
        String text,
        Boolean bold,
        String chineseFontFamily,
        String westernFontFamily,
        String fontSize) {
        XSLFTextRun textRun = addParagraphText(paragraph, appendText, text, bold);    // 添加文本
        textRun.setFontSize(Double.valueOf(fontSize));  // 设置字体大小

        CTTextCharacterProperties rPr = getRPr(textRun.getXmlObject());
        setRPRFontFamily(rPr, chineseFontFamily, westernFontFamily);    // 设置字体

        return textRun;
    }

    public XSLFTextRun addParagraphText(XSLFTextParagraph paragraph,
        Boolean appendText,
        String text,
        Boolean bold,
        String chineseFontFamily,
        String westernFontFamily,
        String fontSize,
        String color) {
        XSLFTextRun textRun = addParagraphText(paragraph, appendText, text, bold, chineseFontFamily, westernFontFamily,
            fontSize);    // 添加文本
        textRun.setFontSize(Double.valueOf(fontSize));  // 设置字体大小
        CTTextCharacterProperties rPr = getRPr(textRun.getXmlObject());

        // 设置字体颜色
        CTSolidColorFillProperties solidColor = rPr.isSetSolidFill() ? rPr.getSolidFill() : rPr.addNewSolidFill();
        CTSRgbColor ctColor = solidColor.isSetSrgbClr() ? solidColor.getSrgbClr() : solidColor.addNewSrgbClr();
        ctColor.setVal(hexToByteArray(color.substring(1)));
        return textRun;
    }

    public XSLFTextRun addParagraphText(XSLFTextParagraph paragraph,
        Boolean appendText,
        String text,
        Boolean bold,
        String chineseFontFamily,
        String westernFontFamily,
        String fontSize,
        String color,
        Boolean italic) {
        XSLFTextRun textRun = addParagraphText(paragraph, appendText, text, bold, chineseFontFamily, westernFontFamily,
            fontSize, color);    // 添加文本
        textRun.setItalic(italic);
        return textRun;
    }

    public XSLFTextRun addParagraphText(XSLFTextParagraph paragraph,
        Boolean appendText,
        String text,
        Boolean bold,
        String chineseFontFamily,
        String westernFontFamily,
        String fontSize,
        String color,
        Boolean italic,
        Boolean strike) {
        XSLFTextRun textRun = addParagraphText(paragraph, appendText, text, bold, chineseFontFamily, westernFontFamily,
            fontSize, color, italic);    // 添加文本
        textRun.setStrikethrough(strike);
        return textRun;
    }

    public XSLFTextRun addParagraphText(XSLFTextParagraph paragraph,
        Boolean appendText,
        String text,
        Boolean bold,
        String chineseFontFamily,
        String westernFontFamily,
        String fontSize,
        String color,
        Boolean italic,
        Boolean strike,
        Boolean underline) {
        XSLFTextRun textRun = addParagraphText(paragraph, appendText, text, bold, chineseFontFamily, westernFontFamily,
            fontSize, color, italic, strike);    // 添加文本
        textRun.setUnderlined(underline);
        return textRun;
    }

    public XSLFTextRun addParagraphText(XSLFTextParagraph paragraph,
        Boolean appendText,
        String text,
        Boolean bold,
        String chineseFontFamily,
        String westernFontFamily,
        String fontSize,
        String color,
        Boolean italic,
        Boolean strike,
        Boolean underline,
        String characterSpacing) {
        XSLFTextRun textRun = addParagraphText(paragraph, appendText, text, bold, chineseFontFamily, westernFontFamily,
            fontSize, color, italic, strike, underline);    // 添加文本
        textRun.setCharacterSpacing(Double.valueOf(characterSpacing));
        return textRun;
    }

    /**
     * 根据已设定好的对象来设置文本的样式
     *
     * @param paragraph
     * @param appendText
     * @param text
     * @param pts
     * @return
     */
    public XSLFTextRun addParagraphText(XSLFTextParagraph paragraph, Boolean appendText, String text,
        ParagraphTextStyle pts) {
        XSLFTextRun textRun = getNewRun(paragraph, appendText); // 添加一个新的 run
        textRun.setText(text);  // 设置文本

        setTextStyle(textRun, pts);

        return textRun;
    }

    // 取消某个方向上的边框
    private void unsetCellBorder(CTTableCellProperties cellProperties, String posStr) {
        switch (posStr) {
            case "left":
                cellProperties.unsetLnL();
            case "right":
                cellProperties.unsetLnR();
            case "top":
                cellProperties.unsetLnT();
            case "bottom":
                cellProperties.unsetLnB();
            default: {
                logger.warn(posStr + " position not exists! position include ['left', 'right', 'top', 'bottom'");
            }
        }
    }

    // 获取边框中某方向上的属性
    private CTLineProperties getCTLineProperties(CTTableCellProperties cellProperties, String posStr) {
        // 定义边框下的方向
        List<String> posList = new ArrayList<String>();
        posList.addAll(Arrays.asList("top", "left", "bottom", "right"));

        switch (posStr) {
            case "left":
                return cellProperties.isSetLnL() ? cellProperties.getLnL() : cellProperties.addNewLnL();
            case "right":
                return cellProperties.isSetLnR() ? cellProperties.getLnR() : cellProperties.addNewLnR();
            case "top":
                return cellProperties.isSetLnT() ? cellProperties.getLnT() : cellProperties.addNewLnT();
            case "bottom":
                return cellProperties.isSetLnB() ? cellProperties.getLnB() : cellProperties.addNewLnB();
            default: {
                logger.warn(posStr + " position not exists! position include ['left', 'right', 'top', 'bottom'");
                return cellProperties.isSetLnL() ? cellProperties.getLnT() : cellProperties.addNewLnT();
            }
        }
    }

    // 设置边框的颜色和线条类型
    private void setCellBorder(CTLineProperties ctLineProperties, String lineType, String lineColorHex) {
        // 构造边框线的类型
        List<String> lineTypes = new ArrayList<String>();
        lineTypes.addAll(
            Arrays.asList("solid", "dot", "dash", "lgDash", "dashDot", "lgDashDot", "lgDashDotDot", "sysDash", "sysDot",
                "sysDashDot", "sysDashDotDot"));

        // 设置边框线的颜色
        CTSolidColorFillProperties ctSolidColorFillProperties = ctLineProperties.addNewSolidFill();
        CTSRgbColor ctsRgbColor = ctSolidColorFillProperties.addNewSrgbClr();
        ctsRgbColor.setVal(hexToByteArray(lineColorHex.substring(1)));

        CTPresetLineDashProperties ctPresetLineDashProperties = ctLineProperties.addNewPrstDash();
        if (lineTypes.contains(lineType)) { // 在 lineTypes 里面
            ctPresetLineDashProperties.setVal(STPresetLineDashVal.Enum.forString(lineType));
        } else {    // 没在 lineTyppes 里面
            ctPresetLineDashProperties.setVal(STPresetLineDashVal.Enum.forString("solid"));
        }
    }

    // 根据 XSLFTableCell 获取 TcPr
    private CTTableCellProperties getCellProperties(XSLFTableCell cell) {
        CTTableCell ctTableCell = (CTTableCell) cell.getXmlObject();
        return ctTableCell.isSetTcPr() ? ctTableCell.getTcPr() : ctTableCell.addNewTcPr();
    }

    // 设置 run 属性
    private void setTextStyle(XSLFTextRun run, ParagraphTextStyle pts) {

        setTextBold(run, pts.getBold());
        setTextFontFamily(run, pts.getFontFamily());
        setTextWesternFontFamily(run, pts.getWesternFontFamily());
        setTextFontSize(run, pts.getFontSize());
        setTextColor(run, pts.getColorHex());
        setTextItalic(run, pts.getItalic());
        setTextStrike(run, pts.getStrike());
        setTextUnderline(run, pts.getUnderline());
    }

    /**
     * 设置字体加粗
     *
     * @param run
     * @param bold
     * @return
     */
    public XSLFTextRun setTextBold(XSLFTextRun run, Boolean bold) {
        if (bold == null) {
            return run;
        }

        run.setBold(bold);
        return run;
    }

    /**
     * 设置中文字体
     *
     * @param run
     * @param fontFamily
     * @return
     */
    public XSLFTextRun setTextFontFamily(XSLFTextRun run, String fontFamily) {
        if (fontFamily == null || "".equals(fontFamily)) {
            return run;
        }

        CTTextCharacterProperties rPr = getRPr(run.getXmlObject());
        this.setRPRChineseFontFamily(rPr, fontFamily);
        return run;
    }

    /**
     * 设置西文字体
     *
     * @param run
     * @param westernFontFamily
     * @return
     */
    public XSLFTextRun setTextWesternFontFamily(XSLFTextRun run, String westernFontFamily) {
        if (westernFontFamily == null || "".equals(westernFontFamily)) {
            return run;
        }

        CTTextCharacterProperties rPr = getRPr(run.getXmlObject());
        this.setRPRWesternFontFamily(rPr, westernFontFamily);
        return run;
    }

    /**
     * 设置字体大小
     *
     * @param run
     * @param fontSize
     * @return
     */
    public XSLFTextRun setTextFontSize(XSLFTextRun run, String fontSize) {
        if (fontSize == null || "".equals(fontSize)) {
            return run;
        }

        run.setFontSize(Double.valueOf(fontSize));  // 设置字体大小
        return run;
    }

    /**
     * 设置字体颜色
     *
     * @param run
     * @param colorHex 16进制颜色单位
     * @return
     */
    public XSLFTextRun setTextColor(XSLFTextRun run, String colorHex) {
        if (colorHex == null || "".equals(colorHex)) {
            return run;
        }

        CTTextCharacterProperties rPr = getRPr(run.getXmlObject());
        CTSolidColorFillProperties solidColor = rPr.isSetSolidFill() ? rPr.getSolidFill() : rPr.addNewSolidFill();
        CTSRgbColor ctColor = solidColor.isSetSrgbClr() ? solidColor.getSrgbClr() : solidColor.addNewSrgbClr();
        ctColor.setVal(hexToByteArray(colorHex.substring(1)));
        return run;
    }

    /**
     * 设置字体是否斜体
     *
     * @param run
     * @param italic
     * @return
     */
    public XSLFTextRun setTextItalic(XSLFTextRun run, Boolean italic) {
        if (italic == null) {
            return run;
        }

        run.setItalic(italic);
        return run;
    }

    /**
     * 设置字体删除线
     *
     * @param run
     * @param strike
     * @return
     */
    public XSLFTextRun setTextStrike(XSLFTextRun run, Boolean strike) {
        if (strike == null) {
            return run;
        }

        run.setStrikethrough(strike);
        return run;
    }

    /**
     * 设置字体下划线
     *
     * @param run
     * @param underline
     * @return
     */
    public XSLFTextRun setTextUnderline(XSLFTextRun run, Boolean underline) {
        if (underline == null) {
            return run;
        }

        // 设置下划线
        run.setUnderlined(underline);
        return run;
    }


    /**
     * 替换段内的标签文本
     *
     * @param paragraph
     * @param paramMap
     */
    public void replaceTagInParagraph(XSLFTextParagraph paragraph, Map<String, Object> paramMap) {
        String paraText = paragraph.getText();
        String regEx = "\\{.+?\\}";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(paraText);

        if (matcher.find()) {
            StringBuilder keyWord = new StringBuilder();
            int start = getRunIndex(paragraph, "{");
            int end = getRunIndex(paragraph, "}");

            // 处理 {***} 在一个 run 内的情况
            if (start == end) {
                String rs = matcher.group(0);   // 存放标签
                keyWord.append(rs.replace("{", "").replace("}", ""));   // 存放 key
                String text = getRunsT(paragraph, start, end + 1);  // 获取标签所在 run 的全部文字
                String v = nullToDefault(paramMap.get(keyWord.toString()),
                    keyWord.toString()); // 如果没在 paramMap 中没有找到这个标签所对应的值，那么就直接替换成标签的值
                setText(paragraph.getTextRuns().get(start), text.replace(rs, v));   // 重新设置文本
            }
            replaceTagInParagraph(paragraph, paramMap); // 继续找
        }
    }

    /**
     * 获取幻灯片内的所有段落
     *
     * @param slide
     * @return
     */
    public List<XSLFTextParagraph> getParagraphsFromSlide(XSLFSlide slide) {
        List<XSLFTextParagraph> textParagraphs = new ArrayList<XSLFTextParagraph>();    // 存放所有 shape 的所有段落
        List<XSLFShape> textShapes = new ArrayList<XSLFShape>();    // 存放所有可能拥有段落文本的 shape

        // 解析出所有段落文本的 shape
        List<XSLFShape> shapes = slide.getShapes();
        for (XSLFShape shape : shapes) {
            getTextShape(textShapes, shape);
        }

        // 解析出所有段落
        for (XSLFShape shape : textShapes) {
            textParagraphs.addAll(parseParagraph(shape));
        }

        return textParagraphs;
    }

    // 解析一个 shape 内的所有段落
    private List<XSLFTextParagraph> parseParagraph(XSLFShape shape) {
        if (shape instanceof XSLFAutoShape) {
            XSLFAutoShape autoShape = (XSLFAutoShape) shape;
            return autoShape.getTextParagraphs();
        } else if (shape instanceof XSLFTextShape) {
            XSLFTextShape textShape = (XSLFTextShape) shape;
            return textShape.getTextParagraphs();
        } else if (shape instanceof XSLFFreeformShape) {
            XSLFFreeformShape freeformShape = (XSLFFreeformShape) shape;
            return freeformShape.getTextParagraphs();
        } else if (shape instanceof TextBox) {
            TextBox textBox = (TextBox) shape;
            return textBox.getTextParagraphs();
        }
        return new ArrayList<XSLFTextParagraph>();
    }

    // 解析出所有可能拥有段落文本的 shape
    private void getTextShape(List<XSLFShape> shapeList, XSLFShape shape) {
        if (shape instanceof XSLFGroupShape) {
            XSLFGroupShape groupShape = (XSLFGroupShape) shape;
            for (XSLFShape xslfShape : groupShape.getShapes()) {
                getTextShape(shapeList, xslfShape);
            }
        } else {
            shapeList.add(shape);
        }
    }

    /**
     * 在标题内添加段落， append 参数指定是追加还是覆盖
     *
     * @param titleShape
     * @param append
     * @return
     */
    public XSLFTextParagraph setChartTitle(XSLFTextShape titleShape, Boolean append) {
        if (append) {
            return titleShape.addNewTextParagraph();
        } else {
            titleShape.clearText();
            return titleShape.addNewTextParagraph();
        }
    }

    /**
     * 从当前图表中获取柱状图列表
     *
     * @param chart
     * @return
     */
    public List<CTBarChart> getBarChartFromChart(XSLFChart chart) {
        CTPlotArea plotArea = this.getChartPlotArea(chart);
        return plotArea.getBarChartList();
    }

    /**
     * 从当前图表中获取折线图列表
     *
     * @param chart
     * @return
     */
    public List<CTLineChart> getLineChartFromChart(XSLFChart chart) {
        CTPlotArea plotArea = this.getChartPlotArea(chart);
        return plotArea.getLineChartList();
    }

    /**
     * 从当前图表中获取饼状图
     *
     * @param chart
     * @return
     */
    public List<CTPieChart> getPieChartFromChart(XSLFChart chart) {
        CTPlotArea plotArea = this.getChartPlotArea(chart);
        return plotArea.getPieChartList();
    }

    /**
     * 从当前图表中获取雷达图
     *
     * @param chart
     * @return
     */
    public List<CTRadarChart> getRadarChartFromChart(XSLFChart chart) {
        CTPlotArea plotArea = this.getChartPlotArea(chart);
        return plotArea.getRadarChartList();
    }

    /**
     * 更新柱状图的 cat 缓存
     *
     * @param barChart
     * @param serIndex
     * @param data
     */
    public void updateBarCat(CTBarChart barChart, int serIndex, List<List<String>> data) {
        CTBarSer ctBarSer = barChart.getSerList().get(serIndex);
        CTAxDataSource cat = ctBarSer.getCat();

        if (barChart.isSetExtLst()) {
            barChart.unsetExtLst();
        }

        this.replaceCat(cat, data);
    }

    /**
     * 更新柱状图的缓存数据
     *
     * @param barChart
     * @param serIndex
     * @param data
     */
    public void updateBarDataCache(CTBarChart barChart, int serIndex, List<String> data) {
        CTBarSer ctBarSer = barChart.getSerList().get(serIndex);
        CTNumRef numRef = ctBarSer.getVal().getNumRef();

        this.replaceVal(numRef, data);
    }

    /**
     * 更新折线图的 cat 缓存
     *
     * @param lineChart
     * @param serIndex
     * @param data
     */
    public void updateLineCat(CTLineChart lineChart, int serIndex, List<List<String>> data) {
        CTLineSer ctLineSer = lineChart.getSerList().get(serIndex);
        CTAxDataSource cat = ctLineSer.getCat();

        if (lineChart.isSetExtLst()) {
            lineChart.unsetExtLst();
        }

        this.replaceCat(cat, data);
    }

    /**
     * 更新折线图的缓存数据
     *
     * @param lineChart
     * @param serIndex
     * @param data
     */
    public void updateLineDataCache(CTLineChart lineChart, int serIndex, List<String> data) {
        CTLineSer ctLineSer = lineChart.getSerList().get(serIndex);
        CTNumRef numRef = ctLineSer.getVal().getNumRef();

        this.replaceVal(numRef, data);
    }

    /**
     * 更新饼图的 cat 缓存
     *
     * @param pieChart
     * @param serIndex
     * @param data
     */
    public void updatePieCat(CTPieChart pieChart, int serIndex, List<List<String>> data) {
        CTPieSer ctPieSer = pieChart.getSerList().get(serIndex);
        CTAxDataSource cat = ctPieSer.getCat();

        if (pieChart.isSetExtLst()) {
            pieChart.unsetExtLst();
        }

        this.replaceCat(cat, data);
    }


    /**
     * 更新饼图的缓存数据
     *
     * @param pieChart
     * @param serIndex
     * @param data
     */
    public void updatePieDataCache(CTPieChart pieChart, int serIndex, List<String> data) {
        CTPieSer ctPieSer = pieChart.getSerList().get(serIndex);
        CTNumRef numRef = ctPieSer.getVal().getNumRef();

        this.replaceVal(numRef, data);
    }

    /**
     * 更新雷达图的 cat 缓存
     *
     * @param radarChart
     * @param serIndex
     * @param data
     */
    public void updateRadarCat(CTRadarChart radarChart, int serIndex, List<List<String>> data) {
        CTRadarSer ctRadarSer = radarChart.getSerList().get(serIndex);
        CTAxDataSource cat = ctRadarSer.getCat();

        if (radarChart.isSetExtLst()) {
            radarChart.unsetExtLst();
        }

        this.replaceCat(cat, data);
    }

    /**
     * 更新雷达图的缓存数据
     *
     * @param radarChart
     * @param serIndex
     * @param data
     */
    public void updateRadarDataCache(CTRadarChart radarChart, int serIndex, List<String> data) {
        CTRadarSer ctRadarSer = radarChart.getSerList().get(serIndex);
        CTNumRef numRef = ctRadarSer.getVal().getNumRef();

        this.replaceVal(numRef, data);
    }

    /**
     * 获取表格的行数
     *
     * @param table
     * @return
     */
    public int getTableRowsNum(XSLFTable table) {
        return table.getNumberOfRows();
    }

    /**
     * 获取表格的列数
     *
     * @param table
     * @return
     */
    public int getTableColsNum(XSLFTable table) {
        return table.getNumberOfColumns();
    }

    /**
     * 设置单元格文本
     *
     * @param cell
     * @param text
     */
    public void setCellText(XSLFTableCell cell, String text) {
//        cell.setText(text);
        CTTableCell ctTableCell = (CTTableCell) cell.getXmlObject();
        CTTextBody ctTextBody = ctTableCell.isSetTxBody() ? ctTableCell.getTxBody() : ctTableCell.addNewTxBody();
        CTTextParagraph p = null;
        if (ctTextBody.getPList().size() < 1) {
            p = ctTextBody.addNewP();
        } else {
            p = ctTextBody.getPList().get(0);
            // 删除多余的 p
            if (ctTextBody.getPList().size() > 1) {
                for (int i = 1; i < ctTextBody.getPList().size(); i++) {
                    ctTextBody.removeP(i);
                }
            }
        }

        CTRegularTextRun r = null;
        if (p.getRList().size() < 1) {
            r = p.addNewR();
        } else {
            r = p.getRList().get(0);
            // 删除多余的 r
            if (p.getRList().size() > 1) {
                for (int i = 1; i < p.getRList().size(); i++) {
                    p.removeR(i);
                }
            }
        }

        r.setT(text);   // 设置文本
    }

    /**
     * 设置该单元格所有的边框颜色和线条
     *
     * @param cell
     * @param lineType
     * @param lineColorHex
     */
    public void setCellBorder(XSLFTableCell cell, String lineType, String lineColorHex) {
        CTTableCellProperties cellProperties = getCellProperties(cell);

        if (lineType == null || "".equals(lineType)) {
            cellProperties.unsetLnT();
            cellProperties.unsetLnB();
            cellProperties.unsetLnR();
            cellProperties.unsetLnL();
        } else {
            setCellBorder(getCTLineProperties(cellProperties, "left"), lineType, lineColorHex);
            setCellBorder(getCTLineProperties(cellProperties, "right"), lineType, lineColorHex);
            setCellBorder(getCTLineProperties(cellProperties, "top"), lineType, lineColorHex);
            setCellBorder(getCTLineProperties(cellProperties, "bottom"), lineType, lineColorHex);
        }
    }

    /**
     * 设置 cell 的颜色
     *
     * @param cell
     * @param colorHex
     */
    public void setCellColor(XSLFTableCell cell, String colorHex) {
        List<XSLFTextParagraph> textParagraphs = cell.getTextParagraphs();
        for (XSLFTextParagraph textParagraph : textParagraphs) {
            for (XSLFTextRun textRun : textParagraph.getTextRuns()) {
                setTextColor(textRun, colorHex);
            }
        }
    }

    /**
     * 设置 cell 文本是否加粗
     *
     * @param cell
     * @param bold
     */
    public void setCellBold(XSLFTableCell cell, Boolean bold) {
        List<XSLFTextParagraph> textParagraphs = cell.getTextParagraphs();
        for (XSLFTextParagraph textParagraph : textParagraphs) {
            for (XSLFTextRun textRun : textParagraph.getTextRuns()) {
                setTextBold(textRun, bold);
            }
        }
    }

    /**
     * 设置单元格是否加下划线
     *
     * @param cell
     * @param underline
     */
    public void setCellUnderline(XSLFTableCell cell, Boolean underline) {
        List<XSLFTextParagraph> textParagraphs = cell.getTextParagraphs();
        for (XSLFTextParagraph textParagraph : textParagraphs) {
            for (XSLFTextRun textRun : textParagraph.getTextRuns()) {
                setTextUnderline(textRun, underline);
            }
        }
    }

    /**
     * 设置单元格是否加删除线
     *
     * @param cell
     * @param strike
     */
    public void setCellStrike(XSLFTableCell cell, Boolean strike) {
        List<XSLFTextParagraph> textParagraphs = cell.getTextParagraphs();
        for (XSLFTextParagraph textParagraph : textParagraphs) {
            for (XSLFTextRun textRun : textParagraph.getTextRuns()) {
                setTextStrike(textRun, strike);
            }
        }
    }

    /**
     * 设置单元格中文字体
     *
     * @param cell
     * @param fontFamily
     */
    public void setCellFontfamily(XSLFTableCell cell, String fontFamily) {
        List<XSLFTextParagraph> textParagraphs = cell.getTextParagraphs();
        for (XSLFTextParagraph textParagraph : textParagraphs) {
            for (XSLFTextRun textRun : textParagraph.getTextRuns()) {
                setTextFontFamily(textRun, fontFamily);
            }
        }
    }

    /**
     * 设置单元格文本字体大小
     *
     * @param cell
     * @param fontSize
     */
    public void setCellFontSize(XSLFTableCell cell, String fontSize) {
        List<XSLFTextParagraph> textParagraphs = cell.getTextParagraphs();
        for (XSLFTextParagraph textParagraph : textParagraphs) {
            for (XSLFTextRun textRun : textParagraph.getTextRuns()) {
                setTextFontSize(textRun, fontSize);
            }
        }
    }

    /**
     * 设置单元格文本斜体
     *
     * @param cell
     * @param italic
     */
    public void setCellItalic(XSLFTableCell cell, Boolean italic) {
        List<XSLFTextParagraph> textParagraphs = cell.getTextParagraphs();
        for (XSLFTextParagraph textParagraph : textParagraphs) {
            for (XSLFTextRun textRun : textParagraph.getTextRuns()) {
                setTextItalic(textRun, italic);
            }
        }
    }

    /**
     * 设置单元格文本样式
     *
     * @param cell
     * @param pts
     */
    public void setCellTextStyle(XSLFTableCell cell, ParagraphTextStyle pts) {
        List<XSLFTextParagraph> textParagraphs = cell.getTextParagraphs();
        for (XSLFTextParagraph textParagraph : textParagraphs) {
            for (XSLFTextRun textRun : textParagraph.getTextRuns()) {
                setTextStyle(textRun, pts);
            }
        }
    }

    /**
     * 设置单元格西文字体
     *
     * @param cell
     * @param westernFontFamily
     */
    public void setCellFontWesternFontfamily(XSLFTableCell cell, String westernFontFamily) {
        List<XSLFTextParagraph> textParagraphs = cell.getTextParagraphs();
        for (XSLFTextParagraph textParagraph : textParagraphs) {
            for (XSLFTextRun textRun : textParagraph.getTextRuns()) {
                setTextWesternFontFamily(textRun, westernFontFamily);
            }
        }
    }

    /**
     * 设置单元格水平对齐方式
     *
     * @param cell
     * @param horizontalAlign
     */
    public void setCellHorizontalAlign(XSLFTableCell cell, String horizontalAlign) {
        List<XSLFTextParagraph> textParagraphs = cell.getTextParagraphs();
        for (XSLFTextParagraph textParagraph : textParagraphs) {
            setParagraphHorizontalAlign(textParagraph, horizontalAlign);
        }
    }

    /**
     * 设置单元格垂直对齐方式
     *
     * @param cell
     * @param verticalAlign
     */
    public void setCellVerticalAlign(XSLFTableCell cell, String verticalAlign) {
        List<XSLFTextParagraph> textParagraphs = cell.getTextParagraphs();
        for (XSLFTextParagraph textParagraph : textParagraphs) {
            setParagraphVerticalAlign(textParagraph, verticalAlign);
        }
    }

    /**
     * 设置单元格某一个方向上的线条和颜色
     *
     * @param cell
     * @param posStr
     * @param lineType
     * @param lineColorHex
     */
    public void setCellBorder(XSLFTableCell cell, String posStr, String lineType, String lineColorHex) {
        CTTableCellProperties cellProperties = getCellProperties(cell);

        if (lineType == null || "".equals(lineType)) {
            unsetCellBorder(cellProperties, posStr);
        }

        String pos = posStr.toLowerCase();  // 转换成小写

        CTLineProperties ctLineProperties = getCTLineProperties(cellProperties, posStr);

        setCellBorder(ctLineProperties, lineType, lineColorHex);    // 设置线条和颜色
    }

    /**
     * 设置单元格的填充颜色
     *
     * @param cell
     * @param fillColorHex
     */
    public void setCellFillColor(XSLFTableCell cell, String fillColorHex) {
        CTTableCell ctTableCell = (CTTableCell) cell.getXmlObject();
        CTTableCellProperties tcPr = ctTableCell.getTcPr();

        if (fillColorHex == null || "".equals(fillColorHex)) {    // 如果 fillColor 为空，那么就是无填充
            if (!tcPr.isSetNoFill()) {
                tcPr.addNewNoFill();
            }
            if (tcPr.isSetSolidFill()) {
                tcPr.unsetSolidFill();
            }
        } else {
            tcPr.unsetNoFill();
            CTSolidColorFillProperties fillProperties =
                tcPr.isSetSolidFill() ? tcPr.getSolidFill() : tcPr.addNewSolidFill();
            CTSRgbColor ctsRgbColor =
                fillProperties.isSetSrgbClr() ? fillProperties.getSrgbClr() : fillProperties.addNewSrgbClr();
            ctsRgbColor.setVal(hexToByteArray(fillColorHex.substring(1)));
        }
    }

    /**
     * 在单元格内添加文本
     *
     * @param cell
     * @return
     */
    public XSLFTextParagraph addCellParagraph(XSLFTableCell cell) {
        CTTableCell ctTableCell = (CTTableCell) cell.getXmlObject();
        return cell.addNewTextParagraph();
    }

    /**
     * 获取表格某行
     *
     * @param table
     * @param rowNum
     * @return
     */
    public XSLFTableRow getTableRow(XSLFTable table, int rowNum) {
        return table.getRows().get(rowNum);
    }

    /**
     * 获取行列表
     *
     * @param table
     * @return
     */
    public List<XSLFTableRow> getTableRows(XSLFTable table) {
        return table.getRows();
    }

    /**
     * 根据某行或取单元格列表
     *
     * @param row
     * @return
     */
    public List<XSLFTableCell> getTableCols(XSLFTableRow row) {
        return row.getCells();
    }


    /**
     * 获取某行中 fromIndex 到 toIndex 的单元格列表
     *
     * @param row
     * @param fromIndex
     * @param toIndex
     * @return
     */
    public List<XSLFTableCell> getTableCols(XSLFTableRow row, int fromIndex, int toIndex) {
        return new ArrayList<XSLFTableCell>() {{
            addAll(row.getCells().subList(fromIndex, toIndex));
        }};
    }

    /**
     * 获取某行中某列到某列的单元格列表
     *
     * @param table
     * @param rowNum
     * @param fromColIndex
     * @param toRowIndex
     * @return
     */
    public List<XSLFTableCell> getTableColsByRow(XSLFTable table, int rowNum, int fromColIndex, int toRowIndex) {
        List<XSLFTableCell> cells = new ArrayList<XSLFTableCell>();

        for (int i = fromColIndex; i < toRowIndex; i++) {
            cells.add(table.getCell(rowNum, i));
        }
        return cells;
    }

    /**
     * 获取某行从 startIndex 索引开始到最后列的单元格列表
     *
     * @param table
     * @param rowNum
     * @param startIndex
     * @return
     */
    public List<XSLFTableCell> getTableColsByRow(XSLFTable table, int rowNum, int startIndex) {
        List<XSLFTableCell> cells = new ArrayList<XSLFTableCell>();

        for (int i = startIndex; i < table.getNumberOfColumns(); i++) {
            cells.add(table.getCell(rowNum, i));
        }

        return cells;
    }

    /**
     * 根据表格中某列的 fromRowIndex 到 toRowIndex 的列表
     *
     * @param table
     * @param colNum
     * @param fromRowIndex
     * @param toRowIndex
     * @return
     */
    public List<XSLFTableCell> getTableColsByCol(XSLFTable table, int colNum, int fromRowIndex, int toRowIndex) {
        List<XSLFTableCell> cells = new ArrayList<XSLFTableCell>();

        for (int i = fromRowIndex; i < toRowIndex; i++) {
            cells.add(table.getCell(i, colNum));
        }

        return cells;
    }

    // 替换 Cat 缓存
    private void replaceCat(CTAxDataSource cat, List<List<String>> data) {
        if (cat.isSetNumRef()) {
            this.updateCat(cat.getNumRef(), data);
        } else if (cat.isSetStrRef()) {
            this.updateCat(cat.getStrRef(), data);
        } else if (cat.isSetMultiLvlStrRef()) {
            this.updateCat(cat.getMultiLvlStrRef(), data);
        }
    }

    // 替换数据
    private void replaceVal(CTNumRef numRef, List<String> data) {
        numRef.unsetNumCache();

        CTNumData ctNumData = numRef.addNewNumCache();
        ctNumData.addNewPtCount().setVal(data.size());

        for (int i = 0; i < data.size(); i++) {
            CTNumVal ctNumVal = ctNumData.addNewPt();
            ctNumVal.setIdx(i);
            ctNumVal.setV(data.get(i));
        }
    }

    // 更新cat中多系列的缓存
    private void updateCat(CTMultiLvlStrRef multiLvlStrRef, List<List<String>> data) {
        multiLvlStrRef.unsetMultiLvlStrCache();

        CTMultiLvlStrData ctMultiLvlStrData = multiLvlStrRef.addNewMultiLvlStrCache();
        ctMultiLvlStrData.addNewPtCount().setVal(data.get(0).size());

        for (int i = 0; i < data.size(); i++) {
            CTLvl ctLvl = ctMultiLvlStrData.addNewLvl();
            for (int j = 0; j < data.get(i).size(); j++) {
                CTStrVal ctStrVal = ctLvl.addNewPt();
                ctStrVal.setV(data.get(i).get(j));
                ctStrVal.setIdx(j);
            }
        }
    }

    // 更新 strRef 类型的 cat 缓存
    private void updateCat(CTStrRef strRef, List<List<String>> data) {

        // 重新设置 pt
        strRef.unsetStrCache();
        CTStrData ctStrData = strRef.addNewStrCache();
        ctStrData.addNewPtCount().setVal(data.get(0).size());
        for (int i = 0; i < data.get(0).size(); i++) {
            CTStrVal ctStrVal = ctStrData.addNewPt();
            ctStrVal.setV(data.get(0).get(i));
            ctStrVal.setIdx(i);
        }

//        // 引用原来的 pt
//        CTStrData strCache = strRef.getStrCache();
//        List<CTStrVal> ptList = strCache.getPtList();
//        for (int i = 0; i < ptList.size(); i++) {
//            ptList.get(i).setV(data.get(0).get(i));
//        }
    }

    // 更新 numRef 类型的 cat 缓存
    private void updateCat(CTNumRef numRef, List<List<String>> data) {
        // 重新设置 pt
        numRef.unsetNumCache();
        CTNumData ctNumData = numRef.addNewNumCache();
        ctNumData.addNewPtCount().setVal(data.get(0).size());
        for (int i = 0; i < data.get(0).size(); i++) {
            CTNumVal ctNumVal = ctNumData.addNewPt();
            ctNumVal.setV(data.get(0).get(i));
            ctNumVal.setIdx(i);
        }

//        // 引用原来的 pt
//        CTNumData numCache = numRef.getNumCache();
//        List<CTNumVal> ptList = numCache.getPtList();
//        for (int i = 0; i < ptList.size(); i++) {
//            ptList.get(i).setV(data.get(0).get(i));
//        }
    }


    // 获取 plotArea
    private CTPlotArea getChartPlotArea(XSLFChart chart) {
        return chart.getCTChart().getPlotArea();
    }

    // 获取段落下特定索引的 run 的值
    private String getRunsT(XSLFTextParagraph paragraph, int start, int end) {
        List<XSLFTextRun> textRuns = paragraph.getTextRuns();
        StringBuilder t = new StringBuilder();
        for (int i = start; i < end; i++) {
            t.append(textRuns.get(i).getRawText());
        }
        return t.toString();
    }

    // 设置 run 的值
    private void setText(XSLFTextRun run, String t) {
        run.setText(t);
    }

    // 获取 word 在段落中出现第一次的 run 的索引
    private int getRunIndex(XSLFTextParagraph paragraph, String word) {
        List<CTRegularTextRun> rList = paragraph.getXmlObject().getRList();
        for (int i = 0; i < rList.size(); i++) {

            String text = rList.get(i).getT();
            if (text.contains(word)) {
                return i;
            }
        }
        return -1;
    }

    // 设置 rPr 的字体
    private void setRPRFontFamily(CTTextCharacterProperties rPr, String chineseFontFamily, String westernFontFamily) {
        this.setRPRChineseFontFamily(rPr, chineseFontFamily);
        this.setRPRWesternFontFamily(rPr, westernFontFamily);
    }

    // 设置中文字体
    private void setRPRChineseFontFamily(CTTextCharacterProperties rPr, String fontFamily) {
        if (fontFamily == null || "".equals(fontFamily)) {
            fontFamily = "宋体";
        }

        if (rPr.isSetLatin()) {
            rPr.unsetLatin();
        }

        CTTextFont ea = rPr.isSetEa() ? rPr.getEa() : rPr.addNewEa();
        ea.setTypeface(fontFamily);
        ea.setPitchFamily(new Byte("34"));
        ea.setCharset(new Byte("-122"));

        CTTextFont cs = rPr.isSetCs() ? rPr.getCs() : rPr.addNewCs();
        cs.setTypeface(fontFamily);
        cs.setPitchFamily(new Byte("34"));
        cs.setCharset(new Byte("-122"));
    }

    // 设置西文字体
    private void setRPRWesternFontFamily(CTTextCharacterProperties rPr, String fontFamily) {
        if (fontFamily == null || "".equals(fontFamily)) {
            fontFamily = "宋体";
        }
        if (rPr.isSetLatin()) {
            rPr.unsetLatin();
        }
        CTTextFont latin = rPr.isSetLatin() ? rPr.getLatin() : rPr.addNewLatin();
        latin.setTypeface(fontFamily);
        latin.setPitchFamily(new Byte("34"));
        latin.setCharset(new Byte("-122"));
    }

    // 获取新添加的 run
    private XSLFTextRun getNewRun(XSLFTextParagraph paragraph, Boolean appendText) {
        if (!appendText) {  // 是否追加文本
            this.clearParagraphText(paragraph);
        }

        return paragraph.addNewTextRun();
    }

    // 清除段落的文本
    private void clearParagraphText(XSLFTextParagraph paragraph) {
        CTTextParagraph ctTextParagraph = paragraph.getXmlObject();
        ctTextParagraph.getRList().clear();
        paragraph.getTextRuns().clear();
    }

    // 获取 rPR
    private CTTextCharacterProperties getRPr(XmlObject xmlObject) {
        if (xmlObject instanceof CTTextField) {
            CTTextField tf = (CTTextField) xmlObject;
            return tf.getRPr() == null ? tf.addNewRPr() : tf.getRPr();
        } else if (xmlObject instanceof CTTextLineBreak) {
            CTTextLineBreak tlb = (CTTextLineBreak) xmlObject;
            return tlb.getRPr() == null ? tlb.addNewRPr() : tlb.getRPr();
        } else {
            CTRegularTextRun tr = (CTRegularTextRun) xmlObject;
            return tr.getRPr() == null ? tr.addNewRPr() : tr.getRPr();
        }
    }

    // 空字符串转默认值
    private String nullToDefault(String goalStr, String defaultStr) {
        if (goalStr == null || "".equals(goalStr)) {
            return defaultStr;
        }
        return goalStr;
    }

    private String nullToDefault(Object o, String defaultStr) {
        if (o == null) {
            return defaultStr;
        } else {
            if ("".equals(o.toString())) {
                return defaultStr;
            } else {
                return o.toString();
            }
        }
    }

    /**
     * 将16进制转换为 byte 数组
     *
     * @param inHex 需要转换的字符串
     * @return
     */
    public byte[] hexToByteArray(String inHex) {
        int hexlen = inHex.length();
        byte[] result;
        if (hexlen % 2 == 1) {   // 奇数的话，就在前面添加 0
            hexlen++;
            result = new byte[(hexlen / 2)];
            inHex = "0" + inHex;
        } else { // 偶数
            result = new byte[(hexlen / 2)];
        }
        int j = 0;
        for (int i = 0; i < hexlen; i += 2) {
            result[j] = this.hexToByte(inHex.substring(i, i + 2));
            j++;
        }
        return result;
    }

    private byte hexToByte(String inHex) {
        return (byte) Integer.parseInt(inHex, 16);
    }

}
