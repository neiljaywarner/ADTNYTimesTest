package com.neiljaywarner.adtnytimestest.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by neil on 2/25/16.
 */
public class DateTimeDeserializer implements JsonDeserializer<MultimediaItem> {
    public MultimediaItem deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return new MultimediaItem(json.getAsJsonPrimitive().getAsString());
    }
}