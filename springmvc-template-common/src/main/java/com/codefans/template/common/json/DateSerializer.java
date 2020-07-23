package com.codefans.template.common.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: ShengzhiCai
 * @Date: 2018-07-10 7:09
 */

public class DateSerializer extends JsonSerializer<Date> {

    private String pattern = "yyyy-MM-dd HH:mm:ss";

    public DateSerializer() {

    }

    public DateSerializer(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        jsonGenerator.writeString(sdf.format(date));
    }
}
