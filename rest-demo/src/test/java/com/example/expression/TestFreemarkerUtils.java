package com.example.expression;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import freemarker.template.TemplateException;

import com.example.util.FreemarkerUtils;

/**
 * Created by Liwh on 2016/4/21.
 */
@SuppressWarnings("unchecked")
public class TestFreemarkerUtils {

    @Test
    public void testFreemarker(){

        try {

            Map map = new HashMap<>();
            map.put("price",new BigDecimal("9.9"));
            map.put("quantity", 2);
            //打折 打0.8折扣
            String priceExpression ="price*0.8";
            String out =     FreemarkerUtils.process("${("+priceExpression+")}",map);
            System.out.println(out);

            //满减 每满80减10块
            map.put("price",280.56);
            priceExpression = "price-(price/80)?int*10";

            out =     FreemarkerUtils.process("${("+priceExpression+")}",map);
            System.out.println(out);

            //5件以上 每满80 减10元
            map.put("quantity", 6);
            priceExpression = " (quantity>5 && price>80 )?string('${price-(price/80)?int*10}','${price}')  ";
            out = FreemarkerUtils.process("${("+priceExpression+")}",map);
            System.out.println(out);

        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }

    }

}
