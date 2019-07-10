package com.ezen.antpeople.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ezen.antpeople.DTO.UserDTO;

@Aspect
@Component
public class LoggingAspect {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	//포인트 컷 지정
	@Pointcut("execution(* com.ezen.antpeople.*.*.get*(..))")
	public void getLogging() {}
	
	@Pointcut("execution(* com.ezen.antpeople.*.*.get*(com.ezen.antpeople.DTO.UserDTO)) && args(user)")
	public void getUserLogging(UserDTO user) {}
	
	//before 어드바이스
	@Before("getLogging()")
	public void logBefore(JoinPoint joinpoint) {
		String message = buildJoinpoint(joinpoint);
		message += "get 메서드 실행 시작";
		log.info(message);
	}
	
	//After 어드바이스
	@After("getLogging()")
	public void logAfter(JoinPoint joinpoint) {
		String message = buildJoinpoint(joinpoint);
		message += "get 메서드 실행 종료";
		log.info(message);
	}
	
	
	//After-returning 어드바이스
	//실행 안됨 
	@AfterReturning("getUserLogging(user)")
	public void logAfterReturning(JoinPoint joinpoint, UserDTO user) {
		String message = buildJoinpoint(joinpoint);
		message += "get 메서드 실행 정상 종료 \n";
		log.info(message);
		log.info(user.toString());
	}
	
	//After-throwing 어드바이스
	@AfterThrowing("getLogging()")
	public void logAfterThrowing(JoinPoint joinpoint) {
		String message = buildJoinpoint(joinpoint);
		message += "get 메서드 실행 정상 종료";
		log.info(message);
	}
	
	
	private String buildJoinpoint(JoinPoint joinpoint) {
		String className = joinpoint.getTarget().getClass().getName();
		String methodName = joinpoint.getSignature().getName();
		String message = className + "클래스의 " + methodName + "( ";
		Object [] args = joinpoint.getArgs();
		for(int i = 0; i<args.length; ++i) {
			Object arg = args[i];
			message += arg.getClass().getTypeName();
			if(i!= args.length -1)
				message += ",";
		}
		message += " ) \n ";
		return message;
	}
	
	
}
