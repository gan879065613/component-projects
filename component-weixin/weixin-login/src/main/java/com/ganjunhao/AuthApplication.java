package com.ganjunhao;

import com.ganjunhao.custom.JustAuthPlatformInfo;
import com.ganjunhao.service.UserService;
import me.zhyd.oauth.cache.AuthCacheConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@ControllerAdvice
@EnableCaching
@SpringBootApplication
public class AuthApplication implements ApplicationRunner {

    @Value("${server.port}")
    public int port;

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        AuthCacheConfig.timeout = 30 * 60 * 1000;
        SpringApplication.run(AuthApplication.class, args);
    }

    @RequestMapping("")
    public ModelAndView index() {
        Map<String, Object> map = new HashMap<>();
        map.put("enableAuthPlatforms", JustAuthPlatformInfo.getPlatformInfos());
        map.put("port", port);
        return new ModelAndView("index", map);
    }

    @RequestMapping("/users")
    public ModelAndView users() {
        Map<String, Object> map = new HashMap<>(1);
        map.put("users", userService.listAll());
        return new ModelAndView("users", map);
    }



    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object handle(Throwable e) {
        e.printStackTrace();
        return e.getMessage();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("已启动： http://localhost:" + port);
    }
}
