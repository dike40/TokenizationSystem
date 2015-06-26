package com.aop;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mybatis.model.Log;
import com.mybatis.service.LogServiceDao;
@Component
@Aspect
public class LogAspect {
	
	private Log log;
	private String content;
	
	@Autowired 
	private LogServiceDao logService ;
	
	@Pointcut("execution(* com.serviceImpl.*.*(..))")  
    public void pointcut(){}  
    
	
    //方法执行前调用  
    @Before("pointcut()")  
    public void before(JoinPoint jp) {  
    	log = new Log();
    	log.setLogbegintime(getTime());
    	
    	Object obj[] = jp.getArgs();
    	 
    	 content = "log Begining method: "  
             + jp.getTarget().getClass().getName() + "."  
             + jp.getSignature().getName()+";";
    	 
    	 System.out.println(content); 
    	 
  	  	for(Object o :obj){
  	  		
  	  		content+="args:"+o.toString()+";";
  	  		
  	  	System.out.println(content);
  	  		}
    	
    }  
      
    //方法执行的前后调用  
   @Around("pointcut()")  
    public void around(ProceedingJoinPoint point) throws Throwable{  
        System.out.println("begin around");  
      
       // Object object = null ;
        try {
        	
        	/*object =*/ point.proceed(); 
        	
		} catch (Throwable e) {
			  System.out.println("error of round:"+e);  
			  
			  content+= "error of round:"+e+";";
			  
			  log.setLogendtime(getTime());
		    	
			  log.setContent(this.content);
			  
			  logService.insert(log);
			 // throw e;
		} 
        System.out.println("end around");  
        //return object;  
       
    }
    
    //方法执行后调用  
    @AfterReturning("pointcut()")
    public void afterSleep(JoinPoint jp){
    
    	
    	content+="log Ending method: "  
            + jp.getTarget().getClass().getName() + "."  
            + jp.getSignature().getName()+";";
    	System.out.println(content); 
    	log.setLogendtime(getTime());
    	
    	log.setContent(this.content);
    	
    	logService.insert(log);
    }
    
    //方法执行异常调用  
  /* @AfterThrowing(pointcut = "pointcut()",throwing="ex")  
    public void afterThrowing(JoinPoint jp,Throwable ex){  
	   
    	System.out.println("log throwing method: "  
                + jp.getTarget().getClass().getName());
    	 System.out.println("error of throwing:"+ex); 
    } */
    public String getTime(){
    	Date now = new Date(); 
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	return  dateFormat.format( now ); 
    	
    }
}
