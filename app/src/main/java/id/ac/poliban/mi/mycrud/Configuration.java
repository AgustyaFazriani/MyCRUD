package id.ac.poliban.mi.mycrud;

public class Configuration {
    //Dibawah ini merupakan Pengalamatan dimana Lokasi Skrip CRUD PHP disimpan
    //Pada tutorial Kali ini, karena kita membuat localhost maka alamatnya tertuju ke IP komputer
    //dimana File PHP tersebut berada
    //PENTING! JANGAN LUPA GANTI IP SESUAI DENGAN IP KOMPUTER DIMANA DATA PHP BERADA

    public static final String URL_ADD = "http://192.168.43.169/employee/InsertPegawai.php";
    public static final String URL_GET_ALL = "http://192.168.43.169/employee/GetAllPegawai.php";
    public static final String URL_GET_EMP = "http://192.168.43.169/employee/GetAPegawai.php?id=";
    public static final String URL_UPDATE_EMP = "http://192.168.43.169/employee/UpdatePegawai.php";
    public static final String URL_DELETE_EMP = "http://192.168.43.169/employee/DeletePegawai.php?id=";
    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP

    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_NAMA = "nama";
    public static final String KEY_EMP_POSISI = "posisi"; // variabel untuk posisi
    public static final String KEY_EMP_GAJI = "gaji"; // variabel untuk gaji
    //JSON Tags

    public static final String TAG_JSON_ARRAY = "data";
    public static final String TAG_ID = "id";
    public static final String TAG_NAMA = "nama";
    public static final String TAG_POSISI = "posisi";
    public static final String TAG_GAJI = "gaji";
    //ID karyawan
    //emp itu singkatan dari Employee

    public static final String EMP_ID = "emp_id";
}


