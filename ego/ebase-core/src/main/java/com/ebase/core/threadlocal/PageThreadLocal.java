package com.ebase.core.threadlocal;


import com.ebase.core.page.PageDTO;

public class PageThreadLocal {
	
	static ThreadLocal<PageDTO<?>> threadlocal = new ThreadLocal<>();
	
	public static void init(PageDTO<?> page){
		threadlocal.set(page);
	}
	
	public static void remove(){
		threadlocal.remove();;
	}
	
	public static PageDTO<?> get(){
		return threadlocal.get();
	}

}
