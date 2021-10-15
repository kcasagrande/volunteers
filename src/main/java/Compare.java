import java.util.ArrayList;
import java.util.List;

public class Compare {

  public List<Volonteer> volonteers = new ArrayList<Volonteer>();
  public List<Volonteer> comparedVolonteers = new ArrayList<Volonteer>();

  public Compare(List<Volonteer> newVolonteers) {
      this.volonteers = newVolonteers;
  }

  public List<Volonteer> CompareNum(String volonteerNum){

    for(int index = 0; index < volonteers.size() ; index ++ ){
      if (volonteerNum == volonteers.get(index).tel){
        this.comparedVolonteers.add(volonteers.get(index));
      }
    }
    return this.comparedVolonteers;
  }

  public List<Volonteer> CompareMail(List<Volonteer> comparedVolonteers, String volonteerMail){

    for(int index = 0; index < volonteers.size() ; index++ ){
      if (volonteerMail == volonteers.get(index).mail){
        this.comparedVolonteers.add(volonteers.get(index));
      }
    }
    return comparedVolonteers;
  }

  public List<Volonteer> CompareFirstName(List<Volonteer> comparedVolonteers,String volonteerFirstName){

    for(int index = 0; index < comparedVolonteers.size() ; index++ ){
      if (volonteerFirstName != volonteers.get(index).firstname){
        comparedVolonteers.remove(index);
        index = index--;
      }
    }
    return comparedVolonteers;
  }

  public List<Volonteer> CompareName(List<Volonteer> comparedVolonteers, String volonteerName){

    for(int index = 0; index < comparedVolonteers.size() ; index++ ){
      if (volonteerName != volonteers.get(index).name){
        comparedVolonteers.remove(index);
        index = index--;
      }
    }
    return comparedVolonteers;
  }

  public List<Volonteer> CompareFirstNameByName(List<Volonteer> comparedVolonteers, String volonteerName){

    for(int index = 0; index < comparedVolonteers.size() ; index++ ){
      if (volonteerName != volonteers.get(index).firstname){
        comparedVolonteers.remove(index);
        index = index--;
      }
    }
    return this.comparedVolonteers;
  }
  
  public List<Volonteer> CompareNameByFirstName(List<Volonteer> comparedVolonteers, String volonteerName){

    for(int index = 0; index < comparedVolonteers.size() ; index++ ){
      if (volonteerName != volonteers.get(index).firstname){
        comparedVolonteers.remove(index);
        index = index--;
      }
    }
    return comparedVolonteers;
  }
}