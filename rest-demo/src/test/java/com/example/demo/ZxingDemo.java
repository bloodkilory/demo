package com.example.demo;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * @author yangkun
 * generate on 2017/2/17
 */
public class ZxingDemo {

    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String IMAGE_FORMAT_NAME_JPG = "jpg";
    private static final String IMAGE_FORMAT_NAME_PNG = "png";
    private static final int QR_CODE_SIZE = 600;
    private static final int INSERT_LOGO_WIDTH = 60;
    private static final int INSERT_LOGO_HEIGHT = 60;

    public static void main(String[] args) throws Exception {
        String text = "https://wtest.133.cn/hangban/webpay?partner=huolihotel&token=732de43cf0a71be7bf037665a57f6f19&orderId=170591448028955&ptOrderId=1957981315&orderType=2&ru=http://localhost:15080/index.html#myOrderDetail/1957981315/-10001/membercard?auth=true&wechatKey=weixin";
        String text2 = "http://www.baidu.com";
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, DEFAULT_CHARSET);
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q);
        hints.put(EncodeHintType.MARGIN, 0);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, QR_CODE_SIZE, QR_CODE_SIZE, hints);
//        bitMatrix = deleteWhite(bitMatrix);
        File outputFile = new File("/Users/bloodkilory/Desktop/new1.png");
        int imagWid = bitMatrix.getWidth();
        int imgHei = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(imagWid, imgHei, BufferedImage.TYPE_INT_RGB);
        for(int x = 0; x < imagWid; x++) {
            for(int y = 0; y < imgHei; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
            }
        }
        image = transferBackgroundAlpha(image);
        ImageIO.write(image, IMAGE_FORMAT_NAME_PNG, outputFile);
        //将图片转成byte[]并上传
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ImageIO.write(image, format, baos);
//        baos.flush();
//        byte[] imageInByte = baos.toByteArray();
//        String url = QiniuUtil.upload(imageInByte, UUID.randomUUID().toString().replaceAll("-", ""));
//        baos.close();
//        return url;
    }

    private static BitMatrix deleteWhite(BitMatrix matrix) {
        int[] rec = matrix.getEnclosingRectangle();
        int resWidth = rec[2] + 1;
        int resHeight = rec[3] + 1;

        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
        resMatrix.clear();
        for(int i = 0; i < resWidth; i++) {
            for(int j = 0; j < resHeight; j++) {
                if(matrix.get(i + rec[0], j + rec[1]))
                    resMatrix.set(i, j);
            }
        }
        return resMatrix;
    }

    /**
     * 将图片背景透明化处理
     *
     * @param image
     * @return
     * @throws Exception
     */
    private static BufferedImage transferBackgroundAlpha(Image image) throws Exception {
        ImageIcon imageIcon = new ImageIcon(image);
        BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();
        g2D.drawImage(imageIcon.getImage(), 0, 0, imageIcon.getImageObserver());
        int alpha = 0;
        for(int j1 = bufferedImage.getMinY(); j1 < bufferedImage.getHeight(); j1++) {
            for(int j2 = bufferedImage.getMinX(); j2 < bufferedImage.getWidth(); j2++) {
                int rgb = bufferedImage.getRGB(j2, j1);
                int R = (rgb & 0xff0000) >> 16;
                int G = (rgb & 0xff00) >> 8;
                int B = (rgb & 0xff);
                if(((255 - R) < 30) && ((255 - G) < 30) && ((255 - B) < 30)) {
                    rgb = ((alpha + 1) << 24) | (rgb & 0x00ffffff);
                }
                bufferedImage.setRGB(j2, j1, rgb);
            }
        }
        g2D.drawImage(bufferedImage, 0, 0, imageIcon.getImageObserver());
        return bufferedImage;
    }

    /**
     * 插入LOGO
     *
     * @param source
     * @param imgPath
     * @param needCompress
     * @throws Exception
     */
    private static void insertLogoImage(BufferedImage source, String imgPath, boolean needCompress) throws Exception {
        File file = new File(imgPath);
        if(!file.exists()) {
            System.err.println("" + imgPath + " 该文件不存在！");
            return;
        }
        Image src = ImageIO.read(new File(imgPath));
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        if(needCompress) { // 压缩LOGO
            if(width > INSERT_LOGO_WIDTH) {
                width = INSERT_LOGO_WIDTH;
            }
            if(height > INSERT_LOGO_HEIGHT) {
                height = INSERT_LOGO_HEIGHT;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics graph = bufferedImage.getGraphics();
            graph.drawImage(image, 0, 0, null); // 绘制缩小后的图
            graph.dispose();
            src = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (QR_CODE_SIZE - width) / 2;
        int y = (QR_CODE_SIZE - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }

}
