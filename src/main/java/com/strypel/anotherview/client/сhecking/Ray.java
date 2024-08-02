package com.strypel.anotherview.client.сhecking;

import net.minecraft.world.phys.Vec3;

public class Ray {
    private Vec3 anchor;
    private Vec3 end;
    public Vec3 direction;
    public double maxLength;
    public Ray(Vec3 anchor, Vec3 direction, double maxLength){
        this.anchor = anchor;
        this.end = anchor;
        this.direction = direction;
        this.maxLength = maxLength;
    }

    public void increase(){
        if(length() < maxLength){
            end = new Vec3(end.x + direction.x,end.y + direction.y,end.z + direction.z);
        }
    }

    public void reduce(){
        if(length() > 0){
            end = new Vec3(end.x - direction.x,end.y - direction.y,end.z - direction.z);
        }
    }

    public Vec3 getAnchor(){
        return this.anchor;
    }
    public Vec3 getEnd(){
        return this.end;
    }

    public double length(){
        double dx = Math.abs(this.end.x - this.anchor.x);
        double dy = Math.abs(this.end.y - this.anchor.y);
        double dz = Math.abs(this.end.z - this.anchor.z);

        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

}
