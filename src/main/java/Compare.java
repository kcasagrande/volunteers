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

  public Volonteer[] CompareMail(String volonteerMail){
    
    for(int index = 0; index < volonteers.length ; index++ ){
      if (volonteerMail == volonteers[index].mail){
        this.comparedVolonteers[index]= volonteers[index];
      }

    }
    return this.comparedVolonteers;
  }

  public Volonteer[] CompareFirstName(String volonteerFirstName){
    
    for(int index = 0; index < volonteers.length ; index++ ){
      if (volonteerFirstName == volonteers[index].firstname){
        this.comparedVolonteers[index]= volonteers[index];
      }

    }
    return this.comparedVolonteers;
  }

  public Volonteer[] CompareName(String volonteerName){
    
    for(int index = 0; index < volonteers.length ; index++ ){
      if (volonteerName == volonteers[index].name){
        this.comparedVolonteers[index]= volonteers[index];
      }

    }
    return this.comparedVolonteers;
  }

}