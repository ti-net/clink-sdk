package com.tinet.clink.openapi.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.util.*;

/**
 * ClassFor:
 * jsonUtils
 *
 * @author yinzk
 * @date 2019/04/11
 */
@SuppressWarnings("unused")
public class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER;
    private static final TypeFactory TYPE_FACTORY;

    static {
        //解决 json中存在 反序列化对象中不存在的字段，反序列化报错的问题。
        OBJECT_MAPPER = new ObjectMapper();
        TYPE_FACTORY = OBJECT_MAPPER.getTypeFactory();
        OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT).enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    }

    public static MapType STRING_VALUE_MAP_TYPE = TYPE_FACTORY.constructMapType(HashMap.class, String.class, String.class);
    public static MapType OBJECT_VALUE_MAP_TYPE = TYPE_FACTORY.constructMapType(HashMap.class, String.class, Object.class);
    public static MapType OBJECT_VALUE_TREE_MAP_TYPE = TYPE_FACTORY.constructMapType(TreeMap.class, String.class, Object.class);

    /**
     * 获取 json操作对象
     */
    public static ObjectMapper objectMapper() {
        return OBJECT_MAPPER;
    }

    /**
     * 类型工厂
     */
    public static TypeFactory typeFactory() {
        return TYPE_FACTORY;
    }


    /**
     * 创建ObjectNode
     *
     * @return 可操作的空ObjectNode
     */
    public static ObjectNode getJsonObject() {
        return OBJECT_MAPPER.createObjectNode();
    }

    /**
     * 将字符串转化为jsonNode (属性不可控)
     *
     * @param text 字符串
     */
    public static JsonNode parse(String text) {
        try {
            return OBJECT_MAPPER.readTree(text);
        } catch (IOException e) {
            System.err.println("parse text error: " + e.getMessage());
        }
        return null;
    }

    /**
     * json字符串转JsonNode
     *
     * @param jsonStr json字符串
     * @return json字符串对应的jsonNode
     */
    public static JsonNode parseJsonNode(String jsonStr) {

        if (Objects.isNull(jsonStr) || jsonStr.length() < 1) {
            return null;
        }

        JsonNode paramNode = null;
        try {
            paramNode = OBJECT_MAPPER.readTree(jsonStr);
        } catch (IOException e) {
            System.err.println("deserialization asr exception" + e.getMessage());
        }
        return paramNode;

    }

    /**
     * 将字符串转化为ArrayNode d对象
     *
     * @param text 字符串
     */
    public static ArrayNode parse2ArrayNode(String text) {
        try {
            JsonNode jsonNode = OBJECT_MAPPER.readTree(text);
            if (jsonNode.isArray()) {
                return (ArrayNode) jsonNode;
            } else {
                System.err.printf("json string %s is not array json\n", text);
            }
        } catch (IOException e) {
            System.err.println("parse2ArrayNode error: " + e.getMessage());
        }
        return null;
    }


    /**
     * 字符串转为为对象
     *
     * @param text  json字符串
     * @param clazz 类
     * @param <T>   类泛型
     */
    public static <T> T parseObject(String text, Class<T> clazz) {
        if (Objects.isNull(text) || text.length() < 1 || Objects.isNull(clazz)) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(text, clazz);
        } catch (IOException e) {
            System.err.printf("parseObject error: %s\n", e.getMessage());
        }
        return null;
    }

    /**
     * 将字符串转化为jsonNode (属性可控)
     *
     * @param text json字符串
     */
    public static ObjectNode parse2ObjectNode(String text) {
        try {
            JsonNode jsonNode = OBJECT_MAPPER.readTree(text);
            if (!jsonNode.isObject()) {
                System.err.printf("json string %s is not is an object\n", text);
            }
            return (ObjectNode) jsonNode;
        } catch (IOException e) {
            System.err.printf("parse2ObjectNode error: %s\n", e.getMessage());
        }
        return null;
    }

    /**
     * 列表字符串转化为列表对象
     *
     * @param text  对象列表json字符串
     * @param clazz T 类型对应的类
     * @param <T>   单独的类型
     */
    public static <T> List<T> parseArray(String text, Class<T> clazz) {
        try {
            CollectionType javaType = OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, clazz);
            return OBJECT_MAPPER.readValue(text, javaType);
        } catch (IOException e) {
            System.err.printf("parseArray error: %s\n", e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * @param text  对象数组json字符串
     * @param clazz T 类型对应的类
     * @param <T>   对象对应的类型
     * @param type  转化方式：1：下划线，0 or null：驼峰
     */
    public synchronized static <T> List<T> parseArray(String text, Class<T> clazz, Integer type) {
        try {
            if (null != type && type == 1) {
                OBJECT_MAPPER.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
            }
            CollectionType javaType = OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, clazz);
            List<T> result = OBJECT_MAPPER.readValue(text, javaType);
            if (null != type && type == 1) {
                OBJECT_MAPPER.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
            }
            return result;
        } catch (IOException e) {
            System.err.printf("parseArray error: %s\n", e.getMessage());
        }
        return null;
    }


    /**
     * 将json字符串转化为对象
     *
     * @param jsonStr      json字符传
     * @param valueTypeRef T对应的类型
     * @param <T>          T 泛型
     */

    public static <T> T readValue(String jsonStr, TypeReference<T> valueTypeRef) {

        if (Objects.isNull(jsonStr) || jsonStr.length() < 1) {
            return null;
        }

        try {
            return OBJECT_MAPPER.readValue(jsonStr, valueTypeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 将json字符串转化为对象
     *
     * @param jsonStr  json字符传
     * @param javaType T对应的类型
     * @param <T>      T 泛型
     */

    public static <T> T readValue(String jsonStr, JavaType javaType) {

        if (Objects.isNull(jsonStr) || jsonStr.length() < 1) {
            return null;
        }

        try {
            return OBJECT_MAPPER.readValue(jsonStr, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 将字符串转换为对象
     * 配合 @JsonIgnoreProperties(ignoreUnknown = true) 解决
     * 反序列化对象中存在json中不存在的字段，导致反序列化报错的问题。
     *
     * @param jsonStr   json字符串
     * @param valueType T对应的类
     * @param <T>       类型
     */

    public static <T> T readValue(String jsonStr, Class<T> valueType) {

        if (Objects.isNull(jsonStr) || jsonStr.length() < 1) {
            return null;
        }

        try {
            return OBJECT_MAPPER.readValue(jsonStr, valueType);
        } catch (IOException e) {
            System.err.printf("json readValue error ,%s, %s\n", jsonStr, e.getMessage());
        }

        return null;
    }

    /**
     * 将封装好的对象转化为字符串
     *
     * @param object       对象实体
     * @param prettyFormat 是否进行完美格式化 true:进行格式化; false:不进行格式化
     */

    public static String toJson(Object object, boolean prettyFormat) {
        try {
            if (prettyFormat) {
                return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            } else {
                return OBJECT_MAPPER.writeValueAsString(object);
            }
        } catch (JsonProcessingException e) {
            System.err.printf("toJsonString error: %s\n", e.getMessage());
        }
        return null;
    }

    /**
     * 将对象转化json字符串
     *
     * @param obj 对象实体
     */

    public static String toJson(Object obj) {

        if (Objects.isNull(obj)) {
            return null;
        }

        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }

    }


}
