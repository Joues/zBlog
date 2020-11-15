package cn.ityihang.zblog.controller;

import cn.ityihang.zblog.common.result.RestResponse;
import cn.ityihang.zblog.common.constant.CommonConstant;
import cn.ityihang.zblog.entity.SysUser;
import cn.ityihang.zblog.service.ISysUserDetailsService;
import cn.ityihang.zblog.service.ISysUserService;
import cn.ityihang.zblog.utils.PasswordUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Random;

/**
 * @Author: yihang
 * @Date: 2020/11/1 23:39
 * @Description:
 * @Version: 1.0
 */
@Api(tags = "用户表")
@Slf4j
@RestController
@RequestMapping(value = "/")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysUserDetailsService sysUserDetailsService;

    /**
     * 随机数
     * @param place 定义随机数的位数
     */
    public static String randomGen(int place) {
        String base = "qwertyuioplkjhgfdsazxcvbnmQAZWSXEDCRFVTGBYHNUJMIKLOP0123456789";
        StringBuffer sb = new StringBuffer();
        Random rd = new Random();
        for(int i=0;i<place;i++) {
            sb.append(base.charAt(rd.nextInt(base.length())));
        }
        return sb.toString();
    }

    @ApiOperation(value = "注册接口")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public RestResponse<SysUser> add(@RequestBody JSONObject jsonObject) {
        RestResponse<SysUser> result = new RestResponse<SysUser>();
//        String selectedRoles = jsonObject.getString("selectedroles");
//        String selectedDeparts = jsonObject.getString("selecteddeparts");
        try {
            SysUser user = JSON.parseObject(jsonObject.toJSONString(), SysUser.class);
            user.setCreateTime(new Date());//设置创建时间
            String salt = randomGen(8);
            user.setSalt(salt);
            String passwordEncode = PasswordUtil.encrypt(user.getUsername(), user.getPassword(), user.getSalt());
            user.setPassword(passwordEncode);
            user.setStatus(1);
            user.setDelFlag(CommonConstant.DEL_FLAG_0);
//            sysUserService.addUserWithRole(user, selectedRoles);
//            sysUserService.addUserWithDepart(user, selectedDeparts);
            sysUserService.save(user);
            result.setMsg("添加成功！");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.setMsg("操作失败");
        }
        return result;
    }

    @ResponseBody
    @ApiOperation(value = "当前用户信息")
    @GetMapping(value = "/userInfo")
    public RestResponse userInfo() {
        RestResponse r = new RestResponse();

        SysUser currentUser = (SysUser) SecurityUtils.getSubject().getPrincipal();

        r.setMsg("用户信息");
        r.setSuccess(true);
        r.setData(currentUser);
        return r;
    }

}
