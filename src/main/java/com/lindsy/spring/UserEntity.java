package com.lindsy.spring;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserEntity {
    private String uid;
    private String upw;
    private String unm;
    private String regdt;
    private String profileImg;
    private int iuser;
    private int gender;


}
