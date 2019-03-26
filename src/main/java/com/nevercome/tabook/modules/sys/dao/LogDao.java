package com.nevercome.tabook.modules.sys.dao;

import com.nevercome.tabook.common.persistence.CrudDao;
import com.nevercome.tabook.common.persistence.annotation.MyBatisDao;
import com.nevercome.tabook.modules.sys.entity.Log;

@MyBatisDao
public interface LogDao extends CrudDao<Log> {
}
