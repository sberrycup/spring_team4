package com.bitc.java505_team4.controller;

import com.bitc.java505_team4.dto.UserDto;
import com.bitc.java505_team4.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    public String doLogin() throws Exception {
        return "login/login";
    }

    @ResponseBody
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
//    input태그의 name속성의 불러옴
    public Object doLoginProcess(@RequestParam("memberEmail") String memberEmail, @RequestParam("memberPw") String memberPw, HttpServletRequest req) throws Exception {
        Map<String, String> resultData = new HashMap<>();
        int result = userService.isUserInfo(memberEmail, memberPw);

        if (result == 1) {
            UserDto userInfo = userService.getUserInfo(memberEmail);

            HttpSession session = req.getSession();

            session.setAttribute("memberEmail", userInfo.getMemberEmail());
            session.setAttribute("memberName", userInfo.getMemberName());
//            관리자인지 확인하기 위해서 sql고치고 가져옴
            session.setAttribute("adminYn",userInfo.getAdminYn());

//            hash맵을 사용해서 ajax 사용
            resultData.put("result", "success");
        }else {
            resultData.put("result", "fail");
            resultData.put("message", "로그인에 실패하였습니다.");
        }
        return resultData;
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

//    회원관리 게시판 페이지
    @RequestMapping(value = "/memberManage.do", method = RequestMethod.GET)
    public ModelAndView doManagement(HttpServletRequest req) throws Exception{
        ModelAndView mv = new ModelAndView("/login/memberManage");

        List<UserDto> userList = userService.selectMemberManage();

        HttpSession session = req.getSession();

//        세션을 가지고 와서 로그인한 아이디의 이름을 보여주기 위해서 사용함
        mv.addObject("userinfo",(String) session.getAttribute("memberName"));
        mv.addObject("userList", userList);
        return mv;
    }

//    회원 삭제
//    @RequestMapping(value = "/{memberNum}", method = RequestMethod.GET)
    @RequestMapping(value = "/{memberNum}", method = RequestMethod.DELETE)
    public String userDelete(@PathVariable("memberNum") int memberNum) throws Exception{
        userService.deleteUser(memberNum);
        return "redirect:/login/memberManage.do";
    }

    @RequestMapping(value = "/{memberNum}", method = RequestMethod.PUT)
    public String userUpdate(@PathVariable("memberNum") int memberNum) throws Exception{
        userService.deleteUser(memberNum);
        return "redirect:/login/memberManage.do";
    }
}






















