package com.laguna.project18.WallapopGratuito.repository;

import com.laguna.project18.WallapopGratuito.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
