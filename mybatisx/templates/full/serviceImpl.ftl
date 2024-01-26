package ${serviceImpl.packageName};

/**
* @author 王洪棟 - Lin
* @description 針對表【${tableClass.tableName}<#if tableClass.remark?has_content>(${tableClass.remark!})</#if>】的數據庫操作service
* @createDate ${.now?string('yyyy-MM-dd HH:mm:ss')}
*/

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import ${serviceInterface.packageName}.${serviceInterface.fileName};
import ${daoInterface.packageName}.${daoInterface.fileName};
import ${dto.packageName}.${dto.fileName};
import java.util.List;


@Service
@Slf4j(topic = ".info")
public class ${serviceImpl.fileName} implements ${serviceInterface.fileName}   {
    @Autowired
    private ${daoInterface.fileName} ${daoInterface.fileName?uncap_first};
    @Override
    public List<${dto.fileName}> list(){
        return ${daoInterface.fileName?uncap_first}.select();
    }
    @Override
    public int saveBatch(List<${dto.fileName}> ${dto.fileName?uncap_first}List){
        return ${daoInterface.fileName?uncap_first}.insertBatch(${dto.fileName?uncap_first}List);
    }
    @Override
    public int updateBatch(List<${dto.fileName}> ${dto.fileName?uncap_first}List){
        return ${daoInterface.fileName?uncap_first}.updateBatch(${dto.fileName?uncap_first}List);
    }
    @Override
    public int delete(){
        return ${daoInterface.fileName?uncap_first}.delete();
    }
    @Override
    public int deleteBatch(){
        return ${daoInterface.fileName?uncap_first}.deleteBatch();
    }
}
