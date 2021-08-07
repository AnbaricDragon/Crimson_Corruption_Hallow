package com.anbaric.terra_reforged.items;

import com.anbaric.terra_reforged.util.handlers.CurioHandler;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import javax.annotation.Nonnull;
import java.util.List;

import top.theillusivec4.curios.api.type.capability.ICurio.DropRule;
import top.theillusivec4.curios.api.type.capability.ICurio.SoundInfo;

public class TerraItemNinjaGear extends TerraItemAccessory
{
    public TerraItemNinjaGear()
    {
        super();
        MinecraftForge.EVENT_BUS.addListener(this::cancelDamage);
    }

    @Override
    public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add(new StringTextComponent(""));
        tooltip.add(new StringTextComponent("\u00A76" + I18n.get("curios.modifiers.charm") + "\u00A76"));
        tooltip.add(new StringTextComponent("\u00A79" + "Grants Wall Gripping"));
        tooltip.add(new StringTextComponent("\u00A79" + "+10% Dodge Chance"));
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT unused)
    {
        return CurioHandler.createProvider(new ICurio()
        {
            @Override
            public void curioTick(String identifier, int index, LivingEntity livingEntity)
            {
                PlayerEntity player = livingEntity instanceof PlayerEntity ? (PlayerEntity) livingEntity : null;
                World        world  = player != null ? player.getCommandSenderWorld() : null;
                if (world == null)
                {
                    return;
                }

                Vector3d vecPos = player.position();
                BlockPos pos    = player.blockPosition();
                double   x      = vecPos.x();
                double   z      = vecPos.z();
                double   xi     = MathHelper.floor(x);
                double   zi     = MathHelper.floor(z);
                double   dotX   = MathHelper.abs((float) (x - xi));
                double   dotZ   = MathHelper.abs((float) (z - zi));
                boolean shouldStick =
                        ((world.getBlockState(pos.relative(Direction.NORTH)).canOcclude() || world.getBlockState(pos.above().relative(Direction.NORTH)).canOcclude()) && dotZ <= 0.301) ||
                        ((world.getBlockState(pos.relative(Direction.EAST)).canOcclude()  || world.getBlockState(pos.above().relative(Direction.EAST)).canOcclude() ) && dotX >= 0.699) ||
                        ((world.getBlockState(pos.relative(Direction.SOUTH)).canOcclude() || world.getBlockState(pos.above().relative(Direction.SOUTH)).canOcclude()) && dotZ >= 0.699) ||
                        ((world.getBlockState(pos.relative(Direction.WEST)).canOcclude()  || world.getBlockState(pos.above().relative(Direction.WEST)).canOcclude() ) && dotX <= 0.301);

                if (!player.isOnGround() && !player.isCreative() && shouldStick && !player.isInWaterOrRain())
                {
                    Vector3d motion = player.getDeltaMovement();
                    if (motion.y <= 0)
                    {
                        if (player.isCrouching())
                        {
                            player.setDeltaMovement(motion.x, 0, motion.z);
                        }
                    }
                }
            }

            @Nonnull
            @Override
            public DropRule getDropRule(LivingEntity livingEntity)
            {
                return DropRule.DEFAULT;
            }

            @Nonnull
            @Override
            public SoundInfo getEquipSound(SlotContext slotContext)
            {
                return new SoundInfo(SoundEvents.ARMOR_EQUIP_GENERIC, 1.0f, 1.0f);
            }

            @Override
            public boolean canEquipFromUse(SlotContext slot)
            {
                return true;
            }
        });
    }

    private void cancelDamage(LivingAttackEvent event)
    {
        CuriosApi.getCuriosHelper().findEquippedCurio(this, event.getEntityLiving()).ifPresent(found ->
        {
            if (event.getEntityLiving().level.random.nextFloat() < 0.1F)
            {
                event.setCanceled(true);
            }
        });
    }
}
