package com.teeny.wms.core.repository;

import com.teeny.wms.dto.CommonDTO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lilei on 2017/7/22.
 */
@Repository
public interface ClientsRepository {

    @Select("SELECT c_id AS id, name from pda_clients")
    List<CommonDTO> findAll(String account);
}
