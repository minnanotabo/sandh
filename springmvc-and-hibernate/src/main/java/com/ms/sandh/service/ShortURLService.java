package com.ms.sandh.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transaction;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.ms.sandh.model.ShortURL;
import com.ms.util.HibernateUtil;

public class ShortURLService {

	public String addShortURL (String originalURL, String loginId) {
		ShortURL shortURL = new ShortURL();
		 
		//shortURL.setId(100);
		shortURL.setOriginalURL(originalURL);
		shortURL.setCreateDate(new Date());
		shortURL.setUpdateDate(new Date());
		String sURL = RandomStringUtils.randomAlphanumeric(6);
		shortURL.setShortURL(sURL);
		shortURL.setLoginId(loginId);

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(shortURL);
		session.getTransaction().commit();
		return sURL;
	}
	
	public String getOriginalURL (String shortURLText) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		ShortURL shortURL = (ShortURL) session.get(ShortURL.class, shortURLText);
        session.close();
        return shortURL.getOriginalURL();
	}
	
	public ShortURL getShortURLObj (String shortURLText) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		ShortURL shortURL = (ShortURL) session.get(ShortURL.class, shortURLText);
        session.close();
        return shortURL;
	}
	
	public void listShortURL() {
	      Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	      try{
	  		session.beginTransaction();
	  		Query query = session.createQuery("from ShortURL"); // the case DOES matter !
	  		List urls = query.list();
	        for (Iterator iterator = 
	        		urls.iterator(); iterator.hasNext();){
	            ShortURL sURL = (ShortURL) iterator.next(); 
	            System.out.println("ShortURL: " + sURL); 
	         }
			session.getTransaction().commit();
	      }catch (HibernateException e) {
	         if (session.getTransaction()!=null) 
	        	 session.getTransaction().rollback();
	      }finally {
	         session.close(); 
	      }
	   }

	public List<String> getShortURLByOriginalURL(String originalURL) {
	      Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	      List<String> result = new ArrayList<String>();
	      try{
	  		session.beginTransaction();
	  		Query query = session.createQuery("from ShortURL where ORIGINALURL = :ourl"); // the case DOES matter !
	  		query.setParameter("ourl", originalURL);
	  		List urls = query.list();
	        for (Iterator iterator = 
	        		urls.iterator(); iterator.hasNext();){
	            ShortURL sURL = (ShortURL) iterator.next(); 
	            System.out.println("ShortURL: " + sURL); 
	            result.add(sURL.getShortURL());
	         }
			session.getTransaction().commit();
	      }catch (HibernateException e) {
	         if (session.getTransaction()!=null) 
	        	 session.getTransaction().rollback();
	      }finally {
	         session.close(); 
	      }
	      return result;
	   }

}
