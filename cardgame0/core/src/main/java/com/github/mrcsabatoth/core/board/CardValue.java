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

  public static CardValue getValueFromDouble(double rnd) {
    double delta = 1.0/13;
    if (rnd < delta)
      return CardValue.VALUE_2;
    if (rnd < 2 * delta)
      return CardValue.VALUE_3;
    if (rnd < 3 * delta)
      return CardValue.VALUE_4;
    if (rnd < 4 * delta)
      return CardValue.VALUE_5;
    if (rnd < 5 * delta)
      return CardValue.VALUE_6;
    if (rnd < 6 * delta)
      return CardValue.VALUE_7;
    if (rnd < 7 * delta)
      return CardValue.VALUE_8;
    if (rnd < 8 * delta)
      return CardValue.VALUE_9;
    if (rnd < 9 * delta)
      return CardValue.VALUE_10;
    if (rnd < 10 * delta)
      return CardValue.VALUE_J;
    if (rnd < 11 * delta)
      return CardValue.VALUE_Q;
    if (rnd < 12 * delta)
      return CardValue.VALUE_K;
    if (rnd <= 13 * delta)
      return CardValue.VALUE_A;
    return CardValue.VALUE_INVALID;
  }
}
