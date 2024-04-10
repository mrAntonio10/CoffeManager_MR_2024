package com.upb.coffe.rest.request;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {

    private String userId;
    private String secret;


}


