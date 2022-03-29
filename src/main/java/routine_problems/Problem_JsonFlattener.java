package routine_problems;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.wnameless.json.flattener.JsonFlattener;
import java.util.HashMap;
import java.util.Map;

//JSON格式转换
//在某个特定应用场景中，我们有一个从JSON获取的内容，比如：
//m = { "a": 1, "b": { "c": 2, "d": [3,4] } }
//现在需要把这个层级的结构做展开，只保留一层key/value结构。对于上述输入，需要得到的结构是：
//o = {"a": 1, "b.c": 2, "b.d": [3,4] }
//也就是说，原来需要通过 m["b"]["c"] 访问的值，在展开后可以通过 o["b.c"] 访问。
//请实现这个“层级结构展开”的代码。
//输入：任意JSON（或者map/dict）
//输出：展开后的JSON（或者map/dict）
public class Problem_JsonFlattener {

    public static void main(String[] args) {
        String s = "{'a':1, 'b': {'c':{'e':5,'f':['x','y']}, 'd':[3, 4]}}";
        System.out.println(convert(s));
    }

    //json: 输入的json转成的map，如{'a':1, 'b': {'c':{'e':5}, 'd':[3, 4]}}
    //ans {'a':1, 'b.c.e': 5, 'b.d':[3, 4]}}
    //ans：收集最终的答案（每一步设置key value）
    public static void func(Map<String, Object> json, HashMap<String, Object> ans) {
        for (String key : json.keySet()) {
            if (json.get(key) instanceof Map) {
                Map<String, Object> cur = (Map<String, Object>) json.get(key);
                Map<String, Object> newJson = new HashMap<>();
                for (String k : cur.keySet()) {
                    newJson.put(key + "." + k, cur.get(k));
                }
                func(newJson, ans);
            } else {
                ans.put(key, json.get(key));
            }
        }
    }

    //主函数
    public static String convert(String s) {
        HashMap<String, Object> collect = new HashMap<>();
        Map<String, Object> json = JSON.parseObject(s, Map.class);
        func(json, collect);
        return new JSONObject(collect).toString();
    }

    /**
     * json 扁平化
     */
    public static void jsonFlatten(String jsonStr) {
        JSONObject jsonObj = JSONObject.parseObject(jsonStr);
        Map<String, Object> flatMap = JsonFlattener.flattenAsMap(jsonObj.toString());
        for (Map.Entry<String, Object> entry : flatMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

