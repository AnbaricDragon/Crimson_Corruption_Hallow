package com.anbaric.terra_reforged.capabilities.hardmode;

public interface IWorldProgression
{
    int getOrbsBroken();
    int getAltarsBroken();

    boolean getHasFoundAngler();
    boolean getHasFoundGoblin();
    boolean getHasFoundStylist();
    boolean getHasFoundMechanic();
    boolean getHasFoundWizard();
    boolean getHasFoundTaxCollector();

    boolean getHasBeatGoblinInvasion();
    boolean getHasBeatPirateInvasion();
    boolean getHasBeatPumpkinInvasion();
    boolean getHasBeatFrostInvasion();

    boolean getHasKingSlimeDied();
    boolean getHasEyeOfCthulhuDied();
    boolean getHasEaterOfWorldsDied();
    boolean getHasBrainOfCthulhuDied();
    boolean getHasQueenBeeDied();
    boolean getHasSkeletronDied();
    boolean getHasWallOfFleshDied();
    boolean getHasQueenSlimeDied();
    boolean getHasTwinsDied();
    boolean getHasDestroyerDied();
    boolean getHasSkeletronPrimeDied();
    boolean getHasPlanteraDied();
    boolean getHasGolemDied();
    boolean getHasEmpressOfLightDied();
    boolean getHasDukeFishronDied();
    boolean getHasLunaticCultistDied();
    boolean getHasMoonLordDied();

    void setOrbsBroken(int orbsBroken);
    void setAltarsBroken(int altarsBroken);

    void setHasFoundAngler(boolean hasFound);
    void setHasFoundGoblin(boolean hasFound);
    void setHasFoundStylist(boolean hasFound);
    void setHasFoundMechanic(boolean hasFound);
    void setHasFoundWizard(boolean hasFound);
    void setHasFoundTaxCollector(boolean hasFound);

    void setHasBeatGoblinInvasion(boolean hasBeat);
    void setHasBeatPirateInvasion(boolean hasBeat);
    void setHasBeatPumpkinInvasion(boolean hasBeat);
    void setHasBeatFrostInvasion(boolean hasBeat);

    void setHasKingSlimeDied(boolean hasKilled);
    void setHasEyeOfCthulhuDied(boolean hasKilled);
    void setHasEaterOfWorldsDied(boolean hasKilled);
    void setHasBrainOfCthulhuDied(boolean hasKilled);
    void setHasQueenBeeDied(boolean hasKilled);
    void setHasSkeletronDied(boolean hasKilled);
    void setHasWallOfFleshDied(boolean hasKilled);
    void setHasQueenSlimeDied(boolean hasKilled);
    void setHasTwinsDied(boolean hasKilled);
    void setHasDestroyerDied(boolean hasKilled);
    void setHasSkeletronPrimeDied(boolean hasKilled);
    void setHasPlanteraDied(boolean hasKilled);
    void setHasGolemDied(boolean hasKilled);
    void setHasEmpressOfLightDied(boolean hasKilled);
    void setHasDukeFishronDied(boolean hasKilled);
    void setHasLunaticCultistDied(boolean hasKilled);
    void setHasMoonLordDied(boolean hasKilled);
}