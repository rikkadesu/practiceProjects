#ifndef GenerateEnemyStats
#define GenerateEnemyStats

#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <unistd.h>
#include <windows.h>

void GenerateEnemy_Attributes(enemy *Alien)
{
    srand((unsigned long)time(NULL)*(unsigned long)GetTickCount());

    Alien->AD = rand() % 100 + 1;
    while(Alien->AD < 65)
        Alien->AD = rand() % 100 + 1;

    Alien->AR = rand() % 50 + 1;
    while(Alien->AR < 20)
        Alien->AR = rand() % 50 + 1;

    Alien->MR = rand() % 40 + 1;
    while(Alien->MR < 20)
        Alien->MR = rand() % 40 + 1;

    Alien->CRIT = rand() % 60;

    Alien->HP = rand() % 2000 + 1;
    while(Alien->HP < 1200)
        Alien->HP = rand() % 2000 + 1;
    Alien->MaxHP = Alien->HP;

    Alien->MP = rand() % 200 + 1;
    while(Alien->MP < 80)
        Alien->MP = rand() % 200 + 1;

    Alien->armor_pen = rand() % 25;
    Alien->magic_pen = rand() % 25;
    Alien->hero_extra_damage = 0;
}

#endif // GenerateEnemyStats
