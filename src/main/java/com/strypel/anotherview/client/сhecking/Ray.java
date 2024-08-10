package com.strypel.anotherview.client.сhecking;

import net.minecraft.util.math.vector.Vector3f;

public class Ray {
    private Vector3f anchor;
    private Vector3f end;
    public Vector3f direction;
    public double maxLength;
    public Ray(Vector3f anchor, Vector3f direction, double maxLength){
        this.anchor = anchor;
        this.end = anchor;
        this.direction = direction;
        this.maxLength = maxLength;
    }

    public void increase(){
        if(length() < maxLength){
            end = new Vector3f(end.x() + direction.x(),end.y() + direction.y(),end.z() + direction.z());
        }
    }

    public void reduce(){
        if(length() > 0){
            end = new Vector3f(end.x() - direction.x(),end.y() - direction.y(),end.z() - direction.z());
        }
    }

    public Vector3f getAnchor(){
        return this.anchor;
    }
    public Vector3f getEnd(){
        return this.end;
    }

    public double length(){
        double dx = Math.abs(this.end.x() - this.anchor.x());
        double dy = Math.abs(this.end.y() - this.anchor.y());
        double dz = Math.abs(this.end.z() - this.anchor.z());

        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

}
