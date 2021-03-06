package cn.ityihang.zblog.system.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.ityihang.zblog.common.result.RestResponse;
import cn.ityihang.zblog.common.constant.CacheConstant;
import cn.ityihang.zblog.common.constant.CommonConstant;
import cn.ityihang.zblog.system.entity.LoginUser;
import cn.ityihang.zblog.system.entity.SysLoginModel;
import cn.ityihang.zblog.system.entity.SysUser;
import cn.ityihang.zblog.system.service.ILoginUserService;
import cn.ityihang.zblog.system.service.ISysLogService;
import cn.ityihang.zblog.system.service.ISysUserService;
import cn.ityihang.zblog.utils.JwtUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.ityihang.zblog.utils.RedisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: yihangjou(周逸航)
 * @Site: www.yihang.ml
 * @cnBlogs: https://www.cnblogs.com/yihangjou/
 * @Date: create in 2020/8/23 14:33
 */
@Api(tags = "登录接口")
@Slf4j
@RestController
@RequestMapping(value = "/")
public class LoginController {
    @Autowired
    ISysUserService sysUserService;
    @Autowired
    ILoginUserService loginUserService;
    @Autowired
    ISysLogService sysLogSerivce;
    @Autowired
    RedisUtil redisUtil;

    private static final String BASE_CHECK_CODES = "qwertyuiplkjhgfdsazxcvbnmQWERTYUPLKJHGFDSAZXCVBNM1234567890";

    @ApiOperation(value = "登录login")
    @PostMapping(value = "/login")
    public RestResponse Login(@RequestBody SysLoginModel user, HttpServletRequest request) {

        RestResponse<JSONObject> result = new RestResponse<JSONObject>();
        log.warn(String.valueOf(request));
        String username = user.getUsername();
        String password = user.getPassword();

//        校验验证码
        String captcha = user.getCaptcha();
        if(captcha==null){
            result.setMsg("验证码无效");
            return result;
        }
        String lowerCaseCaptcha = captcha.toLowerCase();
        String realKey = cn.ityihang.zblog.utils.MD5Util.MD5Encode(lowerCaseCaptcha+user.getCheckKey(), "utf-8");
        Object checkCode = redisUtil.get(realKey);
        if(checkCode==null || !checkCode.equals(lowerCaseCaptcha)) {
            result.setMsg("验证码错误");
            return result;
        }
        //update-end-author:taoyan date:20190828 for:校验验证码

        //1. 校验用户是否有效
        //update-begin-author:wangshuai date:20200601 for: 登录代码验证用户是否注销bug，if条件永远为false
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUsername,username);
        SysUser sysUser = sysUserService.getOne(queryWrapper);
        //update-end-author:wangshuai date:20200601 for: 登录代码验证用户是否注销bug，if条件永远为false
        result = sysUserService.checkUserIsEffective(sysUser);
        if(!result.isSuccess()) {
            return result;
        }

        //2. 校验用户名或密码是否正确
        String userpassword = cn.ityihang.zblog.utils.PasswordUtil.encrypt(username, password, sysUser.getSalt());

        String syspassword = sysUser.getPassword();
        if (!syspassword.equals(userpassword)) {
            result.setMsg("用户名或密码错误");
            return result;
        }

        //用户登录信息
        userInfo(sysUser, result);
        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(sysUser, loginUser);
        sysLogSerivce.addLog("用户名: " + username + ",登录成功！", CommonConstant.LOG_TYPE_1, null,loginUser);
        return result;
    }


    /**
     * 退出登录
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "注销登录")
    @RequestMapping(value = "/logout")
    public RestResponse<Object> logout(HttpServletRequest request, HttpServletResponse response) {
        //用户退出逻辑
        String token = request.getHeader(CommonConstant.X_ACCESS_TOKEN);
        if(StrUtil.isEmpty(token)) {
            return RestResponse.failed("退出登录失败！");
        }
        String username = cn.ityihang.zblog.utils.JwtUtil.getUsername(token);
        LoginUser sysUser = loginUserService.getUserByName(username);
        if(sysUser!=null) {
            //update-begin--Author:wangshuai  Date:20200714  for：登出日志没有记录人员
            sysLogSerivce.addLog("用户名: "+sysUser.getRealname()+",退出成功！", CommonConstant.LOG_TYPE_1, null,sysUser);
            //update-end--Author:wangshuai  Date:20200714  for：登出日志没有记录人员
            log.info(" 用户名:  "+sysUser.getRealname()+",退出成功！ ");
            //清空用户登录Token缓存
            redisUtil.del(CommonConstant.PREFIX_USER_TOKEN + token);
            //清空用户登录Shiro权限缓存
            redisUtil.del(CommonConstant.PREFIX_USER_SHIRO_CACHE + sysUser.getId());
            //清空用户的缓存信息（包括部门信息），例如sys:cache:user::<username>
            redisUtil.del(String.format("%s::%s", CacheConstant.SYS_USERS_CACHE, sysUser.getUsername()));
            //调用shiro的logout
            SecurityUtils.getSubject().logout();
            return RestResponse.ok("退出登录成功！");
        }else {
            return RestResponse.failed("Token无效!");
        }
    }

//
//    /**
//     * 获取访问量
//     * @return
//     */
//    @GetMapping("loginfo")
//    public Result<JSONObject> loginfo() {
//        Result<JSONObject> result = new Result<JSONObject>();
//        JSONObject obj = new JSONObject();
//        //update-begin--Author:zhangweijian  Date:20190428 for：传入开始时间，结束时间参数
//        // 获取一天的开始和结束时间
//        Calendar calendar = new GregorianCalendar();
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//        Date dayStart = calendar.getTime();
//        calendar.add(Calendar.DATE, 1);
//        Date dayEnd = calendar.getTime();
//        // 获取系统访问记录
//        Long totalVisitCount = logService.findTotalVisitCount();
//        obj.put("totalVisitCount", totalVisitCount);
//        Long todayVisitCount = logService.findTodayVisitCount(dayStart,dayEnd);
//        obj.put("todayVisitCount", todayVisitCount);
//        Long todayIp = logService.findTodayIp(dayStart,dayEnd);
//        //update-end--Author:zhangweijian  Date:20190428 for：传入开始时间，结束时间参数
//        obj.put("todayIp", todayIp);
//        result.setResult(obj);
//        result.success("登录成功");
//        return result;
//    }

