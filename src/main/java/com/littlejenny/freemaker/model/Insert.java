package com.littlejenny.freemaker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Insert {
    String columnList;
    String columnListWithColon;
    String tableName;
}
