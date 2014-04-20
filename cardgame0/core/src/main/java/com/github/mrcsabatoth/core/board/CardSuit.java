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
      if (str.startsWith(suit.toString() + "_"))
        return suit;
    }
    return CardSuit.SUIT_INVALID;
  }

  public static CardSuit getValueFromInt(int rnd) {
    for(CardSuit suit : CardSuit.values()) {
      if (rnd == suit.getValue())
        return suit;
    }
    return CardSuit.SUIT_INVALID;
  }
}
