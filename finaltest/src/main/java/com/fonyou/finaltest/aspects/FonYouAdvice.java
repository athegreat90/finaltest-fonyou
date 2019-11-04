package com.fonyou.finaltest.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class FonYouAdvice
{

	Logger logger = LoggerFactory.getLogger(FonYouAdvice.class);

	@AfterThrowing(pointcut = "execution(* com.fonyou.finaltest.service.impl.EmployeeService.*(..))", throwing = "error")
	private void afterThrowing(JoinPoint jp, Throwable error)
	{
		System.out.println("Method Signature: "  + jp.getSignature());  
		System.out.println("Exception: " + error.getMessage());
	}
	
	@Before(value = "execution(* com.fonyou.finaltest.service.impl.EmployeeService.*(..))")
	public void before(JoinPoint joinPoint) 
	{
		this.logger.info("Before execution of method (Signature): " + joinPoint.getSignature());
		
	}
	
	@Before(value = "execution(* com.fonyou.finaltest.util.ValidationEmployee.*(..))")
	public void beforeValidation(JoinPoint joinPoint) 
	{
		this.logger.info("Before execution of method (Signature): " + joinPoint.getSignature());
	}
	
	@After(value = "execution(* com.fonyou.finaltest.util.ValidationEmployee.*(..))")
	public void afterValidation(JoinPoint joinPoint) 
	{
		this.logger.info("After execution of method (Signature): " + joinPoint.getSignature());
	}
	
	@After(value = "execution(* com.fonyou.finaltest.service.impl.EmployeeService.*(..))")
	public void afterMethod(JoinPoint joinPoint)
	{
		this.logger.info("After execution of method (Signature): " + joinPoint.getSignature());
	}
//	
//	@Before(value = "execution(* com.fonyou.finaltest.controller.EmployeeController.*(..))")
//	public void beforeController(JoinPoint joinPoint) 
//	{
//		System.out.println("Before execution of method: " + joinPoint.getSignature());
//	}
	
	@AfterReturning(value = "execution(* com.fonyou.finaltest.service.impl.EmployeeService.*(..))", returning = "result") 
	public void after(JoinPoint joinPoint, Object result) 
	{
		this.logger.info("After execution of " + joinPoint.getSignature() + " - Result: " + result);
	}
}
