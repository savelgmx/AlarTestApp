package com.example.alartestapp.api;

import com.example.alartestapp.model.AuthResponse;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class AuthResponseDeserializer {
    //@Override
    public AuthResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson = new Gson();

        final JsonObject jsonObject = json.getAsJsonObject();
        final JsonElement jsonData = jsonObject.get("data");
        final AuthResponse authResponse = gson.fromJson(jsonData.toString(), AuthResponse.class);

        return authResponse;
    }

}
