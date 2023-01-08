package com.coderdream.pic;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;

/**
 * @author tqf
 * @version 创建时间：2020-3-13 下午2:32:59
 * 类说明:Poster实体类
 */
public class Poster implements Serializable {
    private int width;//海报的宽（像素为单位）
    private int height;//海报的高
    //头像
    private String avatarUrl;//头像url
    private int avatarX;//头像左上角横坐标
    private int avatarY;//头像左上角纵坐标
    private int avatarWidth;//头像宽
    private int avatarHeight;//头像高
    private boolean isCircle;//是否圆形头像
    //名字
    private String name;
    private Font nameFont;
    private Color nameColor;
    private int nameX;
    private int nameY;
    //商品
    private String goodsUrl;
    private int goodsX;
    private int goodsY;
    private int goodsWidth;
    private int goodsHeight;
    //商品描述
    private String desc;
    private Font descFont;
    private Color descColor;
    private int descX;
    private int descY;
    //商品价格
    private String price;
    private Font priceFont;
    private Color priceColor;
    private int priceX;
    private int priceY;

    //测评结果标题
    private String result;
    private Font resultFont;
    private Color resultColor;
    private int resultX;
    private int resultY;

    //测评结果描述
    private String result_content;
    private Font result_contentFont;
    private Color result_contentColor;
    private int result_contentX;
    private int result_contentY;

    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * @return the resultFont
     */
    public Font getResultFont() {
        return resultFont;
    }

    /**
     * @param resultFont the resultFont to set
     */
    public void setResultFont(Font resultFont) {
        this.resultFont = resultFont;
    }

    /**
     * @return the resultColor
     */
    public Color getResultColor() {
        return resultColor;
    }

    /**
     * @param resultColor the resultColor to set
     */
    public void setResultColor(Color resultColor) {
        this.resultColor = resultColor;
    }

    /**
     * @return the resultX
     */
    public int getResultX() {
        return resultX;
    }

    /**
     * @param resultX the resultX to set
     */
    public void setResultX(int resultX) {
        this.resultX = resultX;
    }

    /**
     * @return the resultY
     */
    public int getResultY() {
        return resultY;
    }

    /**
     * @param resultY the resultY to set
     */
    public void setResultY(int resultY) {
        this.resultY = resultY;
    }

    /**
     * @return the result_content
     */
    public String getResult_content() {
        return result_content;
    }

    /**
     * @param result_content the result_content to set
     */
    public void setResult_content(String result_content) {
        this.result_content = result_content;
    }

    /**
     * @return the result_contentFont
     */
    public Font getResult_contentFont() {
        return result_contentFont;
    }

    /**
     * @param result_contentFont the result_contentFont to set
     */
    public void setResult_contentFont(Font result_contentFont) {
        this.result_contentFont = result_contentFont;
    }

    /**
     * @return the result_contentColor
     */
    public Color getResult_contentColor() {
        return result_contentColor;
    }

    /**
     * @param result_contentColor the result_contentColor to set
     */
    public void setResult_contentColor(Color result_contentColor) {
        this.result_contentColor = result_contentColor;
    }

    /**
     * @return the result_contentX
     */
    public int getResult_contentX() {
        return result_contentX;
    }

    /**
     * @param result_contentX the result_contentX to set
     */
    public void setResult_contentX(int result_contentX) {
        this.result_contentX = result_contentX;
    }

    /**
     * @return the result_contentY
     */
    public int getResult_contentY() {
        return result_contentY;
    }

    /**
     * @param result_contentY the result_contentY to set
     */
    public void setResult_contentY(int result_contentY) {
        this.result_contentY = result_contentY;
    }

    //小程序码
    private String qrCodeUrl;
    private int qrCodeX;
    private int qrCodeY;
    private int qrCodeWidth;
    private int qrCodeHeight;
    //提示1
    private String tip1;
    private Font tip1Font;
    private Color tip1Color;
    private int tip1X;
    private int tip1Y;
    //提示2
    private String tip2;
    private Font tip2Font;
    private Color tip2Color;
    private int tip2X;
    private int tip2Y;
    //底部栏
    private Color footerColor;
    private int footerWidth;
    private int footerHeight;
    private int footerX;
    private int footerY;
    //底部栏提示字
    private String footerTip;
    private Font footerTipFont;
    private Color footerTipColor;
    private int footerTipX;
    private int footerTipY;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getAvatarX() {
        return avatarX;
    }

    public void setAvatarX(int avatarX) {
        this.avatarX = avatarX;
    }

    public int getAvatarY() {
        return avatarY;
    }

    public void setAvatarY(int avatarY) {
        this.avatarY = avatarY;
    }

    public int getAvatarWidth() {
        return avatarWidth;
    }

    public void setAvatarWidth(int avatarWidth) {
        this.avatarWidth = avatarWidth;
    }

    public int getAvatarHeight() {
        return avatarHeight;
    }

    public void setAvatarHeight(int avatarHeight) {
        this.avatarHeight = avatarHeight;
    }

    public boolean isCircle() {
        return isCircle;
    }

