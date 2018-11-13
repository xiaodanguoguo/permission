package com.ego.services.base.facade.conf;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ego.services.base.facade.model.jurisdiction.FunctionManage;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.apache.commons.lang3.StringUtils;

//import com.hejinonline.sqlanalyze.model.CodeInfo;
//import com.hejinonline.sqlanalyze.model.ErrMsg;
//import com.hejinonline.sqlanalyze.model.ParseResult;
//import com.hejinonline.sqlanalyze.model.WhereModel;
//import com.hejinonline.sqlanalyze.util.FileUtil;

import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.drop.Drop;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.replace.Replace;
import net.sf.jsqlparser.statement.truncate.Truncate;
import net.sf.jsqlparser.statement.update.Update;
import org.springframework.util.CollectionUtils;


public class JsqlParser {


    public static void main(String[] args) throws Exception {
    	JsqlParser parse = new JsqlParser();
//        StringBuilder sql = new StringBuilder().append("insert into ALLLOG (LOGID, NAME, age) values (4, test);");
//    	String sql = "insert into ALLLOG (LOGID, NAME) values (4, test)";

    	//union下还有union的需要递归获取所有的select语句未解决,
    	//insert as select not enough values的问题需要解决？ 如 Insert into Table2(a, c, d) select a,c from Table1 where id=2
//    	String sql = "Insert into Table2(a, c, d) select a,c,d from Table1 where id=2 union select a,b,c from Table2 where id = 2 ";
//    	String sql = "select id, name from table2 where id = 2 or id = 3 and id = 3 and id = 4";


        //String sql = "select b.id as id from table2 b where b.id = 2 union all select c.id as id from table2 c where c.id = 3 ";

        String sql ="select c.id,c.biid,d.id,a.id from tabit c " +
                " left join tabitc as d on d.id=c.id " +
                " left join tabite as a on a.id=c.id " +
                " where c.id=1 and d.id=2 and a.id=3 " +
                " union all " +
                " select c1.id,c1.biid,d1.id,a1.id from tabit1 c1 " +
                " left join tabit2 as d1 on d1.id=c1.id " +
                " left join tabit3 as a1 on a1.id=c1.id " +
                " where c1.id=1 and d1.id=2 and a1.id=3 " +
                "  ";



//    	String sql = "select id, name from table2 where column_name BETWEEN value1 AND value2 and id = 2 or id = 3 and id = 3 and id = 4";
//    	String sql = "select id, name from table1 where id = 1 UNION select id, name from table2 where column_name BETWEEN value1 AND value2 id = 2 or id = 3";
//    	String sql = "update ALLLOG set id = 1, name = 2 where id = 1 and name = 2 and 1 = 2 or 1 = 1 or 1 = 1";
//    	String sql = "update ALLLOG set id = 1, name = 111 where id = to_date(2016-09-22, yyyy-mm-dd)";
//    	String sql = "insert into CHKPREACCT1999_HIS(RECDATE,VERNO,OPERNO,MOVEWHY,MOVEDATE,VERDATE,ABSTR,RESPRE,SUBPRE,ADDPRE,PIECES,ENDDATE,BEGDATE,CHGCODE,PRECODE,APPF,CLASSCODE,BRANCHID,UPDATETIME,COMPID,UTIME,PRECODE3,PRECODE2,PRECODE1,TYPENO,DELCODE,APPNO,GAPPNO,GRPCODE,POLICYNO,GPOLICYNO,PID) values (0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);";
//    	String sql = "update PLANTRAVEL set begdate=to_date('2016-09-23','yyyy-mm-dd') , updatetime=to_char(sysdate,'yyyy/mm/dd hh24:mi:ss') where gpolicyno='220G171AA040001' and branchid='00000000000029';  ";
//    	String sql = "delete from test ";

    	//String sql = "insert into zylife.acctlist (FOOTDATE, GRPCODE, GPOLICYNO, PID, POLICYNO, SPOLICYNO, CLASSCODE, APPF, FOOTCODE, YEAR, ABSTR, RATETYPE, RATE1, RATE2, RATE3, RATE4, RATE5, ADDAMT, SUBAMT, RESAMT, ADDPRE, SUBPRE, RESPRE, EMPNO, CONTNO, OPERNO, PROCDATE, VERNO, CHKDATE, MOVEDATE, MOVEWHY, UTIME, DAC, DEPTNO, NO, TYPE, ADDUNIT, SUBUNIT, INPRICE, OUTPRICE, ACCTYPE, ACCSRC, COMPID, UPDATETIME, BRANCHID)select FOOTDATE, GRPCODE, GPOLICYNO, PID, POLICYNO, SPOLICYNO, CLASSCODE, APPF, FOOTCODE, YEAR, ABSTR, RATETYPE, RATE1, RATE2, RATE3, RATE4, RATE5, ADDAMT, SUBAMT, RESAMT, ADDPRE, SUBPRE, RESPRE, EMPNO, CONTNO, OPERNO, PROCDATE, VERNO, CHKDATE, MOVEDATE, MOVEWHY, UTIME, DAC, DEPTNO, NO, TYPE, ADDUNIT, SUBUNIT, INPRICE, OUTPRICE, ACCTYPE, ACCSRC, COMPID, UPDATETIME, BRANCHIDfrom zylife.lost_acctlist where gpolicyno='160G101EP400007' and footdate=date'2016-06-24' and addamt='109448.8'";
    	if (sql.contains("\""))
    		System.out.println(sql.replace("\"", ""));
        Statement statement = (Statement) CCJSqlParserUtil.parse(sql);

        if (statement instanceof Select) {
            Select selectStatement = (Select) statement;
            SelectBody selectBody = selectStatement.getSelectBody();
            if (selectBody instanceof PlainSelect) {
                getParent(selectBody,sql);
                //如果是Union类型的select
            } else if (selectBody instanceof SetOperationList) {
                SetOperationList unionSelect = (SetOperationList)selectBody;
                List<SelectBody> list=unionSelect.getSelects();
                for(int i=0;i<list.size();i++){
                    if (list.get(i) instanceof PlainSelect) {
                        PlainSelect plainSelect = (PlainSelect) list.get(i);
                        Expression where = plainSelect.getWhere();
                        getParent(list.get(i),list.get(i).toString());
                        //String name = plainSelect.getFromItem().getAlias().getName();
                        //如果是Union类型的select
                    } else if (list.get(i) instanceof SetOperationList) {
                        //getParent(list.get(i),selectStatement);
                    }
                }
//                List<PlainSelect> plainSelects = unionSelect.getPlainSelects();
//                for (PlainSelect plainSelect : plainSelects) {
//
//                }
            }
        }

//		String csvFileName = "/Users/kim/Documents/work/poc/POC测试第二批次/testdata-noresult.csv";
//		String excelFileName = "/Users/kim/Documents/work/poc/POC测试第二批次/result.xls";
//		List<String> sqlList = FileUtil.getSqlList(csvFileName);
//		List<ParseResult> results = new ArrayList<ParseResult>();
//		for (String sql : sqlList) {
//			if (sql.contains("\"")) {
//				String newSql = sql.replace("\"", "");
//				results.add(parse.grammarParse(newSql));
//			} else
//				results.add(parse.grammarParse(sql));
//		}
//
//		FileUtil.writeExcel(excelFileName, results);
    }


