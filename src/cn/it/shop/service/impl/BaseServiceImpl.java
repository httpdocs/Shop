package cn.it.shop.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import cn.it.shop.service.BaseService;

@Service("baseService")
@Lazy(true)
public class BaseServiceImpl<T> implements BaseService<T>{
	
	private Class clazz;//clazz存储当前操作的类型，即泛型T
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public BaseServiceImpl() {
		ParameterizedType type =(ParameterizedType)this.getClass().getGenericSuperclass();
		clazz = (Class) type.getActualTypeArguments()[0];
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	protected Session getSession() {
		if (null==sessionFactory.getCurrentSession()){
			return sessionFactory.openSession();
		}else{
			return sessionFactory.getCurrentSession();
		}
	}
	
	@Override
	public void save(T t) {
		Session session =getSession();
		session.save(t);	
	}

	@Override
	public void update(T t) {
		getSession().update(t);
	}

	@Override
	public void delete(int id) {
		System.err.println(clazz.getSimpleName());
		String hql = "delete" +clazz.getSimpleName()+"as c where c.id=:id";
		getSession().createQuery(hql).setInteger("id", id).executeUpdate();
	}

	@Override
	public T get(int id) {
		return (T) getSession().get(clazz, id);
	}

	@Override
	public List<T> query() {
		String hql = "from "+ clazz.getSimpleName();
		 return getSession().createQuery(hql).list();
	}

	
	
}
