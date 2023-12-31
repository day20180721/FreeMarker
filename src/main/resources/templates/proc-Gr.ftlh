/**********************************************************************/
/*      gr_${marker.tableNameLowerButNotCamel}.pc            */
/**********************************************************************/
/* Usage:gr_${marker.tableNameLowerButNotCamel}  <id/pwd>    */
/*                                                                    */
/*                    Created by ProcGr Generator                     */
/**********************************************************************/
#include <stdio.h>
#include <string.h>

#define OK 0
#define ORAERR 9
#define TRUE  1
#define FETCH_SIZE 100
#define FALSE 0

#define FORMAT "${marker.printFormat}\n"
/* 預設在最後輸出error_msg，所以加上%s||，||是為了後續ctl的load_data方便讀取 */
#define ERRORFORMAT "${marker.printFormat}%s||\n"
#define SQLCA_INIT      1
EXEC SQL INCLUDE SQLCA.H;
#define ORACA_INIT      1
EXEC SQL INCLUDE ORACA.H;

EXEC SQL BEGIN DECLARE SECTION;

VARCHAR oracle_id[30];
VARCHAR error_msg[50];

/* 為了讀取的屬性 */
/* 由Excel複製過來的屬性的長度會被生成器 + 1，因為C語言中String需要 '\0' 作為字串結尾符號 */
/* Oracle中 Date 與 DateTime會預設當成VARCHAR(30)來處理 */
${marker.readProperty}

/* 為了打印的屬性 */
${marker.writeProperty}

/* 為了處理Fetch時Null的問題 */
${marker.checkForNullProperty};

EXEC SQL END DECLARE SECTION;
EXEC SQL WHENEVER SQLERROR GOTO error;

/*--------------------------------------------------------------------*/
/*                      main()                                        */
/*--------------------------------------------------------------------*/
FILE * fp;

main(argc, argv)
  int argc;
  char *argv[];
{
  char str[132];

  if (argc != 2) {
    sprintf(str, "Usage: %s <id/pw>" , argv[0]);
    prog_error(str);
    ap_exit(1);
  }

  strcpy((char *)oracle_id.arr, argv[1]);
  oracle_id.len = strlen((char *)oracle_id.arr);
  EXEC SQL CONNECT :oracle_id;

  fp = fopen ("invalid-data.txt", "w+");

  process();

  fclose(fp);

  ap_exit(OK);

error:
  dbs_error("main");
  ap_exit(ORAERR);
} /* main() */
/*--------------------------------------------------------------------*/
/*                       process()                                    */
/*--------------------------------------------------------------------*/

int dataHasError = 0;

process()
{
  while (read_data()) {
      if(dataHasError){
        log_error_to_error_txt();
        update_${marker.tableNameLowerButNotCamel}_status_to_error();
      } else {
        print_success_${marker.tableNameLowerButNotCamel}();
        update_${marker.tableNameLowerButNotCamel}_status_to_success();
      }
  }

  return;
}

/*--------------------------------------------------------------------*/
/*                      read_data()                                   */
/*--------------------------------------------------------------------*/

read_data()
{
  char str[100];
  static int first_time = 1,
             now        = 0,
             this       = FETCH_SIZE,
             in         = FETCH_SIZE;

  dataHasError = 0;

  if(first_time){
    first_time = 0;
    EXEC ORACLE OPTION( HOLD_CURSOR=YES );
    EXEC SQL DECLARE DATA_1 CURSOR FOR
    SELECT
${marker.prettyPropertyNameList}
      FROM  ${marker.tableNameUpperButNotCamel}
    EXEC SQL OPEN DATA_1;
  }

  if(this == in){
    if(this < FETCH_SIZE){
      EXEC ORACLE OPTION( HOLD_CURSOR=NO );
      EXEC SQL CLOSE DATA_1;
      return(FALSE);
    }

    EXEC SQL FETCH DATA_1
    INTO
${marker.sqlFetchStatement};

    this = sqlca.sqlerrd[2] - now;
    now  = sqlca.sqlerrd[2];
    in   = 0;
    if(!this){
      EXEC ORACLE OPTION( HOLD_CURSOR=NO );
      EXEC SQL CLOSE DATA_1;
      return(FALSE);
    }
  }
  /* 讀出來的屬性放入打印屬性中 */
${marker.copyValue}

  /* 判斷屬性是否符合規格，不符合的話 dataHasError 設定為 1 */
${marker.filterForErrorMsg}

  in++;
  return(TRUE);

error:
  dbs_error("read_data");
  ap_exit(ORAERR);
} /* read_data() */


log_error_to_error_txt(){
    fprintf(fp, ERRORFORMAT,
${marker.prettyPropertyNameList},
                (char *)error_msg.arr
            );
    return;

error:
  dbs_error("log_error_to_error_txt");
  ap_exit(ORAERR);
} /* log_error_to_error_txt */

update_${marker.tableNameLowerButNotCamel}_status_to_error(){

  EXEC SQL
  UPDATE    ${marker.tableNameUpperButNotCamel}
     SET    STATUS = 9, /* 9 異常 */
            ERR_MSG = :error_msg
   WHERE    CONDITION = :condition;


   return;
error:
  dbs_error("update_${marker.tableNameLowerButNotCamel}_status_to_error");
  ap_exit(ORAERR);
} /* update_${marker.tableNameLowerButNotCamel}_status_to_error */

print_success_${marker.tableNameLowerButNotCamel}(){
    printf(FORMAT,
${marker.prettyPropertyNameList}
          );
     return;
error:
  dbs_error("print_success_${marker.tableNameLowerButNotCamel}");
  ap_exit(ORAERR);
}/* print_success_${marker.tableNameLowerButNotCamel} */

update_${marker.tableNameLowerButNotCamel}_status_to_success(){

  EXEC SQL
  UPDATE    ${marker.tableNameUpperButNotCamel}
     SET    STATUS = 1, /* 1 成功 */
            ERR_MSG = null
   WHERE    condition=:condition;


     return;
error:
  dbs_error("update_${marker.tableNameLowerButNotCamel}_status_to_success");
  ap_exit(ORAERR);
} /* update_${marker.tableNameLowerButNotCamel}_status_to_success */