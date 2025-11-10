package com.devmaster.lesson01;

public interface Shape {
    void draw();
    default void setColor(String color  ) {
        System.out.println("Vẽ hình với màu:"+color);
    }
}
