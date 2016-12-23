package com.hz.dxf.dao.factory.config;

import com.hz.dxf.dao.model.DataBaseParamModel;

public class ConfigModel
{
  private static DataBaseParamModel dataBaseParamModel;
  
  public static DataBaseParamModel getDataBaseParamModel()
  {
    return dataBaseParamModel;
  }
  
  public static void setDataBaseParamModel(DataBaseParamModel dataBaseParamModel)
  {
    ConfigModel.dataBaseParamModel = dataBaseParamModel;
  }
}
