package mainServlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.TokenizationSys.Engine.EngineForDetokenization;
import com.TokenizationSys.Engine.EngineForLifeCycleCtrl;
import com.TokenizationSys.Engine.EngineForRegistry;
import com.TokenizationSys.Engine.EngineForTokenization;
import com.TokenizationSys.Engine.TokenSystemEngine;
import com.TokenizationSys.Utils.Configuration;


public class mainEntry extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 18494851511L;
	
	private static final int REQ_TYPE_TR_REGISTRY = 101;
	private static final int REQ_TYPE_TOKENIZATION = 102;
	private static final int REQ_TYPE_DETOKENIZATION = 103;
	private static final int REQ_TYPE_LIFECTRL = 104;
	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String reqType = (String)request.getParameter("reqType");
		//System.out.println("type:"+reqType);
		String rsp = null;
		switch (Integer.parseInt(reqType)) {
		case REQ_TYPE_TR_REGISTRY:
			rsp = TRRegistryProcess(request,response);			
			break;
		case REQ_TYPE_TOKENIZATION:
			rsp = TokenizationProcess(request,response);
			break;	
		case REQ_TYPE_DETOKENIZATION:
			rsp = DeTokenizationProcess(request,response);
			break;	
		case REQ_TYPE_LIFECTRL:
			rsp = LifeCtrlProcess(request,response);
			break;

		default:
			rsp = Configuration.errorRequest;
			break;
		}
		 	response.setContentType("text/html");
	        PrintWriter out = response.getWriter();        
	        out.write(rsp);
	        out.flush();
	        out.close();
		
		
		                                           // 使用RequestDispatcher对象在服务器端向目的路径跳转
	}

	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
	private String LifeCtrlProcess(HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		String param = (String)request.getParameter("lifeJSON");
		TokenSystemEngine tse =TokenSystemEngine.getInstance();
		EngineForLifeCycleCtrl eLife = new EngineForLifeCycleCtrl(tse);
		
	
		JSONObject jo = JSONObject.fromObject(param);
		
		jo.put("type", Configuration.msgFromLifeCtrl);
		
		String result = eLife.process(jo);//进入TR ID生成阶段；
			
		return result;
	}
	private String TRRegistryProcess(HttpServletRequest request, HttpServletResponse response){
		
		String param = (String)request.getParameter("TRJSON");
		TokenSystemEngine tse =TokenSystemEngine.getInstance();
		EngineForRegistry eRegistry = new EngineForRegistry(tse);
		
		JSONObject jo = JSONObject.fromObject(param);
		
		jo.put("type", Configuration.msgFromRegistry);
		
		String result = eRegistry.process(jo);//进入TR ID生成阶段；
			
		return result;
	}
	private String TokenizationProcess(HttpServletRequest request, HttpServletResponse response){
		 	
			String param = (String)request.getParameter("tokenJSON");
			
			JSONObject jo = JSONObject.fromObject(param);
			jo.put("type", Configuration.msgFromTokenization);
			TokenSystemEngine tse =TokenSystemEngine.getInstance();
			EngineForTokenization eTokenization = new EngineForTokenization(tse);
		
			String result = eTokenization.process(jo);
			
			return result;
	}
	private String DeTokenizationProcess(HttpServletRequest request, HttpServletResponse response){
		
		String param = (String)request.getParameter("deTokenJSON");

		JSONObject jo = JSONObject.fromObject(param);
		jo.put("type", Configuration.msgFromDeTokenization);
		TokenSystemEngine tse =TokenSystemEngine.getInstance();
		EngineForDetokenization eDeTokenization = new EngineForDetokenization(tse);
	
		String result = eDeTokenization.process(jo);
		
		return result;
		
	}
}
