/**
 * Repräsentiert ein Rechteck mit x, y als Koordinaten, Breite und Höhe
 */
public class Rectangle {
    private int x;
    private int y;
    private int width;
    private int height;

    /**
     * Erstellt ein Rechteck
     * @param xInput x Koordinate von Anfang des Rechtecks (linke obere Ecke)
     * @param yInput y Koordinate von Anfang des Rechtecks (linke obere Ecke)
     * @param widthInput Wert für die Breite
     * @param heightInput Wert für die Höhe
     */
    public Rectangle(int xInput, int yInput, int widthInput, int heightInput) {
        if (widthInput < 0 || heightInput < 0) {
            Utils.error("Width and height must be non-negative.");
        } else {
            this.x = xInput;
            this.y = yInput;
            this.width = widthInput;
            this.height = heightInput;
        }
    }

    /**
     * Erstellt ein Quadrat
     * @param xInput x Koordinate von Anfang des Rechtecks (linke obere Ecke)
     * @param yInput y Koordinate von Anfang des Rechtecks (linke obere Ecke)
     * @param sidelengthInput Wert, das für Breite und Höhe steht
     */
    // Constructor with three arguments for square
    public Rectangle(int xInput, int yInput, int sidelengthInput) {
        if (sidelengthInput < 0) {
            Utils.error("Side length must be non-negative.");
        } else {
            this.x = xInput;
            this.y = yInput;
            this.width = sidelengthInput;
            this.height = sidelengthInput;
        }
    }

    /**
     * Erstellt eine Kopie des übergebenen Rechtecks.
     * @param toCopy das Rechteck, das kopiert werden soll
     * @return eine neue Instanz von Rectangle mit den gleichen Eigenschaften wie übergebene Rechteck
     */
    public Rectangle copy(Rectangle toCopy) {
        return new Rectangle(toCopy.x, toCopy.y, toCopy.width, toCopy.height);
    }

    /**
     * @return x Koordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Gibt neue Koordiante für x
     * @param x der neue Wert für x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return y Koordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Gibt neue Koordiante für y
     * @param y der neue Wert für y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return die Breite
     */
    public int getWidth() {
        return width;
    }

    /**
     * Verändert die Breite
     * @param width der neue Wert für die Breite
     * @throws "Width must be non-negative.", wenn Breite unzulässig ist.
     */
    public void setWidth(int width) {
        if (width < 0) {
            Utils.error("Width must be non-negative.");
        } else {
            this.width = width;
        }
    }

    /**
     * @return die Höhe
     */
    public int getHeight() {
        return height;
    }

    /**
     * Verändert die Höhe
     * @param height der neue Wert für die Breite
     * @throws "Height must be non-negative.", wenn Höhe unzulässig ist.
     */
    public void setHeight(int height) {
        if (height < 0) {
            Utils.error("Height must be non-negative.");
        } else {
            this.height = height;
        }
    }

    /**
     * Überprüft, ob alle übergebenen Rechtecke Quadrate sind.
     * @param rectangles eine beliebige Anzahl von Rechtecken
     * @return true, wenn alle Rechtecke Quadrate sind, andernfalls false
     */
    public static boolean areSquares(Rectangle... rectangles) {
        for (Rectangle rect : rectangles) {
            if (rect.width != rect.height) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return die Fläche
     */
    public int area() {
        return width * height;
    }


    private static Rectangle intersectTwo(Rectangle r1, Rectangle r2) {
        int x = Utils.max(r1.x, r2.x);
        int y = Utils.max(r1.y, r2.y);
        int right = Utils.min(r1.x + r1.width, r2.x + r2.width);
        int bottom = Utils.min(r1.y + r1.height, r2.y + r2.height);

        int width = right - x;
        int height = bottom - y;

        if (width <= 0 || height <= 0) {
            return null;
        }

        return new Rectangle(x, y, width, height);
    }

    /**
     * Berechnet den Schnitt der übergebenen Rechtecke.
     * @param rectangles eine beliebige Anzahl von Rechtecken
     * @return ein neues Rectangle, das den Schnittpunkt darstellt, oder null, wenn kein Schnittpunkt vorhanden ist
     */
    public static Rectangle intersection(Rectangle... rectangles) {
        if (rectangles.length == 0) {
            return null;
        }

        Rectangle result = rectangles[0];
        for (int i = 1; i < rectangles.length; i++) {
            result = intersectTwo(result, rectangles[i]);
            if (result == null) {
                return null;
            }
        }
        return result;
    }

    /**
     * Das Format ist (x|y),(x|y+height),(x+width|y+height),(x+width|y).
     * @return die String-Darstellung des Rechtecks
     */
    @Override
    public String toString() {
        return String.format("(%d|%d),(%d|%d),(%d|%d),(%d|%d)",
                x, y,
                x, y + height,
                x + width, y + height,
                x + width, y
        );
    }

    /**
     * Der Einstiegspunkt des Programms, der zwei Rechtecke erstellt und ihren Schnittpunkt berechnet.
     * Gibt den Schnittpunkt der Rechtecke aus oder eine Meldung, wenn kein Schnittpunkt vorhanden ist.
     */
    public static void main(String[] args) {
        Rectangle rect1 = new Rectangle(1, 4, 2, 3);
        Rectangle rect2 = new Rectangle(2, 5, 3, 3);
        Rectangle intersection = Rectangle.intersection(rect1, rect2);
        System.out.println("Intersection: " + (intersection != null ? intersection : "No intersection"));
    }
}