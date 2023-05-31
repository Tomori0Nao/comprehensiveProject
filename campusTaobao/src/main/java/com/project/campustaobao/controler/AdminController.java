package com.project.campustaobao.controler;

import com.project.campustaobao.pojo.Administrator;
import com.project.campustaobao.server.AdminServer;
import com.project.campustaobao.vo.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    private AdminServer adminServer;
    /**
     * 获取所有的普通管理员
     * @return 返回管理员集合
     */
    @GetMapping("/getAdmins")
    @ResponseBody
    public ResultMessage<List<Administrator>> getAdmins(){
        ResultMessage<List<Administrator>> resultMessage;
        List<Administrator> list = adminServer.queryAdminList();
        if(list == null){
            resultMessage = new ResultMessage<>(ResultMessage.ERROR_CODE,
                    "未获取所有普通管理员信息",null);
        }else{
            resultMessage = new ResultMessage<>(ResultMessage.ERROR_CODE,
                    "已获取所有普通管理员信息",list);
        }
        return resultMessage;
    }
}
