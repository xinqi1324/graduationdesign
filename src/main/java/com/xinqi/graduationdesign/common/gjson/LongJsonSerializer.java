package com.xinqi.graduationdesign.common.gjson;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by 李坤 on 2016/12/7.
 */
public class LongJsonSerializer implements JsonSerializer<Long> {
    @Override
    public JsonElement serialize(Long value, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(String.valueOf(value));
    }
}
