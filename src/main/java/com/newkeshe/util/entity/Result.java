package com.newkeshe.util.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Result {
    private Integer rCode;
    private String rMsg;
    public Result(){
        this.rCode = 200;
        this.rMsg = "操作成功";
    }
    public Result(Integer rCode,String rMsg){
        this.rCode = rCode;
        this.rMsg = rMsg;
    }
}
