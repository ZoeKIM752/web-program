package du.board.domain;

import org.springframework.util.StringUtils;

import du.common.Pagination;

public class BoardCriteria extends Pagination {

	private String title = "";
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if(StringUtils.hasText(title)) {
			this.title = title;
		}		
	}
	
	public void pageInfo(int listCnt) {		
		super.pageInfo(page, range, listCnt);
	}

	public void setPage(Integer page) {		
		setPage((page == null) ? 1: page);
	}
	
	public void setRange(Integer range) {
		setRange((range == null) ? 1: range);
	}	
}