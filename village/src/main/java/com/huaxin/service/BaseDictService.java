package com.huaxin.service;

import java.util.List;

import com.huaxin.pojo.BaseDict;

public interface BaseDictService {

	List<BaseDict> getDictListByTypeCode(String typeCode);
}
