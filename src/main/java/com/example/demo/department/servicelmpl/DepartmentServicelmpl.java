package com.example.demo.department.servicelmpl;

import com.example.demo.department.entity.DepartmentInfo;
import com.example.demo.department.mapper.DepartmentMapper;
import com.example.demo.department.service.DepartmentService;
import com.example.demo.util.AppResponse;
import com.example.demo.util.PagedData;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class DepartmentServicelmpl implements DepartmentService {
    @Resource
    private DepartmentMapper departmentMapper;
    /**
     * 新增科室信息
     * @param departmentInfo
     * @return
     */
    @Override
    public AppResponse addDepartment(DepartmentInfo departmentInfo) {
        //生成科室编号
        departmentInfo.setDepartmentId(UUID.randomUUID().toString().replace("-",""));
        //查看科室编号是否存在
        if (departmentMapper.countDepartment(departmentInfo.getDepartmentCode()) != 0){
            return AppResponse.bizError("科室房间已存在,请重新输入");
        }
        if (departmentMapper.addDepartment(departmentInfo) == 0){
            return AppResponse.bizError("新增失败");
        }
        return AppResponse.success("新增成功");
    }

    /**
     * 分页查询科室信息
     * @param departmentInfo
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public AppResponse listDepartment(DepartmentInfo departmentInfo, Integer pageNo, Integer pageSize) {
        Map<String,Object> param = new HashMap<>();
        param.put("department",departmentInfo);
        Page<DepartmentInfo> page = PageHelper.startPage(pageNo,pageSize).doSelectPage(() ->{
            departmentMapper.listDepartment(param);
        });
        return AppResponse.success("查询成功", PagedData.getInstance(page));
    }

    /**
     * 修改科室信息
     * @param departmentInfo
     * @return
     */
    @Override
    public AppResponse updateDepartment(DepartmentInfo departmentInfo) {
        if (departmentMapper.updateDepartment(departmentInfo) == 0){
            return AppResponse.bizError("修改失败");
        }
        return AppResponse.success("修改成功");
    }

    /**
     * 删除科室信息
     * @param departmentId
     * @return
     */
    @Override
    public AppResponse deleteDepartment(List<String> departmentId) {
       if (departmentMapper.deleteDepartment(departmentId) == 0){
           return AppResponse.bizError("删除失败");
       }
       return AppResponse.success("删除成功");
    }
}