//    /**
//     * 获取访问量
//     * @return
//     */
//    @GetMapping("visitInfo")
//    public Result<List<Map<String,Object>>> visitInfo() {
//        Result<List<Map<String,Object>>> result = new Result<List<Map<String,Object>>>();
//        Calendar calendar = new GregorianCalendar();
//        calendar.set(Calendar.HOUR_OF_DAY,0);
//        calendar.set(Calendar.MINUTE,0);
//        calendar.set(Calendar.SECOND,0);
//        calendar.set(Calendar.MILLISECOND,0);
//        calendar.add(Calendar.DAY_OF_MONTH, 1);
//        Date dayEnd = calendar.getTime();
//        calendar.add(Calendar.DAY_OF_MONTH, -7);
//        Date dayStart = calendar.getTime();
//        List<Map<String,Object>> list = logService.findVisitCount(dayStart, dayEnd);
//        result.setResult(oConvertUtils.toLowerCasePageList(list));
//        return result;
//    }


    /**
     * 用户信息
     *
     * @param SysUser
     * @param result
     * @return
     */
    private RestResponse userInfo(SysUser SysUser, RestResponse result) {
        String syspassword = SysUser.getPassword();
        String username = SysUser.getUsername();
        // 生成token
        String token = cn.ityihang.zblog.utils.JwtUtil.sign(username, syspassword);
        // 设置token缓存有效时间
        redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, token);
        redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME*2 / 1000);

        // 获取用户部门信息
        JSONObject obj = new JSONObject();
//        List<SysDepart> departs = sysDepartService.queryUserDeparts(SysUser.getId());
//        obj.put("departs", departs);
//        if (departs == null || departs.size() == 0) {
//            obj.put("multi_depart", 0);
//        } else if (departs.size() == 1) {
//            sysUserService.updateUserDepart(username, departs.get(0).getOrgCode());
//            obj.put("multi_depart", 1);
//        } else {
//            obj.put("multi_depart", 2);
//        }
        obj.put("token", token);
        obj.put("userInfo", SysUser);
        result.setData(obj);
        result.setMsg("登录成功");
        return result;
    }

    /**
     * 后台生成图形验证码 ：有效
     * @param response
     * @param key
     */
    @ApiOperation("获取验证码")
    @GetMapping(value = "/randomImage/{key}")
    public RestResponse<String> randomImage(HttpServletResponse response,@PathVariable String key){
        RestResponse<String> res = new RestResponse<String>();
        try {
            String code = RandomUtil.randomString(BASE_CHECK_CODES,4);
            String lowerCaseCode = code.toLowerCase();
            String realKey = cn.ityihang.zblog.utils.MD5Util.MD5Encode(lowerCaseCode+key, "utf-8");
            redisUtil.set(realKey, lowerCaseCode, 60);
            String base64 = cn.ityihang.zblog.utils.RandImageUtil.generate(code);
            res.setSuccess(true);
            res.setData(base64);
        } catch (Exception e) {
            res.setMsg("获取验证码出错"+e.getMessage());
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 图形验证码
     * @param sysLoginModel
     * @return
     */
    @RequestMapping(value = "/checkCaptcha", method = RequestMethod.POST)
    public RestResponse<?> checkCaptcha(@RequestBody SysLoginModel sysLoginModel){
        String captcha = sysLoginModel.getCaptcha();
        String checkKey = sysLoginModel.getCheckKey();
        if(captcha==null){
            return RestResponse.failed("验证码无效");
        }
        String lowerCaseCaptcha = captcha.toLowerCase();
        String realKey = cn.ityihang.zblog.utils.MD5Util.MD5Encode(lowerCaseCaptcha+checkKey, "utf-8");
        Object checkCode = redisUtil.get(realKey);
        if(checkCode==null || !checkCode.equals(lowerCaseCaptcha)) {
            return RestResponse.failed("验证码错误");
        }
        return RestResponse.ok();
    }




}
