package net.dubbo.common.utils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import net.dubbo.common.model.ErrorResponse;

import java.io.IOException;

/**
 * @author lishulong.never@gmail.com
 * @date 2017/12/28
 * @doing
 */
public class JsonUtil {

    private static ObjectMapper mapper = null;

    private static Boolean singleInstance = true;


    private static void getObjectMapperInstance() {
        if (singleInstance) {
            if (mapper == null)
                synchronized (JsonUtil.class) {
                    if (mapper == null)
                        mapper = new ObjectMapper();
                }
        } else
            mapper = new ObjectMapper();
    }

    /**
     * @param arg object JsonRoot
     * @return string
     * @throws JsonProcessingException
     */
    public static String convertObject2String(Object arg) throws JsonProcessingException {
        getObjectMapperInstance();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
        return mapper.writeValueAsString(arg);
    }

    /**
     * not content jsonroot
     * @param content
     * @param ags1
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T convertString2Object(String content, Class<T> ags1) throws IOException {
        getObjectMapperInstance();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
        T t = mapper.readValue(content, ags1);
        return t;
    }


    /***
     * get json Node
     * @param content
     * @param key
     * @return
     * @throws IOException
     */
    public static JsonNode getJsonNodeByKey(String content, String key) throws IOException {
        getObjectMapperInstance();
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false);
        JsonNode jsonNode = mapper.readTree(content);
        return jsonNode.findValue(key);
    }


    /**
     * vs fastJson
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {


        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setCode("400");
        errorResponse.setMsg("bad request");
        errorResponse.setSub_code("500");
        errorResponse.setSub_msg("server error");

        String s = JsonUtil.convertObject2String(errorResponse);
        System.out.println(s);

        ErrorResponse errorResponse1 = convertString2Object(s, ErrorResponse.class);
//        ErrorResponse errorResponse1 = convertString2Object(JSON.toJSON(errorResponse).toString(), ErrorResponse.class);

        System.out.println(errorResponse1.getCode());
        System.out.println(errorResponse1.getMsg());

        System.out.println(JSON.toJSON(errorResponse));
        JsonNode msg = JsonUtil.getJsonNodeByKey(s, "msg");
        System.out.println(msg);
        System.out.println(msg.asText());

    }
}
