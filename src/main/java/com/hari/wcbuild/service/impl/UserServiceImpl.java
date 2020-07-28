package com.hari.wcbuild.service.impl;

import com.hari.wcbuild.config.WeChatConfig;
import com.hari.wcbuild.domain.User;
import com.hari.wcbuild.mapper.UserMapper;
import com.hari.wcbuild.service.UserService;
import com.hari.wcbuild.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

public class UserServiceImpl implements UserService {


    @Autowired
    private WeChatConfig weChatConfig;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User saveWeChatUser(String code) {
        String accessTokenUrl = String.format(WeChatConfig.OPEN_USER_INFO_URL,weChatConfig.getOpenAppid(),weChatConfig.getOpenAppsecret(),code);

        //获取access_token
        Map<String ,Object> baseMap =  HttpUtils.doGet(accessTokenUrl);

        if(baseMap == null || baseMap.isEmpty()){ return  null; }
        String accessToken = (String)baseMap.get("access_token");
        String openId  = (String) baseMap.get("openid");

        User dbUser = userMapper.findByopenid(openId);

        if(dbUser!=null) { //更新用户，直接返回
            return dbUser;
        }

        //获取用户基本信息
        String userInfoUrl = String.format(WeChatConfig.OPEN_USER_INFO_URL,accessToken,openId);
        //获取access_token
        Map<String ,Object> baseUserMap =  HttpUtils.doGet(userInfoUrl);

        if(baseUserMap == null || baseUserMap.isEmpty()){ return  null; }
        String nickname = (String)baseUserMap.get("nickname");

        Double sexTemp  = (Double) baseUserMap.get("sex");
        int sex = sexTemp.intValue();
        String province = (String)baseUserMap.get("province");
        String city = (String)baseUserMap.get("city");
        String country = (String)baseUserMap.get("country");
        String headimgurl = (String)baseUserMap.get("headimgurl");
        StringBuilder sb = new StringBuilder(country).append("||").append(province).append("||").append(city);
        String finalAddress = sb.toString();
        try {
            //解决乱码
            nickname = new String(nickname.getBytes("ISO-8859-1"), "UTF-8");
            finalAddress = new String(finalAddress.getBytes("ISO-8859-1"), "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        User user = new User();
        user.setName(nickname);
        user.setHeadImg(headimgurl);
        user.setCity(finalAddress);
        user.setOpenid(openId);
        user.setSex(sex);
        user.setCreateTime(new Date());
        userMapper.save(user);
        return user;
    }
}
