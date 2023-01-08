package com.coderdream.pic;

import sun.font.FontDesignMetrics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

/**
 * @author tqf
 * @version 创建时间：2020-3-13 下午2:34:33
 * 类说明:海报工具类
 */
public class AppDetailUtil {
    static Poster initPoster(Poster poster_) {
        Poster poster = new Poster();
        //画布
        poster.setWidth(poster_.getWidth());
        poster.setHeight(poster_.getHeight());
        //头像
      /* poster.setAvatarUrl(avatarUrl);
       poster.setCircle(true);
       poster.setAvatarX((int)(width/11.5));
       poster.setAvatarY(height/28);
       poster.setAvatarWidth(width/6);
       poster.setAvatarHeight(width/6);
       //名字
       poster.setName(name);
       poster.setNameFont(new Font("宋体",Font.PLAIN,width/24));
       poster.setNameColor(new Color(33,33,33));
       poster.setNameX(poster.getAvatarX()+poster.getAvatarWidth()+20);
       poster.setNameY(poster.getAvatarY()+poster.getAvatarHeight()/2+15);*/
        //商品
        poster.setGoodsUrl(poster_.getGoodsUrl());
        poster.setGoodsWidth(375); //banner图宽度填充满
        poster.setGoodsHeight(150);
        poster.setGoodsX((poster_.getWidth() - poster.getGoodsWidth()) / 2);
        poster.setGoodsY(poster.getAvatarY() + poster.getAvatarHeight()); //+100是往下移动
        //测评结果
        poster.setDesc("测评结果");
        poster.setDescColor(Color.GRAY);
        int size = poster_.getWidth() / 17;
        poster.setDescFont(new Font("宋体", Font.BOLD, 18));
        poster.setDescX((poster_.getWidth() - "测评结果".length() * size) / 6 - 15);
        poster.setDescY(poster.getGoodsY() + poster.getGoodsHeight() + (int) (poster_.getHeight() / 21.3) + 15);
        //测评结果标题
        poster.setPrice(poster_.getPrice());
        poster.setPriceColor(Color.BLACK);
        poster.setPriceFont(new Font("宋体", Font.BOLD, size));
        poster.setPriceX((poster_.getWidth() - poster_.getPrice().length() * size) / 5);
        poster.setPriceY(poster.getGoodsY() + poster.getGoodsHeight() + (int) (poster_.getHeight() / 10.6) + 18);

        //测评说明
        poster.setResult("测评说明");
        poster.setResultColor(Color.GRAY);
        poster.setResultFont(new Font("宋体", Font.BOLD, 18));
        poster.setResultX((poster_.getWidth() - "测评说明".length() * size) / 6 - 15);
        poster.setResultY(poster.getGoodsY() + poster.getGoodsHeight() + (int) (poster_.getHeight() / 21.3) + 90);

        //测评说明描述
        poster.setResult_content(poster_.getDesc());
        poster.setResult_contentColor(Color.BLACK);
        poster.setResult_contentFont(new Font("宋体", Font.BOLD, 17));
        poster.setResult_contentX((3 * size) / 6 + 23);
        poster.setResult_contentY(poster.getGoodsY() + poster.getGoodsHeight() + (int) (poster_.getHeight() / 21.3) + 120);


        //小程序码
        poster.setQrCodeUrl(poster_.getQrCodeUrl());
        poster.setQrCodeWidth((int) (poster_.getWidth() / 2.85));
        poster.setQrCodeHeight((int) (poster_.getWidth() / 2.85));
        poster.setQrCodeX((int) (poster_.getWidth() / 16.5));
        poster.setQrCodeY(poster_.getHeight() - poster.getQrCodeHeight() - (int) (poster_.getHeight() / 7.68));
        //tips1
        poster.setTip1("长按识别小程序码");
        poster.setTip1Color(Color.BLACK);
        poster.setTip1Font(new Font("宋体", Font.BOLD, poster_.getWidth() / 21));
        poster.setTip1X(poster.getQrCodeX() + poster.getQrCodeWidth() + 20);
        poster.setTip1Y(poster.getQrCodeY() + poster.getQrCodeHeight() / 2 + 10);
        //tips2
        poster.setTip2("咨询我们是专业的");//好物与好友一起分享
        poster.setTip2Color(Color.GRAY);
        poster.setTip2Font(new Font("宋体", Font.PLAIN, poster_.getWidth() / 25));
        poster.setTip2X(poster.getQrCodeX() + poster.getQrCodeWidth() + 20);
        poster.setTip2Y(poster.getQrCodeY() + poster.getQrCodeHeight() / 2 + 32);//90是这句问题与上面的间距
        //footer
        poster.setFooterColor(new Color(49, 196, 141));
        poster.setFooterWidth(poster_.getWidth());
        poster.setFooterHeight(poster_.getHeight() / 13);
        poster.setFooterX(0);
        poster.setFooterY(poster_.getHeight() - poster.getFooterHeight());
        //footer tips
        poster.setFooterTip("「心觉咨询」提供内容及技术支持");
        poster.setFooterTipColor(Color.WHITE);
        poster.setFooterTipFont(new Font("宋体", Font.BOLD, poster_.getWidth() / 21));
        poster.setFooterTipX((poster_.getWidth() - (poster.getFooterTip().length() * poster_.getWidth() / 21)) / 2);
        poster.setFooterTipY(poster_.getHeight() - poster.getFooterHeight() / 3);
        return poster;
    }

