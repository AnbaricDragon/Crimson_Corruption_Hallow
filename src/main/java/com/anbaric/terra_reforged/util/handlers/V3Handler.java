package com.anbaric.terra_reforged.util.handlers;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Objects;

public class V3Handler
{
    public static final V3Handler ZERO = new V3Handler(0, 0, 0);
    public static final V3Handler ONE = new V3Handler(1, 1, 1);

    public final double x;
    public final double y;
    public final double z;

    public V3Handler(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public V3Handler(Vec3 vec)
    {
        this(vec.x, vec.y, vec.z);
    }

    public static V3Handler fromBlockPos(BlockPos pos)
    {
        return new V3Handler(pos.getX(), pos.getY(), pos.getZ());
    }

    public static V3Handler fromEntityCenter(Entity e)
    {
        return new V3Handler(e.getX(), e.getY() - e.getBbWidth() + e.getBbHeight() / 2, e.getZ());
    }

    public static V3Handler fromTileEntity(BlockEntity e)
    {
        return fromBlockPos(e.getBlockPos());
    }

    public static V3Handler fromTileEntityCenter(BlockEntity e)
    {
        return fromTileEntity(e).add(0.5);
    }

    public double dotProduct(V3Handler vec)
    {
        double d = vec.x * x + vec.y * y + vec.z * z;

        if (d > 1 && d < 1.00001)
        {
            d = 1;
        }
        else if (d < -1 && d > -1.00001)
        {
            d = -1;
        }
        return d;
    }

    public double dotProduct(double d, double d1, double d2)
    {
        return d * x + d1 * y + d2 * z;
    }

    public V3Handler crossProduct(V3Handler vec)
    {
        double d  = y * vec.z - z * vec.y;
        double d1 = z * vec.x - x * vec.z;
        double d2 = x * vec.y - y * vec.x;
        return new V3Handler(d, d1, d2);
    }

    public V3Handler add(double d, double d1, double d2)
    {
        return new V3Handler(x + d, y + d1, z + d2);
    }

    public V3Handler add(V3Handler vec)
    {
        return add(vec.x, vec.y, vec.z);
    }

    public V3Handler add(double d)
    {
        return add(d, d, d);
    }

    public V3Handler subtract(V3Handler vec)
    {
        return new V3Handler(x - vec.x, y - vec.y, z - vec.z);
    }

    public V3Handler multiply(double d)
    {
        return multiply(d, d, d);
    }

    public V3Handler multiply(V3Handler f)
    {
        return multiply(f.x, f.y, f.z);
    }

    public V3Handler multiply(double fx, double fy, double fz)
    {
        return new V3Handler(x * fx, y * fy, z * fz);
    }

    public double mag()
    {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public double magSquared()
    {
        return x * x + y * y + z * z;
    }

    public V3Handler normalize()
    {
        double d = mag();
        if (d != 0)
        {
            return multiply(1 / d);
        }

        return this;
    }

    @Override
    public String toString()
    {
        MathContext cont = new MathContext(4, RoundingMode.HALF_UP);
        return "V3Handler(" + new BigDecimal(x, cont) + ", " + new BigDecimal(y, cont) + ", " + new BigDecimal(z, cont) + ")";
    }

    public V3Handler perpendicular()
    {
        if (z == 0)
        {
            return zCrossProduct();
        }
        return xCrossProduct();
    }

    public V3Handler xCrossProduct()
    {
        double d  = z;
        double d1 = -y;
        return new V3Handler(0, d, d1);
    }

    public V3Handler zCrossProduct()
    {
        double d  = y;
        double d1 = -x;
        return new V3Handler(d, d1, 0);
    }

    public V3Handler yCrossProduct()
    {
        double d  = -z;
        double d1 = x;
        return new V3Handler(d, 0, d1);
    }

    public Vec3 toVector3d()
    {
        return new Vec3(x, y, z);
    }

    public double angle(V3Handler vec)
    {
        double projection = normalize().dotProduct(vec.normalize());
        return Math.acos(Mth.clamp(projection, -1, 1));
    }

    public boolean isZero()
    {
        return x == 0 && y == 0 && z == 0;
    }

    public V3Handler negate()
    {
        return new V3Handler(-x, -y, -z);
    }

    public double scalarProject(V3Handler b)
    {
        double l = b.mag();
        return l == 0 ? 0 : dotProduct(b) / l;
    }

    public V3Handler project(V3Handler b)
    {
        double l = b.magSquared();
        if (l == 0)
        {
            return ZERO;
        }

        double m = dotProduct(b) / l;
        return b.multiply(m);
    }

    public AABB boxForRange(double range)
    {
        return boxForRange(range, range, range);
    }

    public AABB boxForRange(double rangeX, double rangeY, double rangeZ)
    {
        return new AABB(x - rangeX, y - rangeY, z - rangeZ, x + rangeX, y + rangeY, z + rangeZ);
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof V3Handler))
        {
            return false;
        }

        V3Handler v = (V3Handler) o;
        return x == v.x && y == v.y && z == v.z;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x, y, z);
    }
}