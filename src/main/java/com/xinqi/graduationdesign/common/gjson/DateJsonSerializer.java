package com.xinqi.graduationdesign.common.gjson;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: xinqi
 * Date: 2017/2/23
 * Time: 8:32
 */
public class DateJsonSerializer implements JsonSerializer<Date> {
    @Override
    public JsonElement serialize(Date date, Type type, JsonSerializationContext jsonSerializationContext) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return new JsonPrimitive(sdf.format(date));
    }
}
