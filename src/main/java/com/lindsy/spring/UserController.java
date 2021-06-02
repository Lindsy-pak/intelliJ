package com.lindsy.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user") /* 1차 주소값 아무것도 안적으면 자동으로 get방식으로 날아간다*/
public class UserController {

    @Autowired /* UserService 타입의 service 객체 주소값을 자동으로 연결시켜준다 */
    private UserService service;

//    method=RequestMethod.POST : post방식으로 보낼때 적어준다.
    @RequestMapping("/login") /* 2차 주소값*/
    public String login() {
        return "user/login";
    }

    @RequestMapping(value="/join") /* get방식 */
    public String join() {
        return "user/join";
    }

    @RequestMapping(value="/join", method= RequestMethod.POST)
    public String join(UserEntity param) {
        System.out.println("uid" + param);
        service.join(param);
        return "redirect:/user/login";/* redirect -> response.sendRedirect의 기능을 한다.*/
    }
}
