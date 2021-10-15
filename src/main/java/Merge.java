public class Merge{

  public static Volonteer MergeName(List<Volonteer> volonteers){
    if(!volonteers.get(0).name){
      for(int i =0; i<volonteers.size(); i++){
        if(volonteers.get(i).name && volonteers.get(i).name != volonteers.get(0).firstname){
          volonteers.get(0).name = volonteers.get(i).name;
          break;
        }
      }
    }
    return volonteers;
  }

  public static Volonteer MergeFirstName(List<Volonteer> volonteers){
    if(!volonteers.get(0).firstName){
      for(int i =0; i<volonteers.size(); i++){
        if(volonteers.get(i).firstName && volonteers.get(i).firstName != volonteers.get(0).Name){
          volonteers.get(0).firstName = volonteers.get(i).firstName;
          break;
        }
      }
    }
    return volonteers;
  }

  public static Volonteer MergeNametag(List<Volonteer> volonteers){
    if(!volonteers.get(0).nametag){
      for(int i =0; i<volonteers.size(); i++){
        if(volonteers.get(i).nametag){
          volonteers.get(0).nametag = volonteers.get(i).nametag;
          break;
        }
      }
    }
    return volonteers;
  }

  public static Volonteer MergeMail(List<Volonteer> volonteers){
    if(!volonteers.get(0).mail){
      for(int i =0; i<volonteers.size(); i++){
        if(volonteers.get(i).mail){
          volonteers.get(0).mail = volonteers.get(i).mail;
          break;
        }
      }
    }
    return volonteers;
  }

  public static Volonteer MergeTel(List<Volonteer> volonteers){
    if(!volonteers.get(0).tel){
      for(int i =0; i<volonteers.size(); i++){
        if(volonteers.get(i).tel){
          volonteers.get(0).tel = volonteers.get(i).tel;
          break;
        }
      }
    }
    return volonteers;
  }
}