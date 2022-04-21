package org.example.volunteers;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

// Cette classe est une suite de tests servant d'exemple et d'aide-mémoire de la syntaxe Java et JUnit.
// Elle n'est pas nécessaire à la réalisation de l'exercice.
public class HasPhonePrefixTest {

  @Test
  public void testThatHasPrefixeIndicatorCorrectlyReturnFalse() {
// Given
    String fakeNumber = "+991534836599";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(fakeNumber);

// Then
    assertFalse(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsAfghanistanPrefixe() {
// Given
    String number = "+931719487589";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsAfriqueduSudPrefixe() {
// Given
    String number = "+271518880950";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsAlbaniePrefixe() {
// Given
    String number = "+3551149246898";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsAlgeriePrefixe() {
// Given
    String number = "+2131243548570";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsAllemagnePrefixe() {
// Given
    String number = "+491989696373";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsAndorrePrefixe() {
// Given
    String number = "+3761971676718";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsAngolaPrefixe() {
// Given
    String number = "+2441888578841";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsAnguillaPrefixe() {
// Given
    String number = "+12641730132993";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsAntiguaetBarbudaPrefixe() {
// Given
    String number = "+12681453220219";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsArabiesaouditePrefixe() {
// Given
    String number = "+9661267994330";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsArgentinePrefixe() {
// Given
    String number = "+541570856343";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsArmeniePrefixe() {
// Given
    String number = "+3741654529041";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsArubaPrefixe() {
// Given
    String number = "+2971431223234";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsAscensionSainteHeleneAscensionetTristandaCunhaPrefixe() {
// Given
    String number = "+2471085695790";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsAustraliePrefixe() {
// Given
    String number = "+611840783974";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsAutrichePrefixe() {
// Given
    String number = "+431326497450";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsAzerbaidjanPrefixe() {
// Given
    String number = "+9941866707752";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsBahamasPrefixe() {
// Given
    String number = "+12421652467092";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsBahreinPrefixe() {
// Given
    String number = "+9731391700855";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsBangladeshPrefixe() {
// Given
    String number = "+8801555983155";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsBarbadePrefixe() {
// Given
    String number = "+12461095702795";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsBelgiquePrefixe() {
// Given
    String number = "+321306517849";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsBelizePrefixe() {
// Given
    String number = "+5011931035644";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsBeninPrefixe() {
// Given
    String number = "+2291088915932";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsBermudesPrefixe() {
// Given
    String number = "+14411340283498";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsBhoutanPrefixe() {
// Given
    String number = "+9751271156491";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsBielorussiePrefixe() {
// Given
    String number = "+3751859220870";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsBirmaniePrefixe() {
// Given
    String number = "+951219950776";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsBoliviePrefixe() {
// Given
    String number = "+5911292673614";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsBosnieHerzegovinePrefixe() {
// Given
    String number = "+3871575907141";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsBotswanaPrefixe() {
// Given
    String number = "+2671354263743";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsBresilPrefixe() {
// Given
    String number = "+551563450412";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsBruneiPrefixe() {
// Given
    String number = "+6731864397922";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsBulgariePrefixe() {
// Given
    String number = "+3591147357002";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsBurkinaFasoPrefixe() {
// Given
    String number = "+2261573730301";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsBurundiPrefixe() {
// Given
    String number = "+2571309213734";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsCambodgePrefixe() {
// Given
    String number = "+8551648072832";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsCamerounPrefixe() {
// Given
    String number = "+2371260328339";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsCanadaPrefixe() {
// Given
    String number = "+11668956024";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsCapVertPrefixe() {
// Given
    String number = "+2381817723414";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsIlesCaimansPrefixe() {
// Given
    String number = "+13451782657576";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsRepubliquecentrafricainePrefixe() {
// Given
    String number = "+2361283419343";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsChiliPrefixe() {
// Given
    String number = "+561444151895";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsChineRepubliquePopulairedeChinePrefixe() {
// Given
    String number = "+861932399314";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsChyprePrefixe() {
// Given
    String number = "+3571602070001";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsColombiePrefixe() {
// Given
    String number = "+571636626610";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsComoresPrefixe() {
// Given
    String number = "+2691797476869";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsRepubliquedemocratiqueduCongoPrefixe() {
// Given
    String number = "+2431503002739";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsRepubliqueduCongoPrefixe() {
// Given
    String number = "+2421880511018";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsIlesCookPrefixe() {
// Given
    String number = "+6821060145320";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsCoreeduNordPrefixe() {
// Given
    String number = "+8501393194276";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsCoreeduSudPrefixe() {
// Given
    String number = "+821844476400";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsCostaRicaPrefixe() {
// Given
    String number = "+5061987521799";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsCotedIvoirePrefixe() {
// Given
    String number = "+2251376332682";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsCroatiePrefixe() {
// Given
    String number = "+3851376158064";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsCubaPrefixe() {
// Given
    String number = "+531384515486";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsCuracaoetPaysBascaribeensPrefixe() {
// Given
    String number = "+5991437927338";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsDanemarkPrefixe() {
// Given
    String number = "+451490679194";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsDiegoGarciaTerritoirebritanniquedeloceanIndienPrefixe() {
// Given
    String number = "+2461913570891";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsDjiboutiPrefixe() {
// Given
    String number = "+2531320256774";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsRepubliquedominicainePrefixe() {
// Given
    String number = "+11368249299";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsDominiquePrefixe() {
// Given
    String number = "+17671519756957";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsEgyptePrefixe() {
// Given
    String number = "+201097414767";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsEmiratsarabesunisPrefixe() {
// Given
    String number = "+9711974640771";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsEquateurPrefixe() {
// Given
    String number = "+5931579882995";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsErythreePrefixe() {
// Given
    String number = "+2911175897707";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsEspagnePrefixe() {
// Given
    String number = "+341705781834";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsEstoniePrefixe() {
// Given
    String number = "+3721671337104";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsEtatsUnisPrefixe() {
// Given
    String number = "+11903318419";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsEthiopiePrefixe() {
// Given
    String number = "+2511435542081";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsIlesFeroePrefixe() {
// Given
    String number = "+2981415959911";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsFidjiPrefixe() {
// Given
    String number = "+6791053389524";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsFinlandePrefixe() {
// Given
    String number = "+3581278317849";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsFrancePrefixe() {
// Given
    String number = "+331064480578";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsGabonPrefixe() {
// Given
    String number = "+2411045179294";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsGambiePrefixe() {
// Given
    String number = "+2201997293930";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsGeorgiePrefixe() {
// Given
    String number = "+9951559311731";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsGhanaPrefixe() {
// Given
    String number = "+2331581203786";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsGibraltarPrefixe() {
// Given
    String number = "+3501898217835";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsGrecePrefixe() {
// Given
    String number = "+301555239055";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsGrenadePrefixe() {
// Given
    String number = "+14731925052955";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsGroenlandPrefixe() {
// Given
    String number = "+2991414646033";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsGuadeloupePrefixe() {
// Given
    String number = "+5901688292077";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsGuamPrefixe() {
// Given
    String number = "+16711065120262";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsGuatemalaPrefixe() {
// Given
    String number = "+5021811422418";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsGuineePrefixe() {
// Given
    String number = "+2241147121237";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsGuineeequatorialePrefixe() {
// Given
    String number = "+2401989249000";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsGuineeBissauPrefixe() {
// Given
    String number = "+2451241578322";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsGuyanaPrefixe() {
// Given
    String number = "+5921332620427";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsGuyanePrefixe() {
// Given
    String number = "+5941752605317";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsHaitiPrefixe() {
// Given
    String number = "+5091553422186";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsHondurasPrefixe() {
// Given
    String number = "+5041729046690";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsHongKongPrefixe() {
// Given
    String number = "+8521954547013";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsHongriePrefixe() {
// Given
    String number = "+361839770589";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsIndePrefixe() {
// Given
    String number = "+911830600836";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsIndonesiePrefixe() {
// Given
    String number = "+621026057648";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsIrakPrefixe() {
// Given
    String number = "+9641567312475";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsIranPrefixe() {
// Given
    String number = "+981003661745";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsIrlandePrefixe() {
// Given
    String number = "+3531458477409";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsIslandePrefixe() {
// Given
    String number = "+3541128605494";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsIsraelPrefixe() {
// Given
    String number = "+9721498619887";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsItaliePrefixe() {
// Given
    String number = "+391399321064";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsJamaiquePrefixe() {
// Given
    String number = "+18761686055429";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsJaponPrefixe() {
// Given
    String number = "+811657454010";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsJordaniePrefixe() {
// Given
    String number = "+9621597938951";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsKazakhstanPrefixe() {
// Given
    String number = "+71203258083";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsKenyaPrefixe() {
// Given
    String number = "+2541829680284";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsKirghizistanPrefixe() {
// Given
    String number = "+9961878303522";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsKiribatiPrefixe() {
// Given
    String number = "+6861617820339";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsKosovodepuisle6Prefixe() {
// Given
    String number = "+3831009049971";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsKoweitPrefixe() {
// Given
    String number = "+9651020617825";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsLaosPrefixe() {
// Given
    String number = "+8561169839965";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsLesothoPrefixe() {
// Given
    String number = "+2661259086084";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsLettoniePrefixe() {
// Given
    String number = "+3711172867827";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsLibanPrefixe() {
// Given
    String number = "+9611969302925";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsLiberiaPrefixe() {
// Given
    String number = "+2311991288687";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsLibyePrefixe() {
// Given
    String number = "+2181862295784";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsLiechtensteinPrefixe() {
// Given
    String number = "+4231631802933";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsLituaniePrefixe() {
// Given
    String number = "+3701154508799";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsMacaoPrefixe() {
// Given
    String number = "+8531807193568";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsMacedoineduNordPrefixe() {
// Given
    String number = "+3891336254075";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsMadagascarPrefixe() {
// Given
    String number = "+2611100328055";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsMalaisiePrefixe() {
// Given
    String number = "+601084124326";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsMalawiPrefixe() {
// Given
    String number = "+2651293538136";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsMaldivesPrefixe() {
// Given
    String number = "+9601194356434";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsMaliPrefixe() {
// Given
    String number = "+2231036424869";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsMalouinesPrefixe() {
// Given
    String number = "+5001826383038";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsMaltePrefixe() {
// Given
    String number = "+3561677255461";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsIlesMariannesduNordPrefixe() {
// Given
    String number = "+16701982130006";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsMarocPrefixe() {
// Given
    String number = "+2121747639226";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsIlesMarshallPrefixe() {
// Given
    String number = "+6921468946262";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsMartiniquePrefixe() {
// Given
    String number = "+5961291419282";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsMauricePrefixe() {
// Given
    String number = "+2301444983124";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsMauritaniePrefixe() {
// Given
    String number = "+2221026423232";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsMayottePrefixe() {
// Given
    String number = "+2621580549689";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsMexiquePrefixe() {
// Given
    String number = "+521605649073";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsEtatsfederesdeMicronesiePrefixe() {
// Given
    String number = "+6911154252342";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsMoldaviePrefixe() {
// Given
    String number = "+3731444014797";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsMonacoPrefixe() {
// Given
    String number = "+3771852248302";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsMongoliePrefixe() {
// Given
    String number = "+9761316453910";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsMontenegroPrefixe() {
// Given
    String number = "+3821398346883";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsMontserratPrefixe() {
// Given
    String number = "+16641768387154";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsMozambiquePrefixe() {
// Given
    String number = "+2581509426053";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsNamibiePrefixe() {
// Given
    String number = "+2641093911740";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsNauruPrefixe() {
// Given
    String number = "+6741060080809";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsNepalPrefixe() {
// Given
    String number = "+9771321960317";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsNicaraguaPrefixe() {
// Given
    String number = "+5051388766275";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsNigerPrefixe() {
// Given
    String number = "+2271416815212";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsNigeriaPrefixe() {
// Given
    String number = "+2341167834531";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsNiuePrefixe() {
// Given
    String number = "+6831165229029";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsNorvegePrefixe() {
// Given
    String number = "+471343525229";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsNouvelleCaledoniePrefixe() {
// Given
    String number = "+6871773938301";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsNouvelleZelandePrefixe() {
// Given
    String number = "+641456481375";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsOmanPrefixe() {
// Given
    String number = "+9681710149236";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsOugandaPrefixe() {
// Given
    String number = "+2561567996835";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsOuzbekistanPrefixe() {
// Given
    String number = "+9981424850785";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsPakistanPrefixe() {
// Given
    String number = "+921645611859";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsPalaosPrefixe() {
// Given
    String number = "+6801539494750";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsPalestinereservePrefixe() {
// Given
    String number = "+9701085830521";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsPanamaPrefixe() {
// Given
    String number = "+5071558757643";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsPapouasieNouvelleGuineePrefixe() {
// Given
    String number = "+6751864362187";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsParaguayPrefixe() {
// Given
    String number = "+5951776864143";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsPaysBasPrefixe() {
// Given
    String number = "+311357191879";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsPerouPrefixe() {
// Given
    String number = "+511114005030";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsPhilippinesPrefixe() {
// Given
    String number = "+631608003390";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsPolognePrefixe() {
// Given
    String number = "+481996815274";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsPolynesiefrancaisePrefixe() {
// Given
    String number = "+6891811494874";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsPortoRicoPrefixe() {
// Given
    String number = "+11556439044";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsPortugalPrefixe() {
// Given
    String number = "+3511085252807";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsQatarPrefixe() {
// Given
    String number = "+9741557539702";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsLaReunionPrefixe() {
// Given
    String number = "+2621082381846";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsRoumaniePrefixe() {
// Given
    String number = "+401439720083";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsRoyaumeUniPrefixe() {
// Given
    String number = "+441130596861";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsRussiePrefixe() {
// Given
    String number = "+71584025306";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsRwandaPrefixe() {
// Given
    String number = "+2501357354696";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsSaintChristopheetNievesPrefixe() {
// Given
    String number = "+18691006977229";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsSainteHeleneAscensionetTristandaCunhailePrefixe() {
// Given
    String number = "+2901178874416";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsSainteLuciePrefixe() {
// Given
    String number = "+17581428412729";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsSaintMarinPrefixe() {
// Given
    String number = "+3781893635209";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsSaintPierreetMiquelonPrefixe() {
// Given
    String number = "+5081230354627";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsSaintVincentetlesGrenadinesPrefixe() {
// Given
    String number = "+17841420055233";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsIlesSalomonPrefixe() {
// Given
    String number = "+6771561033453";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsSalvadorPrefixe() {
// Given
    String number = "+5031834255634";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsSamoaPrefixe() {
// Given
    String number = "+6851552794339";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsSamoaamericainesPrefixe() {
// Given
    String number = "+16841225022206";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsSaoTomeetPrincipePrefixe() {
// Given
    String number = "+2391708655301";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsSenegalPrefixe() {
// Given
    String number = "+2211571559031";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsSerbiePrefixe() {
// Given
    String number = "+3811464567833";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsSeychellesPrefixe() {
// Given
    String number = "+2481493883878";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsSierraLeonePrefixe() {
// Given
    String number = "+2321308920692";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsSingapourPrefixe() {
// Given
    String number = "+651565746457";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsSlovaquiePrefixe() {
// Given
    String number = "+4211016196138";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsSloveniePrefixe() {
// Given
    String number = "+3861416639966";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsSomaliePrefixe() {
// Given
    String number = "+2521869545070";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsSoudanPrefixe() {
// Given
    String number = "+2491842741107";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsSoudanduSudPrefixe() {
// Given
    String number = "+2111724714662";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsSriLankaPrefixe() {
// Given
    String number = "+941946893469";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsSuedePrefixe() {
// Given
    String number = "+461018844885";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsSuissePrefixe() {
// Given
    String number = "+411742404597";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsSurinamePrefixe() {
// Given
    String number = "+5971462050195";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsEswatiniPrefixe() {
// Given
    String number = "+2681300481362";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsSyriePrefixe() {
// Given
    String number = "+9631872940704";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsTadjikistanPrefixe() {
// Given
    String number = "+9921974428094";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsTanzaniePrefixe() {
// Given
    String number = "+2551476243074";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsTaiwanRepubliquedeChinePrefixe() {
// Given
    String number = "+8861813002699";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsTchadPrefixe() {
// Given
    String number = "+2351881495739";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsRepubliquetchequePrefixe() {
// Given
    String number = "+4201120722473";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsTerritoiresexterieursdelAustralieIleChristmasIlesCocosIlesHeardetMacDonaldPrefixe() {
// Given
    String number = "+6721462279954";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsThailandePrefixe() {
// Given
    String number = "+661862974148";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsTimororientalPrefixe() {
// Given
    String number = "+6701611141524";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsTogoPrefixe() {
// Given
    String number = "+2281104159961";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsTokelauPrefixe() {
// Given
    String number = "+6901802808293";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsTongaPrefixe() {
// Given
    String number = "+6761985069165";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsTriniteetTobagoPrefixe() {
// Given
    String number = "+18681086412105";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsTunisiePrefixe() {
// Given
    String number = "+2161811063558";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsTurkmenistanPrefixe() {
// Given
    String number = "+9931851187059";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsIlesTurquesetCaiquesPrefixe() {
// Given
    String number = "+16491287910630";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsTurquiePrefixe() {
// Given
    String number = "+901854819514";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsTuvaluPrefixe() {
// Given
    String number = "+6881164439326";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsUkrainePrefixe() {
// Given
    String number = "+3801115741048";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsUruguayPrefixe() {
// Given
    String number = "+5981891885137";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsVanuatuPrefixe() {
// Given
    String number = "+6781547866474";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsVaticanSaintSiegePrefixe() {
// Given
    String number = "+391483442461";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsVenezuelaPrefixe() {
// Given
    String number = "+581768575530";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsIlesViergesdesEtatsUnisPrefixe() {
// Given
    String number = "+13401519745821";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsIlesViergesbritanniquesPrefixe() {
// Given
    String number = "+12841915079486";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsVietNamPrefixe() {
// Given
    String number = "+841377587995";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsWallisetFutunaPrefixe() {
// Given
    String number = "+6811109194998";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsYemenPrefixe() {
// Given
    String number = "+9671544644012";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsZambiePrefixe() {
// Given
    String number = "+2601211762599";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }

  @Test
  public void testIfPrefixeOfNumberIsZimbabwePrefixe() {
// Given
    String number = "+2631553483979";

// When
    boolean isValidNumber = PhoneConverter.hasPrefixeIndicatorValid(number);

// Then
    assertTrue(isValidNumber);
  }
}
