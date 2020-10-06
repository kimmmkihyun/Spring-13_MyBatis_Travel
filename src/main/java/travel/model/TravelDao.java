package travel.model;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import utility.Paging;



@Component("myTravelDao")
public class TravelDao {

	String namespace = "travel.TravelBean";
	@Autowired //객체는 root-context.xml에서 만들었다
	SqlSessionTemplate sqlSessionTemplate;

	public void insertTravel(TravelBean travel) {
		
		int cnt = sqlSessionTemplate.insert(namespace+".InsertTravel", travel);
		System.out.println("insertTravel cnt : " + cnt);
					
	}

	public List<TravelBean> getTravelList(Paging pageInfo,Map<String, String> map) {
		List<TravelBean> lists = new ArrayList<TravelBean>();
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());
		lists = sqlSessionTemplate.selectList(namespace+".GetListTravel",map,rowBounds);
		System.out.println("lists.size():"+lists.size());
		return lists; 
	}

	public void deleteTravel(int num) {
		
		int cnt = sqlSessionTemplate.delete(namespace+".DeleteTravel", num);
		System.out.println("deleteTravel cnt : " +cnt);
		
	}

	public TravelBean getOneTravel(int num) {
		
		TravelBean travel = null;
		travel = sqlSessionTemplate.selectOne(namespace+".GetOneTravel",num);
		return travel;
	}

	public int updateTravel(TravelBean travel) {
		
		int cnt = sqlSessionTemplate.update(namespace+".UpdateTravel", travel);
		return cnt;
	}

	public int getTotalCount(Map<String, String> map) {
		int cnt = sqlSessionTemplate.selectOne(namespace+".GetTotalCount",map);
		return cnt;
	}
	
	
	
	
}
