package com.pgf.politicalgovernanceframeworkbackend.repository.keycloak;

import com.pgf.politicalgovernanceframeworkbackend.entity.keycloak.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
}
