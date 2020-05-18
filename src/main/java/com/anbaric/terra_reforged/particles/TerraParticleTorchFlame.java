package com.anbaric.terra_reforged.particles;

import net.minecraft.client.particle.*;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT) public class TerraParticleTorchFlame extends SpriteTexturedParticle
{
    protected TerraParticleTorchFlame(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn)
    {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);

        float f = this.rand.nextFloat() * 1.0f;
        this.particleRed = f;
        this.particleGreen = f;
        this.particleBlue = f;

        this.setSize(0.02f, 0.02f);
        this.particleScale *= this.rand.nextFloat() * 1.1F;
        this.motionX *= (double) 0.0f;
        this.motionY *= (double) 0.0f;
        this.motionZ *= (double) 0.0f;
        this.maxAge = (int) (20.0D / (Math.random() * 1.0D));

    }

    @Override
    public IParticleRenderType getRenderType()
    {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void tick()
    {
        if (this.maxAge-- <= 0)
        {
            this.setExpired();
        }
    }

    @OnlyIn(Dist.CLIENT) public static class Factory implements IParticleFactory<BasicParticleType>
    {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite sprite)
        {
            this.spriteSet = sprite;
        }
        @Override
        public Particle makeParticle(BasicParticleType typeIn, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed)
        {
            TerraParticleTorchFlame flame = new TerraParticleTorchFlame(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            flame.setColor(1.0f, 1.0f, 1.0f);
            flame.selectSpriteRandomly(this.spriteSet);
            return flame;
        }
    }
}





