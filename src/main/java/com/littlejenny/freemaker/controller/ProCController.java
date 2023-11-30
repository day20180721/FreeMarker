package com.littlejenny.freemaker.controller;

import com.littlejenny.freemaker.model.ExcelRow;
import com.littlejenny.freemaker.model.freemarker.proc.ProcGrWholeFileMarker;
import com.littlejenny.freemaker.util.FreeMarkerUtil;
import com.littlejenny.freemaker.util.SourceUtil;
import com.littlejenny.freemaker.wrapper.ProcGrExcelRowListWrapper;
import freemarker.template.TemplateException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author 王洪棟 - Lin
 * @created date 2023/11/17
 */
@RestController
@RequestMapping("${target.pro_c}")
public class ProCController {
    @ResponseBody
    @GetMapping("/gr" + "${function.whole_file}" + "${from.excel.column}")
    public String grWholeFileFromExcelColumn(String tableName, String excelColumn) throws TemplateException, IOException {
        List<ExcelRow> excelRowList = SourceUtil.getExcelRowFromOracle(excelColumn);
        ProcGrExcelRowListWrapper wrapper = ProcGrExcelRowListWrapper.of(excelRowList);
        ProcGrWholeFileMarker marker = wrapper.getWholeFile(tableName);
        Map<String, Object> procGrWholeFile = FreeMarkerUtil.param().put("marker", marker).build();

        return FreeMarkerUtil.getResult(procGrWholeFile, "proc-Gr");
    }
}
