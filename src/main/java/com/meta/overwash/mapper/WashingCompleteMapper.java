package com.meta.overwash.mapper;

import java.util.List;

import com.meta.overwash.domain. WashingCompleteDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WashingCompleteMapper {
	
	public List<WashingCompleteDTO> getList();
	
	// 관리자 세탁완료 시 데이터 삽입
	public void insertWashingComplete(WashingCompleteDTO wc);
	
	public WashingCompleteDTO getWashingComplete(Long wcId); 

	public int updateWashingComplete(WashingCompleteDTO wc);
	
	public int deleteWashingComplete(Long wcId); 
}
