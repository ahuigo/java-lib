package jproj.var.str.json;

import com.google.gson.Gson;

public class marshal {
    public static void main(String[] args) {
        String json = "{\"name\":\"John Doe\",\"age\":30,\"email\":\"johndoe@example.com\"}";

        Gson gson = new Gson();
        UserInfo userInfo = gson.fromJson(json, UserInfo.class);

        System.out.println(userInfo.getName());
        System.out.println(userInfo.getAge());
        System.out.println(userInfo.getEmail());

        String json2 = gson.toJson(userInfo);
        System.out.println(json2);
    }
}

class UserInfo {
    private String name;
    private int age;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}