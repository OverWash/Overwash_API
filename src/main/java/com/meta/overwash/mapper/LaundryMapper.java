package com.meta.overwash.mapper;

import java.util.List;

import com.meta.overwash.domain.Criteria;
import com.meta.overwash.domain.LaundryDTO;
import com.meta.overwash.domain.ReservationConfirmedDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LaundryMapper {
	
	public List<LaundryDTO> getList();

	public List<LaundryDTO> getListPaging(Criteria cri);

	public ReservationConfirmedDTO updateWashComplete();

	public void insertLaundry(LaundryDTO laundry);

	public int getLaundry(Long laundryId);

	public int updateLaundry(LaundryDTO laundry);
	
	public int deleteLaundry(Long laundryId);

	public Long getLaundryCnt();
}
