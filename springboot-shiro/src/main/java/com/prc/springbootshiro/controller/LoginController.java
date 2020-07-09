package com.prc.springbootshiro.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.prc.springbootshiro.pojo.dto.UserDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Controller
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @PostMapping("/login")
    public String login(String username, String password,String verifyCode, boolean rememberMe, Model model){
        logger.info("remember:" + rememberMe);
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject currentUser = SecurityUtils.getSubject();

        // 获取session中的验证码
        String verCode = (String) currentUser.getSession().getAttribute("verifySessionCode");
        if("".equals(verifyCode)||(!verCode.equals(verifyCode))){
            model.addAttribute("msg","验证码不正确");
            return "login";
        }

        try {
            //主体提交登录请求到SecurityManager
            token.setRememberMe(rememberMe);
            currentUser.login(token);
        }catch (IncorrectCredentialsException ice){
            model.addAttribute("msg","密码不正确");
        }catch(UnknownAccountException uae){
            model.addAttribute("msg","账号不存在");
        }catch(AuthenticationException ae){
            model.addAttribute("msg","状态不正常");
        }
        if(currentUser.isAuthenticated()){
            logger.info("认证成功");
            model.addAttribute("username",username);
            return "/success";
        }else{
            token.clear();
            return "login";
        }
    }

    @GetMapping("/403")
    public String page_403(){
        return "403";
    }

    @GetMapping({"/","/success"})
    public String success(Model model){
        Subject currentUser = SecurityUtils.getSubject();
        UserDto user = (UserDto) currentUser.getPrincipal();
        model.addAttribute("username", user.getUser().getUsername());
        return "success";
    }

    /**
     * 获取验证码
     * @param response
     */
    @GetMapping("/getCode")
    public void getGifCode(HttpServletResponse response, HttpServletRequest request) throws IOException {
        byte[] verByte = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            request.getSession().setAttribute("verifySessionCode",createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge,"jpg",jpegOutputStream);
        } catch (IllegalArgumentException e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        } catch (IOException e){
            e.printStackTrace();
        }
        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        verByte = jpegOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(verByte);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
}
