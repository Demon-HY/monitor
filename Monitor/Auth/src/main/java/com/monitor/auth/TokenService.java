package com.monitor.auth;

import com.monitor.auth.domain.TokenInfo;
import com.monitor.auth.domain.TokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private TokenMapper tokenMapper;

    /**
     * 获取用户登录信息
     * @param token
     * @return
     */
    public TokenInfo getTokenInfo(String token) {
        return tokenMapper.selectOne(new TokenInfo(token));
    }
}
