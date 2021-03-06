package com.example.jyuen.baseproject.model.flickr;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class ContentDeserializer<T> implements JsonDeserializer<T> {
    @Override
    public T deserialize (JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonElement object = json.getAsJsonObject().get("photos");
        return new Gson().fromJson(object, typeOfT);
    }
}
