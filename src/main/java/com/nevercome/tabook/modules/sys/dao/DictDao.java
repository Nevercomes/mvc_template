package com.nevercome.tabook.modules.sys.dao;

import com.nevercome.tabook.common.persistence.CrudDao;
import com.nevercome.tabook.common.persistence.annotation.MyBatisDao;
import com.nevercome.tabook.modules.sys.entity.Dict;

import java.util.List;

@MyBatisDao
public interface DictDao extends CrudDao<Dict> {

    List<String> findTypeList(Dict dict);

}
