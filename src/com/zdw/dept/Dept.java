package com.zdw.dept;

import jakarta.servlet.http.HttpSessionListener;

import java.util.Objects;

public class Dept  {
    private String emptno;
    private String ename;
    private String loc;

    @Override
    public String toString() {
        return "Dept{" +
                "emptno='" + emptno + '\'' +
                ", ename='" + ename + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dept dept = (Dept) o;
        return Objects.equals(emptno, dept.emptno) && Objects.equals(ename, dept.ename) && Objects.equals(loc, dept.loc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emptno, ename, loc);
    }

    public void setEmptno(String emptno) {
        this.emptno = emptno;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getEmptno() {
        return emptno;
    }

    public String getEname() {
        return ename;
    }

    public String getLoc() {
        return loc;
    }

    public Dept() {
    }

    public Dept(String emptno, String ename, String loc) {
        this.emptno = emptno;
        this.ename = ename;
        this.loc = loc;
    }
}
