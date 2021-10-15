import java.util.ArrayList;
import java.util.List;

public class Compare {

  public List<Volunteer> volonteers = new ArrayList<Volunteer>();
  public List<Volunteer> comparedVolonteers = new ArrayList<Volunteer>();

  public Compare(List<Volunteer> newVolonteers) {
      this.volonteers = newVolonteers;
  }

  public List<Volunteer> CompareNum(String volonteerNum){

    for(int index = 0; index < volonteers.size() ; index ++ ){
      if (volonteerNum == volonteers.get(index).tel){
        this.comparedVolonteers.add(volonteers.get(index));
      }
    }
    return this.comparedVolonteers;
  }

  public List<Volunteer> CompareMail(List<Volunteer> comparedVolonteers, String volonteerMail){

    for(int index = 0; index < volonteers.size() ; index++ ){
      if (volonteerMail == volonteers.get(index).mail){
        this.comparedVolonteers.add(volonteers.get(index));
      }
    }
    return comparedVolonteers;
  }

  public List<Volunteer> CompareFirstName(List<Volunteer> comparedVolonteers,String volonteerFirstName){

    for(int index = 0; index < comparedVolonteers.size() ; index++ ){
      if (volonteerFirstName != volonteers.get(index).firstname){
        this.CompareNameByFirstName(comparedVolonteers, volonteerFirstName);
      }
    }
    return comparedVolonteers;
  }

  public List<Volunteer> CompareName(List<Volunteer> comparedVolonteers, String volonteerName){

    for(int index = 0; index < comparedVolonteers.size() ; index++ ){
      if (volonteerName != volonteers.get(index).name){
        this.CompareFirstNameByName(comparedVolonteers, volonteerName);
      }
    }
    return comparedVolonteers;
  }

  public List<Volunteer> CompareFirstNameByName(List<Volunteer> comparedVolonteers, String volonteerName){

    for(int index = 0; index < comparedVolonteers.size() ; index++ ){
      if (volonteerName != volonteers.get(index).firstname){
        comparedVolonteers.remove(index);
        index = index--;
      }
    }
    return this.comparedVolonteers;
  }

  public List<Volunteer> CompareNameByFirstName(List<Volunteer> comparedVolonteers, String volonteerName){

    for(int index = 0; index < comparedVolonteers.size() ; index++ ){
      if (volonteerName != volonteers.get(index).firstname){
        comparedVolonteers.remove(index);
        index = index--;
      }
    }
    return comparedVolonteers;
  }
}