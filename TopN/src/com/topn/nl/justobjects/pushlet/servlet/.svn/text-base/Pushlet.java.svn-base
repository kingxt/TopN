package com.topn.nl.justobjects.pushlet.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.topn.action.help.CookieHelp;
import com.topn.nl.justobjects.pushlet.core.*;
import com.topn.nl.justobjects.pushlet.util.Log;
import com.topn.nl.justobjects.pushlet.util.Servlets;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;


/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-4-6 下午10:24:06
 * 
 * 此类用来接收用户请求，采用轮循的方式保持链接
 */
public class Pushlet extends HttpServlet implements Protocol {

	public void init() throws ServletException {
		try {
			// Load configuration (from classpath or WEB-INF root path)
			String webInfPath = getServletContext().getRealPath("/") + "/WEB-INF";
			Config.load(webInfPath);

			//pushlets 初始化日志
			Log.init();
			
			// Start session manager  启动实例，此session不同httpsession，此类的作用是维持一个会话
			SessionManager.getInstance().start();

			// Start event Dispatcher  dispatcher，此类的作用是分发请求
			Dispatcher.getInstance().start();


			if (Config.getBoolProperty(Config.SOURCES_ACTIVATE)) {
				EventSourceManager.start(webInfPath);
			} else {
				Log.info("Not starting local event sources");
			}
		} catch (Throwable t) {
			throw new ServletException("Failed to initialize Pushlet framework " + t, t);
		}
	}

	/**
	 * servlet销毁的时候将session和Dispatcher都注销
	 */
	public void destroy() {
		Log.info("destroy(): Exit Pushlet webapp");

		if (Config.getBoolProperty(Config.SOURCES_ACTIVATE)) {
			// Stop local event sources
			EventSourceManager.stop();
		} else {
			Log.info("No local event sources to stop");
		}

		// Should abort all subscribers
		Dispatcher.getInstance().stop();

		// Should stop all sessions
		SessionManager.getInstance().stop();
	}

	/**
	 * Servlet GET request: handles event requests.
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Event event = null;
		//System.out.println("cookie id " + CookieHelp.getPersonalId(request));
		try {
			// Event parm identifies event type from the client
			String eventType = Servlets.getParameter(request, P_EVENT);

			// Always must have an event type
			if (eventType == null) {
				Log.warn("Pushlet.doGet(): bad request, no event specified");
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No eventType specified");
				return;
			}

			// Create Event and set attributes from parameters
			event = new Event(eventType);
			for (Enumeration e = request.getParameterNames(); e.hasMoreElements();) {
				String nextAttribute = (String) e.nextElement();
				event.setField(nextAttribute, request.getParameter(nextAttribute));
			}


		} catch (Throwable t) {
			// Error creating event
			Log.warn("Pushlet: Error creating event in doGet(): ", t);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		//int id = CookieHelp.getPersonalId(request);
		// Handle parsed request
		doRequest(event, request, response);

	}

	/**
	 * Servlet POST request: extracts event data from body.
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Event event = null;
		try {
			// Create Event by parsing XML from input stream.
			event = EventParser.parse(new InputStreamReader(request.getInputStream()));

			// Always must have an event type
			if (event.getEventType() == null) {
				Log.warn("Pushlet.doPost(): bad request, no event specified");
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No eventType specified");
				return;
			}


		} catch (Throwable t) {
			// Error creating event
			Log.warn("Pushlet:  Error creating event in doPost(): ", t);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
	//	int id = CookieHelp.getPersonalId(request);
		// Handle parsed request
		doRequest(event, request, response);

	}

	/**
	 * 这里修改了id产生方式,由于数据的id已经是唯一的,这里没有必要再随机生成id,这里就用了用户的id
	 * @param personId
	 * @param anEvent
	 * @param request
	 * @param response
	 */
	protected void doRequest(Event anEvent, HttpServletRequest request, HttpServletResponse response) {
		/*String msg = anEvent.getField("msg");
		if(msg != null){
			try {
				msg = new String(msg.getBytes("ISO8859-1"), "UTF-8");
				System.out.println(msg);
				System.out.println(anEvent.toString());
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			anEvent.setField("msg", msg);
		}*/
		
		//int pid = CookieHelp.getPersonalId(request);
		//System.out.println(pid + " from sessionManager"); 
		//System.out.println(anEvent);
		
		// Must have valid event type.
		String eventType = anEvent.getEventType();
		try {

			// Get Session: either by creating (on Join eventType)
			// or by id (any other eventType, since client is supposed to have joined).
			Session session = null;
			if (eventType.startsWith(Protocol.E_JOIN)) {
				// Join request: create new subscriber
				int personId = CookieHelp.getPersonalId(request); 
				session = SessionManager.getInstance().createSession(personId, anEvent);

				String userAgent = request.getHeader("User-Agent");
				if (userAgent != null) {
					userAgent = userAgent.toLowerCase();
				} else {
					userAgent = "unknown";
				}
				session.setUserAgent(userAgent);

			} else {
				// Must be a request for existing Session

				// Get id
				String id = anEvent.getField(P_ID);

				// We must have an id value
				if (id == null) {
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No id specified");
					Log.warn("Pushlet: bad request, no id specified event=" + eventType);
					return;
				}

				// We have an id: get the session object
				session = SessionManager.getInstance().getSession(id);

				// Check for invalid id
				if (session == null) {
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid or expired id: " + id);
					Log.warn("Pushlet:  bad request, no session found id=" + id + " event=" + eventType);
					return;
				}
			}

			// ASSERTION: we have a valid Session

			// Let Controller handle request further
			// including exceptions
			Command command = Command.create(session, anEvent, request, response);
			session.getController().doCommand(command);
		} catch (Throwable t) {
			// Hmm we should never ever get here
			Log.warn("Pushlet:  Exception in doRequest() event=" + eventType, t);
			t.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

	}
}