    static void drawPoster(Poster poster) throws Exception {
        long startTime = System.currentTimeMillis();
        String qrCodeUrl = poster.getQrCodeUrl();
        String goodsUrl = poster.getGoodsUrl();
        String avatarUrl = poster.getAvatarUrl();
        BufferedImage qrCodeImage = ImageIO.read(new URL(qrCodeUrl));
        BufferedImage goodsImage = ImageIO.read(new URL(goodsUrl));
        int width = poster.getWidth();
        int height = poster.getHeight();
        //画布
        BufferedImage canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) canvas.getGraphics();
        g.setBackground(Color.WHITE);//设置背景色
        g.clearRect(0, 0, width, height);

        // 设置文字抗锯齿
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
       /*//圆形头像
       BufferedImage newAvatar = circle(avatarUrl,poster.getAvatarWidth());
       //画头像
       g.drawImage(newAvatar.getScaledInstance(newAvatar.getWidth(), newAvatar.getHeight(), Image.SCALE_SMOOTH), poster.getAvatarX(), poster.getAvatarY(), null);
       // 4. 写字（昵称）
       g.setColor(poster.getNameColor());
       g.setFont(poster.getNameFont());
       g.drawString(poster.getName(), poster.getNameX(), poster.getNameY());*/
        //画商品
//       g.drawImage(Thumbnails.of(goodsImage).size(poster.getGoodsWidth(), poster.getGoodsHeight()).asBufferedImage(), poster.getGoodsX(), poster.getGoodsY(), null);
        g.drawImage(goodsImage.getScaledInstance(poster.getGoodsWidth(), poster.getGoodsHeight(), Image.SCALE_SMOOTH), poster.getGoodsX(), poster.getGoodsY(), null);
        //测评结果
        g.setColor(poster.getDescColor());
        g.setFont(poster.getDescFont());
        g.drawString("测评结果", poster.getDescX(), poster.getDescY());
        //测评结果标题
        g.setColor(poster.getPriceColor());
        g.setFont(poster.getPriceFont());
        g.drawString(poster.getPrice(), poster.getPriceX(), poster.getPriceY());

        //画测评说明
        g.setColor(poster.getResultColor());
        g.setFont(poster.getResultFont());
        g.drawString(poster.getResult(), poster.getResultX(), poster.getResultY());


        //画测评说明描述
     /*  g.setColor(poster.getResult_contentColor());
       g.setFont(poster.getResult_contentFont());
       g.drawString("结婚苏丹发挥撒旦反对萨苏打法苏丹法苏丹法", poster.getResult_contentX()+25, poster.getResult_contentY());*/


