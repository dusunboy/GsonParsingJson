package com.example;

import com.example.model.BaseJsonBean;
import com.example.type_builder.TypeBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/16.
 */

public class BaseJsonBeanDeserializer<T> implements JsonDeserializer<BaseJsonBean> {


    private final Class<T> aClass;

    public BaseJsonBeanDeserializer(Class<T> aClass) {
        this.aClass = aClass;
    }

    @Override
    public BaseJsonBean deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        BaseJsonBean baseJsonBean = new BaseJsonBean();
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement jsonDataElement = jsonObject.get("data");
        baseJsonBean.setStatus(jsonObject.get("status").getAsString());
        baseJsonBean.setMessage(jsonObject.get("message").getAsString());
        if (jsonDataElement.isJsonArray()) {
            baseJsonBean.setData(null);
            baseJsonBean.setDataList(fromJson2Array(gson, jsonDataElement, aClass));
        } else {
            baseJsonBean.setData(fromJson2Object(gson, jsonDataElement, aClass));
            baseJsonBean.setDataList(null);
        }
        return baseJsonBean;
    }

    /**
     * json转为数组实体
     *
     * @param gson
     * @param jsonResultElement
     * @param aClass
     * @param <T>
     * @return
     */
    private <T> ArrayList<T> fromJson2Array(Gson gson, JsonElement jsonResultElement, Class<T> aClass) {
        Type type = TypeBuilder
                .newInstance(ArrayList.class)
                .addTypeParam(aClass)
                .build();
        return gson.fromJson(jsonResultElement, type);
    }

    /**
     * json转为实体
     *
     * @param gson
     * @param jsonResultElement
     * @param aClass
     * @param <T>
     * @return
     */
    private <T> T fromJson2Object(Gson gson, JsonElement jsonResultElement, Class<T> aClass) {
        return gson.fromJson(jsonResultElement, aClass);
    }

}
