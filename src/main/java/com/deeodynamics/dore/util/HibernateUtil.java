package com.deeodynamics.dore.util;

import java.util.List;

import org.hibernate.Query;

public class HibernateUtil {
    public static <T> List<T> listFrom(Query query) {
        @SuppressWarnings("unchecked")
		List<T> list = query.list();
        return list;
    }
}
