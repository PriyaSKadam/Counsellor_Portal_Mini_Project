package in.priya.service;

import in.priya.binding.Dashboard;
import in.priya.entity.Counsellor;

public interface CounsellorService {
	
	public boolean saveCounsellor(Counsellor c);
	
	public Counsellor handleLogin(String email, String password);
	
	public boolean handlePassword(String email);
	
	public Dashboard getDashboard(Integer cid);

}
