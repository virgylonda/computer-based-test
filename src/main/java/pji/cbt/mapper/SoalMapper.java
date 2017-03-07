package pji.cbt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import pji.cbt.entities.Soal;

@Mapper
public interface SoalMapper {
	public List<Soal> findAll();

}
