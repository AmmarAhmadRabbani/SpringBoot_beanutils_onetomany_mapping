package com.te.beanutils.response;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Component
public class AppResponse {
	private boolean error;
	private String message;
	private int status;
	private Object data;
}
