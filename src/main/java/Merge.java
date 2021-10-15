import java.util.List;

public class Merge{

  public static List<Volunteer> MergeName(List<Volunteer> volunteers){
    if(volunteers.get(0).name == ""){
      for(int i =0; i<volunteers.size(); i++){
        if(volunteers.get(i).name != "" && volunteers.get(i).name != volunteers.get(0).firstname){
          volunteers.get(0).name = volunteers.get(i).name;
          break;
        }
      }
    }
    return volunteers;
  }

  public static List<Volunteer> Mergefirstname(List<Volunteer> volunteers){
    if(volunteers.get(0).firstname == ""){
      for(int i =0; i<volunteers.size(); i++){
        if(volunteers.get(i).firstname != "" && volunteers.get(i).firstname != volunteers.get(0).name){
          volunteers.get(0).firstname = volunteers.get(i).firstname;
          break;
        }
      }
    }
    return volunteers;
  }

  public static List<Volunteer> MergeNametag(List<Volunteer> volunteers){
    if(volunteers.get(0).nametag == ""){
      for(int i =0; i<volunteers.size(); i++){
        if(volunteers.get(i).nametag != ""){
          volunteers.get(0).nametag = volunteers.get(i).nametag;
          break;
        }
      }
    }
    return volunteers;
  }

  public static List<Volunteer> MergeMail(List<Volunteer> volunteers){
    if(volunteers.get(0).mail == ""){
      for(int i =0; i<volunteers.size(); i++){
        if(volunteers.get(i).mail != ""){
          volunteers.get(0).mail = volunteers.get(i).mail;
          break;
        }
      }
    }
    return volunteers;
  }

  public static List<Volunteer> MergeTel(List<Volunteer> volunteers){
    if(volunteers.get(0).tel == ""){
      for(int i =0; i<volunteers.size(); i++){
        if(volunteers.get(i).tel != ""){
          volunteers.get(0).tel = volunteers.get(i).tel;
          break;
        }
      }
    }
    return volunteers;
  }
}