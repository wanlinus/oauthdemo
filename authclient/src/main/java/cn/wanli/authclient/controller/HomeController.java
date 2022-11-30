package cn.wanli.authclient.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Slf4j
@Controller
@RequestMapping
public class HomeController {

//    @GetMapping("/")
//    public void index(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
//                      @AuthenticationPrincipal OAuth2User oauth2User) {
//        System.out.println(authorizedClient.getAccessToken());
//        log.info("user[{}]", JSON.toJSONString(oauth2User));
//    }

    @GetMapping("/")
    public String home(Principal principal, Model model) {
        model.addAttribute("user", principal);
        return "index";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello(Principal principal) {
        return "Hello," + principal.getName();
    }
}
