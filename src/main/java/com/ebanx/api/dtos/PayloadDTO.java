package com.ebanx.api.dtos;

import com.ebanx.api.enums.RequestType;

public class PayloadDTO {

    private RequestType type;
    private String destination;
    private Double amount;
}
