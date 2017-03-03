package pji.cbt.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pji.cbt.entities.User;

/**
 * 
 * This class act as Mapper 
 * Connect/mapping *Mapper.xml
 *  */
@Mapper
public interface UserMapper {
	public List<User> findAll();
}
