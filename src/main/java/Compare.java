public class Compare {

  public List<Volonteer> volonteers = new Volonteer[900];
  public List<Volonteer> comparedVolonteers = new Volonteer[900];

  public Compare(List<Volonteer> newVolonteers) {
      this.volonteers = newVolonteers;
  }

  public List<Volonteer> CompareNum(String volonteerNum){

    for(int index = 0; index < volonteers.length ; index ++ ){
      if (volonteerNum == volonteers[index].tel){
        this.comparedVolonteers[index]= volonteers[index];
      }
    }
    return this.comparedVolonteers;
  }

  public List<Volonteer> CompareMail(List<Volonteer> comparedVolonteers, String volonteerMail){

    for(int index = 0; index < volonteers.length ; index++ ){
      if (volonteerMail == volonteers[index].mail){
        comparedVolonteers.add(volonteer[index]) ;
      }
    }
    return comparedVolonteers;
  }

  public List<Volonteer> CompareFirstName(List<Volonteer> comparedVolonteers,String volonteerFirstName){

    for(int index = 0; index < comparedVolonteers.length ; index++ ){
      if (volonteerFirstName != comparedVolonteers[index].firstname){
        comparedVolonteers[index].remove()
        index = index--
      }
    }
    return comparedVolonteers;
  }

  public List<Volonteer> CompareName(List<Volonteer> comparedVolonteers, String volonteerName){

    for(int index = 0; index < comparedVolonteers.length ; index++ ){
      if (volonteerName != comparedVolonteers[index].name){
        comparedVolonteers[index].remove()
        index = index--
      }
    }
    return comparedVolonteers;
  }

  public List<Volonteer> CompareFirstNameByName(List<Volonteer> comparedVolonteers, String volonteerName){

    for(int index = 0; index < comparedVolonteers.length ; index++ ){
      if (volonteerName != comparedVolonteers[index].firstname){
        comparedVolonteers[index].remove()
        index = index--
      }
    }
    return this.comparedVolonteers;
  }
  public List<Volonteer> CompareNameByFirstName(List<Volonteer> comparedVolonteers, String volonteerFirstName){

    for(int index = 0; index < comparedVolonteers.length ; index++ ){
      if (volonteerFirstName != comparedVolonteers[index].name){
        comparedVolonteers[index].remove()
        index = index--
      }
    }
    return comparedVolonteers;
  }
}