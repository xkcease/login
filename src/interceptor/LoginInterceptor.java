package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import po.User;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("interceptor start");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null) {
			System.out.println("not login");
			response.sendRedirect("login");
			return false;
		}
		else {
			System.out.println("login success");
			return true;
		}
	}
}
