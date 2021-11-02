public class Volunteer {
    String firstname;
    String name;
    String nametag;
    String mail;
    String tel; 

    Volunteer(String firstname, String name, String nametag, String mail, String tel){
        this.firstname = firstname;
        this.name = name;
        this.nametag = nametag;
        this.mail = mail;
        this.tel = tel;
    }
    Volunteer(){}

    @Override
    public boolean equals(Object obj) {        
        return obj instanceof Volunteer && this.firstname.equals(((Volunteer)obj).firstname) && this.name.equals(((Volunteer)obj).name) && this.mail.equals(((Volunteer)obj).mail) && this.nametag.equals(((Volunteer)obj).nametag) && this.tel.equals(((Volunteer)obj).tel) ;
    } 
}
