package com.jpa.SpringBootCRUD.generic;

import java.io.Serializable;

public interface GenericDao<T> {
	
    T create(T t);
    T update(T t);
    void delete(T t);
}