package com.littlejenny.freemaker.model;
/**
* @author 王洪棟 - Lin
* @TableName PX_PROM_ITEMS
* @createDate 2023-10-26 16:25:18
*/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RtPxPromItems implements Serializable {

    /**
    * 
    */
    @NotNull(message="[]不能為空")
    private Timestamp workDate;
    /**
    * 
    */
    @NotBlank(message="[]不能為空")
    @Size(max= 30,message="字串長度不能超過30")@Length(max= 30,message="字串長度不能超過30")
    private String pxSeqNo;
    /**
    * 
    */
    @NotNull(message="[]不能為空")
    private Integer itemNo;
    /**
    * 
    */
    @NotNull(message="[]不能為空")
    private Integer pxPromLevel;
    /**
    * 
    */
    @NotBlank(message="[]不能為空")
    @Size(max= 30,message="字串長度不能超過30")@Length(max= 30,message="字串長度不能超過30")
    private String pxPromNo;
    /**
    * 
    */
    @NotBlank(message="[]不能為空")
    @Size(max= 100,message="字串長度不能超過100")@Length(max= 100,message="字串長度不能超過100")
    private String pxPromDescr;
    /**
    * 
    */
    @NotNull(message="[]不能為空")
    private Timestamp sellBeginDate;
    /**
    * 
    */
    @NotNull(message="[]不能為空")
    private Timestamp sellEndDate;
    /**
    * 
    */
    @NotNull(message="[]不能為空")
    private Integer activeSellPrice;
    /**
    * 
    */
    @NotNull(message="[]不能為空")
    private Integer sellPrice2;
    /**
    * 
    */
    @NotNull(message="[]不能為空")
    private Integer normalSellPrice;
    /**
    * 
    */
    private Integer promotionNo;
    /**
    * 
    */
    @Size(max= 1,message="字串長度不能超過1")@Length(max= 1,message="字串長度不能超過1")
    private String promType;
    /**
    * 
    */
    @NotNull(message="[]不能為空")
    private Integer promLevel;
    /**
    * 
    */
    private Integer bonusPoint;
    /**
    * 
    */
    private Integer block;
    /**
    * 
    */
    @NotNull(message="[]不能為空")
    private Integer limitQty;
    /**
    * 
    */
    @Size(max= 30,message="字串長度不能超過30")@Length(max= 30,message="字串長度不能超過30")
    private String promTxt;
    /**
    * 
    */
    @Size(max= 30,message="字串長度不能超過30")@Length(max= 30,message="字串長度不能超過30")
    private String memo;
    /**
    * 
    */
    @Size(max= 1,message="字串長度不能超過1")@Length(max= 1,message="字串長度不能超過1")
    private String ruleType;
    /**
    * 
    */
    private Integer actionNo;
    /**
    * 
    */
    private Integer moduleCode;
    /**
    * 
    */
    @Size(max= 1,message="字串長度不能超過1")@Length(max= 1,message="字串長度不能超過1")
    private String moduleRuleType;
    /**
    * 
    */
    private Integer moduleRuleValue;
    /**
    * 
    */
    private Integer discActionNo;
    /**
    * 
    */
    @Size(max= 1,message="字串長度不能超過1")@Length(max= 1,message="字串長度不能超過1")
    private String discRuleType;
    /**
    * 
    */
    private Integer discRuleValue;
    /**
    * 
    */
    private Integer storeConstQty;
    /**
    * 
    */
    private Integer custConstQty;
    /**
    * 
    */
    private Integer dayLimitType;
    /**
    * 
    */
    private Timestamp updTime;
    /**
     *
     */
    private Integer storeNbr;
}
