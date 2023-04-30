#ifndef EnemyAbilities
#define EnemyAbilities

#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <unistd.h>
#include <windows.h>

void enemy_basic_attack(hero *Fighter, enemy *Alien);
void GalacticLaser(hero *Fighter, enemy *Alien);
void MeteorStrike(hero *Fighter, enemy *Alien);

void enemy_basic_attack(hero *Fighter, enemy *Alien)
{
    srand((unsigned long)time(NULL)*(unsigned long)GetTickCount());
    int damage = 0;
    int crit = rand() % 101;
    if(crit > (100 - Alien->CRIT))
    {
        damage = Alien->AD * 2;
        if(Fighter->enemy_extra_damage > 0)
        {
            damage += damage * Fighter->enemy_extra_damage;
            Fighter->enemy_extra_damage = 0;
        }
        damage -= (Fighter->AR - Alien->armor_pen) / 2;
        Fighter->HP -= damage;
        cout << "CRTICIAL!" << endl;
        cout << "The Martian Invader used a basic attack dealing " << damage << " physical damage!" << endl;
    }
    else
    {
        damage = Alien->AD;
        if(Fighter->enemy_extra_damage > 0)
        {
            damage += damage * Fighter->enemy_extra_damage;
            Fighter->enemy_extra_damage = 0;
        }
        damage -= (Fighter->AR - Alien->armor_pen) / 2;
        Fighter->HP -= damage;
        cout << "The Martian Invader used a basic attack, dealing " << damage << " physical damage!" << endl;
    }
}

void GalacticLaser(hero *Fighter, enemy *Alien)
{
    int damage = 0;
    damage = 30 + (Alien->AD * 0.25) + (Alien->MP * 0.35);
    if(Fighter->enemy_extra_damage > 0)
    {
        damage += damage * Fighter->enemy_extra_damage;
        Fighter->enemy_extra_damage = 0;
    }
    damage -= (Fighter->MR - Alien->magic_pen) / 2;
    Fighter->HP -= damage;
    cout << "The Martian Invader used Galactic Laser, dealing " << damage << " magic damage!" << endl;
}

void MeteorStrike(hero *Fighter, enemy *Alien)
{
    int damage = 0;
    damage = 65+(Alien->AD*0.60);
    if(Fighter->enemy_extra_damage > 0)
    {
        damage += damage * Fighter->enemy_extra_damage;
        Fighter->enemy_extra_damage = 0;
    }
    damage -= (Fighter->AR - Alien->armor_pen) / 2;
    Fighter->HP -= damage;
    cout << "The Martian Invader used Meteor Strike, dealing " << damage << " physical damage!" << endl;
}

#endif // EnemyAbilities
