package com.project.campustaobao.mapper;
import com.project.campustaobao.pojo.Administrator;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface AdminMapper {
    /**
     * 通过账号查找管理员
     * @param adminAccount 管理员账号
     * @return 返回该账号的管理员
     */
    Administrator queryAdminByAccount(@Param("account")String adminAccount);
    /**
     * 此方法用于获取管理员集合，
     * 主要是由系统管理员调用
     * @param adminType 管理员类型必须是0，即系统管理员才可以调用
     * @return 普通管理员的集合
     */
    List<Administrator> queryAdminList(@Param("adminType") int adminType);
    /**
     * 查询某管理员是否是禁用状态
     * @param adminAccount 管理员账号
     * @return 该管理员账号是禁用则返回true
     */
    boolean isBaned(@Param("account")String adminAccount);

    /**
     * 通过账号获取密码，用于登陆时校验
     * @param adminAccount 账号
     * @return 返回管理员注册时的密码
     */
    String queryPasswordAccount(@Param("account")String adminAccount);
    Map<String,String> queryAdminSimpleInfoByAdminAccount(@Param("account")String adminAccount);
    boolean updateAdminSimpleInfo(Map<String,String> simpleInfo);

    /**
     * 新的管理员注册成功后(插入成功后)需要返回一个账号
     * @param admin 新管理员
     * @return 返回新管理员的·账号
     */
    boolean insertAdmin(Administrator admin);

    /**
     * 更新管理员状态：   正常/禁用
     * @param adminAccount 管理员账号
     * @return 禁用成功返回true
     */
    boolean updateAdminStatus(@Param("account")String adminAccount, @Param("status") boolean status );
}
