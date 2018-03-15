package cs206c;

class Element {
  public static final short OPERAND = 0;
  public static final short OPERATOR = 1;
  private short type;
  private double operand;
  private char operator;

  public Element(double value) {
    this.type = this.OPERAND;
    this.operand = value;
  }

  public Element(char value) {
    this.type = this.OPERATOR;
    this.operator = value;
  }

  public short getType() {
    return this.type;
  }

  public double getOperand() {
    return this.operand;
  }

  public char getOperator() {
    return this.operator;
  }

  public String toString() {
    if (this.type == this.OPERAND) {
      return new Double(operand).toString();
    } else if (this.type == this.OPERATOR) {
      return new Character(operator).toString();
    }
    return null;
  }
}
