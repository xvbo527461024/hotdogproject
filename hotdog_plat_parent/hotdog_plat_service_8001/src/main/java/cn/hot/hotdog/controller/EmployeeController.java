package cn.hot.hotdog.controller;

import cn.hot.hotdog.domain.Employee;
import cn.hot.hotdog.util.AjaxResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public AjaxResult login(@RequestBody Employee employee){
        if (employee.getName().equals("admin")&&employee.getPassword().equals("admin")){
            return new AjaxResult().setSuccess(true).setMes("登录成功");
        }else {
            return new AjaxResult().setMes("登录失败").setSuccess(false);
        }
    }
}
