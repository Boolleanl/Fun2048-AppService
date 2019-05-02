package com.boollean.Utils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Boollean
 */
public class GetRequestBodyUtils {

    /**
     * 将请求里的JSON字段提取出来
     * @param request 请求
     * @return 提取出的JSON字段
     * @throws IOException
     */
    public static String getRequestJsonString(HttpServletRequest request) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
        String s = "";
        String jsonString = "";
        while ((s = reader.readLine()) != null) {
            jsonString += s;
        }
        return jsonString;
    }
}
