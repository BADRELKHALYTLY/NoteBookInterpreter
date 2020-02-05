package com.oracle.controller;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

 import com.oracle.model.CodeObject;
import com.oracle.model.InputCode;
import com.oracle.model.Result;

@Controller
public class InterpreterController {
	
	 
     
    static CodeObject codeObject;
    
    
    static {
    	codeObject = new CodeObject();
    	 
    }
    



	
	  @PostMapping("/echo")
	    public ResponseEntity<Result> echo(@RequestBody InputCode inputCode ) throws Exception {
	    	
	     	Result result = new Result();
	    	 String vaue = codeObject.manageExpression(inputCode.getCode());
	    	 result.setResultat(vaue);
	    	 
	    	 
 	            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
	              .path("/{id}")
	              .buildAndExpand(inputCode.getCode())
	              .toUri();
	     
	            return ResponseEntity.created(uri)
	              .body(result);
	         
	    }
	
  @PostMapping("/execute")
    public ResponseEntity<Result> test(@RequestBody InputCode inputCode, HttpServletRequest request) throws Exception {
    	
    	
	  
	  codeObject = (CodeObject) request.getSession().getAttribute(inputCode.getIdSession());
     	Result result = new Result();
    	 if (codeObject == null) {
    		 codeObject = new CodeObject();
			System.out.println(codeObject);
     
 			request.getSession().setAttribute(inputCode.getIdSession(), codeObject);
 		} 	
    	 
    	 try {
     	 String vaue = codeObject.manageExpression(inputCode.getCode());
     	result.setResultat(vaue);
    	 }catch(Exception e) {
    		 return new ResponseEntity<Result>(HttpStatus.BAD_REQUEST);

    	 }
    	 
    	 
    	 request.getSession().setAttribute(inputCode.getIdSession(), codeObject);
    	 
             URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
              .path("/{id}")
              .buildAndExpand(inputCode.getCode())
              .toUri();
     
            return ResponseEntity.created(uri)
              .body(result);
         
    }

	
}