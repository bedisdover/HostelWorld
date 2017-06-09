package cn.edu.nju.dao;

import cn.edu.nju.mapper.ApplicationMapper;
import cn.edu.nju.model.Hostel;
import cn.edu.nju.model.ModifyApplication;
import cn.edu.nju.model.OpenApplication;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by song on 17-3-13.
 * <p>
 * 开店申请/修改信息申请相关
 */
@Repository
public class ApplicationDao {

    @Resource
    private ApplicationMapper applicationMapper;

    public boolean applyForOpen(String hostelID, String name, String address) {
        try {
            applicationMapper.applyForOpen(hostelID, name, address);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 开店申请结果
     */
    public OpenApplication getOpenApplication(String hostelID) {
        return applicationMapper.getOpenApplication(hostelID);
    }

    /**
     * 申请修改hostel信息
     */
    public boolean applyForModify(Hostel hostel, String name, String address) {
        try {
            applicationMapper.applyForModify(hostel.getHostelID(), hostel.getName(),
                    hostel.getAddress(), name, address);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ModifyApplication getModifyApplication(String hostelID) {
        return applicationMapper.getModifyApplication(hostelID);
    }

    public List<OpenApplication> getOpenApplicationList() {
        return applicationMapper.getOpenApplicationList();
    }

    public List<ModifyApplication> getModifyApplicationList() {
        return applicationMapper.getModifyApplicationList();
    }

    public boolean approveOpen(String hostelID, String pass, String notes) {
        try {
            applicationMapper.approveOpen(hostelID, pass, notes);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean approveModify(int id, String pass, String notes) {
        try {
            applicationMapper.approveModify(id, pass, notes);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean approveOpenBatch(int[] id, String pass) {
        return applicationMapper.approveOpenBatch(id, pass) == id.length;
    }

    public boolean approveModifyBatch(int[] id, String pass) {
        return applicationMapper.approveModifyBatch(id, pass) == id.length;
    }
}
