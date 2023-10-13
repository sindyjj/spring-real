package com.spring.myweb.freeboard.dto.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Setter;

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
public class FreeRegistRequestDTO {

	private String title;
	private String content;
	private String writer;
}
