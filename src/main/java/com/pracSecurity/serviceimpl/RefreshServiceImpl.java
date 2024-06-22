//package com.pracSecurity.serviceimpl;
//
//import com.pracSecurity.Model.RefreshModel;
//import com.pracSecurity.Model.RegisterModel;
//import com.pracSecurity.Repository.RefreshRepository;
//import com.pracSecurity.Repository.RegisterRepository;
//import com.pracSecurity.Service.RefreshService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.Instant;
//import java.util.UUID;
//
//@Service
//public class RefreshServiceImpl implements RefreshService {
//
//    @Autowired
//    private RegisterRepository registerRepository;
//
//    @Override
//    public RefreshModel createRefreshToken(String userName) {
////        return RefreshModel.builder()
////                .refreshToken(UUID.randomUUID().toString())
////                .expiry(Instant.now().plusMillis(60*60*1000))
////                .registerModel(registerRepository.findByUserName(userName))
////                .build();
//        System.out.println("Refresh token created for user: " + userName);
//
//        RegisterModel register=registerRepository.findByUserName(userName);
//        RefreshModel refreshModel=register.getRefreshModel();
//        System.out.println(refreshModel+" refreshModel");
//        if(refreshModel==null) {
//            System.out.println("start here");
//            System.out.println(registerRepository.findByUserName(userName));
//            RefreshModel refresh = new RefreshModel();
//            refresh.setRefreshToken(UUID.randomUUID().toString());
//            refresh.setExpiry(Instant.now().plusMillis(60 * 60 * 1000));
//            refresh.setRegisterModel(registerRepository.findByUserName(userName));
//        } else {
//            refreshModel.setExpiry(Instant.now().plusMillis(60 * 60 * 1000));
//        }
//        return refreshRepository.save(refreshModel);
//    }
//
//
//    @Autowired
//    private RefreshRepository refreshRepository;
//
//    @Override
//    public RefreshModel verifyRefreshToken(String token) {
//        RefreshModel refresh_token=refreshRepository.findByRefreshToken(token).orElse(null);
//        if(refresh_token!=null && refresh_token.getExpiry().isAfter(Instant.now())){
//            return refresh_token;
//        }
//
//        // expire
//        else{
//            refreshRepository.delete(refresh_token);
//            throw new RuntimeException("refresh token expired");
//        }
//    }
//}


package com.pracSecurity.serviceimpl;

import com.pracSecurity.Model.RefreshModel;
import com.pracSecurity.Model.RegisterModel;
import com.pracSecurity.Repository.RefreshRepository;
import com.pracSecurity.Repository.RegisterRepository;
import com.pracSecurity.Service.RefreshService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshServiceImpl implements RefreshService {

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private RefreshRepository refreshRepository;

    @Override
    public RefreshModel createRefreshToken(String userName) {
        System.out.println("Refresh token created for user: " + userName);

        RegisterModel register = registerRepository.findByUserName(userName);
        RefreshModel refreshModel = register.getRefreshModel();

        if (refreshModel == null) {
            System.out.println("start here");
            System.out.println(registerRepository.findByUserName(userName));

            refreshModel = new RefreshModel();
            refreshModel.setRefreshToken(UUID.randomUUID().toString());
            refreshModel.setExpiry(Instant.now().plusMillis(60 * 60 * 1000));
            refreshModel.setRegisterModel(register);
        } else {
            refreshModel.setExpiry(Instant.now().plusMillis(60 * 60 * 1000));
        }

        return refreshRepository.save(refreshModel);
    }

    @Override
    public RefreshModel verifyRefreshToken(String token) {
        RefreshModel refresh_token = refreshRepository.findByRefreshToken(token).orElse(null);

        if (refresh_token != null && refresh_token.getExpiry().isAfter(Instant.now())) {
            return refresh_token;
        } else {
                refreshRepository.delete(refresh_token);
            throw new RuntimeException("refresh token expired");
        }
    }
}

