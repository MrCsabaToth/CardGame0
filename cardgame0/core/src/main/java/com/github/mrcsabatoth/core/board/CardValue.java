package com.github.mrcsabatoth.core.board;

public enum CardValue {

  VALUE_2(0),
  VALUE_3(1),
  VALUE_4(2),
  VALUE_5(3),
  VALUE_6(4),
  VALUE_7(5),
  VALUE_8(6),
  VALUE_9(7),
  VALUE_10(8),
  VALUE_J(9),
  VALUE_Q(10),
  VALUE_K(11),
  VALUE_A(12),
  VALUE_INVALID(-1);

  private final int cardValue;

  CardValue(int value) {
	this.cardValue = value;
  }
  
  public int getValue() {
    return cardValue;
  }

  public static CardValue getValueFromString(String str) {
    for(CardValue value : CardValue.values()) {
      if (str.endsWith(value.toString()))
        return value;
    }
    return CardValue.VALUE_INVALID;
  }

  public static CardValue getValueFromInt(int rnd) {
    for(CardValue value : CardValue.values()) {
      if (rnd == value.getValue())
        return value;
    }
    return CardValue.VALUE_INVALID;
  }
}
