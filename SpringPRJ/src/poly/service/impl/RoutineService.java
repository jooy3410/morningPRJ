package poly.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import poly.dto.RoutineDTO;
import poly.persistance.mapper.IRoutineMapper;
import poly.service.IRoutineService;

@Service("RoutineService")
public class RoutineService implements IRoutineService{
	
	@Resource(name="RoutineMapper")
	private IRoutineMapper routineMapper;
	
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public int insertRoutineInfo(RoutineDTO pDTO) throws Exception{
		

		log.info(this.getClass().getName() + " insertRoutineInfo 시작");
		
		return routineMapper.insertRoutineInfo(pDTO);
	}
	
	
	
	

}
