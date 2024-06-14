package com.pracSecurity.Repository;

import com.pracSecurity.Model.RegisterModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringRepository extends JpaRepository<RegisterModel,Long> {
    RegisterModel findByuserName(String username);
}
