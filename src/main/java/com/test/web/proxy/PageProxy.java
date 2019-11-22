package com.test.web.proxy;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import lombok.Data;
@Component("pager")@Lazy
@Data
public class PageProxy extends Proxy{
	 private int rowCount, startRow, endRow,
	 			pageCount, pageSize, currPage, startPage, endPage,
	 			blockCount, blockSize, currBlock, nextBlock, prevBlock;
	 private boolean existNext, existPrev;
	
	 public void paging() {
		 //currPage, pageSize, blockSize, rowCount
		pageCount = (rowCount % pageSize != 0) ? rowCount / pageSize + 1: rowCount / pageSize;
		blockCount = (pageCount % blockSize != 0) ? pageCount / blockSize + 1: pageCount / blockSize;
		startRow = currPage * pageSize;
		endRow = (currPage != (pageCount - 1)) ? startRow + (pageSize-1) : rowCount - 1;
		currBlock = currPage / blockSize;
		startPage = currBlock * blockSize;
		endPage = (currBlock != (blockCount - 1)) ? startPage + (blockSize-1) : pageCount - 1;
		prevBlock = startPage - blockSize;
		nextBlock = startPage + blockSize;
		existPrev = currBlock != 0;
		existNext = (currBlock +1) != blockCount; 
	 }
}






