/*                    Created by Ctl Generator                     */
load data
append
into table L_${procDo.tableNameNotCamel}
fields terminated by '||'
(
PROM_TYPE
)