package com.nevercome.tabook.modules.sys.dao;

import com.nevercome.tabook.common.persistence.TreeDao;
import com.nevercome.tabook.common.persistence.annotation.MyBatisDao;
import com.nevercome.tabook.modules.sys.entity.Area;

@MyBatisDao
public interface AreaDao extends TreeDao<Area> {

}
