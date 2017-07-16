package com.monitor.auth;

import com.monitor.auth.domain.TokenInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2017/7/16 0016.
 */
public interface ITokenDao extends JpaRepository<TokenInfo, Long> {
}
