package com.anbaric.terra_reforged;

public class TEST
{
//    @OnlyIn(Dist.CLIENT)
    //    private boolean hasReleasedJumpKey;
    //
    //    @OnlyIn(Dist.CLIENT)
    //    private boolean jumpState = false;
    //
    //    @OnlyIn(Dist.CLIENT)
    //    private boolean lastJumpState = false;
    //
    //    @SubscribeEvent
    //    @OnlyIn(Dist.CLIENT)
    //    void onClientTick(TickEvent.ClientTickEvent event)
    //    {
    //        if (event.phase == TickEvent.Phase.END)
    //        {
    //            ClientPlayerEntity thisPlayer = Minecraft.getInstance().player;
    //
    //            if(thisPlayer == null)
    //            {
    //                throw new RuntimeException("YA DONE FUCKERD THIS SHIT BUD");
    //            }
    //
    //            jumpState = thisPlayer.movementInput.jump;
    //
    //            if (jumpState != lastJumpState)
    //            {
    //                if (jumpState && !thisPlayer.isOnGround() && !thisPlayer.isInWater())
    //                {
    //                    pickJump(thisPlayer);
    //                }
    //            }
    //            lastJumpState = jumpState;
    //        }
    //    }
    //
    //    public static void pickJump(PlayerEntity player)
    //    {
    //        player.getCapability(TerraCapabilityMultiJump.MULTI_JUMP_CAPABILITY).ifPresent(cap -> {
    //            if (cap.getCanCloudJump())
    //            {
    //                cap.setCanCloudJump(false);
    //                NetworkHandler.INSTANCE.sendToServer(new CloudJumpPacket());
    //                jump(player);
    //                player.playSound(SoundEvents.BLOCK_WOOL_FALL, 1, 0.9F + player.getRNG().nextFloat() * 0.2F);
    //                return;
    //            }
    //            else if (cap.getCanBlizzardJump())
    //            {
    //                cap.setCanBlizzardJump(false);
    //                NetworkHandler.INSTANCE.sendToServer(new BlizzardJumpPacket());
    //                jump(player);
    //                player.playSound(SoundEvents.BLOCK_SNOW_FALL, 1, 0.9F + player.getRNG().nextFloat() * 0.2F);
    //                return;
    //            }
    //            else if (cap.getCanSandstormJump())
    //            {
    //                cap.setCanSandstormJump(false);
    //                NetworkHandler.INSTANCE.sendToServer(new SandstormJumpPacket());
    //                jump(player);
    //                player.playSound(SoundEvents.BLOCK_SAND_FALL, 1, 0.9F + player.getRNG().nextFloat() * 0.2F);
    //                return;
    //            }
    //            else if (cap.getCanTsunamiJump())
    //            {
    //                cap.setCanTsunamiJump(false);
    //                NetworkHandler.INSTANCE.sendToServer(new TsunamiJumpPacket());
    //                jump(player);
    //                player.playSound(SoundEvents.AMBIENT_UNDERWATER_EXIT, 1, 0.9F + player.getRNG().nextFloat() * 0.2F);
    //                return;
    //            }
    //            else if (cap.getCanFartJump())
    //            {
    //                cap.setCanFartJump(false);
    //                NetworkHandler.INSTANCE.sendToServer(new FartJumpPacket());
    //                jump(player);
    //                player.playSound(SoundEvents.ENTITY_DROWNED_DEATH_WATER, 1, 0.9F + player.getRNG().nextFloat() * 0.2F);
    //                return;
    //            }
    //        });
    //    }
    //
    //    public static void jump(PlayerEntity player)
    //    {
    //        double upwardsMotion = 0.5;
    //        if (player.isPotionActive(Effects.JUMP_BOOST))
    //        {
    //            upwardsMotion += 0.1 * (player.getActivePotionEffect(Effects.JUMP_BOOST).getAmplifier() + 1);
    //        }
    //        Vector3d motion = player.getMotion();
    //
    //        player.setMotion(player.getMotion().add(0, upwardsMotion - motion.y, 0));
    //
    //        player.isAirBorne = true;
    //        net.minecraftforge.common.ForgeHooks.onLivingJump(player);
    //
    //        player.addStat(Stats.JUMP);
    //        player.addExhaustion(player.isSprinting() ? 0.2F : 0.05F);
    //    }
}
