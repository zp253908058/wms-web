package com.teeny.wms.service.impl;

import com.teeny.wms.core.domain.baseEntity.BaseEntity;
import com.teeny.wms.core.repository.TranBillRepository;
import com.teeny.wms.dto.CommonDTO;
import com.teeny.wms.dto.LocationAndCountDTO;
import com.teeny.wms.dto.Putaway.PutawayAddDTO;
import com.teeny.wms.dto.TransferListDTO;
import com.teeny.wms.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lilei on 2017/8/7.
 */
@Service
@Transactional
public class TransfenServiceImpl implements TransferService {

    @Autowired
    TranBillRepository tranBillRepository;

    @Override
    public BaseEntity<List<TransferListDTO>> getTransferList(String billNo, String account) {
        List<TransferListDTO> list = tranBillRepository.getTransferList(billNo, account);
        tranBillRepository.updateBill(billNo, account);
        return new BaseEntity<List<TransferListDTO>>(list);
    }

    @Override
    public BaseEntity<String> updateAll(List<Integer> ids, String account) {
        if (ids.size() > 0) {
            for (Integer id : ids) {
                tranBillRepository.updateOne(id, account);
            }
            int count = tranBillRepository.countByDealstatus(ids.get(0), account);
            if (count == 0) {
                tranBillRepository.updateBillStatusBySmbId(ids.get(0), account);
            }
            return new BaseEntity<String>();
        }
        return null;
    }

    @Override
    public BaseEntity<String> updateOne(int id, String account) {
        tranBillRepository.updateOne(id, account);
        int count = tranBillRepository.countByDealstatus(id, account);
        if (count == 0) {
            tranBillRepository.updateBillStatusBySmbId(id, account);
        }
        return new BaseEntity<String>();
    }

    @Override
    public BaseEntity<String> update(PutawayAddDTO putawayAddDTO, String account) {

        List<Integer> ids = tranBillRepository.getIdsBySmbId(putawayAddDTO.getId(), account);
        if (putawayAddDTO.getLocations().size()>0) {
            for (PutawayAddDTO.Location loc : putawayAddDTO.getLocations()) {
                tranBillRepository.copyData(putawayAddDTO.getId(),loc.getAmount(),loc.getLocationCode());
            }
            for (Integer i : ids) {
                tranBillRepository.deleteById(i,account);
            }
        }else {
            tranBillRepository.copyData(putawayAddDTO.getId(),0,"");
        }
        int count = tranBillRepository.countByDealstatus(putawayAddDTO.getId(), account);
        if (count == 0) {
            tranBillRepository.updateBillStatusBySmbId(putawayAddDTO.getId(), account);
        }

//        //tranBillRepository.updateDetails(putawayAddDTO.getId(), putawayAddDTO.getAmount(), account);
//        for (PutawayAddDTO.Location location : putawayAddDTO.getLocations()) {
//            tranBillRepository.copyData(putawayAddDTO.getId(), location.getAmount(), location.getLocationCode());
//        }
//        int count = tranBillRepository.countByDealstatus(putawayAddDTO.getId(), account);
//        if (count == 0) {
//            tranBillRepository.updateBillStatusBySmbId(putawayAddDTO.getId(), account);
//        }
        return new BaseEntity<String>();
    }

    @Override
    public BaseEntity<List<LocationAndCountDTO>> getLocationListById(int id, String account) {

        List<LocationAndCountDTO> list = tranBillRepository.getLocationById(id, account);

        return new BaseEntity<List<LocationAndCountDTO>>(list);
    }

    @Override
    public BaseEntity<List<CommonDTO>> getBills(int saId, int sId, String account) {
        List<CommonDTO> list = tranBillRepository.getBills(saId, sId, account);
        return new BaseEntity<List<CommonDTO>>(list);
    }

}
