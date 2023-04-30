/*
Author: James Lenard N. Balad-on

This header file compiles all dependencies
that the program needs to properly run
*/

#ifndef dependencies
#define dependencies

typedef struct
{
    int MaxHP;
    int AD, MP, HP, AR, MR, CRIT;
    int armor_pen, magic_pen, damage_modifier;
    float enemy_extra_damage;
    bool immune, stunned;
} hero;

typedef struct
{
    int MaxHP;
    int AD, MP, HP, AR, MR, CRIT;
    int armor_pen, magic_pen, damage_modifier;
    float hero_extra_damage;
    bool immune, stunned;
} enemy;

//Heroes
#include "ShadowReaper.h"
#include "BloodHarvester.h"

//Enemies
#include "GenerateEnemyStats.h"
#include "EnemyAbilities.h"

//Items
#include "EnchantedAmulet.h"
#include "DragonsFang.h"
#include "CelestialOrb.h"
#include "ForeignRune.h"
#include "ShadowBlade.h"

#endif //dependencies
