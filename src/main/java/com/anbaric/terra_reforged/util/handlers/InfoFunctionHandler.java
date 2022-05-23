package com.anbaric.terra_reforged.util.handlers;

public class InfoFunctionHandler
{
    public enum Functions
    {
        DEPTH_METER("depth_counter"),
        CLOCK("time_counter"),
        COMPASS("location_counter"),
        RADAR("radar_counter"),
        TALLY_COUNTER("kill_counter"),
        MOB_FINDER("rare_mob_name"),
        DPS_METER("dps_counter"),
        SPEED_METER("speed_counter"),
        METAL_FINDER("rare_metal_name"),
        FISH_FINDER("fish_name"),
        WEATHER("weather_name"),
        MOON_FINDER("moon_phase_name");

        private String tag;

        Functions(String tagPreset)
        {
            this.tag = tagPreset;
        }

        public String getTag()
        {
            return this.tag;
        }
    }
}
