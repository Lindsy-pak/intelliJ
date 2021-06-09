package com.lindsy.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user") /* 1차 주소값 아무것도 안적으면 자동으로 get방식으로 날아간다*/
public class UserController {

    @Autowired /* UserService 타입의 service 객체 주소값을 자동으로 연결시켜준다 */
    private UserService service;

    @Autowired
    private HttpSession session;

//    method=RequestMethod.POST : post방식으로 보낼때 적어준다.
    @RequestMapping("/login") /* 2차 주소값*/
    public String login(@RequestParam(value = "err", defaultValue = "0") int err, Model model) {
        switch (err) { //case 0:은 처리해줄 필요 없다. 
            case 1: //아이디 없음
                model.addAttribute("errMsg", "아이디를 확인 해 주세요"); //request.setAttribute("errMsg", "아이디를 확인 해 주세요");
                break;
            case 2: //비밀번호 틀림
                model.addAttribute("errMsg", "비밀번호를 확인 해 주세요");
                break;
        }
        return "user/login";
    }

    @RequestMapping(value = "/login", method=RequestMethod.POST)
    public String login(UserEntity param) {
        return "redirect:" + service.login(param);
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

    @GetMapping("/logout")
    public String logout(HttpSession hs, HttpServletRequest req) {
        hs.invalidate();
        String referer = req.getHeader("Referer");/* referer : 전페이지 어디서 왓는지 */
        return "redirect:" + referer;
        //return "redirect:/user/login";
    }

    @GetMapping("profile")
    public void profile() {

    }
}
