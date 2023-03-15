package com.springrest.springrest.repository;
import java.util.List;

import com.springrest.springrest.model.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface IUsersRepository extends JpaRepository<Users,Integer> {

    @Query(value = "Select * from tbl_users where userName = :userName And status_statusId = 1",countQuery = "SELECT count(*) from tbl_users", nativeQuery = true)
    public List<Users> FindByUserName(String userName);

    @Query(value = "Select * from tbl_users where userName = :userName And password = :password And status_statusId = 1",countQuery = "SELECT count(*) from tbl_users", nativeQuery = true)
    public List<Users> findByUP(String userName, String password);
    
    @Modifying
    @Transactional
    @Query(value = "Update tbl_users set status_statusId = 2 where userName = :userName And password = :password And status_statusId = 1", countQuery = "Select count(*) From tbl_users", nativeQuery = true)
    public Integer deleteUserByUP(String userName, String password);


}
