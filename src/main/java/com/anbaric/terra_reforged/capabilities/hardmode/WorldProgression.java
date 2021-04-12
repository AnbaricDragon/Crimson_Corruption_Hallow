package com.anbaric.terra_reforged.capabilities.hardmode;

public class WorldProgression implements IWorldProgression
{
    private int     orbsBroken;
    private int     altarsBroken;

    private boolean FoundAngler;
    private boolean FoundGoblin;
    private boolean FoundStylist;
    private boolean FoundMechanic;
    private boolean FoundWizard;
    private boolean FoundTaxCollector;

    private boolean BeatGoblinInvasion;
    private boolean BeatPirateInvasion;
    private boolean BeatPumpkinInvasion;
    private boolean BeatFrostInvasion;

    private boolean KingSlimeDied;
    private boolean EyeOfCthulhuDied;
    private boolean EaterOfWorldsDied;
    private boolean BrainOfCthulhuDied;
    private boolean QueenBeeDied;
    private boolean SkeletronDied;
    private boolean WallOfFleshDied;
    private boolean QueenSlimeDied;
    private boolean TwinsDied;
    private boolean DestroyerDied;
    private boolean SkeletronPrimeDied;
    private boolean PlanteraDied;
    private boolean GolemDied;
    private boolean EmpressOfLightDied;
    private boolean DukeFishronDied;
    private boolean LunaticCultistDied;
    private boolean MoonLordDied;

    public int getOrbsBroken() { return this.orbsBroken; }
    public int getAltarsBroken() { return this.altarsBroken; }

    public boolean getHasFoundAngler() { return this.FoundAngler; }
    public boolean getHasFoundGoblin() { return this.FoundGoblin; }
    public boolean getHasFoundStylist() { return this.FoundStylist; }
    public boolean getHasFoundMechanic() { return this.FoundMechanic; }
    public boolean getHasFoundWizard() { return this.FoundWizard; }
    public boolean getHasFoundTaxCollector() { return this.FoundTaxCollector; }

    public boolean getHasBeatGoblinInvasion() { return this.BeatGoblinInvasion; }
    public boolean getHasBeatPirateInvasion() { return this.BeatPirateInvasion; }
    public boolean getHasBeatPumpkinInvasion() { return this.BeatPumpkinInvasion; }
    public boolean getHasBeatFrostInvasion() { return this.BeatFrostInvasion; }

    public boolean getHasKingSlimeDied() { return this.KingSlimeDied; }
    public boolean getHasEyeOfCthulhuDied() { return this.EyeOfCthulhuDied; }
    public boolean getHasEaterOfWorldsDied() { return this.EaterOfWorldsDied; }
    public boolean getHasBrainOfCthulhuDied() { return this.BrainOfCthulhuDied; }
    public boolean getHasQueenBeeDied() { return this.QueenBeeDied; }
    public boolean getHasSkeletronDied() { return this.SkeletronDied; }
    public boolean getHasWallOfFleshDied() { return this.WallOfFleshDied; }
    public boolean getHasQueenSlimeDied() { return this.QueenSlimeDied; }
    public boolean getHasTwinsDied() { return this.TwinsDied; }
    public boolean getHasDestroyerDied() { return this.DestroyerDied; }
    public boolean getHasSkeletronPrimeDied() { return this.SkeletronPrimeDied; }
    public boolean getHasPlanteraDied() { return this.PlanteraDied; }
    public boolean getHasGolemDied() { return this.GolemDied; }
    public boolean getHasEmpressOfLightDied() { return this.EmpressOfLightDied; }
    public boolean getHasDukeFishronDied() { return this.DukeFishronDied; }
    public boolean getHasLunaticCultistDied() { return this.LunaticCultistDied; }
    public boolean getHasMoonLordDied() { return this.MoonLordDied; }

    public void setOrbsBroken(int orbsBroken) { this.orbsBroken = orbsBroken; }
    public void setAltarsBroken(int altarsBroken) { this.altarsBroken = altarsBroken; }

    public void setHasFoundAngler(boolean hasFound) { this.FoundAngler = hasFound; }
    public void setHasFoundGoblin(boolean hasFound) { this.FoundGoblin = hasFound; }
    public void setHasFoundStylist(boolean hasFound) { this.FoundStylist = hasFound; }
    public void setHasFoundMechanic(boolean hasFound) { this.FoundMechanic = hasFound; }
    public void setHasFoundWizard(boolean hasFound) { this.FoundWizard = hasFound; }
    public void setHasFoundTaxCollector(boolean hasFound) { this.FoundTaxCollector = hasFound; }

    public void setHasBeatGoblinInvasion(boolean hasBeat) { this.BeatGoblinInvasion = hasBeat; }
    public void setHasBeatPirateInvasion(boolean hasBeat) { this.BeatPirateInvasion = hasBeat; }
    public void setHasBeatPumpkinInvasion(boolean hasBeat) { this.BeatPumpkinInvasion = hasBeat; }
    public void setHasBeatFrostInvasion(boolean hasBeat) { this.BeatFrostInvasion = hasBeat; }

    public void setHasKingSlimeDied(boolean hasKilled) { this.KingSlimeDied = hasKilled; }
    public void setHasEyeOfCthulhuDied(boolean hasKilled) { this.EyeOfCthulhuDied = hasKilled; }
    public void setHasEaterOfWorldsDied(boolean hasKilled) { this.EaterOfWorldsDied = hasKilled; }
    public void setHasBrainOfCthulhuDied(boolean hasKilled) { this.BrainOfCthulhuDied = hasKilled; }
    public void setHasQueenBeeDied(boolean hasKilled) { this.QueenBeeDied = hasKilled; }
    public void setHasSkeletronDied(boolean hasKilled) { this.SkeletronDied = hasKilled; }
    public void setHasWallOfFleshDied(boolean hasKilled) { this.WallOfFleshDied = hasKilled; }
    public void setHasQueenSlimeDied(boolean hasKilled) { this.QueenSlimeDied = hasKilled; }
    public void setHasTwinsDied(boolean hasKilled) { this.TwinsDied = hasKilled; }
    public void setHasDestroyerDied(boolean hasKilled) { this.DestroyerDied = hasKilled; }
    public void setHasSkeletronPrimeDied(boolean hasKilled) { this.SkeletronPrimeDied = hasKilled; }
    public void setHasPlanteraDied(boolean hasKilled) { this.PlanteraDied = hasKilled; }
    public void setHasGolemDied(boolean hasKilled) { this.GolemDied = hasKilled; }
    public void setHasEmpressOfLightDied(boolean hasKilled) { this.EmpressOfLightDied = hasKilled; }
    public void setHasDukeFishronDied(boolean hasKilled) { this.DukeFishronDied = hasKilled; }
    public void setHasLunaticCultistDied(boolean hasKilled) { this.LunaticCultistDied = hasKilled; }
    public void setHasMoonLordDied(boolean hasKilled) { this.MoonLordDied = hasKilled; }
}