        FontDesignMetrics metrics = FontDesignMetrics.getMetrics(poster.getResult_contentFont());
        String zh = poster.getResult_content();
        String[] rows = makeLineFeed(zh, metrics, 300).split("\n");
        int y = poster.getResult_contentY();
        for (int i = 0; i < rows.length; i++) {
            g.setColor(poster.getResult_contentColor());
            g.setFont(poster.getResult_contentFont());
            if (i > 0) {
                y += 28;
            }
            if (i > 4) {
                break;
            } else if (i == 4) {
                g.drawString(rows[i].substring(0, rows[i].length() - 3) + " ...", poster.getResult_contentX() + 25, y);
                break;
            }
            g.drawString(rows[i], poster.getResult_contentX() + 25, y);

        }


        //画小程序码
        g.drawImage(qrCodeImage.getScaledInstance(poster.getQrCodeWidth(), poster.getQrCodeHeight(), Image.SCALE_SMOOTH),
                poster.getQrCodeX(), poster.getQrCodeY(), null);
        //画tips1
        g.setColor(poster.getTip1Color());
        g.setFont(poster.getTip1Font());
        g.drawString(poster.getTip1(), poster.getTip1X(), poster.getTip1Y());
        //画tips2
        g.setColor(poster.getTip2Color());
        g.setFont(poster.getTip2Font());
        g.drawString(poster.getTip2(), poster.getTip2X(), poster.getTip2Y());
        //画底部栏
        g.setColor(poster.getFooterColor());
        g.fillRect(poster.getFooterX(), poster.getFooterY(), poster.getFooterWidth(), poster.getFooterHeight());
        //画底部栏提示
        g.setColor(poster.getFooterTipColor());
        g.setFont(poster.getFooterTipFont());
        g.drawString(poster.getFooterTip(), poster.getFooterTipX(), poster.getFooterTipY());
        g.dispose();
        File resultImg = new File("D:\\demo.png");
        ImageIO.write(canvas, "png", resultImg);
        //上传服务器代码
        //ByteArrayOutputStream bs = new ByteArrayOutputStream();
        //ImageOutputStream imgOut = ImageIO.createImageOutputStream(bs);
        //ImageIO.write(canvas, "png", imgOut);
        //InputStream inSteam = new ByteArrayInputStream(bs.toByteArray());
        //String url = OSSFactory.build().upload(inSteam, UUID.randomUUID().toString()+".png");
        System.out.println("生成成功！");
        System.out.println("耗时: " + (System.currentTimeMillis() - startTime) / 1000.0 + "s");
        System.out.println("生成文件路径: " + resultImg.getAbsolutePath());
    }


    private static BufferedImage circle(String avatar_img, int width) throws Exception {
        BufferedImage avatar = ImageIO.read(new URL(avatar_img));
        BufferedImage newAvatar = new BufferedImage(width, width, BufferedImage.TYPE_INT_ARGB);
        Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, width, width);
        Graphics2D g2 = newAvatar.createGraphics();
        newAvatar = g2.getDeviceConfiguration().createCompatibleImage(width, width, Transparency.TRANSLUCENT);
        g2 = newAvatar.createGraphics();
        g2.setComposite(AlphaComposite.Clear);
        g2.fill(new Rectangle(newAvatar.getWidth(), newAvatar.getHeight()));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 1.0f));
        g2.setClip(shape);
        // 使用 setRenderingHint 设置抗锯齿
        g2 = newAvatar.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillRoundRect(0, 0, width, width, width, width);
        g2.setComposite(AlphaComposite.SrcIn);
        g2.drawImage(avatar, 0, 0, width, width, null);
        g2.dispose();
        return newAvatar;
    }

    //文本换行处理
    public static String makeLineFeed(String zh, FontDesignMetrics metrics, int max_width) {
        StringBuilder sb = new StringBuilder();
        int line_width = 0;
        for (int i = 0; i < zh.length(); i++) {
            char c = zh.charAt(i);
            sb.append(c);
            // 如果主动换行则跳过
            if (sb.toString().endsWith("\n")) {
                line_width = 0;
                continue;
            }
            // FontDesignMetrics 的 charWidth() 方法可以计算字符的宽度
            int char_width = metrics.charWidth(c);
            line_width += char_width;
            // 如果当前字符的宽度加上之前字符串的已有宽度超出了海报的最大宽度，则换行
            if (line_width >= max_width - char_width) {
                line_width = 0;
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
