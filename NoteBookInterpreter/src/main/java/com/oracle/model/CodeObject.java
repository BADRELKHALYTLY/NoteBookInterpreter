package com.oracle.model;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class CodeObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 	HashMap<String,String> hashKey;
	
	
	
	public HashMap<String, String> getHashKey() {
		return hashKey;
	}



	public void setHashKey(HashMap<String, String> hashKey) {
		this.hashKey = hashKey;
	}



	 

   
	public String manageExpression(String expr) throws Exception {
		ScriptEngineManager mgr = new ScriptEngineManager();
		   ScriptEngine engine = mgr.getEngineByName("python");
		if(expr.startsWith("%python"))
		{
			String expr1 = expr.replace("%python", "");
			if(expr1.contains("=") && !expr1.contains("print"))
		{
			StringBuilder leftExpr = new StringBuilder(expr1.split("=")[0].replace(" ", ""));
			StringBuilder rightExpr = new StringBuilder(expr1.split("=")[1]);

			
			try {
				engine.eval(leftExpr +"="+replaceAlphaNumericByValue(rightExpr.toString()));
				hashKey.put(leftExpr.toString().trim(), engine.get(leftExpr.toString().trim()).toString());
				return  "";
			} catch (ScriptException e) {
				throw new Exception("Syntactical error");
			}
			
			
			
 		}
		
		
 		else if(expr1.contains("print"))
			{
 			
 			if(expr1.contains("(") && expr1.contains("(") && expr1.contains("'"))
 				{try {
 					engine.eval(expr1);
 	 			} catch (ScriptException e) {
 	 				throw new Exception("Syntactical error");
 				}
 				}
 			else if(!expr1.contains("(") && !expr1.contains("(") && !expr1.contains("'")){
 			StringBuilder toDisplay = new StringBuilder(expr1.split("print")[1]);
			try {
				engine.eval("fer = "+replaceAlphaNumericByValue(toDisplay.toString()));
 			} catch (ScriptException e) {
 				throw new Exception("Syntactical error");
			}
			
			return engine.get("fer").toString();
 			}
			}
 		else {
 			throw new Exception("Syntactical error");
 		}
		}
		else if(!expr.startsWith("%python"))
			throw new Exception("The expression doesn't begin with python");
		
		
		return null;
	}
	
	
	public String replaceAlphaNumericByValue(String expr) {
		 
					
	 
 		
		for (Entry<String, String> e : hashKey.entrySet()) {
		     
		    if(e.getValue()!=null)
		    	expr = expr.replaceAll(e.getKey(), e.getValue().toString());
		}
		
				
 		
		return expr;
	}
	
	
	


	public CodeObject() {
		hashKey = new HashMap<String, String>();
		 
 	}
	
 
	


	
 
	
	

}