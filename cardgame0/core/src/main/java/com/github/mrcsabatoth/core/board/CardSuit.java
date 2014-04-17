package com.github.mrcsabatoth.core.board;

public enum CardSuit {

  SUIT_SPADE(0),
  SUIT_CLUB(1),
  SUIT_DIAMOND(2),
  SUIT_HEART(3),
  SUIT_INVALID(-1);

  private final int suitIdx;

  CardSuit(int value) {
	this.suitIdx = value;
  }
  
  public int getValue() {
    return suitIdx;
  }

  public static CardSuit getValueFromString(String str) {
    for(CardSuit suit : CardSuit.values()) {
      if (str.startsWith(suit.toString()))
        return suit;
    }
    return CardSuit.SUIT_INVALID;
  }

  public static CardSuit getValueFromDouble(double rnd) {
    if (rnd < 0.25)
      return CardSuit.SUIT_SPADE;
    if (rnd < 0.5)
      return CardSuit.SUIT_CLUB;
    if (rnd < 0.75)
      return CardSuit.SUIT_DIAMOND;
    if (rnd <= 1.0)
      return CardSuit.SUIT_HEART;
    return CardSuit.SUIT_INVALID;
  }
}
