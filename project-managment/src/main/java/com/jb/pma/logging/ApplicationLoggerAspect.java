package com.jb.pma.logging;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApplicationLoggerAspect {
	
	private final Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
	
	//dotts - class methods else
	@Pointcut("within(com.jb.pma.controllers..*)")
	//+ "|| within(com.jb.pma.dao..*)")
	public void definePackagePointcuts() {
		//empty method just to name the location specified in the pointcut
	}
	
	/*
	 * @Before("definePackagePointcuts()") public void logBefore(JoinPoint jp){
	 * log.debug(" \n \n \n"); log.
	 * debug("********* Before Method Execution ********* \n {}.{}() with arguments[s] = {}"
	 * , jp.getSignature().getDeclaringTypeName(), jp.getSignature().getName(),
	 * Arrays.toString(jp.getArgs()));
	 * log.debug("_____________________________________________ \n \n \n");
	 * 
	 * }
	 */
	
	@Around("definePackagePointcuts()")
	public Object logAround(ProceedingJoinPoint jp){
		log.debug(" \n \n \n");
		log.debug("********* Before Method Execution ********* \n {}.{}() with arguments[s] = {}",
				jp.getSignature().getDeclaringTypeName(),
				jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
		log.debug("_____________________________________________ \n \n \n");
		
		Object o = null;
		try {
			o = jp.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.debug(" \n \n \n");
		log.debug("********* After Method Execution ********* \n {}.{}() with arguments[s] = {}",
				jp.getSignature().getDeclaringTypeName(),
				jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
		log.debug("_____________________________________________ \n \n \n");
		
		return o;
		
	}

}
