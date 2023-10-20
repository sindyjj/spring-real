package com.spring.myweb.user.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.spring.myweb.user.entity.User;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class UserMapperTest {

	@Autowired
	private IUserMapper mapper;
	
	@Test
    @DisplayName("회원 가입을 진행했을 때 회원가입이 성공해야 한다.")
    void registTest() {
        User user = User.builder()
        		.userId("sindyjj")
        		.userPw("jj1023")
        		.userName("원정욱")
        		.userPhone1("010")
        		.userPhone2("41294291")
        		.userEmail1("sindyjj1")
        		.userEmail2("naver.com")
        		.addrBasic("서울특별시 마포구")
        		.addrDetail(" 백범로 22 지하 1층")
        		.addrZipNum("04091")
        		.build();
        mapper.join(user);
    }
    
    @Test
    @DisplayName("존재하는 회원 아이디를 조회했을 시 1이 리턴되어야 한다.")
    void checkIdTest() {
        	String id = "sindyjj1";
        	
        	assertEquals(1, mapper.idCheck(id));
    }
    
    @Test
    @DisplayName("존재하는 회원 아이디를 입력 했을 시 그 회원의 비밀번호가 리턴되어야 한다.")
    void loginTest() {
    	String id ="sindyjj1";
    	
    	assertNotNull(mapper.login(id));
    	assertNotNull("sindyjj1",mapper.login(id));
        
    }
    
    @Test
    @DisplayName("존재하지 않는 회원의 아이디를 전달하면 null이 올 것이다.")
    void getInfoTest() {
        assertNull(mapper.getInfo("merong"));
    }
    
    @Test
    @DisplayName("id를 제외한 회원의 정보를 수정할 수 있어야 한다.")
    void updateTest() {
        User user = User.builder()
        		.userId("sindyjj")
        		.userPw("jj1023")
        		.userName("정욱")
        		.userEmail1("sindyjj2")
        		.userEmail2("gmail.com")
        		.addrBasic("서울 동작구")
        		.addrDetail("양녕로 170")
        		.addrZipNum("35610")
        		.build();
        mapper.updateUser(user);
        
        assertEquals(user.getUserEmail1(), mapper.getInfo("sindyjj1").getUserEmail1());
    }
}
