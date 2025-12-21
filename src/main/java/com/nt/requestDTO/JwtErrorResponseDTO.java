package com.nt.requestDTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JwtErrorResponseDTO {
	private int responseCode;
    private String responseContent;
    private String responseMessage;
    private String responseStatus;
    private LocalDateTime timestamp;
    
}
