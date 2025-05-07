package com.ebanx.api.dtos;

import com.ebanx.api.enums.RequestType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDTO {

    private RequestType type;
    private Long destination;
    private Long origin;
    private Integer amount;
}
