package com.boollean.Utils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetRequestBodyUtils {
    public static String getRequestJsonString(HttpServletRequest request) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));
        String s = "";
        String jsonString = "";
        while ((s = reader.readLine())!=null){
            jsonString += s;
            System.out.println(jsonString);
        }
        return jsonString;
    }
}
