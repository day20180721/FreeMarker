package com.littlejenny.freemaker.controller;

import com.littlejenny.freemaker.model.freemarker.demand.TSR2312001Marker;
import com.littlejenny.freemaker.util.DateUtil;
import com.littlejenny.freemaker.util.FreeMarkerUtil;
import com.littlejenny.freemaker.util.StoreUtil;
import freemarker.template.TemplateException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 王洪棟 - Lin
 * @created date 2023/12/1
 */
@RestController
@RequestMapping("${target.demand}")
public class DemandController {
    @ResponseBody
    @GetMapping("/TSR2312001")
    public String demand00001() throws TemplateException, IOException {
        List<String> startDateList = getDemand00001StartDateList();
        List<String> endDateList = getDemand00001EndDateList();
        TSR2312001Marker marker = new TSR2312001Marker();

        marker.setStoreIdList(StoreUtil.getStoreList());
        marker.setStartAndEndDateList(marker.getFromBothStringList(startDateList, endDateList));

        Map<String, Object> param = FreeMarkerUtil.param().put("marker", marker).build();
        // 生成的String 必須手動刪除最後一個Union all
        return FreeMarkerUtil.getResult(param, "demand-TSR2312001");
    }

    private List<String> getDemand00001StartDateList() {
        LocalDate startDate = DateUtil.getDateFromString("2022-01-01");
        List<String> startDateList = new ArrayList<>();
        for (int i = 0; i < 23; i++) {
            startDateList.add("\'" + DateUtil.getStringFromDate(startDate) + "\'");
            startDate = startDate.plusMonths(1);
        }
        return startDateList;
    }

    private List<String> getDemand00001EndDateList() {
        LocalDate endDate = DateUtil.getDateFromString("2022-02-01");
        List<String> endDateList = new ArrayList<>();
        for (int i = 0; i < 22; i++) {
            endDateList.add("\'" + DateUtil.getStringFromDate(endDate) + "\'");
            endDate = endDate.plusMonths(1);
        }
        endDateList.add("\'" + "2023-11-29" + "\'");
        return endDateList;
    }
}
