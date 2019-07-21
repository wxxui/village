package com.huaxin.mapper;

import java.util.List;

import com.huaxin.pojo.BaseDict;

public interface BaseDictDao {

	List<BaseDict> getDictListByTypeCode(String typeCode);
}
