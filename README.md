# Anotherview

A mod that adds switching to first-person view when entering a room or other enclosed space.
# Features #
* View controller - automatically switches any view to first-person view when entering a cramped room
* Several modes of operation (at the moment of alpha1.0.3 release there is only one - RAYCAST)
* Hotkeys to change the operating mode (default is `minus`)
* Fully compatible with [Shoulder Surfing Reloaded](https://www.curseforge.com/minecraft/mc-mods/shoulder-surfing-reloaded)
* Client side only - can work on any servers

## Installation guide ##
1. Open the `.minecraft/mods` folder
2. Move `anotherview-[mc_version]-[mod_version].jar` inside the folder.
3. Start the game


## Guide ##
In the game, press `minus` the default to change anotherview mode

## How it works ##
The mod currently has only one mode of narrow space detection and it is called `RAYCAST`.

`RAYCAST` is a mode in which every tick above the player's head a beam is launched upwards for a certain distance. When the beam collides with any block except foliage, the mod switches the view to a first-person view.

# License #
MIT license. Made by [Strypel](https://github.com/strypel)

