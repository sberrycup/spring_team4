package com.bitc.java505_team4.controller;

import com.bitc.java505_team4.dto.UserDto;
import com.bitc.java505_team4.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    public String doLogin() throws Exception {
        return "login/login";
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
//    input태그의 name속성의 불러옴
    public String doLoginProcess(@RequestParam("memberEmail") String memberEmail, @RequestParam("memberPw") String memberPw, HttpServletRequest req) throws Exception {
        int result = userService.isUserInfo(memberEmail, memberPw);

        if (result == 1) {
            UserDto userInfo = userService.getUserInfo(memberEmail);

            HttpSession session = req.getSession();

            session.setAttribute("memberEmail", userInfo.getMemberEmail());
            session.setAttribute("memberName", userInfo.getMemberName());
//            관리자인지 확인하기 위해서 sql고치고 가져옴
            session.setAttribute("adminYn",userInfo.getAdminYn());

            return "redirect:/login/loginOK.do";
        }else {
            return "redirect:/login/loginFail.do";
        }
    }

    @GetMapping("/loginOK.do")
    public ModelAndView doLoginOK(HttpServletRequest req) throws Exception {
        ModelAndView mv = new ModelAndView("login/loginOK");

        HttpSession session = req.getSession();

        UserDto user = new UserDto();
        user.setMemberEmail((String)session.getAttribute("memberEmail"));
        user.setMemberName((String)session.getAttribute("memberName"));
        user.setAdminYn((String)session.getAttribute("adminYn"));

        mv.addObject("userInfo", user);

        return mv;
    }

//    로그인실패시 html에 toast메세지만 띄우고 바로 로그인페이지로 이동함
    @GetMapping("/loginFail.do")
    public String doLoginFail() throws Exception {
        return "login/loginFail";
    }

//    세션 삭제를 위한 로그아웃임
    @RequestMapping(value = "/logout.do", method = RequestMethod.GET)
    public String doLogout(HttpServletRequest req) throws Exception {
        HttpSession session = req.getSession();

        session.removeAttribute("memberEmail");
        session.removeAttribute("memberName");
        session.removeAttribute("adminYn");

        session.invalidate();

//        return값을 main page로 이동 후 login/logout.html 제거(현재값 : 임시)
        return "login/logout";
    }

    @RequestMapping(value = "/joinMembership.do", method = RequestMethod.GET)
    public String doJoinMembership() throws Exception{
        return "login/member";
    }

    @RequestMapping(value = "/joinMembership.do", method = RequestMethod.POST)
    public String resultJoinMembership(UserDto userDto) throws Exception{
        userService.insertMembership(userDto);
        return "login/login";
    }

//    @RequestMapping(value = "", method = RequestMethod.DELETE)
//    public String
}
