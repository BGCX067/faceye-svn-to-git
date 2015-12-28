package com.faceye.core.util.taglib.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.faceye.core.util.helper.PaginationSupport;
import com.faceye.core.util.helper.StringPool;
public class Page extends SimpleTagSupport {
	public PaginationSupport page;

	public String method;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public PaginationSupport getPage() {
		return page;
	}

	public void setPage(PaginationSupport page) {
		this.page = page;
	}

	public void doTag() throws JspException, IOException {
		try {

			JspWriter out = getJspContext().getOut();

			int startIndex = page.getStartIndex();
			int nextIndex = page.getNextIndex();
			int previousIndex = page.getPreviousIndex();
			int lastIndex;
			String link;
			if (page.getIndexes() != null && page.getIndexes().length > 1) {
				lastIndex = page.getIndexes()[page.getIndexes().length - 1];
			} else {
				lastIndex = 0;
			}
			// 当前页
			int currentPage;
			if (startIndex == 0) {
				currentPage = 1;
			} else {
				currentPage = startIndex / page.getPageSize() + 1;
			}
			int totalPage = page.getIndexes().length;
			int begin = 0;
			int end = 0;
			int between = totalPage - currentPage;
			if (between > 8) {
				if (currentPage - 4 <= 0) {
					begin = 0;
					end = 8;
				} else {
					begin = currentPage - 4;
					end = currentPage + 4;
				}
			} else {
				if (currentPage - 4 <= 0) {
					begin = 0;
					if (currentPage + 4 > totalPage) {
						end = totalPage;
					} else {
						end = currentPage + 4;
					}
				} else {
					begin = currentPage - 4;
					if (currentPage + 4 > totalPage) {
						end = totalPage;
					} else {
						end = currentPage + 4;
					}
				}
			}
			out.println("<div	id=\"PageContainer\">");
			int temp;
			for (int i = begin; i < end; i++) {
                 temp=i+1;
				if (i == currentPage) {
					out.print(temp);
				} else {
					link="<a href=\"" + this.getMethod()
					+ StringPool.CHARACTER_AND
					+ StringPool.CURRENT_INDEX
					+ StringPool.CHARACTER_SAME + page.getIndexes()[i]
					+ "\">" + temp + "</a>";
					out.print(link);
				}
				if (i == end) {
					if (i < totalPage) {
						out.print("...");
						out.print("<a href=\"" + StringPool.CHARACTER_AND
								+ StringPool.CURRENT_INDEX
								+ StringPool.CHARACTER_SAME
								+ page.getIndexes()[totalPage - 1] + "\">"
								+ totalPage + "</a>");
					}
				}
			}
//			getJspBody().invoke(null);
			
			out.println("</div>");
		} catch (Exception e) {
			System.out.println("分页对象出现异常:" + e.toString());
		}

	}
}
