//package com.meta.overwash.controller;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpRequest;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.SessionAttribute;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.meta.overwash.domain.CrewDTO;
//import com.meta.overwash.domain.DeliveryDTO;
//import com.meta.overwash.domain.UserDTO;
//import com.meta.overwash.domain.WashingCompleteDTO;
//import com.meta.overwash.service.CrewService;
//import com.meta.overwash.service.ReservationConfirmedService;
//
//@Controller
//@RequestMapping("/crew")
//public class CrewController {
//
//	@Autowired
//	private CrewService crewService;
//
//	@Autowired
//	private ReservationConfirmedService rcService;
//
//	@GetMapping("/main")
//	public void crewMain(HttpSession session, Model model) throws Exception {
//		// 메인페이지에서 보여줄 것들 추가
//		UserDTO user = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		CrewDTO crew = crewService.getCrew(user.getUserId());
//
//		model.addAttribute("collectList", crewService.getToBeCollectList()); // 수거해야할 리스트
//		model.addAttribute("wcList", crewService.getWcList()); // 배달해야할 리스트
//		model.addAttribute("deliveringCnt", crewService.getDeliveryList(crew.getCrewId(), "배달중").size());
//		model.addAttribute("doneDeliverCnt", crewService.getDeliveryList(crew.getCrewId(), "배달완료").size());
//		session.setAttribute("username", crew.getCrewName()); // navBar에 크루 네임 계속 보여 주기 위해
//		session.setAttribute("member", crew);
//		session.setAttribute("crewId", crew.getCrewId());
//	}
//
//	@PostMapping("/mypage")
//	public String myPage(HttpServletRequest request, Model model) throws Exception {
//		Long userId = Long.parseLong(request.getParameter("userId"));
//		model.addAttribute("crewDTO", crewService.getCrew(userId));
//
//		return "crew/mypage";
//	}
//
//	@PostMapping("/modifyInfo")
//	public String modifyInfo(HttpServletRequest request, Model model) throws Exception {
//		Long userId = Long.parseLong(request.getParameter("userId"));
//		model.addAttribute("crewDTO", crewService.getCrew(userId));
//		return "crew/modifyInfo";
//	}
//
//	@PostMapping("/remove")
//	public String remove(@RequestParam("crewId") Long crewId, RedirectAttributes rttr) throws Exception {
//		if (crewService.remove(crewId)) {
//			rttr.addFlashAttribute("result", "success"); // view에서 success시 탈퇴 완료 alert?
//		}
//
//		return "redirect:/login"; // /crew/login 에서 /login 으로 변경함
//	}
//
//	@GetMapping("/pickuplist")
//	public void pickupList(Model model) throws Exception {
//		model.addAttribute("collectList", crewService.getToBeCollectList());
//	}
//
//	@GetMapping("/tobedelivery")
//	public void tobedelivery(Model model) throws Exception {
//		model.addAttribute("wcList", crewService.getWcList()); // 배달해야할 리스트
//	}
//
////	@GetMapping("/delivering")
////	public void delivering(Model model) throws Exception {
////		UserDTO user = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
////		CrewDTO crew = crewService.getCrew(user.getUserId());
////		model.addAttribute("deliveryList", crewService.getDeliveryList(crew.getCrewId(), "배달중"));
////	}
////
////	@GetMapping("/donedelivery")
////	public void donedelivery(Model model) throws Exception {
////		UserDTO user = (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
////		CrewDTO crew = crewService.getCrew(user.getUserId());
////		model.addAttribute("deliveryList", crewService.getDeliveryList(crew.getCrewId(), "배달완료"));
////	}
//
//	@PostMapping("/collect/{reservationId}")
//	public String collect(@PathVariable("reservationId") Long reservationId, CrewDTO crew, String flag)
//			throws Exception {
//		rcService.insertReservationConfirmed(reservationId, crew);
//		if (flag.equals("table"))
//			return "redirect:/crew/pickuplist";
//		return "redirect:/crew/main";
//	}
////	 Long reservationId, String flag, Long wcId, Long crewId)
//
//	@PostMapping("/delivery/{reservationId}")
//	public String delivery(@PathVariable("reservationId") HttpServletRequest request)
//			throws Exception {
//
//		CrewDTO crewDTO = new CrewDTO();
//		crewDTO.setCrewId(Long.parseLong(request.getParameter("crewId")));
//
//		WashingCompleteDTO washingCompleteDTO = new WashingCompleteDTO();
//		washingCompleteDTO.setWcId(Long.parseLong(request.getParameter("wcId")));
//
//		DeliveryDTO deliveryDTO = new DeliveryDTO();
//		deliveryDTO.setCrew(crewDTO);
//		deliveryDTO.setWc(washingCompleteDTO);
//
//		crewService.updateDelivering(Long.parseLong(request.getParameter("reservationId")), deliveryDTO);
//		if ( String.valueOf(request.getParameter("flag")).equals("table"))
//			return "redirect:/crew/tobedelivery";
//		return "redirect:/crew/main";
//	}
//
//	@PostMapping("/donedelivery/{reservationId}")
//	public String doneDelivery(@PathVariable("reservationId") Long reservationId, String flag, Long crewId, Long deliveryId)
//			throws Exception {
//		crewService.updateResDoneDelivery(reservationId, deliveryId);
//		if (flag.equals("table"))
//			return "redirect:/crew/delivering";
//		return "redirect:/crew/main";
//	}
//}
