package com.jb.pma.dao;

import org.springframework.data.repository.CrudRepository;

import com.jb.pma.entities.UserAccount;

public interface UserAccountRepository  extends CrudRepository<UserAccount, Long>{

}
