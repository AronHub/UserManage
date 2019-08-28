
/**  
 * @Title: PageUtil.java
 * @Package com.fjt.util
 * @Description: TODO(用一句话描述该文件做什么)
 * @author fujiantao
 * @date 2019年8月7日 下午3:37:48 
 * @version V1.0  
 */

package com.fjt.util;

import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
     * @ClassName: 分页工具
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @author fujiantao
     * @date 2019年8月7日
     *
     */

public class PageUtil {

	public static Pageable getPageAble(Map<String, Object> params) {
		//获取一页有几条记录。
		Integer pageRows = Integer.valueOf((String) params.get("Rows"));
		//获取当前页。
		Integer pageNow = Integer.valueOf((String) params.get("pageNow")) - 1;
		//获取排序名称
		String sortName = (String) params.get("sortName");
		//排序方式
		Sort sort = null;
		if (null != sortName && !"".equals(sortName)) {
			sort = new Sort(Sort.Direction.ASC, sortName);
		} else {
			sort = new Sort(Sort.Direction.ASC, "id");
		}

		Pageable pageable = new PageRequest(pageNow, pageRows, sort);

		return pageable;
	}
}
