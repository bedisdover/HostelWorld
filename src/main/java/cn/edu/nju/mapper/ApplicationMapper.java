package cn.edu.nju.mapper;

import cn.edu.nju.model.ModifyApplication;
import cn.edu.nju.model.OpenApplication;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by song on 17-3-13.
 *
 * 申请相关，包括开店申请、修改信息申请
 */
public interface ApplicationMapper {

    /**
     * 开店申请
     */
    int applyForOpen(String hostelID, String name, String address);

    /**
     * 获得开店申请
     */
    OpenApplication getOpenApplication(String hostelID);

    /**
     * 修改信息申请
     */
    int applyForModify(String hostelID, String name_before, String address_before,
                       String name_after, String address_after);

    /**
     * 获得修改信息申请
     */
    ModifyApplication getModifyApplication(String hostelID);

    List<OpenApplication> getOpenApplicationList();

    List<ModifyApplication> getModifyApplicationList();

    int approveOpen(String hostelID, String pass, String notes);

    int approveModify(int id, String pass, String notes);

    int approveOpenBatch(@Param("id") int[] id, String pass);

    int approveModifyBatch(@Param("id") int[] id, String pass);
}
