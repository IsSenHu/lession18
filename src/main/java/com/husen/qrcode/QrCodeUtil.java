package com.husen.qrcode;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

/**
 * @author 11785
 */
public class QrCodeUtil {
    public static final Logger logger = LogManager.getLogger(QrCodeUtil.class);
    /**
    * 根据内容生成指定宽高、指定格式的二维码图片
    *
    * @param text 内容
    * @param width 宽
    * @param height 高
    * @param format 图片格式
    * @return 生成二维码图片的路径
    * @throws Exception
    */
    public static String generateQRCode(String text, int width, int height, String format) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
        String pathName = "F:/new.png";
        File outputFile = new File(pathName);
        MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
        return pathName;
    }
    /**
     * 随机生成指定长度的验证码
     *
     * @param length 验证码长度
     * @return 生成的验证码
     */
    public static String generateNumCode(int length){
        String val = "";
        String charStr = "char";
        String numStr = "num";
        Random random = new Random();

        //参数length表示生成几位随机数
        for (int i = 0; i < length; i++){
            String charOrNum = random.nextInt(2) % 2 == 0 ? charStr : numStr;
            //输出字母还是数字
            if(charStr.equalsIgnoreCase(charOrNum)){
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            }else if(numStr.equalsIgnoreCase(charOrNum)){
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    /**
     * 解析指定路径下的二维码图片
     *
     * @param filePath 二维码图片路径
     * @return
     */
    public static String parseQRCode(String filePath){
        String content = "";
        try{
            File file = new File(filePath);
            BufferedImage image = ImageIO.read(file);
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map<DecodeHintType, Object> hints = new HashMap<>(10);
            hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
            MultiFormatReader formatReader = new MultiFormatReader();
            Result result = formatReader.decode(binaryBitmap, hints);
            logger.info("result为：" + result.toString());
            logger.info("resultFormat为：" + result.getBarcodeFormat());
            logger.info("resultText为：" + result.getText());
            //设置返回值
            content = result.getText();
        }catch (Exception e){
            e.printStackTrace();
        }
        return content;
    }

    /**
     * 利用Google的ZXing工具包，生成和解析二维码图片
     * <p>
     * Created by Eric on 2017/2/15.
     */
    public static void main(String[] args){
        //二维码图片的宽
        int width = 300;
        //二维码图片的高
        int height = 300;
        //二维码图片的格式
        String format = "png";

        try{
            //生成二维码图片，并返回图片路径
            String pathName = generateQRCode("http://39.108.155.158/work_two/qrcode.do", width, height ,format);
            logger.error("生成二维码的图片路径：" + pathName);
            String content = parseQRCode(pathName);
            logger.error("解析出二维码的图片的内容为：" + content);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