    public void setCircle(boolean circle) {
        isCircle = circle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Font getNameFont() {
        return nameFont;
    }

    public void setNameFont(Font nameFont) {
        this.nameFont = nameFont;
    }

    public Color getNameColor() {
        return nameColor;
    }

    public void setNameColor(Color nameColor) {
        this.nameColor = nameColor;
    }

    public int getNameX() {
        return nameX;
    }

    public void setNameX(int nameX) {
        this.nameX = nameX;
    }

    public int getNameY() {
        return nameY;
    }

    public void setNameY(int nameY) {
        this.nameY = nameY;
    }

    public String getGoodsUrl() {
        return goodsUrl;
    }

    public void setGoodsUrl(String goodsUrl) {
        this.goodsUrl = goodsUrl;
    }

    public int getGoodsX() {
        return goodsX;
    }

    public void setGoodsX(int goodsX) {
        this.goodsX = goodsX;
    }

    public int getGoodsY() {
        return goodsY;
    }

    public void setGoodsY(int goodsY) {
        this.goodsY = goodsY;
    }

    public int getGoodsWidth() {
        return goodsWidth;
    }

    public void setGoodsWidth(int goodsWidth) {
        this.goodsWidth = goodsWidth;
    }

    public int getGoodsHeight() {
        return goodsHeight;
    }

    public void setGoodsHeight(int goodsHeight) {
        this.goodsHeight = goodsHeight;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Font getDescFont() {
        return descFont;
    }

    public void setDescFont(Font descFont) {
        this.descFont = descFont;
    }

    public Color getDescColor() {
        return descColor;
    }

    public void setDescColor(Color descColor) {
        this.descColor = descColor;
    }

    public int getDescX() {
        return descX;
    }

    public void setDescX(int descX) {
        this.descX = descX;
    }

    public int getDescY() {
        return descY;
    }

    public void setDescY(int descY) {
        this.descY = descY;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Font getPriceFont() {
        return priceFont;
    }

    public void setPriceFont(Font priceFont) {
        this.priceFont = priceFont;
    }

    public Color getPriceColor() {
        return priceColor;
    }

    public void setPriceColor(Color priceColor) {
        this.priceColor = priceColor;
    }

    public int getPriceX() {
        return priceX;
    }

    public void setPriceX(int priceX) {
        this.priceX = priceX;
    }

    public int getPriceY() {
        return priceY;
    }

    public void setPriceY(int priceY) {
        this.priceY = priceY;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public int getQrCodeX() {
        return qrCodeX;
    }

    public void setQrCodeX(int qrCodeX) {
        this.qrCodeX = qrCodeX;
    }

    public int getQrCodeY() {
        return qrCodeY;
    }

    public void setQrCodeY(int qrCodeY) {
        this.qrCodeY = qrCodeY;
    }

    public int getQrCodeWidth() {
        return qrCodeWidth;
    }

    public void setQrCodeWidth(int qrCodeWidth) {
        this.qrCodeWidth = qrCodeWidth;
    }

    public int getQrCodeHeight() {
        return qrCodeHeight;
    }

    public void setQrCodeHeight(int qrCodeHeight) {
        this.qrCodeHeight = qrCodeHeight;
    }

    public String getTip1() {
        return tip1;
    }

    public void setTip1(String tip1) {
        this.tip1 = tip1;
    }

    public Font getTip1Font() {
        return tip1Font;
    }

    public void setTip1Font(Font tip1Font) {
        this.tip1Font = tip1Font;
    }

    public Color getTip1Color() {
        return tip1Color;
    }

    public void setTip1Color(Color tip1Color) {
        this.tip1Color = tip1Color;
    }

    public int getTip1X() {
        return tip1X;
    }

    public void setTip1X(int tip1X) {
        this.tip1X = tip1X;
    }

    public int getTip1Y() {
        return tip1Y;
    }

    public void setTip1Y(int tip1Y) {
        this.tip1Y = tip1Y;
    }

    public String getTip2() {
        return tip2;
    }

    public void setTip2(String tip2) {
        this.tip2 = tip2;
    }

    public Font getTip2Font() {
        return tip2Font;
    }

    public void setTip2Font(Font tip2Font) {
        this.tip2Font = tip2Font;
    }

    public Color getTip2Color() {
        return tip2Color;
    }

    public void setTip2Color(Color tip2Color) {
        this.tip2Color = tip2Color;
    }

    public int getTip2X() {
        return tip2X;
    }

    public void setTip2X(int tip2X) {
        this.tip2X = tip2X;
    }

    public int getTip2Y() {
        return tip2Y;
    }

    public void setTip2Y(int tip2Y) {
        this.tip2Y = tip2Y;
    }

    public Color getFooterColor() {
        return footerColor;
    }

    public void setFooterColor(Color footerColor) {
        this.footerColor = footerColor;
    }

    public int getFooterWidth() {
        return footerWidth;
    }

    public void setFooterWidth(int footerWidth) {
        this.footerWidth = footerWidth;
    }

    public int getFooterHeight() {
        return footerHeight;
    }

    public void setFooterHeight(int footerHeight) {
        this.footerHeight = footerHeight;
    }

    public int getFooterX() {
        return footerX;
    }

    public void setFooterX(int footerX) {
        this.footerX = footerX;
    }

    public int getFooterY() {
        return footerY;
    }

    public void setFooterY(int footerY) {
        this.footerY = footerY;
    }

    public String getFooterTip() {
        return footerTip;
    }

    public void setFooterTip(String footerTip) {
        this.footerTip = footerTip;
    }

    public Font getFooterTipFont() {
        return footerTipFont;
    }

    public void setFooterTipFont(Font footerTipFont) {
        this.footerTipFont = footerTipFont;
    }

    public Color getFooterTipColor() {
        return footerTipColor;
    }

    public void setFooterTipColor(Color footerTipColor) {
        this.footerTipColor = footerTipColor;
    }

    public int getFooterTipX() {
        return footerTipX;
    }

    public void setFooterTipX(int footerTipX) {
        this.footerTipX = footerTipX;
    }

    public int getFooterTipY() {
        return footerTipY;
    }

    public void setFooterTipY(int footerTipY) {
        this.footerTipY = footerTipY;
    }
}

