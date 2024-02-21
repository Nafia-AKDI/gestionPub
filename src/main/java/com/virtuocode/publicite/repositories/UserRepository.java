package com.virtuocode.publicite.repositories;
import com.virtuocode.publicite.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User,Long> {

}
