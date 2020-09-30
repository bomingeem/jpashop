package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {
    //폼 객체를 사용하서 화면 계층과 서비스 계층을 명확하게 분리한다
    @NotEmpty(message = "회원 이름은 필수입니다")
    private String name;
    
    private String city;
    private String street;
    private String zipcode;
}
