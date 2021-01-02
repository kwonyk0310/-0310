package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MemberDAO;
import VO.MemberVO;
import common.IndexController;
import common.SuperClass;

public class MemberLoginController extends SuperClass{
	private String id ;
	private String password ;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("회원 로그인 호출됨");

		super.doGet(request, response);
		super.GotoPage("/member/login.jsp");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.id = request.getParameter("id");
		this.password = request.getParameter("password1");

		System.out.println("id : " + request.getParameter("id"));
		System.out.println("password : " + request.getParameter("password1"));

		if(!this.validate(request)) {

			String errmsg = "로그인 양식이 잘못되었습니다.";


			super.setErrorMessage(errmsg);
			this.doGet(request, response);
		}else {

			MemberDAO dao = new MemberDAO();
			try {
				MemberVO member = dao.selectMember(id,password);
				super.doPost(request, response);
				super.session.setAttribute("loginfo", member);
			} catch (NoSuchFieldException e) {

				String errmsg = "아이디 혹은 비밀번호가 잘못되었습니다.";

				System.out.println(errmsg);
				super.setErrorMessage(errmsg);
				this.doGet(request, response);
			}

			new IndexController().doGet(request, response);
		}
	}

	@Override
	public boolean validate(HttpServletRequest request) {

		boolean isCheck = true;	// 기본 값은 true 입니다.
		// 만일 유효성 검사의 문제가 있으면 isCheck 는 false 로 변경합니다.

		if(this.id.length() < 4 || this.id.length()> 12) {
			request.setAttribute(super.PREFIX+"id", "아이디는 4자리 이상 12자리 이하이어야 합니다.");

			isCheck = false;
		}

		if(this.password.length() < 4 || this.password.length()> 15) {
			request.setAttribute(super.PREFIX+"passowrd", "비밀번호는 4자리 이상 15자리 이하이어야 합니다.");

			isCheck = false;
		}
		return isCheck;
	}
}