package com.coderdream.freeapps.util.ppt.pptutil.entity;

/**
 * paragraph 中 run 的文本样式
 */
public class ParagraphTextStyle {
    private String fontFamily;  // 中文字体
    private String westernFontFamily;   // 西文字体
    private String fontSize;    // 字体大小
    private String colorHex;    // 颜色16进制字符串
    private Boolean bold;   // 是否加粗
    private Boolean italic; // 是否斜体
    private Boolean strike; // 删除线
    private Boolean underline;  // 下划线

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public String getWesternFontFamily() {
        return westernFontFamily;
    }

    public void setWesternFontFamily(String westernFontFamily) {
        this.westernFontFamily = westernFontFamily;
    }

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public String getColorHex() {
        return colorHex;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }

    public Boolean getBold() {
        return bold;
    }

    public void setBold(Boolean bold) {
        this.bold = bold;
    }

    public Boolean getItalic() {
        return italic;
    }

    public void setItalic(Boolean italic) {
        this.italic = italic;
    }

    public Boolean getStrike() {
        return strike;
    }

    public void setStrike(Boolean strike) {
        this.strike = strike;
    }

    public Boolean getUnderline() {
        return underline;
    }

    public void setUnderline(Boolean underline) {
        this.underline = underline;
    }
}
