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
	private static final Logger logger = LoggerFactory.getLogger(FonYouAdvice.class);

	@AfterThrowing(pointcut = "execution(* com.fonyou.finaltest.service.impl.EmployeeService.*(..))", throwing = "error")
	private void afterThrowing(JoinPoint jp, Throwable error)
	{
		logger.info("Method Signature: {}", jp.getSignature());
		logger.info("Exception: {}", error.getMessage());
	}
	@Before(value = "execution(* com.fonyou.finaltest.controller.EmployeeController.*(..))")
	public void beforeController(JoinPoint joinPoint)
	{
		logger.info("Before execution of method (Signature): {}", joinPoint.getSignature());

	}

	@Before(value = "execution(* com.fonyou.finaltest.service.impl.EmployeeService.*(..))")
	public void before(JoinPoint joinPoint) 
	{
		logger.info("Before execution of method (Signature): {}", joinPoint.getSignature());
		
	}
	
	@Before(value = "execution(* com.fonyou.finaltest.util.ValidationEmployee.*(..))")
	public void beforeValidation(JoinPoint joinPoint) 
	{
		logger.info("Before execution of method (Signature): {}", joinPoint.getSignature());
	}
	
	@After(value = "execution(* com.fonyou.finaltest.util.ValidationEmployee.*(..))")
	public void afterValidation(JoinPoint joinPoint) 
	{
		logger.info("After execution of method (Signature): {}", joinPoint.getSignature());
	}
	
	@After(value = "execution(* com.fonyou.finaltest.service.impl.EmployeeService.*(..))")
	public void afterMethod(JoinPoint joinPoint)
	{
		logger.info("After execution of method (Signature): {}", joinPoint.getSignature());
	}
	
	@AfterReturning(value = "execution(* com.fonyou.finaltest.service.impl.EmployeeService.*(..))", returning = "result") 
	public void after(JoinPoint joinPoint, Object result) 
	{
		logger.info("After execution of {} - Result: {}", joinPoint.getSignature(), result);
	}
}
