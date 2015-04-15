package mainServlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.TokenizationSys.Engine.EngineForRegistry;
import com.TokenizationSys.Engine.TokenSystemEngine;
import com.TokenizationSys.Utils.Configuration;


public class mainEntry extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 18494851511L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String domainString = (String)request.getParameter("domain");
		String posEntryMode = (String)request.getParameter("mode");
		String merchantId = (String)request.getParameter("merchantId");
		String param_1 = (String)request.getParameter("param1");
		String param_2 = (String)request.getParameter("param2");
		
		
		
		
		TokenSystemEngine tse =TokenSystemEngine.getInstance();
		EngineForRegistry eRegistry = new EngineForRegistry(tse);
		
		JSONObject jo = new JSONObject();
		jo.put("type", Configuration.msgFromRegistry);
		jo.put("domain", domainString);
		jo.put("mode", posEntryMode);
		jo.put("merchantId", merchantId);
		jo.put("param1", param_1);
		jo.put("param2", param_2);
		
			String[] result = eRegistry.process(jo);//����TR ID���ɽ׶Σ�
			
			request.setAttribute("TR_ID", result[1]);                                        // Ϊrequest������Ӳ���
	        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");    // ʹ��req�����ȡRequestDispatcher����
	        dispatcher.forward(request, response);                                            // ʹ��RequestDispatcher�����ڷ���������Ŀ��·����ת
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}


}
