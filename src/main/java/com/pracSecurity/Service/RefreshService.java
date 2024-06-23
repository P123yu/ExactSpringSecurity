package com.pracSecurity.Service;

import com.pracSecurity.Model.RefreshModel;
import org.springframework.stereotype.Service;

@Service
public interface RefreshService {

    // generate refresh token
    public RefreshModel createRefreshToken(String userName);

    // verify refresh token
    public RefreshModel verifyRefreshToken(String token);
}
