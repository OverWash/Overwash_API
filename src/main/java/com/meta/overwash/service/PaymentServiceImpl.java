package com.meta.overwash.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meta.overwash.domain.CheckDTO;
import com.meta.overwash.domain.Criteria;
import com.meta.overwash.domain.LaundryDTO;
import com.meta.overwash.domain.PagenationDTO;
import com.meta.overwash.domain.PaymentRequestDTO;
import com.meta.overwash.domain.ReceiptDTO;
import com.meta.overwash.domain.ReservationConfirmedDTO;
import com.meta.overwash.domain.ReservationDTO;
import com.meta.overwash.mapper.CheckMapper;
import com.meta.overwash.mapper.PaymentRequestMapper;
import com.meta.overwash.mapper.ReceiptMapper;
import com.meta.overwash.mapper.ReservationConfirmedMapper;
import com.meta.overwash.mapper.ReservationMapper;

import lombok.extern.log4j.Log4j;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	ReservationConfirmedMapper rcMapper;

	@Autowired
	CheckMapper checkMapper;

	@Autowired
	PaymentRequestMapper prMapper;

	@Autowired
	ReservationMapper reservationMapper;

	@Autowired
	ReceiptMapper receiptMapper;

	// 관리자 결제 요청서 생성 서비스
	@Override
	@Transactional
	public PaymentRequestDTO requestToAdmin(Long confirmId, List<LaundryDTO> laundryList) {
		ReservationConfirmedDTO rcDto = rcMapper.getReservationConfirm(confirmId);
		Long price = 0L;
		for (LaundryDTO laundry : laundryList) {
			CheckDTO check = new CheckDTO(null, rcDto, laundry);
			checkMapper.insertCheck(check);
			price += laundry.getLaundryPrice().getPrice();
		}

		PaymentRequestDTO prDto = new PaymentRequestDTO();
		prDto.setPrPrice(price);
		prDto.setConfirm(rcDto);
		System.out.println(rcDto);
		prMapper.insertPaymentRequest(prDto);
		rcDto.getReservation().setReservationStatus("검수완료");
		reservationMapper.updateReservationStatus(rcDto.getReservation());
		return prDto;
	}

	// RestController Paging 관리자 내역
	@Override
	public Map<String, Object> getListToAdmin(Criteria cri) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("paymentPaging", new PagenationDTO(cri, getCountToAdmin(cri)));
		map.put("paymentRequests", prMapper.getListToAdmin(cri));
		return map;
	}

	// Rest Controller Paging 고객 내역
	@Override
	public Map<String, Object> getListToMember(Criteria cri, Long userId) {
		// Mapper에 들어갈 파라미터 map으로 변환
		HashMap<String, Object> vo = new HashMap<String, Object>();
		vo.put("pageNum", cri.getPageNum());
		vo.put("amount", cri.getAmount());
		vo.put("userId", userId);

		// 페이징 처리를 위해 map으로 데이터 리턴
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paymentPaging", new PagenationDTO(cri, getCountToMember(userId)));
		map.put("paymentRequests", prMapper.getListToMember(vo));
		return map;
	}

	@Override
	public List<PaymentRequestDTO> getPrListToMember(Long userId) {
		return prMapper.getPrListToMemberNotPaging(userId);
	}

//	@Override
//	public List<PaymentRequestDTO> getListToMember(Long userId){
//			return prMapper.getListToMemberNotPaging(userId);
//	}

	// prid의 결제요청서 조회
	@Override
	public PaymentRequestDTO get(Long pno) {
		return prMapper.getPaymentRequest(pno);
	}

	@Override
	@Transactional
	public void paymentProcess(Long prId, ReceiptDTO receipt) {

		// 영수증 발급 후 예약의 예약상태 '결제완료'로 변경
		// 결제를 진행할때는 결제요청서를 가지고 있음. 결제요청서에는 예약확정번호가 있고 거기엔 예약번호가 있다.

		PaymentRequestDTO pr = prMapper.getPaymentRequest(prId);
		ReservationConfirmedDTO rc = rcMapper.getReservationConfirm(pr.getConfirm().getConfirmId());
		ReservationDTO r = rc.getReservation();
		r.setReservationStatus("결제완료");
		receipt.setPr(pr);

		receiptMapper.insertReceipt(receipt);
		reservationMapper.updateReservationStatus(r);
	}

	@Override
	public ReceiptDTO getReceipt(Long receiptId) {
		return receiptMapper.getReceipt(receiptId);
	}

	@Override
	public List<ReceiptDTO> getReceiptList(Long userId) {
		return receiptMapper.getReceiptList(userId);
	}

	@Override
	public List<ReceiptDTO> getDeliveryCompletedList(Long userId) {
		return receiptMapper.getDeliveryCompletedList(userId);
	}

	/* ------------서비스 내부에서 쓸 메소드 -------------- */

	private int getCountToAdmin(Criteria cri) {

		return prMapper.getCountToAdmin(cri).intValue();
	}

	private int getCountToMember(Long userId) {
		return prMapper.getCountToMember(userId).intValue();
	}
}
