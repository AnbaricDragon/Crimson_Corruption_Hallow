package com.anbaric.terra_reforged;

public class TEST
{
//    if (event.phase == TickEvent.Phase.END)
//    {
//        ClientPlayerEntity player = Minecraft.getInstance().player;
//        if (player != null)
//        {
//            player.sendMessage(new StringTextComponent(player.isOnGround() ? "Grounded" : "Flying"), UUID.fromString("f648da61-7d7c-449b-9fd7-05fa8eac0b0f"));
//            if (!player.isOnGround() && !player.isInWater() && !player.isInLava())
//            {
//                if (player.movementInput.jump)
//                {
//                    player.sendMessage(new StringTextComponent("I tried to Cloud Jump"), UUID.fromString("f648da61-7d7c-449b-9fd7-05fa8eac0b0f"));
//                    player.getCapability(TerraCapabilityMultiJump.MULTI_JUMP_CAPABILITY).ifPresent(cap ->
//                    {
//                        if (cap.getCanCloudJump())
//                        {
//                            cap.setCanCloudJump(false);
//                            NetworkHandler.INSTANCE.sendToServer(new CloudJumpPacket());
//                            JumpHandler.cloudJump(player);
//                        }
//                    });
//                }
//            }
//        }
//    }
}
