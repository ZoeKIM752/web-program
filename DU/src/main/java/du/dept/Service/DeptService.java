package du.dept.Service;

import java.util.List;

import du.dept.domain.DeptVO;

public interface DeptService {
	public List<DeptVO> selectDeptList();
	public void insertDept(DeptVO dept);
	public void deleteDept(String deptCd);
}
