import java.util.ArrayList;
import java.util.List;

public class Compare {

  public List<Volunteer> volunteers = new ArrayList<Volunteer>();
  public List<Volunteer> comparedVolunteers = new ArrayList<Volunteer>();

  public Compare(List<Volunteer> newVolunteers) {
      this.volunteers = newVolunteers;
  }

  public List<Volunteer> CompareNum(String volunteerNum){

    for(int index = 0; index < volunteers.size() ; index ++ ){
      if (volunteerNum == volunteers.get(index).tel){
        this.comparedVolunteers.add(volunteers.get(index));
      }
    }
    return this.comparedVolunteers;
  }

  public List<Volunteer> CompareMail(List<Volunteer> comparedVolunteers, String volunteerMail){

    for(int index = 0; index < volunteers.size() ; index++ ){
      if (volunteerMail == volunteers.get(index).mail){
        this.comparedVolunteers.add(volunteers.get(index));
      }
    }
    return comparedVolunteers;
  }

  public List<Volunteer> CompareFirstName(List<Volunteer> comparedVolunteers,String volunteerFirstName){

    for(int index = 0; index < comparedVolunteers.size() ; index++ ){
      if (volunteerFirstName != volunteers.get(index).firstname){
        this.CompareNameByFirstName(comparedVolunteers, volunteerFirstName);
      }
    }
    return comparedVolunteers;
  }

  public List<Volunteer> CompareName(List<Volunteer> comparedVolunteers, String volunteerName){

    for(int index = 0; index < comparedVolunteers.size() ; index++ ){
      if (volunteerName != volunteers.get(index).name){
        this.CompareFirstNameByName(comparedVolunteers, volunteerName);
      }
    }
    return comparedVolunteers;
  }

  public List<Volunteer> CompareFirstNameByName(List<Volunteer> comparedVolunteers, String volunteerName){

    for(int index = 0; index < comparedVolunteers.size() ; index++ ){
      if (volunteerName != volunteers.get(index).firstname){
        comparedVolunteers.remove(index);
        index = index--;
      }
    }
    return this.comparedVolunteers;
  }

  public List<Volunteer> CompareNameByFirstName(List<Volunteer> comparedVolunteers, String volunteerName){

    for(int index = 0; index < comparedVolunteers.size() ; index++ ){
      if (volunteerName != volunteers.get(index).firstname){
        comparedVolunteers.remove(index);
        index = index--;
      }
    }
    return comparedVolunteers;
  }
}