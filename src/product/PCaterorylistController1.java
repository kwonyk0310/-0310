package product;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ProductDAO;
import VO.ProductVO;
import common.SuperClass;
import utility.FlowParameters;
import utility.Paging;

public class PCaterorylistController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FlowParameters parameters2
		= new FlowParameters(
				request.getParameter("pageNumber"), 
				request.getParameter("pageSize"), 
				request.getParameter("mode"));
		
		System.out.println(this.getClass() + " : " + parameters2.toString());
		
		ProductDAO dao = new ProductDAO();
		
		int totalCount2
		= dao.SelectTotalCount2(
				parameters2.getMode());
		
		String contextPath = request.getContextPath() ;
		String myurl = contextPath + "/Shopping?command=pcategorylist" ;
		
		Paging pageInfo2 = new Paging(
				parameters2.getPageNumber(), 
				parameters2.getPageSize(), 
				totalCount2, 
				myurl, 
				parameters2.getMode()) ;
		
		List<ProductVO> lists2 = dao.SelectDataList2(
				pageInfo2.getBeginRow(), 
				pageInfo2.getEndRow(),
				parameters2.getMode());
		
		
		// ǥ�� �� ��ϵ�
		request.setAttribute("lists", lists2);
		
		// ����¡ ���� �׸��
		request.setAttribute("pagingHtml", pageInfo2.getPagingHtml());
		request.setAttribute("pagingStatus", pageInfo2.getPagingStatus());
		
		// �ʵ� �˻��� ���õ� �׸��
		request.setAttribute("mode", parameters2.getMode());
		//request.setAttribute("keyword", parameters2.getKeyword());
		
		// �Ķ���� ����Ʈ ���ڿ� : �󼼺���, ����, ����, ��� � ����
		request.setAttribute("parameters", parameters2.toString());		
		
		super.doGet(request, response);
		//System.out.println("�� �ȳ���" + request.getAttribute("mode"));
		String gotopage = "product/pcategorylist.jsp" ;
		super.GotoPage(gotopage);
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		String gotopage = "" ;
		if (this.validate(request) == true) {
			gotopage = "" ;
			super.GotoPage(gotopage);
			
		}else {
			gotopage = "" ;
			super.GotoPage(gotopage);
		}
	}
	@Override
	public boolean validate(HttpServletRequest request) {
		boolean isCheck = true ;
		
		return isCheck ;
	}
}