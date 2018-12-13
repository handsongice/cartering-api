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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MessageController {

    @Autowired
    BroadcastService broadcastService;

    @Autowired
    MessageService messageService;

    /**
     * 列表
     * @param params
     * @return
     */
    @RequestMapping("/allBroadcastList")
    public ResultMap allBroadcastList(@RequestParam Map<String, Object> params) {
        try {
            List<Broadcast> broadcasts = broadcastService.getBroadcastList(params);
            return ResultMap.success(broadcasts);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultMap.fail("出错了");
        }
    }
}
