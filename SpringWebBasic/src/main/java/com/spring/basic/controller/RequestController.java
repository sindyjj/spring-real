package com.spring.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.basic.model.UserVO;

//자동으로 스프링 컨테이너에 해당 클래스의 빈을 등록하는 아노테이션
//빈을 등록해 놔야(객체가 생성되어 있어야) HandlerMapping이 클래스의
//객체를 검색할 수 있을 것이다.
@Controller
@RequestMapping("/request") // 밑에 주소 앞에 /request 생략가능하다.
public class RequestController {

	public RequestController() {
		System.out.println("RequestController 생성");
	}
	
	@RequestMapping("/test")
	public String testCall() {
		System.out.println("/request/test 요청이 들어옴!");
		return "test";	
	}
	
	/*
	 만약 사용자가 /request/req 요청을 보내 왔을 때
	 views 폴더 아래에 request폴더 안에 존재하는
	 req-ex01.jsp파일을 열도록 메서드를 구성해보시오. 
	*/
	
	@RequestMapping("/req")
	public String req() {
		System.out.println("/request/req 요청이 들어옴");
		return "request/req-ex01";
	}
//	@RequestMapping(value = "request/basic01", method = RequestMethod.GET)
	@GetMapping("/basic01")
	public String basicGet() {
		System.out.println("/basic01 요청이 들어옴~! : GET 방식!");
		return "request/req-ex01";
	}
	
//	@RequestMapping(value = "request/basic01", method = RequestMethod.POST)
	@PostMapping("/basic01")
	public String basicPost() {
		System.out.println("/basic01 요청이 들어옴~! : POST 방식!");
		return "request/req-ex01";
	}
	
	////////////////////////////////////////////////////////////////////
	
	
	//컨트롤러 내의 메서드 타입을 void로 선언하면
	//요청이 들어온 URL 값을 뷰 리졸버에게 전달합니다.
	@GetMapping("/join")
	public void register() { // void 를 쓴다면 위에 주소에 따라 return을 안줘도 페이지가 응답된다.
		System.out.println("/request/join: GET");
//		return "request/join"; // 이게 join.jsp
	}
	
	//요청 URI 주소가 같더라도, 전송 방식에 따라 맵핑을 다르게 하기 떄문에
	//같은 주소를 사용하는 것이 가능합니다. (GET -> 화면처리, POST -> 입력값 처리)

	/*
		# 스프링에서 요청과 함께 전달된 데이터를 처리하는 방식
		
		1. 전통적인 jsp/servlet 방식의 파라미터 읽기 처리 방법.
		-HttpServletRequest 객체를 활용 (우리가 jsp에서 사용하던 방식)
		-> 스프링에서는 잘 사용하지 않음.

	@PostMapping("join")
	public void register(HttpServletRequest request) {
		System.out.println("/request/join: POST");
		
		System.out.println("ID : " + request.getParameter("userId"));
		System.out.println("PW : " + request.getParameter("userPw"));
		System.out.println("HOBBY : " + Arrays.toString(request.getParameterValues("hobby")));
	}
	*/
	
	/*
	 2. @RequestParam 아노테이션을 이용한 요청 파라미터 처리.
	 @RequestParam("파라미터 변수명") 값을 받아서 처리할 변수 
	 파라미터 변수명과 값을 받을 변수명을 동일하게 작성하면 @RequestParam 생략 가능.
	 
	 
	@PostMapping("/join")
	public void register(
			String userId,
			String userPw,
			String userName,
			@RequestParam(value = "hobby" , required = false, 
			defaultValue = "no hobby person") List<String> hobby		
	// Requsetparam은 체크를 하나도 안하면 에러400뜸 체크를 하나라도 하는 전제로 운영
	) {
		System.out.println("ID : " + userId);
		System.out.println("PW : " + userPw);
		System.out.println("Name : " + userName);	
		System.out.println("HOBBY : " + hobby);
	}
	*/
	
	/*
	  3. 커맨드 객체를 활용한 파라미터 처리
	  - 파라미터 데이터와 연동되는 VO 클래스가 필요합니다.
	  - VO 클래스의 필드는 파라미터 변수명과 동일하게 작성합니다. (setter 메서드를 호출)
	  
	  # 커맨드 객체: 사용자의 입력을 담기 위해 설계되고, 특정 검증 로직이나 비지니스 로직을
	  수행할 수 있음. (VO 보다는 역할이 좀 더 많고 , 특정 목적을 가진 객체)
	 */
	
	@PostMapping("/join")
	public void register(UserVO vo) {
		System.out.println(vo);
	}
	
	
		
		
	
}
