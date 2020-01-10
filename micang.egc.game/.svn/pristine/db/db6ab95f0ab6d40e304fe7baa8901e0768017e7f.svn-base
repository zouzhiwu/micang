package com.json.test;

import java.io.IOException;

import com.game.entity.ResultGroupInfo;
import com.game.helper.JsonMapper;

public class JsonDemo {
    public static void main(String[] args) throws IOException {
        //对于复杂的list，要先转换为map集合，再转为对象
        String jsonStr = "{\"tinfos\":[{\"icon\":null,\"announcement\":null,\"updatetime\":1558321687007,\"muteType\":0,\"uptinfomode\":0,\"maxusers\":200,\"intro\":null,\"size\":1,\"createtime\":1558321687007,\"upcustommode\":0,\"owner\":\"1001\",\"tname\":\"testGroup\",\"beinvitemode\":0,\"joinmode\":0,\"tid\":2550735096,\"members\":[],\"invitemode\":0,\"mute\":false}],\"code\":200}";
//        JavaType javaType = JsonMapper.getInstance().constructParametricType(ArrayList.class, ResultGroup.class);
        ResultGroupInfo rg = JsonMapper.getInstance().fromJson(jsonStr, ResultGroupInfo.class);
    	System.out.println(rg);
    }

}
