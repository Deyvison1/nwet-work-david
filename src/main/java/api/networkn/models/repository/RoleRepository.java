package api.networkn.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.networkn.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
