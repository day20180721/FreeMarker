package com.littlejenny.freemaker.model.freemarker.demand;

import com.littlejenny.freemaker.model.StartAndEndDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 王洪棟 - Lin
 * @created date 2023/12/1
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TSR2312001Marker {
    private List<String> storeIdList;
    private List<StartAndEndDate> startAndEndDateList;

    public List<StartAndEndDate> getFromBothStringList(List<String> startDateList, List<String> endDateList) {
        if (startDateList.size() != endDateList.size()) {
            throw new RuntimeException("開始跟結束時間必須是一對");
        }
        List<StartAndEndDate> startAndEndDates = new ArrayList<>();
        for (int i = 0; i < startDateList.size(); i++) {
            StartAndEndDate startAndEndDate = new StartAndEndDate(startDateList.get(i), endDateList.get(i));
            startAndEndDates.add(startAndEndDate);
        }
        return startAndEndDates;
    }

}
