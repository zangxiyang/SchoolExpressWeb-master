package Beans;


import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class TaskBean {
    private int taskId ,uid , cid ;
    //订单状态 : -> 0 未被领取 , 1:正在进行 , 2:已完成
    private int taskStatus;
    private String name , phone ,pickUpCode , pickupAddress , toAddress;




}
