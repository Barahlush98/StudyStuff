package model;
import javax.xml.bind.annotation.XmlRootElement;

import static java.lang.Math.max;
import static java.lang.Math.min;


@XmlRootElement
public class RectanglesState {
    private Rectangle Rectangle1;
    private Rectangle Rectangle2;
    private double IntersectionArea;

    public RectanglesState() {
        this.Rectangle1 = new Rectangle();
        this.Rectangle2 = new Rectangle();
    }

    public RectanglesState(Rectangle r1, Rectangle r2) {
        this.Rectangle1 = r1;
        this.Rectangle2 = r2;
    }

    public Rectangle getRectangle1() {
        return this.Rectangle1;
    }

    public Rectangle getRectangle2() {
        return this.Rectangle2;
    }

    public Rectangle[] getState() {
        Rectangle[] res = {this.Rectangle1, this.Rectangle2 };
        return res;
    }

    public void setRectangle1(Rectangle rectangle1) {
        this.Rectangle1 = rectangle1;
    }

    public void setRectangle2(Rectangle rectangle2){
        this.Rectangle2 = rectangle2;
    }

    public double getIntersectionArea(){
        // Левый нижний угол первого прямоугольника
        double A_x3 = this.Rectangle1.getX3();
        double A_y3 = this.Rectangle1.getY3();
        // Правый верхний угол первого прямоугольника
        double A_x1 = this.Rectangle1.getX1();
        double A_y1 = this.Rectangle1.getY1();
        // Левый нижний угол второго прямоугольника
        double B_x3 = this.Rectangle2.getX3();
        double B_y3 = this.Rectangle2.getY3();
        // Правый верхний угол первого прямоугольника
        double B_x1 = this.Rectangle2.getX1();
        double B_y1 = this.Rectangle2.getY1();
        // Прямоугольник-пересечение
        double C_x3 = max(A_x3, B_x3);
        double C_y3 = max(A_y3, B_y3);
        double C_x1 = min(A_x1, B_x1);
        double C_y1 = min(A_y1, B_y1);

        return C_x1 > C_x3 && C_y1 > C_y3 ? (C_x1 - C_x3) * (C_y1 - C_y3) : 0;
    }
}
