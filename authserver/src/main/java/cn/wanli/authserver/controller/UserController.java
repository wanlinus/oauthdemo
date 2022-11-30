package cn.wanli.authserver.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("authorized")
    public void authorized(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        System.out.println(contextPath);
    }

    @GetMapping("/user")
//    @PreAuthorize("hasRole('user')")
    public JSONObject userInfo(Authentication authentication) {
        log.info("userinfo:[{}]", JSON.toJSONString(authentication));
        String name = authentication.getName();
        JSONObject json = new JSONObject();
        json.put("username", name);
        return json;
    }

}
