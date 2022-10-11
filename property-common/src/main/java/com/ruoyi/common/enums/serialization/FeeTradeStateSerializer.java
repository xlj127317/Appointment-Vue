package com.ruoyi.common.enums.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ruoyi.common.enums.FeeTradeState;

import java.io.IOException;

public class FeeTradeStateSerializer extends JsonSerializer<FeeTradeState> {
    @Override
    public void serialize(FeeTradeState feeTradeState, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(feeTradeState.getValue());
    }
}
