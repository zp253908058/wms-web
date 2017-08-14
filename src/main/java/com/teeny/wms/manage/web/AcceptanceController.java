package com.teeny.wms.manage.web;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.dto.OrderDetailDTO;
import com.teeny.wms.dto.RecUpdateDTO;
import com.teeny.wms.service.AcceptanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lilei on 2017/7/19.
 * 验收
 */
@Controller
public class AcceptanceController {

    @Autowired
    private AcceptanceService acceptanceService;

    //获取单位
    @RequestMapping(value = "/api/acceptance/unit", method = RequestMethod.GET)
    @ResponseBody
    public BaseEntity<List<CommonDTO>> getUnit(@RequestHeader("account") String account) {
        return acceptanceService.getUnit(account);
    }

    //获取订单
    @RequestMapping(value = "/api/acceptance/orders/{unitId}", method = RequestMethod.GET)
    @ResponseBody
    public BaseEntity<List<CommonDTO>> getOrderWithUnitId(@PathVariable("unitId") int unitId, @RequestHeader("account") String account) {
        return acceptanceService.getOrderWithUnitId(unitId, account);
    }

    //获取订单详情
    @RequestMapping(value = "/api/acceptance/detail/{orderId}", method = RequestMethod.GET)
    @ResponseBody
    public BaseEntity<OrderDetailDTO> getOrderDetailsWithOrderId(Model model, @RequestHeader("account") String account, @PathVariable("orderId") int orderId) {
        return acceptanceService.getOrderDetailsWithOrderId(account, orderId);
    }

    //一键完成
    @RequestMapping(value = "/api/acceptance/allCompete", method = RequestMethod.POST)
    public void allCompeteByOrderId(Model model, @RequestBody RecUpdateDTO recUpdateDTO, @RequestHeader("account") String account) {
        acceptanceService.updateGoodsByOrderId(recUpdateDTO, account);
        //acceptanceService.updateRecBillStatus(1);
    }

    public void competeByGoodsId(Model model, @RequestBody RecUpdateDTO recUpdateDTO, @RequestHeader("account") String account) {
        acceptanceService.updateGoodsByGoodsId(recUpdateDTO, account);
    }

}
