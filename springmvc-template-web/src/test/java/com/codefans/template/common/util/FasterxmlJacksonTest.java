package com.codefans.template.common.util;

import com.codefans.template.common.data.Result;
import com.codefans.template.web.convert.FastxmlObjectMapper;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonBooleanFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: codefans
 * @Date: 2018-07-09 20:38
 */

public class FasterxmlJacksonTest {

    public final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";
    FastxmlObjectMapper objectMapper = new FastxmlObjectMapper();

    @Before
    public void before() {
//        objectMapper.disable(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS);
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        objectMapper.enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES);
//        objectMapper.enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);
//        objectMapper.enable(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS);
//        objectMapper.enable(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER);
//        objectMapper.enable(JsonParser.Feature.ALLOW_COMMENTS);
//        objectMapper.setDateFormat(new SimpleDateFormat(DATEFORMAT));
    }

    @Test
    public void obj2json() {

        Boolean trueFlag = new Boolean(true);
        String value = writeValue(trueFlag);
        System.out.println("Boolean:" + value);

        System.out.println("Result:" + writeValue(Result.UPDATEPWD));

        System.out.println("Date:" + writeValue(new Date()));

//        JsonBooleanFormatVisitor booleanFormatVisitor = new JsonBooleanFormatVisitor.Base();
//        JsonValueFormat jsonValueFormat = JsonValueFormat.DATE;
//        booleanFormatVisitor.format(jsonValueFormat);
//        System.out.println(jsonValueFormat.toString());


    }

    public String writeValue(Object entity) throws RuntimeException {
        return writeValue(entity, false);
    }

    public String writeValue(Object entity, boolean prettyPrint) throws RuntimeException {
        try {
            StringWriter e = new StringWriter();
            JsonGenerator jg = objectMapper.getJsonFactory().createJsonGenerator(e);
            if(prettyPrint) {
                jg.useDefaultPrettyPrinter();
            }
//            objectMapper.getSerializerProviderInstance();

            objectMapper.writeValue(jg, entity);
            return e.toString();
        } catch (JsonGenerationException var4) {
            var4.printStackTrace();
            throw new RuntimeException(var4);
        } catch (JsonMappingException var5) {
            var5.printStackTrace();
            throw new RuntimeException(var5);
        } catch (IOException var6) {
            var6.printStackTrace();
            throw new RuntimeException(var6);
        }
    }

}
