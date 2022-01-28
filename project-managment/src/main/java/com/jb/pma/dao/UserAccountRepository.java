package com.jb.pma.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.jb.pma.entities.UserAccount;

public interface UserAccountRepository  extends PagingAndSortingRepository<UserAccount, Long>{

}
