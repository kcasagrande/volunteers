import java.util.List;

public class Merge{

  public static List<Volunteer> MergeName(List<Volunteer> volonteers){
    if(volonteers.get(0).name == ""){
      for(int i =0; i<volonteers.size(); i++){
        if(volonteers.get(i).name != "" && volonteers.get(i).name != volonteers.get(0).firstname){
          volonteers.get(0).name = volonteers.get(i).name;
          break;
        }
      }
    }
    return volonteers;
  }

  public static List<Volunteer> Mergefirstname(List<Volunteer> volonteers){
    if(volonteers.get(0).firstname == ""){
      for(int i =0; i<volonteers.size(); i++){
        if(volonteers.get(i).firstname != "" && volonteers.get(i).firstname != volonteers.get(0).name){
          volonteers.get(0).firstname = volonteers.get(i).firstname;
          break;
        }
      }
    }
    return volonteers;
  }

  public static List<Volunteer> MergeNametag(List<Volunteer> volonteers){
    if(volonteers.get(0).nametag == ""){
      for(int i =0; i<volonteers.size(); i++){
        if(volonteers.get(i).nametag != ""){
          volonteers.get(0).nametag = volonteers.get(i).nametag;
          break;
        }
      }
    }
    return volonteers;
  }

  public static List<Volunteer> MergeMail(List<Volunteer> volonteers){
    if(volonteers.get(0).mail == ""){
      for(int i =0; i<volonteers.size(); i++){
        if(volonteers.get(i).mail != ""){
          volonteers.get(0).mail = volonteers.get(i).mail;
          break;
        }
      }
    }
    return volonteers;
  }

  public static List<Volunteer> MergeTel(List<Volunteer> volonteers){
    if(volonteers.get(0).tel == ""){
      for(int i =0; i<volonteers.size(); i++){
        if(volonteers.get(i).tel != ""){
          volonteers.get(0).tel = volonteers.get(i).tel;
          break;
        }
      }
    }
    return volonteers;
  }
}