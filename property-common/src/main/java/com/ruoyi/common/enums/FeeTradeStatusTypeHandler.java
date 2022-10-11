package com.ruoyi.common.enums;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FeeTradeStatusTypeHandler implements TypeHandler<FeeTradeState> {

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, FeeTradeState feeTradeState, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, feeTradeState.ordinal());
    }

    @Override
    public FeeTradeState getResult(ResultSet resultSet, String s) throws SQLException {
        int enumValue = resultSet.getInt(s);
        return FeeTradeState.values()[enumValue];
    }

    @Override
    public FeeTradeState getResult(ResultSet resultSet, int i) throws SQLException {
        int enumValue = resultSet.getInt(i);
        return FeeTradeState.values()[enumValue];
    }

    @Override
    public FeeTradeState getResult(CallableStatement callableStatement, int i) throws SQLException {
        return null;
    }
}
