package com.tdtk.mapper;

import com.tdtk.pojo.SearchRecords;
import com.tdtk.utils.MyMapper;

import java.util.List;

public interface SearchRecordsMapper extends MyMapper<SearchRecords> {
   public List<String> getHotWords();
}