    private static String getParent(SelectBody selectBody,String sql) throws JSQLParserException {
        Statement statement = (Statement) CCJSqlParserUtil.parse(sql);
        Select selectStatement = (Select) statement;
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        List tableList = tablesNamesFinder.getTableList(selectStatement);
        for(int i=0;i<tableList.size();i++){
            System.out.println(tableList.get(i));
        }

        PlainSelect plainSelect = (PlainSelect) selectBody;
        Expression where = plainSelect.getWhere();
        System.out.println(where);
        String name = plainSelect.getFromItem().getAlias().getName();
        //from 主表 别名
        System.out.println(name);
        //from 表名+别名
        System.out.println(plainSelect.getFromItem());

        if(!CollectionUtils.isEmpty(plainSelect.getJoins())){
            for(int i=0;i<plainSelect.getJoins().size();i++){
                Join join=plainSelect.getJoins().get(i);
                System.out.println(join.getRightItem().getAlias().getName());
            }
        }
        return "";
    }
//
//    public ParseResult grammarParse(String sql) {
//    	ParseResult result = new ParseResult();
//    	result.setSql(sql);
//
//    	//去掉=date'的情况
//		if (sql.toLowerCase().indexOf("=date'") != -1)
//			sql = sql.toLowerCase().replace("=date'", "='");
//		if (sql.toLowerCase().indexOf("=date '") != -1)
//			sql = sql.toLowerCase().replace("=date '", "='");
//		if (sql.toLowerCase().indexOf("= date '") != -1)
//			sql = sql.toLowerCase().replace("= date '", "='");
//
//		if (StringUtils.isEmpty(sql)) {
//			result.setCode(CodeInfo.SQL_ERR);
//			result.setMessage(ErrMsg.SQL_EMPTY);
//			result.setSuccess(false);
//			return result;
//		}
//
//        CCJSqlParserManager parseManager = new CCJSqlParserManager();
//        Statement statement = null;
//        try {
//        	statement = parseManager.parse(new StringReader(sql));
//		} catch (Exception e) {
//			return setError(result, e.getCause().toString());
//		} catch(Error e1) {
//			return setError(result, e1.getMessage().toString());
//		}
//
//        if (statement instanceof Insert) {
//	        Insert insertStatement = (Insert) statement;
//	        List<?> columns = insertStatement.getColumns();
//
//	        ItemsList itemsList = insertStatement.getItemsList();
//	        if (itemsList instanceof ExpressionList) {
//		        ExpressionList items = (ExpressionList) insertStatement.getItemsList();
//		        List<?> expressions = items.getExpressions();
//
//		        //校验参数长度是否和字段长度一致
//		        if (columns != null)
//			        if (columns.size() != expressions.size()) {
//			        	result.setCode(CodeInfo.SQL_ERR);
//						result.setMessage(ErrMsg.PARAM_SIZE_ERR);
//						result.setSuccess(false);
//						return result;
//			        }
//
//	        } else if(itemsList instanceof SubSelect) {
//	        	SubSelect select  = (SubSelect) itemsList;
//	        	SelectBody selectBody = select.getSelectBody();
//	        	return checkSelectBodyWhere(selectBody, result);
//	        }
//
//        } else if (statement instanceof Update)
//        	return checkWhere(((Update) statement).getWhere(), result);
//
//        else if (statement instanceof Delete)
//        	return checkWhere(((Delete) statement).getWhere(), result);
//
//        else if (statement instanceof Select) {
//        	Select selectStatement = (Select) statement;
//        	SelectBody selectBody = selectStatement.getSelectBody();
//        	return checkSelectBodyWhere(selectBody, result);
//
//        } else {
//        	result.setCode(CodeInfo.SQL_ERR);
//			if (statement instanceof Drop)
//				result.setMessage(ErrMsg.NOT_SUPPORT_DROP);
//			else if (statement instanceof Truncate)
//				result.setMessage(ErrMsg.NOT_SUPPORT_TRUNCATE);
//			else if (statement instanceof CreateTable)
//				result.setMessage(ErrMsg.NOT_SUPPORT_CREATE_TABLE);
//			else if (statement instanceof Replace)
//				result.setMessage(ErrMsg.NOT_SUPPORT_REPLACE);
//			else if (sql.toUpperCase().indexOf("ALTER TABLE") != -1)
//				result.setMessage(ErrMsg.NOT_SUPPORT_ALTER_TABLE);
//        }
//        return result;
//    }
//
//    private ParseResult setError(ParseResult result, String error) {
//    	result.setCode(CodeInfo.SQL_ERR);
//		result.setMessage("语法错误:" + error.substring(error.indexOf(":") + 1));
//
//		result.setSuccess(false);
//		return result;
//	}
//
//	private ParseResult checkWhere(Expression where, ParseResult result) {
//    	if (where == null) {
//    		result.setCode(CodeInfo.SQL_ERR);
//    		result.setMessage(ErrMsg.HAS_NO_WHERE);
//    		return result;
//    	}
//
//    	WhereModel model = new WhereModel();
//    	//获取所有的where条件
//    	List<Expression> expressions = getExpressions(where, new ArrayList<Expression>(), model);
//    	for (Expression expression : expressions) {
//    		Expression leftExpression = null;
//    		Expression rightExpression = null;
//    		if (expression instanceof BinaryExpression) {
//    			leftExpression = ((BinaryExpression) expression).getLeftExpression();
//    			rightExpression = ((BinaryExpression) expression).getRightExpression();
//    		} else
//    			//其余的都跳过不做校验
//    			continue;
//
//			String leftValue = "";
//			String rightValue = "";
//			//当where条件两边是String或者long等写死的值或者两边都是column类型的时候方可进行比较
//			if (((leftExpression instanceof LongValue || leftExpression instanceof StringValue)
//					&& (rightExpression instanceof LongValue || rightExpression instanceof StringValue))
//					|| (leftExpression instanceof Column && rightExpression instanceof Column)) {
//
//	    		if (leftExpression instanceof LongValue) {
//	    			LongValue longValue = (LongValue) leftExpression;
//	    			leftValue = longValue.getStringValue();
//	    		} else if (leftExpression instanceof StringValue) {
//	    			StringValue stringValue = (StringValue) leftExpression;
//	    			leftValue = stringValue.getValue();
//	    		} else if (leftExpression instanceof Column) {
//	    			Column column = (Column) leftExpression;
//	    			leftValue = column.getColumnName();
//	    		}
//
//	    		if (rightExpression instanceof LongValue) {
//	    			LongValue longValue = (LongValue) rightExpression;
//	    			rightValue = longValue.getStringValue();
//	    		} else if (rightExpression instanceof StringValue) {
//	    			StringValue stringValue = (StringValue) rightExpression;
//	    			rightValue = stringValue.getValue();
//	    		} else if (rightExpression instanceof Column) {
//	    			Column column = (Column) rightExpression;
//	    			rightValue = column.getColumnName();
//	    		}
//
//	    		result.setCode(CodeInfo.SQL_ERR);
//
//	    		if (rightValue.equals(leftValue))
//	    			result.setMessage(ErrMsg.IDENTITY_ERR);
//	    		else
//	    			result.setMessage(ErrMsg.INEQUATION_ERR);
//	    		result.setSuccess(false);
//
//	    		return result;
//			}
//		}
//    	return result;
//	}
//
//    /**
//     * 获取所有的where、and和or的条件   between没处理
//     * @param where
//     * @param expressions
//     * @return
//     */
//	private List<Expression> getExpressions(Expression where, List<Expression> expressions, WhereModel model) {
//		if (where instanceof OrExpression) {
//			model.addOr();
//			checkWhereOrAnd(where, expressions, model);
//		} else if (where instanceof AndExpression) {
//			model.addAnd();
//    		checkWhereOrAnd(where, expressions, model);
//		} else if (where instanceof EqualsTo) {
//    		expressions.add(where);
//    		return expressions;
//    	}
////		else if (where instanceof Between) {
////    		expressions.add(where);
////    	}
//		return expressions;
//	}
//
//	//获取or和and条件的数量抽取的方法
//	private void checkWhereOrAnd(Expression where, List<Expression> expressions, WhereModel model) {
//		Expression leftExpression = ((BinaryExpression) where).getLeftExpression();
//		Expression rightExpressionStr = ((BinaryExpression) where).getRightExpression();
//
//		if (!(leftExpression instanceof EqualsTo)) {
//			expressions.add(rightExpressionStr);
//			getExpressions(leftExpression, expressions, model);
//		} else {
//			expressions.add(leftExpression);
//			getExpressions(rightExpressionStr, expressions, model);
//		}
//	}
//
//	/**
//	 * 校验select和union的select的where
//	 * @param selectBody
//	 * @param result
//	 * @return
//	 */
//	private ParseResult checkSelectBodyWhere(SelectBody selectBody, ParseResult result) {
//		if (selectBody instanceof PlainSelect) {
//    		PlainSelect plainSelect = (PlainSelect) selectBody;
//			Expression where = plainSelect.getWhere();
//			String name = plainSelect.getFromItem().getAlias().getName();
//			return checkWhere(where, result);
//    	//如果是Union类型的select
//    	} else if (selectBody instanceof SetOperationList) {
//    		SetOperationList unionSelect = (SetOperationList)selectBody;
//			List<PlainSelect> plainSelects = unionSelect.getPlainSelects();
//    		for (PlainSelect plainSelect : plainSelects) {
//    			result = checkWhere(plainSelect.getWhere(), result);
//
//    			//说明语法有错直接返回，如果没有错就继续检查下一条select语句的where条件
//    			if (result.getCode() != CodeInfo.SUCCESS.getCode())
//    				return result;
//			}
//    	}
//		return result;
//	}
}