package com.xinyi.xinfo.util;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.ITypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;

public class MyOracleTypeConvert implements ITypeConvert {

	@Override
	public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
		String t = fieldType.toLowerCase();
        if (t.contains("char")) {
            return DbColumnType.STRING;
        } else if (t.contains("date") || t.contains("timestamp")) {
        	return DbColumnType.DATE;
        } else if (t.contains("number")) {
            return DbColumnType.LONG;
        } else if (t.contains("float")) {
            return DbColumnType.FLOAT;
        } else if (t.contains("clob")) {
            return DbColumnType.CLOB;
        } else if (t.contains("blob")) {
            return DbColumnType.BLOB;
        } else if (t.contains("binary")) {
            return DbColumnType.BYTE_ARRAY;
        } else if (t.contains("raw")) {
            return DbColumnType.BYTE_ARRAY;
        }
        return DbColumnType.STRING;
	}

}
