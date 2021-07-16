package du.common;

public class Pagination {

	protected int listSize = 10;
		
	protected int rangeSize = 10;
	
	//현재 페이지
	protected int page = 1;
	
	//현재 페이지 범위
	protected int range = 1;
	
	//총 게시물 수
	protected int listCnt;
	
	//총 페이지 범위 개수
	protected int pageCnt;
	
	//각 페이지 범위의 시작번호
	protected int startPage;
	
	//
	protected int startList;
	
	//각 페이지 범위의 끝 번호
	protected int endPage;
	
	//이전 페이지 여부
	protected boolean prev;
	
	//다음 페이지 여부
	protected boolean next;

	public int getListSize() {
		return listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getPageCnt() {
		return pageCnt;
	}

	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getStartList() {
		return startList;
	}

	public void setStartList(int startList) {
		this.startList = startList;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getRangeSize() {
		return rangeSize;
	}

	public int getListCnt() {
		return listCnt;
	}
	
	public void pageInfo(int page, int range, int listCnt){
		
		//current location
		this.page = page;
		this.range = range;
		this.listCnt = listCnt;
		
		//total page cnt
		this.pageCnt = (int) Math.ceil((float)listCnt / listSize);
		
		//start page
		this.startPage = (range - 1) * rangeSize + 1;
		
		//end page
		this.endPage = range * rangeSize;
		if(endPage >= pageCnt) {
			endPage = pageCnt;
		}
		
		//board start count
		this.startList = (page - 1) * listSize;
		
		//prev state
		//this.prev = range == 1 ? false : true;
		this.prev = (range != 1);
		
		//next state
		//this.next = endPage > pageCnt ? false : true;
		this.next = (endPage < pageCnt);
		/*
		if(endPage >= pageCnt) {
			this.endPage = this.pageCnt;
			this.next = false;
		}
		*/
	}
	
}
