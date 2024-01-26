-- 網際網路包裝減量法規
SELECT  AA.STORE_ID AS "店號",
        B.LOC_EN_NAME AS "店名",
        AA.TXN_RPT_AS_DT AS "入賬日期",
        AA.TRANSATION_ID AS "送貨單號",
        AA.ITEM_ID AS "商品ID",
        C.ITEM_NAME AS "商品名稱",
        AA.QTY AS "商品數量",
        G.DIVISION_CD AS "處別ID",
        G.DIVISION_NAME AS "處別名稱",
        F.SECTION_CD AS "課別ID",
        F.SECTION_NAME AS "課別名稱",
        E.ITEM_CLASS_CD AS "大類別ID",
        E.ITEM_CLASS_NAME AS "大類別名稱",
        D.ITEM_SUBCS_CD AS "小類別ID",
        D.ITEM_SUBCS_NAME AS "小類別名稱"
FROM (
<#list marker.storeIdList as storeId>
    <#list marker.startAndEndDateList as startAndEndDate>
        SELECT A.TXN_RPT_AS_DT AS "TXN_RPT_AS_DT",
        A.SELL_LOC_ID AS "STORE_ID",
        A.SHPG_TXN_ID AS "TRANSATION_ID",
        B.ITEM_ID AS "ITEM_ID",
        B.ITEM_QTY AS "QTY"
        FROM PDATA.SHPG_TXN_ST${storeId}    A,
        PDATA.SHPG_TXN_LN_ST${storeId} B,
        PMART.TNV_FCT_ITEM_STST_DLY C
        WHERE A.TXN_DT=B.TXN_DT
        AND A.SELL_LOC_ID=B.SELL_LOC_ID
        AND A.POS_RGSTR_ID=B.POS_RGSTR_ID
        AND A.SHIFT_NO=B.SHIFT_NO
        AND A.SHPG_TXN_ID=B.SHPG_TXN_ID
        AND A.TXN_RPT_AS_DT >= ${startAndEndDate.startDate}
        AND A.TXN_RPT_AS_DT < ${startAndEndDate.endDate}
        AND A.PK_TYPE_CD = 3                       -- 3.ec
        AND C.DIVISION_CD != 9                     -- 第9處(EX.運費)不列入計算
        AND B.TXN_DT = C.CLND_DT
        AND B.SELL_LOC_ID = C.LOC_ID
        AND B.ITEM_ID = C.ITEM_ID
        union all
    </#list>
</#list>
) AA,  --  各店POS銷售明細
        PDATA.LOC B,
        PDATA.ITEM C,
        PDATA.ITEM_SUBCS D,
        PDATA.ITEM_CLASS E,
        PDATA.LOC_SECTION F,
        PDATA.LOC_DIVISION G
WHERE   AA.STORE_ID = B.LOC_ID
        AND AA.ITEM_ID = C.ITEM_ID
        AND C.ITEM_CLASS_CD = D.ITEM_CLASS_CD
        AND C.ITEM_SUBCS_CD = D.ITEM_SUBCS_CD
        AND D.ITEM_CLASS_CD = E.ITEM_CLASS_CD
        AND D.SECTION_CD = F.SECTION_CD
        AND F.DIVISION_CD = G.DIVISION_CD