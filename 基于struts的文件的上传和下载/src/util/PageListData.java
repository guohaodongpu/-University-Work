package util;

import java.util.List;

public class PageListData {
	// ��ҳ�����
	private List dataList = null;
	// ��¼����
	private int totalcount = 0;
	// ÿҳ��ʾ��¼��
	private int pageSize = 0;
	// ��ǰҳ��
	private int currentPage = 1;
	// ��ҳ��
	private int totalPageCount = 1;
	//��ҳҳ��
	private String footer;
	/*��ʼ����ҳ���*/
	public PageListData(int totalcount, int pageSize, int currentPage,
			List dataList) {
		setTotalcount(totalcount);
		setPageSize(pageSize);
		setCurrentPage(currentPage);
		setDataList(dataList);
		setFooter(getFooter());
	}
	/**
	 * ��װ��ҳ������ ���豻������ĳ��Form֮��
	 * 
	 * @return String pages ��ǰҳ�� pageSize ÿҳ��ʾ����
	 */
	public String getFooter() {
		StringBuffer pageStr = new StringBuffer("");
		pageStr.append("<center><p class='pages'>");
		int totalPages = getTotalPageCount(); // ��ҳ��
		int prevPage = currentPage - 1; // ��һҳ
		int nextPage = currentPage + 1; // ��һҳ
		pageStr.append("<span style='color:#6795B4;'>����" + totalcount
				+ "����¼</span>&nbsp;&nbsp;");
		pageStr.append("<span style='color:#6795B4;'>��" + currentPage + "ҳ/��"
				+ totalPages + "ҳ</span>&nbsp;&nbsp;");
		if (currentPage > 1)
			pageStr
					.append("<span><a style='cursor: pointer;text-decoration:underline;color:#6795B4'onclick='document.getElementById(\"pages\").value=1;document.getElementById(\"pages\").form.submit();'>��ҳ</a></span>&nbsp;&nbsp;");
		if (currentPage == 1)
			pageStr
					.append("<span style='color:#6795B4'>��ҳ</span>&nbsp;&nbsp;&nbsp;&nbsp;");
		if (currentPage > 1)
			pageStr
					.append("<span><a style='cursor: pointer;text-decoration:underline;color:#6795B4' onclick='document.getElementById(\"pages\").value="
							+ prevPage
							+ ";document.getElementById(\"pages\").form.submit();'>��һҳ</a></span>&nbsp;&nbsp;");
		if (currentPage <= 1)
			pageStr
					.append("<span style='color:#6795B4'>��һҳ</span>&nbsp;&nbsp;");
		if (currentPage < totalPages)
			pageStr
					.append("<span><a style='cursor: pointer;text-decoration:underline;color:#6795B4;' onclick='document.getElementById(\"pages\").value="
							+ nextPage
							+ ";document.getElementById(\"pages\").form.submit();'>��һҳ</a></span>&nbsp;&nbsp;");
		if (currentPage >= totalPages)
			pageStr
					.append("<span style='color:#6795B4;'>��һҳ</span>&nbsp;&nbsp;");
		if (currentPage < totalPages)
			pageStr
					.append("<span><a style='cursor: pointer;text-decoration:underline;color:#6795B4;' onclick='document.getElementById(\"pages\").value="
							+ totalPages
							+ ";document.getElementById(\"pages\").form.submit();'>ĩҳ</a></span>&nbsp;&nbsp;");
		if (currentPage == totalPages)
			pageStr
					.append("<span style='color:#6795B4;'>ĩҳ</span>&nbsp;&nbsp;");
		pageStr
				.append("<span style='color:#6795B4;'>��ת����:<input type='text' value='"
						+ currentPage
						+ "'id='jumpPageBox' size='2' onblur='checkCurrentPage(document.getElementById(\"jumpPageBox\").value,"
						+ totalPages
						+ ")'/>ҳ<input class='right-button02' type='button' value='��ת' onclick='document.getElementById(\"pages\").value=document.getElementById(\"jumpPageBox\").value;document.getElementById(\"pages\").form.submit();'/></span>");
		pageStr.append("</p></center>");
		pageStr.append("<input type='hidden' value='" + currentPage
				+ "' name='currentPage' id='pages' />");
		pageStr.append("<input type='hidden' value='" + pageSize
				+ "' name='pageSize' id='pageSize' />");
		return pageStr.toString();
	}
	public List getDataList() {
		return dataList;
	}

	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	public int getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	//������ҳ�������Ϊ0����Ϊ1
	public int getTotalPageCount() {
		int p;
		if (totalcount % pageSize == 0) {
			p=totalcount / pageSize;
		} else {
			p=totalcount / pageSize + 1;
		}
		return p==0?1:p;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public void setFooter(String footer) {
		this.footer = footer;
	}
}
