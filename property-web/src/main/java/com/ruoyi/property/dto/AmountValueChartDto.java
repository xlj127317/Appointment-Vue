package com.ruoyi.property.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.*;

@Data
public class AmountValueChartDto {
    private List<NamedAggregatedSumDto> Months;
    private List<NamedAggregatedSumDto> Quarters;

    public static class Builder {
        private HashMap<Integer, NamedAggregatedSumDto> months = new HashMap<>();
        private HashMap<Integer, NamedAggregatedSumDto> quarters = new HashMap<>();

        public Builder() {
            for (int i = 1; i <= 12; i++) {
                NamedAggregatedSumDto dto = new NamedAggregatedSumDto();
                dto.setIndex(i);
                dto.setName(String.format("%d月", i));
                dto.setAmount(BigDecimal.ZERO);
                months.put(i, dto);
            }

            for (int i = 1; i <= 4; i++) {
                NamedAggregatedSumDto dto = new NamedAggregatedSumDto();
                dto.setIndex(i);
                dto.setName(String.format("第%d季度", i));
                dto.setAmount(BigDecimal.ZERO);
                quarters.put(i, dto);
            }
        }

        public Builder addMonths(List<AggregatedSumDto> months) {
            for (AggregatedSumDto item : months) {
                this.months.get(item.getIndex()).setAmount(item.getAmount());
            }
            return this;
        }

        public Builder addQuarters(List<AggregatedSumDto> quarters) {
            for (AggregatedSumDto item : quarters) {
                this.quarters.get(item.getIndex()).setAmount(item.getAmount());
            }
            return this;
        }

        public AmountValueChartDto build() {
            List<NamedAggregatedSumDto> months = new ArrayList<>(this.months.values());
            Collections.sort(months, Comparator.comparingInt(NamedAggregatedSumDto::getIndex));

            List<NamedAggregatedSumDto> quarters = new ArrayList<>(this.quarters.values());
            Collections.sort(quarters, Comparator.comparingInt(NamedAggregatedSumDto::getIndex));

            AmountValueChartDto dto = new AmountValueChartDto();
            dto.setMonths(months);
            dto.setQuarters(quarters);
            return dto;
        }
    }
}
