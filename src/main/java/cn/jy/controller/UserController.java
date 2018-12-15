package cn.jy.controller;

import cn.jy.dto.ResultMap;
import cn.jy.entity.Customer;
import cn.jy.service.CustomerService;
import cn.jy.util.JWTUtils;
import cn.jy.util.MD5;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    CustomerService customerService;

    @SuppressWarnings("unused")
    private static class LoginResponse {
        public String token;
        public LoginResponse(final String token) {
            this.token = token;
        }
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResultMap register (@RequestParam Map<String, Object> params) {
        if(null == params
                || StringUtils.isEmpty(params.get("phone").toString())
                || StringUtils.isEmpty(params.get("password").toString())) {
            return ResultMap.fail("信息不全！");
        }
        try {
            Customer register = new Customer();
            register.setPhone(params.get("phone").toString());
            register.setPassword(MD5.md5(params.get("password").toString()));
            String uuid = customerService.addCustomer(register);
            Map<String, Object> claims = new HashMap<>();
            claims.put("uuid",uuid);
            String token = JWTUtils.create("register","uuid",claims,10);
            return ResultMap.success("注册成功",new LoginResponse(token));
        } catch (Exception e) {
            return ResultMap.fail(e.getMessage());
        }

    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResultMap login (@RequestParam Map<String, Object> params) {
        if(null == params
                || StringUtils.isEmpty(params.get("phone").toString())
                || StringUtils.isEmpty(params.get("password").toString())) {
            return ResultMap.fail("信息不全！");
        }
        try {
            Customer customer = customerService.getCustomer(params.get("phone").toString());
            if(null == customer || null == customer.getId()) {
                return ResultMap.fail("用户不存在！");
            }
            if (!MD5.md5(params.get("password").toString()).equals(customer.getPassword())) {
                return ResultMap.fail("密码错误！");
            }
            Map<String, Object> claims = new HashMap<>();
            claims.put("uuid",customer.getUuid());
            String token = JWTUtils.create("login","uuid",claims,10);
            return ResultMap.success("登录成功",new LoginResponse(token));
        } catch (Exception e) {
            return ResultMap.fail(e.getMessage());
        }

    }
}
