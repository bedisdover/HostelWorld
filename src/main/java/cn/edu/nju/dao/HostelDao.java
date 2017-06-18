package cn.edu.nju.dao;

import cn.edu.nju.mapper.HostelMapper;
import cn.edu.nju.model.Hostel;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by song on 17-3-8.
 * <p>
 * Hostel相关
 */
@Repository
public class HostelDao {

    @Resource
    private HostelMapper hostelMapper;

    /**
     * 判断是否存在地址相同的同名hostel
     */
    public boolean isHostelExisted(String name, String address) {
        return hostelMapper.isHostelExisted(name, address) == 1;
    }

    /**
     * 获取最后一个ID
     */
    public synchronized String getLastID() {
        return hostelMapper.getLastID();
    }

    /**
     * 创建hostel
     */
    public boolean createHostel(Hostel hostel) {
        try {
            hostelMapper.createHostel(hostel);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 激活
     */
    public boolean activateHostel(String hostelID) {
        try {
            hostelMapper.activateHostel(hostelID);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 通过ID获取hostel对象
     */
    public Hostel getHostelByID(String hostelID) {
        return hostelMapper.getHostelByID(hostelID);
    }

    public List<Hostel> getAllHostel() {
        return hostelMapper.getAllHostel();
    }

    /**
     * 旅店数量
     */
    public int getHostelNum() {
        return hostelMapper.getHostelNum();
    }
}
