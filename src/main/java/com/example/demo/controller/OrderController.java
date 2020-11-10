package com.example.demo.controller;

import com.sjlh.hotel.crs.core.CrsOrderService;
import com.sjlh.hotel.crs.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: HR
 * @Date: 2020/7/17 14:43
 * @Description:
 */
@Controller
public class OrderController {

    @Autowired
    private CrsOrderService crsOrderService;



    @ResponseBody
    @RequestMapping("healthz")
    public  String healthz(){
        return "success";
    }

    /**
     * 调用mvm-drp-crs 创建订单
     * @throws ParseException
     */
    @ResponseBody
    @RequestMapping("create")
    public void create() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        OrderCreateReq crsRoomOrderReq = new OrderCreateReq();
        // crs订单创建
        crsRoomOrderReq.setOrderNo("YD12345678");
        crsRoomOrderReq.setOtaOrderNo("D12345678");
        crsRoomOrderReq.setPmsHotelCode("MTS");
        crsRoomOrderReq.setRateCode("MBUOB");
        crsRoomOrderReq.setRoomCount(1);
        crsRoomOrderReq.setRoomTypeCode("6DS");
        crsRoomOrderReq.setTotalPrice(Double.valueOf(500));
        crsRoomOrderReq.setCheckInDate(sdf.parse("2020-11-20"));
        crsRoomOrderReq.setCheckOutDate(sdf.parse("2020-11-21"));
        crsRoomOrderReq.setProductName("大王棕豪华园景房");
        crsRoomOrderReq.setMobile("13001108111");

        List<OrderGuest> crsGuests = new ArrayList<OrderGuest>();
        OrderGuest orderGuest =  new OrderGuest();
        orderGuest.setFirstName("试");
        orderGuest.setLastName("测");
        orderGuest.setName("测试");
        crsGuests.add(orderGuest);
        crsRoomOrderReq.setGuests(crsGuests);

        List<OrderDayPrice> crsDayPriceList = new ArrayList<>();
        OrderDayPrice orderDayPrice = new OrderDayPrice();
        orderDayPrice.setDate(sdf.parse("2020-11-09"));
        orderDayPrice.setPrice(Double.valueOf(500));
        crsDayPriceList.add(orderDayPrice);
        crsRoomOrderReq.setDayPrices(crsDayPriceList);

//        crsRoomOrderReq.setAccountOfTravelAgency("MWEB");
//        crsRoomOrderReq.setTravelAgency("N1");
//        crsRoomOrderReq.setSource("MWEB");
//        crsRoomOrderReq.setMarket("IND");
//        crsRoomOrderReq.setGuesttypeCode("0000");
        crsRoomOrderReq.setRemarks("TEST");
        OrderCreateRsp crsRoomOrderRsp = crsOrderService.create(crsRoomOrderReq);

        //4195606
    }

    /**
     * 调用mvm-drp-crs 取消订单
     */
    @ResponseBody
    @RequestMapping("cancel")
    public void cancel() {
        OrderCancelReq orderCancelReq = new OrderCancelReq();
        orderCancelReq.setCrsOrderId("4195606");
        crsOrderService.cancel(orderCancelReq);
    }

    /**
     * 调用mvm-drp-crs 获取订单信息
     */
    @ResponseBody
    @RequestMapping("detail")
    public void detail() {
        OrderDetailReq orderDetailReq = new OrderDetailReq();
        orderDetailReq.setCrsOrderId(4195606);
        crsOrderService.detail(orderDetailReq);
    }
}
