package com.teeny.wms.manage.web;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.Putaway.PutawayAddDTO;
import com.teeny.wms.dto.PutawayDTO;
import com.teeny.wms.service.PutOnBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by lilei on 2017/7/19.
 * 上架入库
 */
@Controller
public class PutawayController {

    @Autowired
    private PutOnBillService putOnBillService;

//    //获取单号
//    @ResponseBody
//    @RequestMapping(value = "/api/shelve/orderNoList", method = RequestMethod.GET)
//    public BaseEntity<List<CommonDTO>> getBillList(@RequestHeader("sId") int sId) {
//        return null;
//    }
//
//    //获取货位
//    @ResponseBody
//    @RequestMapping(value = "/api/shelve/allocationList/{orderNoId}", method = RequestMethod.GET)
//    public BaseEntity<List<CommonDTO>> getAllocationList(@PathVariable("orderNoId") int orderNoId) {
//        return null;
//    }
//
//    //获取商品
//    @ResponseBody
//    @RequestMapping(value = "/api/shelve/goodsList/{orderNoId}/{allocationId}", method = RequestMethod.GET)
//    public BaseEntity<List<CommonDTO>> getGoodsList(@PathVariable("orderNoId") int orderNoId, @PathVariable("allocationId") int allocationId) {
//
//        return null;
//    }

    //查询
    @RequestMapping(value = "/api/shelve/goodsDetailList/{orderNoId}", method = RequestMethod.POST)
    public void getPutOnBill(@PathVariable("orderNoId") int orderNoId, @RequestHeader("account") String account, @RequestHeader("sId") int sId) {
        BaseEntity<List<PutawayDTO>> data = putOnBillService.getGoodsDetailList(orderNoId, account, sId);
    }

    //快速上架
    @RequestMapping(value = "/api/shelve/all", method = RequestMethod.POST)
    @ResponseBody
    public BaseEntity putOnQuickly(@RequestBody int orderNoId, @RequestBody int allocationId, @RequestBody int goodsId, @RequestHeader("account") String account) {
        putOnBillService.putOnBillQuickly(orderNoId, allocationId, goodsId, account);
        return new BaseEntity();
    }

    //单个上架
    @RequestMapping(value = "/api/shelve/single", method = RequestMethod.POST)
    @ResponseBody
    public BaseEntity putOnWithOne(@RequestBody int goodsDetailId, @RequestHeader("account") String account) {
        putOnBillService.putOnBillWithOne(goodsDetailId, account);
        return new BaseEntity();
    }

    //修改
    @RequestMapping(value = "/api/shelve/update", method = RequestMethod.POST)
    public void updateByBdId(@RequestBody PutawayAddDTO putawayAddDTO, @RequestHeader("account") String account) {
        putOnBillService.updateOne(putawayAddDTO, account);
    }


}
