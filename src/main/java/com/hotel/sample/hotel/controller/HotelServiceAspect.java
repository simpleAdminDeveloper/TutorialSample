package com.hotel.sample.hotel.controller;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class HotelServiceAspect {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Around("execution(* HotelService.getHotels())") // point-cut expression
	public Object logBeforeV1(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("EmployeeCRUDAspect.logBeforeV1() : " + joinPoint.getSignature().getName());
		Object response = joinPoint.proceed();
		logger.info("finishe around method AspectJ");
		return response;
	}

	// @Around("execution(*
	// com.howtodoinjava.app.service.impl.EmployeeManagerImpl.*(..))")
	// public void logAroundAllMethods(ProceedingJoinPoint joinPoint) {
	//
	//
	// }
}
