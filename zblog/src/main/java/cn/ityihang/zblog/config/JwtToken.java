package cn.ityihang.zblog.config;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author: yihang
 * @Date: 2020/10/26 0:04
 * @Description:
 * @Version: 1.0
 */
public class JwtToken implements AuthenticationToken {

    private static final long serialVersionUID = 1L;
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
