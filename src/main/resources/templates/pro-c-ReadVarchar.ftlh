<#list itemList as item>
    r_${item}[in].arr[ r_${item}[in].len ] = '\0';
    strcpy((char *)${item}.arr, (char *)r_${item}[in].arr ) ;
    ${item}.len = strlen((char *)${item}.arr) ;
    
</#list>

