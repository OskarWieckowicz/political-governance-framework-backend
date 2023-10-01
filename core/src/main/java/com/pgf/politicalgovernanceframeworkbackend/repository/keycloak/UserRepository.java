package com.pgf.politicalgovernanceframeworkbackend.repository.keycloak;

import com.pgf.politicalgovernanceframeworkbackend.entity.keycloak.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
