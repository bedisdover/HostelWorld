package cn.edu.nju.dao;

import cn.edu.nju.mapper.ManagerMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by song on 17-3-9.
 * <p>
 * manager相关
 */
@Repository
public class ManagerDao {

    @Resource
    private ManagerMapper managerMapper;

    public String getPassword(String name) {
        return managerMapper.getPassword(name);
    }
}
