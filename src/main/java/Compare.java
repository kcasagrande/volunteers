public class Compare {

  public Volonteer[] volonteers = new Volonteer[900];
  public Volonteer[] comparedVolonteers = new Volonteer[900];

  public Compare(Volonteer[] newVolonteers) {
      this.volonteers = newVolonteers;
  }

  public Volonteer[] CompareNum(String volonteerNum){

    for(int index = 0; index < volonteers.length ; index ++ ){
      if (volonteerNum == volonteers[index].tel){
        this.comparedVolonteers[index]= volonteers[index];
      }

    }
    return this.comparedVolonteers;
  }

  public Volonteer[] CompareMail(Volonteer comparedVolonteers, String volonteerMail){

    for(int index = 0; index < volonteers.length ; index++ ){
      if (volonteerMail == volonteers[index].mail){
        comparedVolonteers[index]= volonteers[index];
      }

    }
    return comparedVolonteers;
  }

  public Volonteer[] CompareFirstName(Volonteer comparedVolonteers,String volonteerFirstName){

    for(int index = 0; index < comparedVolonteers.length ; index++ ){
      if (volonteerFirstName !== comparedVolonteers[index].firstname){
        comparedVolonteers[index]= volonteers[index];
      }

    }
    return comparedVolonteers;
  }

  public Volonteer[] CompareName(Volonteer comparedVolonteers, String volonteerName){

    for(int index = 0; index < comparedVolonteers.length ; index++ ){
      if (volonteerName !== comparedVolonteers[index].name){
        comparedVolonteers[index]= volonteers[index];
      }

    }
    return comparedVolonteers;
  }

  public Volonteer[] CompareFirstNameByName(Volonteer comparedVolonteers, String volonteerName){

    for(int index = 0; index < comparedVolonteers.length ; index++ ){
      if (volonteerName !== comparedVolonteers[index].firstname){
        comparedVolonteers[index]= volonteers[index];
      }

    }
    return this.comparedVolonteers;
  }
  public Volonteer[] CompareNameByFirstName(Volonteer comparedVolonteers, String volonteerFirstName){

    for(int index = 0; index < comparedVolonteers.length ; index++ ){
      if (volonteerFirstName !== comparedVolonteers[index].name){
        comparedVolonteers[index]= volonteers[index];
      }

    }
    return comparedVolonteers;
  }
}