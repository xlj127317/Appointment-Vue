package com.ruoyi.web.controller.web;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.FeeTradeState;
import com.ruoyi.property.dto.NamedAggregatedSumDto;
import com.ruoyi.property.dto.FeeTradeListInputDto;
import com.ruoyi.property.dto.FeeTradeOutputDto;
import com.ruoyi.property.service.IFeeTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hcx/property/feetrades")
public class FeeTradesController extends BaseController {
    @Autowired
    private IFeeTradeService feeTradeService;

    @PreAuthorize("@ss.hasPermi('property:feetrades:list')")
    @PostMapping("")
    public TableDataInfo list(@RequestBody FeeTradeListInputDto input)
    {
        startPage();


        List<Integer> multiStateValues = new ArrayList<Integer>();
        multiStateValues.add(FeeTradeState.SUCCEED.getValue());
        multiStateValues.add(FeeTradeState.FINISHED.getValue());

        Map params = BeanUtil.beanToMap(input);
        params.put("multiStateValues", multiStateValues);
        List<Map> trades = feeTradeService.listTrades(params);
        List<FeeTradeOutputDto> output = trades.stream()
                .map(x -> BeanUtil.mapToBean(x, FeeTradeOutputDto.class, true, CopyOptions.create()))
                .collect(Collectors.toList());
        return getDataTable(output);
    }

    @GetMapping("/{tradeId}")
    public FeeTradeOutputDto getFeeTrade(@PathVariable String tradeId)
    {
        Map params = new HashMap();
        params.put("id", tradeId);
        Map trade = feeTradeService.getTrade(params);
        FeeTradeOutputDto dto = BeanUtil.mapToBean(trade, FeeTradeOutputDto.class, false, new CopyOptions());
        return dto;
    }

    @PostMapping(
            value = "/pdf",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public byte[] Generate(@RequestBody List<String> ids) throws DocumentException, IOException {
        BaseFont baseFont = BaseFont.createFont("/fonts/simsun001.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        Font font = new Font(baseFont, 16f);
        Font contentFont = new Font(baseFont, 14f);
        Document document = new Document(PageSize.A3, 0, 0, 32f, 0);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);
        document.open();

        PdfPTable pdfTable = new PdfPTable(8);
        pdfTable.setWidths(new float[]{12, 16.5f, 12.5f, 8.5f, 12.5f, 12.5f, 12.5f, 12.5f});
        PdfPCell cell = new PdfPCell(new Paragraph("支付账单明细列表", new Font(baseFont, 24f)));
        cell.setColspan(8);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        pdfTable.addCell(cell);

        addHeaderCell(pdfTable, "缴费时间", font);
        addHeaderCell(pdfTable, "交易类型", font);
        addHeaderCell(pdfTable, "交易对方", font);
        addHeaderCell(pdfTable, "收/支", font);
        addHeaderCell(pdfTable, "金额（元）", font);
        addHeaderCell(pdfTable, "缴费方式", font);
        addHeaderCell(pdfTable, "缴费状态", font);
        addHeaderCell(pdfTable, "交易单号", font);

        List<Integer> multiStateValues = new ArrayList<Integer>();
        multiStateValues.add(FeeTradeState.SUCCEED.getValue());
        multiStateValues.add(FeeTradeState.FINISHED.getValue());

        Map params = new HashMap();
        params.put("multiIdValues", ids);
        params.put("multiStateValues", multiStateValues);
        List<Map> trades = feeTradeService.listTrades(params);
        List<FeeTradeOutputDto> items = trades.stream()
                .map(x -> BeanUtil.mapToBean(x, FeeTradeOutputDto.class, true, CopyOptions.create()))
                .collect(Collectors.toList());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < 100; i++) {
            for (FeeTradeOutputDto item: items) {
                addCell(pdfTable, dateFormat.format(item.getPaidAt()), contentFont);
                addCell(pdfTable, item.getSubject(), contentFont);
                addCell(pdfTable, item.getOwnerId(), contentFont);
                addCell(pdfTable, "支出", contentFont);
                addCell(pdfTable, "￥" + item.getPaidAmount().toPlainString(), contentFont);
                addCell(pdfTable, "微信支付", contentFont);
                addCell(pdfTable, "支付成功", contentFont);
                addCell(pdfTable, item.getNo(), contentFont);
            }
        }

        document.add(pdfTable);

        document.close();

        return outputStream.toByteArray();
    }

    private void addHeaderCell(PdfPTable table, String text, Font font) {
        PdfPCell cell = new PdfPCell(new Paragraph(text, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setNoWrap(true);
        table.addCell(cell);
    }

    private void addCell(PdfPTable table, String text, Font font) {
        PdfPCell cell = new PdfPCell(new Paragraph(text, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
    }

    @GetMapping("statistics/summary")
    public Map summaryStatistics() {
        HashMap<Integer, NamedAggregatedSumDto> allByMonth = new HashMap<Integer, NamedAggregatedSumDto>();
        for (int i = 1; i <= 12; i++) {
            NamedAggregatedSumDto dto = new NamedAggregatedSumDto();
            dto.setName(String.format("%d月", i));
            dto.setAmount(BigDecimal.ZERO);
            allByMonth.put(i, dto);
        }

        HashMap<Integer, NamedAggregatedSumDto> allByQuarter = new HashMap<Integer, NamedAggregatedSumDto>();
        for (int i = 1; i <= 4; i++) {
            NamedAggregatedSumDto dto = new NamedAggregatedSumDto();
            dto.setName(String.format("第%d季度", i));
            dto.setAmount(BigDecimal.ZERO);
            allByQuarter.put(i, dto);
        }

        Map output = new HashMap();

        List<Map> byMonths = feeTradeService.statisticSummaryByMonth(Year.now().getValue());
        for (Map item : byMonths) {
            NamedAggregatedSumDto dto = allByMonth.get((Integer)item.get("month"));
            dto.setAmount((BigDecimal)item.get("paidAmount"));
        }

        output.put("months", allByMonth);

        List<Map> byQuarters = feeTradeService.statisticSummaryByQuarter(Year.now().getValue());
        for (Map item : byQuarters) {
            NamedAggregatedSumDto dto = allByQuarter.get((Integer)item.get("quarter"));
            dto.setAmount((BigDecimal)item.get("paidAmount"));
        }

        output.put("quarters", allByQuarter);

        return output;
    }
}
