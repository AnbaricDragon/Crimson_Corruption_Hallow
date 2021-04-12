package com.anbaric.terra_reforged.entities.tiles;

import com.anbaric.terra_reforged.util.init.TerraTileEntityRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class TerraTileEntityHeartLantern extends TileEntity implements ITickableTileEntity
{
    public TerraTileEntityHeartLantern(TileEntityType<?> tileEntityTypeIn)
    {
        super(tileEntityTypeIn);
    }

    public TerraTileEntityHeartLantern()
    {
        super(TerraTileEntityRegistry.LANTERN_HEART.get());
    }

    @Override
    public void tick()
    {
        boolean powered = !this.world.isRemote && world.getRedstonePowerFromNeighbors(pos.up()) > 0;

        if (powered)
        {
            this.addEffectsToPlayers();
        }
    }

    private void addEffectsToPlayers()
    {
        if (!this.world.isRemote)
        {
            double             range     = 16.0D;
            AxisAlignedBB      rangeAABB = new AxisAlignedBB(this.pos).grow(range);
            List<PlayerEntity> list      = this.world.getEntitiesWithinAABB(PlayerEntity.class, rangeAABB);

            for (PlayerEntity playerentity : list)
            {
                playerentity.addPotionEffect(new EffectInstance(Effects.REGENERATION, 3, 0, true, true));
            }
        }
    }
}
