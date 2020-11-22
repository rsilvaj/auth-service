package com.auth.service;

import com.auth.exception.BusinessException;

public interface AfterSavingService<E, D> {
	
	public void run(E e, D d) throws BusinessException;

}
