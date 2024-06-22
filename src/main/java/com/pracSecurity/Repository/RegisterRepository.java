package com.pracSecurity.Repository;

import com.pracSecurity.Model.RegisterModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepository extends JpaRepository<RegisterModel, Long> {
    RegisterModel findByUserName(String username);
}
