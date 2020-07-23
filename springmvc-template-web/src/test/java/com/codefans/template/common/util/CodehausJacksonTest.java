//package com.codefans.template.common.util;
//
//import com.codefans.template.common.data.Result;
//import com.codefans.template.web.convert.CustomObjectMapper;
//import org.codehaus.jackson.JsonGenerationException;
//import org.codehaus.jackson.JsonGenerator;
//import org.codehaus.jackson.map.DeserializationConfig;
//import org.codehaus.jackson.map.JsonMappingException;
//import org.junit.Test;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.StringWriter;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @Author: codefans
// * @Date: 2018-07-09 20:38
// */
//
//public class CodehausJacksonTest {
//
//    CustomObjectMapper objectMapper = new CustomObjectMapper();
//
//    @Test
//    public void obj2json() {
//
//        Boolean trueFlag = new Boolean(true);
//        String value = writeValue(trueFlag);
//        System.out.println("Boolean:" + value);
//
//        System.out.println("Result:" + writeValue(Result.UPDATEPWD));
//
//        System.out.println("Date:" + writeValue(new Date()));
//
//    }
//
//    public String writeValue(Object entity) throws RuntimeException {
//        return writeValue(entity, false);
//    }
//
//    public String writeValue(Object entity, boolean prettyPrint) throws RuntimeException {
//        try {
//            StringWriter e = new StringWriter();
//            JsonGenerator jg = objectMapper.getJsonFactory().createJsonGenerator(e);
//            if(prettyPrint) {
//                jg.useDefaultPrettyPrinter();
//            }
//
//            objectMapper.writeValue(jg, entity);
//            return e.toString();
//        } catch (JsonGenerationException var4) {
//            var4.printStackTrace();
//            throw new RuntimeException(var4);
//        } catch (JsonMappingException var5) {
//            var5.printStackTrace();
//            throw new RuntimeException(var5);
//        } catch (IOException var6) {
//            var6.printStackTrace();
//            throw new RuntimeException(var6);
//        }
//    }
//
//
//
//
//
//}
