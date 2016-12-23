package com.hz.dxf.dao.factory.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.NullArgumentException;

/**
 * 语句工具
 * 
 * @author johb
 * @time 2016年10月14日
 */
public class StatementUtil {

	/**
	 * 单表查询sql语句拼接格式化
	 * 
	 * @author johb
	 * @time 2016年10月14日
	 * @param pMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String sqlFromat(Map<String, Object> pMap) {

		if (!pMap.containsKey("tableName")) {
			throw new NullArgumentException("表名称不能为空!");
		}
		String tableName = (String) pMap.get("tableName");
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT ");
		if (pMap.containsKey("attributes") && null != pMap.get("attributes")) {
			List<String> attributes = (List<String>) pMap.get("attributes");
			int intSize = attributes.size();
			if (intSize > 1) {
				for (int i = 0; i < intSize - 1; i++) {
					builder.append(attributes.get(i)).append(" , ");
				}
			}
			builder.append(attributes.get(intSize - 1));
		} else {
			builder.append("* ");
		}
		builder.append(" FROM ").append(tableName);
		if (pMap.containsKey("where") && null != pMap.get("where")) {
			builder.append(" WHERE ");
			List<String> params = (List<String>) pMap.get("where");
			int intSize = params.size();
			if (intSize > 1) {
				for (int i = 0; i < intSize - 1; i++) {
					builder.append(params.get(i)).append(" = ? ")
							.append("AND ");
				}
			}
			builder.append(params.get(intSize - 1)).append(" = ? ");
		}

		return builder.toString();
	}

	/**
	 * @Description : 简单SQL拼接
	 * @author johb
	 * @time 2016年10月19日 下午2:16:37
	 * @param pList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String sqlFromatByManyTable(Map<String, Object> pMap) {

		StringBuilder builder = new StringBuilder();
		// 拼接select
		builder.append("SELECT ");
		if (pMap.containsKey("attributes") && null != pMap.get("attributes")) {
			List<String> attributes = (List<String>) pMap.get("attributes");
			int intSize = attributes.size();
			if (intSize > 1) {
				for (int i = 0; i < intSize - 1; i++) {
					builder.append(attributes.get(i)).append(",");
				}
			}
			builder.append(attributes.get(intSize - 1));
		} else {
			builder.append("* ");
		}
		// 拼接from
		builder.append(" FROM ").append(" ");
		if ((!pMap.containsKey("from")) || (null == pMap.get("from"))) {
			System.out.println(pMap.get("from"));
			throw new NullArgumentException("查询的表名不能为空!");
		}
		List<String> tableList = (List<String>) pMap.get("from");
		int intTableSize = tableList.size();
		if (intTableSize > 1) {
			for (int i = 0; i < intTableSize - 1; i++) {
				builder.append(tableList.get(i)).append(",");
			}
		}
		builder.append(tableList.get(intTableSize - 1)).append(" ");

		// 拼接where
		if (pMap.containsKey("where") && null != pMap.get("where")) {
			builder.append(" WHERE ");
			List<Map<String, String>> params = (List<Map<String, String>>) pMap
					.get("where");
			int intSize = params.size();
			System.out.println("intSize=" + intSize);
			if (intSize > 0) {
				Map<String, String> map = params.get(0);
				int intMapSize = map.size();
				Iterator<String> iterator = map.keySet().iterator();
				int count = 0;
				while (iterator.hasNext()) {
					count++;
					String key = (String) iterator.next();
					String value = map.get(key);
					if (count == intMapSize) {
						builder.append(key).append("=").append(value)
								.append("");
					} else {
						builder.append(key).append("=").append(value)
								.append(" AND ");
					}

				}
			}
		}

		return builder.toString();
	}

	/**
	 * @Description : 复杂SQL语句拼接
	 * @author johb
	 * @time 2016年11月18日 上午11:03:04
	 *
	 */
	@SuppressWarnings("unchecked")
	public static String complexSqlFromatByManyTable(Map<String, Object> pMap) {

		StringBuilder builder = new StringBuilder();
		// 拼接select
		builder.append("SELECT ");
		if (pMap.containsKey("attributes") && null != pMap.get("attributes")) {
			List<String> attributes = (List<String>) pMap.get("attributes");
			int intSize = attributes.size();
			if (intSize > 1) {
				for (int i = 0; i < intSize - 1; i++) {
					builder.append(attributes.get(i)).append(",");
				}
			}
			builder.append(attributes.get(intSize - 1));
		} else {
			builder.append("* ");
		}
		// 拼接from
		builder.append(" FROM ").append(" ");
		if ((!pMap.containsKey("from")) || (null == pMap.get("from"))) {
			System.out.println(pMap.get("from"));
			throw new NullArgumentException("查询的表名不能为空!");
		}
		List<String> tableList = (List<String>) pMap.get("from");
		int intTableSize = tableList.size();
		if (intTableSize > 1) {
			for (int i = 0; i < intTableSize - 1; i++) {
				builder.append(tableList.get(i)).append(",");
			}
		}
		builder.append(tableList.get(intTableSize - 1)).append(" ");

		// 拼接join(left join ..... on.........;right join ...... on......; full
		// join ...... on.....)
		/**
		 * 内连接(inner join ...... on ......) 包括相等连接和自然连接
		 * 内连接使用比较运算符根据每个表共有的列的值匹配两个表中的行 外连接 1. 左外连接 left join ..... on ......
		 * 左向外连接的结果集包括LEFT OUTER子句中指定的左表的所有行，而不仅仅是连接列所匹配的行。
		 * 如果左表的某行在右表中没有匹配行，则在相关联的结果集行中右表的所有选择列表列均为空值。 2. 右外连接 right join ....
		 * on ...... 右向外连接是左向外连接的反向连接。将返回右表的所有行。如果右表的某行在左表中没有匹配行，则将为左表返回空值。 3.
		 * 全连接 full join ...... on .....
		 * 完整外部连接返回左表和右表中的所有行。当某行在另一个表中没有匹配行时，则另一个表的选择列表列包含空值。
		 * 如果表之间有匹配行，则整个结果集行包含基表的数据值。 交叉连接
		 * 交叉连接返回左表中的所有行，左表中的每一行与右表中的所有行组合。交叉连接也称作笛卡尔积。
		 */
		
		

		// 拼接where
		if (pMap.containsKey("where") && null != pMap.get("where")) {
			Map<String, String> params = (Map<String, String>) pMap
					.get("where");
			if (null != params && !params.isEmpty() && params.size() != 0) {
				builder.append(" WHERE ");
				int intMapSize = params.size();
				Iterator<String> iterator = params.keySet().iterator();
				int count = 0;
				while (iterator.hasNext()) {
					count++;
					String key = (String) iterator.next();
					String value = params.get(key);
					if (count == intMapSize) {
						builder.append(key).append("=").append(value)
								.append("");
					} else {
						builder.append(key).append("=").append(value)
								.append(" AND ");
					}
				}
			}
		}

		// 拼接 group by
		if (pMap.containsKey("groups") && null != pMap.get("groups")) {
			Map<Integer, String> groupMap = (Map<Integer, String>) pMap
					.get("groups");
			if (null != groupMap && !groupMap.isEmpty() && groupMap.size() != 0) {
				builder.append(" GROUP BY ");
				int intGroupSize = groupMap.size();
				Iterator<Integer> iterator = groupMap.keySet().iterator();
				int count = 0;
				while (iterator.hasNext()) {
					count ++;
					Integer key = (Integer) iterator.next();
					String value = groupMap.get(key);
					if (count == intGroupSize) {
						builder.append(value);
					}else {
						builder.append(value).append(",");
					}
				}
			}
		}

		// 拼接having(对聚合函数运算结果的输出进行限制)
		if (pMap.containsKey("havings") && null != pMap.get("havings")) {
			List<String> havingList = (List<String>)pMap.get("havings");
			if (!havingList.isEmpty() && null != havingList && havingList.size() != 0) {
				builder.append("HAVING ");
				int intHavingSize = havingList.size();
				int count = 0;
				for (String string : havingList) {
					count ++;
					if (count == intHavingSize) {
						builder.append(string).append(" ");
					}else {
						builder.append(string).append(", ");
					}
				}
			}
		}
		

		// 拼接order by
		if (pMap.containsKey("orders") && null != pMap.get("orders")) {
			List<Map<String, String>> orderList = (List<Map<String, String>>)pMap.get("orders");
			if (!orderList.isEmpty() && null != orderList && orderList.size() != 0) {
				builder.append(" ORDER BY ");
				int intOrderSize = orderList.size();
				int count = 0;
				for (Map<String, String> map : orderList) {
					count ++;
					if (!map.isEmpty() && null != map && map.size() != 0) {
						Iterator<String> iterator = map.keySet().iterator();
						while (iterator.hasNext()) {
							String key = iterator.next();
							String value = map.get(key);
							if (count == intOrderSize) {
								builder.append(key).append(" ").append(value);
							}else {
								builder.append(key).append(" ").append(value).append(",");
							}
						}
					}
				}
				
			}
		}

		return builder.toString();
	}

}
