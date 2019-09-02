package com.guoyue.kitchenhelper.domain.admin;

/**
 * 管理员实体类
 */
public class AdminInfo {
    public static AdminInfo currentLoginAdmin=null;
    private Integer empId;
    private String empName;
    private String password;

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AdminInfo{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
