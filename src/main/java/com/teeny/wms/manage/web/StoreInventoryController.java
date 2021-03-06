package com.teeny.wms.manage.web;

import com.teeny.wms.core.domain.UserEntity;
import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.dto.*;
import com.teeny.wms.security.CurrentUser;
import com.teeny.wms.service.CommonService;
import com.teeny.wms.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lilei on 2017/7/19.
 * 门店初盘
 */
@Controller
public class StoreInventoryController {


    private final InventoryService inventoryService;
    private final CommonService commonService;

    @Autowired
    public StoreInventoryController(InventoryService inventoryService, CommonService commonService) {
        this.inventoryService = inventoryService;
        this.commonService = commonService;
    }

    //门店盘点
    @ResponseBody
    @RequestMapping(value = "/api/shopFirst/getHomeData/{pdType}/{saId}/{areaId}", method = RequestMethod.GET)
    public BaseEntity<List<StoreInventoryGoodsDTO>> getInventory(@PathVariable("pdType") String pdType, @PathVariable("saId") int saId, @PathVariable("areaId") int areaId, @RequestHeader("account") String account, @RequestHeader("sId") int sId) {
        return inventoryService.getInventoryList(pdType, saId, areaId, account, sId);
    }

    //单个完成
    @ResponseBody
    @RequestMapping(value = "/api/shopFirst/single", method = RequestMethod.POST)
    public BaseEntity<Integer> completeOne(@RequestParam("id") int goodsDetailId, @RequestHeader("account") String account, @CurrentUser UserEntity user) {
        BaseEntity<Integer> result = inventoryService.completeOne(goodsDetailId, account, user.getId());
        result.setData(goodsDetailId);
        return result;
    }

    //确定
    @ResponseBody
    @RequestMapping(value = "/api/shopFirst/complete", method = RequestMethod.POST)
    public BaseEntity<String> completeByBillId(@RequestBody List<Integer> ids, @RequestHeader("account") String account, @CurrentUser UserEntity user) {
        return inventoryService.completeByParam(ids, account, user.getId());
    }

    //盘点编辑
    @ResponseBody
    @RequestMapping(value = "/api/shopFirst/edit", method = RequestMethod.POST)
    public BaseEntity<String> edit(@RequestBody PdEditDTO pdEditDTO, @RequestHeader("account") String account, @CurrentUser UserEntity user) {
        return inventoryService.edit(pdEditDTO, account, user.getId());
    }

    //获取库区
    @ResponseBody
    @RequestMapping(value = "/api/shopFirst/saList", method = RequestMethod.GET)
    public BaseEntity<List<CommonDTO>> getSaList(@RequestHeader("account") String account) {
        return commonService.getSaList(account);
    }

    //获取区域
    @ResponseBody
    @RequestMapping(value = "/api/shopFirst/areaList", method = RequestMethod.GET)
    public BaseEntity<List<CommonDTO>> getAreaList(@RequestHeader("account") String account) {
        return commonService.getAreaList(account);
    }

    //获取批次
    @ResponseBody
    @RequestMapping(value = "/api/shopFirst/getLotList", method = RequestMethod.GET)
    public BaseEntity<List<LotDTO>> getLotList(@RequestParam("originalId") int originalId, @RequestHeader("account") String account) {
        return inventoryService.getLotList(originalId, account);
    }

    //获取盘点类型
    //2017/10/12修改
    @ResponseBody
    @RequestMapping(value = "/api/shopFirst/pdType", method = RequestMethod.GET)
    public BaseEntity<List<CommonDTO>> getPdType(@RequestHeader("account") String account, @RequestHeader("sId") int sId) {
        return inventoryService.getPdType(1, account, sId);
    }

    //新增
    @ResponseBody
    @RequestMapping(value = "/api/shopFirst/add", method = RequestMethod.PUT)
    public BaseEntity addProduct(@RequestBody InventoryAddDTO addProductDTO, @RequestHeader("account") String account, @RequestHeader("sId") int sId, @CurrentUser UserEntity user) {
        return inventoryService.addProduct(1, addProductDTO, account, sId, user.getId());
    }


    //2017/10/12修改
    //门店盘点
    @ResponseBody
    @RequestMapping(value = "/api/shopFirst/getHomeData", method = RequestMethod.GET)
    public BaseEntity<List<InventoryGoodsDTO>> getInventory(@RequestParam("id") int id, @RequestHeader("account") String account) {
        return inventoryService.getInventoryList(id, true, account);
    }
}
