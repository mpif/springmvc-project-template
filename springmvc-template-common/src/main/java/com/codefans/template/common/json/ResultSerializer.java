package com.codefans.template.common.json;

import com.codefans.template.common.data.Result;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @Author: ShengzhiCai
 * @Date: 2018-07-10 7:07
 */

public class ResultSerializer extends JsonSerializer<Result> {

    @Override
    public void serialize(Result result, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(result.getValue());
    }


}
