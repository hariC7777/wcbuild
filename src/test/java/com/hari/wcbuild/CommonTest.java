package com.hari.wcbuild;

import com.hari.wcbuild.domain.User;
import com.hari.wcbuild.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;

public class CommonTest {

    @Test
    public void testGeneJwt(){
        User user = new User();
        user.setId(999);
        user.setHeadImg("www.xdclass.net");
        user.setName("xd");
        String token = JwtUtils.geneJsonWebToken(user);
        System.out.println(token);
    }

    @Test
    public void testCheck(){

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoYXJpdmlkZW8iLCJpZCI6OTk5LCJuYW1lIjoieGQiLCJpbWciOiJ3d3cueGRjbGFzcy5uZXQiLCJpYXQiOjE1OTU5MTYzNjUsImV4cCI6MTU5NjUyMTE2NX0.3WOu6iEoeQYdNb_z29dDW7vn5tLwKIxuIbmgcDVUVM4";
        Claims claims = JwtUtils.checkJWT(token);
        if(claims != null){
            String name = (String)claims.get("name");
            String img = (String)claims.get("img");
            int id =(Integer) claims.get("id");
            System.out.println(name);
            System.out.println(img);
            System.out.println(id);
        }else{
            System.out.println("非法token");
        }


    }

}
