package travel.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import travel.model.TravelBean;
import travel.model.TravelDao;

@Controller
public class TravelUpdateController {

	final String command = "update.tv";
	final String getPage = "TravelUpdateForm";
	final String gotoPage = "redirect:/list.tv";
	
	@Autowired
	//@Qualifier("")
	private TravelDao travelDao;
	
	@RequestMapping(value=command , method = RequestMethod.GET)
	public String doAction(@RequestParam("num") int num,
							@RequestParam("pageNumber") int pageNumber,
							@RequestParam("pageSize") int  pageSize,
							Model model) {
		
		TravelBean travel =travelDao.getOneTravel(num);  
		model.addAttribute("travel", travel);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("pageSize", pageSize);
		
		return getPage;
	}
	
	@RequestMapping(value=command , method = RequestMethod.POST)
	public ModelAndView doAction(@RequestParam("pageNumber") int pageNumber,
								@RequestParam("pageSize") int pageSize,
								@ModelAttribute("travel") @Valid TravelBean travel,
								BindingResult result) {
		
		System.out.println("update travel num : "+travel.getNum() );
		System.out.println("update travel area : "+travel.getArea() );
		System.out.println("update travel style : "+travel.getStyle() );
		
		ModelAndView mav = new ModelAndView();
		
		if(result.hasErrors()) {
			mav.addObject("pageNumber", pageNumber);
			mav.addObject("pageSize", pageSize);
			mav.setViewName(getPage); //updateForm갈때
			return mav;
		}
		
		int cnt = travelDao.updateTravel(travel);
		if(cnt>0) {
			mav.setViewName(gotoPage+"?pageNumber="+pageNumber+"&pageSize="+pageSize);
			//redirect:/list.ab?pageNumber=pageNumber&pageSize=pageSize 이런식으로 넘겨야한다
		}else {
			mav.setViewName(getPage);
		}
		return mav;
	
	}
	
}
