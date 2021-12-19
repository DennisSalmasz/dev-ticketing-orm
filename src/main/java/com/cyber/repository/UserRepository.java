package com.cyber.repository;

import com.cyber.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    //@Where -- findByUserNameAndIsDeletedFalse();
    User findByUserName(String username);

    //hard delete - not good practice - when we delete anything in UI, we should not be deleting data from DB - we should keep saving data!!
    //@Where -- deleteByUserNameAndIsDeletedFalse();
    @Transactional // we need this annotation for deleting
    void deleteByUserName(String username);

    List<User> findAllByRoleDescriptionIgnoreCase(String description);
}
