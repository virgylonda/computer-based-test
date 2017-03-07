package pji.cbt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pji.cbt.entities.Soal;
import pji.cbt.iservices.SoalService;
import pji.cbt.mapper.SoalMapper;

@Service
public class SoalServiceImpl implements SoalService {
	
	@Autowired
	private SoalMapper sMapper;

	@Override
	public List<Soal> findAllSoal() {
		// TODO Auto-generated method stub
		return sMapper.findAll();
	}

}
