package vn.edu.tdmu.lethanhhiep.bcck.Model;

public class User
{
    private String hoten;
    private String matkhau;
    private String ngaysinh;
    private String diachi;

    public User(String hoten, String matkhau, String ngaysinh, String diachi)
    {
        this.hoten = hoten;
        this.matkhau = matkhau;
        this.ngaysinh = ngaysinh;
        this.diachi = diachi;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}