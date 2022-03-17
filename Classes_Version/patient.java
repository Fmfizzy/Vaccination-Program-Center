
public class patient extends Booth{
    public String surname,city,NIC,vacReq;
    public int age;

    public patient(String fname,String lname,String City,int Age,String passport,String vacreq){    /*https://www.geeksforgeeks.org/inheritance-in-java*/
        super(fname);
        surname=lname;
        city=City;
        age=Age;
        NIC=passport;
        vacReq=vacreq;
    }
    

}
