package com.meta.overwash.mapper;

import java.util.List;

import com.meta.overwash.domain.LaundryPriceDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LaundryPriceMapper {
	
	public List<LaundryPriceDTO> getList();

	public void insertLaundryPrice(LaundryPriceDTO price);
	
	public LaundryPriceDTO getLaundryPrice(Long priceId); 

	public int updateLaundryPrice(LaundryPriceDTO price);
	
	public int deleteLaundryPrice(Long priceId); 
}
