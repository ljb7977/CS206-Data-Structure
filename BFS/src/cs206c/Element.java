package cs206c;

class Element {
  public int x;
  public int y;
  public int distance;
  // for LinkedQueue
  public Element next;

  public Element(int x, int y, int distance) {
    this.x = x;
    this.y = y;
    this.distance = distance;
    this.next = null;
  }

  public Element(int x, int y, int distance, Element nextElem) {
    this.x = x;
    this.y = y;
    this.distance = distance;
    this.next = nextElem;
  }

  public String toString() {
    return "(" + x + ", " + y + "): " + distance;
  }
}