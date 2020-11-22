package com.auth.converter;

import java.util.ArrayList;
import java.util.List;

import com.auth.exception.ConverterException;

public interface Converter<F, T> {

	public T convertTo(F f);
	
	default List<T> convertListToList(List<F> fs) throws ConverterException {
		List<T> list = new ArrayList<>();
		for(F f: fs) list.add(this.convertTo(f));
		return list;
	}
	
}
