package model;

import javax.xml.bind.annotation.XmlElement;

import static java.lang.Math.*;


public class Rectangle {
    @XmlElement
    private double x_0;
    @XmlElement
    private double y_0;
    @XmlElement
    private double x_2;
    @XmlElement
    private double y_2;

    // Конструктор по умолчанию
    public Rectangle() {
        this(0.0, 1.0, 1.0, 0.0);
    }

    // Конструктор от координат диагонали
    public Rectangle(double x0, double y0, double x2, double y2) {
        // Задаются две точки на плоскости и строится как бы вектор,
        // который является диагональю: из левого верхнего в правый нижний угол
        this.x_0 = min(x0, x2);
        this.y_0 = max(y0, y2);
        this.x_2 = max(x2, x0);
        this.y_2 = min(y2, y0);
    }

    public double getX0() {
        return this.x_0;
    }

    public double getY0() {
        return this.y_0;
    }

    public double getX2() {
        return this.x_2;
    }

    public double getY2() {
        return this.y_2;
    }

    public double getX3() {
        return this.x_0;
    }
    public double getY3() {
        return this.y_2;
    }
    public double getX1() {
        return this.x_2;
    }
    public double getY1() {
        return this.y_0;
    }

    public double getHight() {
        return abs(this.y_0 - this.y_2);
    }

    public double getWidth() {
        return abs(this.x_0 - this.x_2);
    }

    public void MoveX(double x_difference) {
        this.x_0 += x_difference;
        this.x_2 += x_difference;
    }

    public void MoveY(double y_difference) {
        this.y_2 += y_difference;
        this.y_0 += y_difference;
    }

    public void StretchingX(double x_difference) {
        if (x_difference < 0 && abs(x_difference) > abs(this.x_0 - this.x_2))
            return;
        // Растяжение/сжатие по горидонтали, двигается правая сторона
        this.x_2 += x_difference;
    }

    public void StretchingY(double y_difference) {
        if (y_difference < 0 && abs(y_difference) > abs(this.y_0 - this.y_2))
            return;
        // Растяжение/сжатие по вертикали, двигается нижняя сторона
        this.y_2 -= y_difference;
    }
}
