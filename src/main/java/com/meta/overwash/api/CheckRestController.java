package com.meta.overwash.api;

import com.meta.overwash.domain.CheckDTO;
import com.meta.overwash.domain.Criteria;
import com.meta.overwash.domain.MemberDTO;
import com.meta.overwash.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CheckRestController {

    @Autowired
    CheckService checkService;

//    // 검수 리스트를 json 으로 전송
//	@GetMapping(value = "/check/{confirmId}")
//	public ResponseEntity<List<CheckDTO>> checkList(@PathVariable("confirmId") Long confirmId) {
//		return new ResponseEntity<List<CheckDTO>>(checkService.getCheckList(confirmId), HttpStatus.OK);
//	}

	// 검수 리스트 페이징
	@GetMapping("/check/{confirmId}")
	public ResponseEntity<Map<String, Object>> checkListPaging(Criteria cri, @PathVariable("confirmId") Long confirmId,
															   @RequestParam(required = false, defaultValue = "1") int page,
															   @RequestParam(required = false, defaultValue = "10") int amount) throws Exception {
		cri.setPageNum(page);
		cri.setAmount(amount);
		return new ResponseEntity<Map<String, Object>>(checkService.getCheckListPaging(cri, confirmId), HttpStatus.OK);
	}
}
