
// import org.json.simple.JSONObject;
// import org.json.simple.JSONValue;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.util.*;

import java.util.Arrays;
import java.util.Collections;

public class json {

    public static void encode() throws JSONException {
        JSONObject obj = new JSONObject();
        JSONObject subobj = new JSONObject();
        subobj.put("name", "alex");
        subobj.put("age", 1);
        obj.put("hello", "world");
        obj.put("user", subobj);
        obj.put("collection", new JSONArray(Arrays.asList(1, "two", Collections.singletonMap("three", 30))));
        System.out.println("obj.toString() = " + obj.toString());
        System.out.println(obj);
    }

    public static void encodeHashMap() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "jon doe");
        map.put("age", "22");
        map.put("city", "chicago");
        JSONObject jo = new JSONObject(map);
        System.out.println("hashMap->json:");
        System.out.println(jo);
    }

    public static void main(String[] args) {
        try {
            encode();
            encodeHashMap();
            decode();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    public static void decode() throws JSONException {
        String s = """
                {"user":{"name":"Alex","marks":648.50,"roll":12}, "posts":[1,2]}
                """;
        JSONObject obj = new JSONObject(s);

        String pageName = obj.getJSONObject("user").getString("name");
        System.out.println("pageName:" + pageName);

        JSONArray arr = obj.getJSONArray("posts"); // notice that `"posts": [...]`
        System.out.println(arr);

        String name = (String) obj.getJSONObject("user").get("name");
        System.out.println("name:" + name);
    }
}
