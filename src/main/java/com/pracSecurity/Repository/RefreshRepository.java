package com.pracSecurity.Repository;
import com.pracSecurity.Model.RefreshModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshRepository  extends JpaRepository<RefreshModel, Long> {
    Optional<RefreshModel> findByRefreshToken(String refreshToken);
}
