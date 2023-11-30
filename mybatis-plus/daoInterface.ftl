package ${daoInterface.packageName};

/**
* @author 王洪棟 - Lin
* @description 針對表【${tableClass.tableName}<#if tableClass.remark?has_content>(${tableClass.remark!})</#if>】的數據庫操作DaoImpl
* @createDate ${.now?string('yyyy-MM-dd HH:mm:ss')}
*/

import ${dto.packageName}.${dto.fileName};
import java.util.List;


public interface ${daoInterface.fileName} {

    List<${dto.fileName}> list();
    int saveBatch(List<${dto.fileName}> ${dto.fileName?uncap_first}List);
    int updateBatch(List<${dto.fileName}> ${dto.fileName?uncap_first}List);
    int delete();
    int deleteBatch();

}
