//package cn.ityihang.zblog.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import cn.ityihang.zblog.entity.SysUser;
//import cn.ityihang.zblog.service.impl.UserServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.*;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//import cn.ityihang.zblog.vo.Result;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
///**
// * @Author: yihangjou(周逸航)
// * @Site: www.yihang.ml
// * @cnBlogs: https://www.cnblogs.com/yihangjou/
// * @Date: create in 2020/7/10 16:18
// */
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    UserServiceImpl userServiceImpl;
//
//    @Bean
//    PasswordEncoder PasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userServiceImpl);
//        auth.inMemoryAuthentication()
//                .withUser("admin").password("$2a$10$piuexki3ZSmtj5E9mBx1Qu.kmA1.eGkBpVIpMsQvySZGJyFH79A4a").roles("admin")
//                .and()
//                .withUser("user").password("$2a$10$XPpOTdgVlEeiaSocIRwSy.t5zEjLYBnTGeCblc6/qtWbFuLdAW0FG").roles("user");
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/api/zblog/**","/css/**","/js/**","/index.html","/img/**","/fonts/**","/favicon.ico","/swagger-ui.html","/login");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .loginProcessingUrl("/doLogin")
//                .loginPage("/login")
//                .successHandler(new AuthenticationSuccessHandler() {
//                    @Override
//                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
//                        resp.setContentType("application/json;charset=utf-8");
//                        PrintWriter out = resp.getWriter();
//                        SysUser user = (SysUser) authentication.getPrincipal();
//                        Result ok = Result.ok("欢迎回来！",user);
//                        String s = new ObjectMapper().writeValueAsString(ok);
//                        out.write(s);
//                        out.flush();
//                        out.close();
//                    }
//                })
//                .failureHandler(new AuthenticationFailureHandler() {
//                    @Override
//                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
//                        resp.setContentType("application/json;charset=utf-8");
//                        PrintWriter out =  resp.getWriter();
//                        Result result = Result.error("登录失败！");
//                        if(e instanceof LockedException) {
//                            result.setMsg("账户被锁定，请联系管理员！");
//                        } else if (e instanceof BadCredentialsException) {
//                            result.setMsg("用户名或密码输入错误！");
//                        } else if (e instanceof DisabledException) {
//                            result.setMsg("账户被禁用，请联系管理员！");
//                        } else if (e instanceof AccountExpiredException) {
//                            result.setMsg("账户过期，请联系管理员！");
//                        } else if (e instanceof CredentialsExpiredException) {
//                            result.setMsg("密码过期，请联系管理员！");
//                        } else {
//                            result.setMsg("登录失败！");
//                        }
//                        out.write(new ObjectMapper().writeValueAsString(result));
//                        out.flush();
//                        out.close();
//                    }
//                })
//                .permitAll()
//                .and()
//                .logout()
//                .logoutSuccessHandler(new LogoutSuccessHandler() {
//                    @Override
//                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
//                        resp.setContentType("application/json;charset=utf-8");
//                        Result result = Result.ok("See you~ 我们江湖再见!");
//                        ObjectMapper om = new ObjectMapper();
//                        PrintWriter out = resp.getWriter();
//                        out.write(om.writeValueAsString(result));
//                        out.flush();
//                        out.close();
//                    }
//                })
//                .permitAll()
//                .and()
//                .csrf().disable();
//    }
//}