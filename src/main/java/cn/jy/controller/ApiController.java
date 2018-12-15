package cn.jy.controller;

import cn.jy.dto.ResultMap;
import cn.jy.entity.Broadcast;
import cn.jy.entity.Employee;
import cn.jy.entity.Message;
import cn.jy.pojo.LayUiPageParams;
import cn.jy.service.BroadcastService;
import cn.jy.service.MessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    BroadcastService broadcastService;

    @Autowired
    MessageService messageService;

    /**
     *
     * @param params
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "role", method = RequestMethod.POST)
    public ResultMap role(@RequestParam Map<String, Object> params, final HttpServletRequest request) {
        try {
            final Claims claims = (Claims) request.getAttribute("claims");
            //List<Broadcast> broadcasts = broadcastService.getBroadcastList(params);
            return ResultMap.success(claims);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMap.fail("出错了");
        }
    }
}
