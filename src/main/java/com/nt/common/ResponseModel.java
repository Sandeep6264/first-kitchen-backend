package com.nt.common;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseModel<T> {
	  private int responseCode;
	  private String responseMessage;
	  private T responseContent;
	  private LocalDateTime timestamp;
	  private String responseStatus;
}


