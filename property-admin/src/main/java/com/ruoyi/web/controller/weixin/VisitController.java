package com.ruoyi.web.controller.weixin;

import java.math.BigDecimal;
import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.property.vo.resp.VisitStatisticsSummaryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.property.domain.Visit;
import com.ruoyi.property.service.VisitService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 来访出入申请Controller
 *
 * @author wind
 * @date 2022-09-29
 */
@Api(tags = "来访出入申请")
@RestController
@RequestMapping("/hcx/property/visit")
public class VisitController extends BaseController {
    @Autowired
    private VisitService visitService;

    /**
     * 查询来访出入申请列表
     */
    @ApiOperation("查询来访出入申请列表")
    @PreAuthorize("@ss.hasPermi('property:visit:list')")
    @GetMapping("/list")
    public TableDataInfo list(Visit visit) {
        startPage();
        List<Visit> list = visitService.selectVisitList(visit);
        return getDataTable(list);
    }

    /**
     * 导出来访出入申请列表
     */
    @ApiOperation("导出来访出入申请列表")
    @PreAuthorize("@ss.hasPermi('property:visit:export')")
    @Log(title = "来访出入申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Visit visit) {
        List<Visit> list = visitService.selectVisitList(visit);
        ExcelUtil<Visit> util = new ExcelUtil<>(Visit.class);
        util.exportExcel(response, list, "来访出入申请数据");
    }

    /**
     * 获取来访出入申请详细信息
     */
    @ApiOperation("获取来访出入申请详细信息")
    @PreAuthorize("@ss.hasPermi('property:visit:query')")
    @GetMapping(value = "/getInfo")
    public AjaxResult getInfo(@RequestParam String id) {
        return AjaxResult.success(visitService.selectVisitById(id));
    }

    /**
     * 新增来访出入申请
     */
    @ApiOperation("新增来访出入申请")
    @PreAuthorize("@ss.hasPermi('property:visit:add')")
    @Log(title = "来访出入申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Visit visit) {
        return toAjax(visitService.insertVisit(visit));
    }

    /**
     * 修改来访出入申请
     */
    @ApiOperation("修改来访出入申请")
    @PreAuthorize("@ss.hasPermi('property:visit:edit')")
    @Log(title = "来访出入申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Visit visit) {
        return toAjax(visitService.updateVisit(visit));
    }

    /**
     * 删除来访出入申请
     */
    @ApiOperation("删除来访出入申请")
    @PreAuthorize("@ss.hasPermi('property:visit:remove')")
    @Log(title = "来访出入申请", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(visitService.deleteVisitByIds(ids));
    }

    @GetMapping("/chart")
    public AjaxResult chart() {
        return AjaxResult.success(visitService.getAmountValueChart(Year.now().getValue()));
    }

    @GetMapping("/statistics/summary")
    public AjaxResult statisticsSummary() {
        Map result = visitService.getStatisticsSummary();
        VisitStatisticsSummaryVo vo = new VisitStatisticsSummaryVo();
        vo.setAudited((Long) result.get("audited"));
        vo.setUnaudited((Long) result.get("unaudited"));
        vo.setTotal((Long) result.get("total"));
        return AjaxResult.success(vo);
    }
}
