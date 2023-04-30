#ifndef BloodHarvester
#define BloodHarvester

#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <unistd.h>

using std::cout;
using std::endl;

namespace BloodHarvester
{
    void BloodHarvester_Info(int *lock_in)
    {
        system("cls");
        cout << "Name: Blood Harvester" << endl;
        cout << "Tags: Sustain" << endl;
        cout << "========================================================================" << endl;
        cout << "Stats:" << endl;
        cout << "AD: 60 | MP: 0" << endl;
        cout << "HP: 650 | Armor: 35" << endl << "Magic Resist: 28" << endl;
        cout << "========================================================================" << endl;
        cout << "0. Basic Attack" << endl;
        cout << "   Performs basic attack dealing 60 damage." << endl;
        cout << "   Critical strikes deal 200% damage." << endl;
        cout << endl << "   Cooldown: 0 Turns" << endl;
        cout << "1. Consume" << endl;
        cout << "   Harvester extracts blood from the enemy, dealing 50(+65% AP)"   << endl;
        cout << "   magic damage. Heals for 50% of the damage dealt." << endl;
        cout << endl << "   Cooldown: 2 Turns" << endl << endl;
        cout << "2. Blood Spike" << endl;
        cout << "   Summons blood spikes on the ground, dealing 55(+50% AP)(+5% Max HP)"   << endl;
        cout << "   magic damage." << endl;
        cout << endl << "   Cooldown: 3 Turns" << endl << endl;
        cout << "3. Blood Surge" << endl;
        cout << "   When active, consume 3% of your max HP as bonus damage to your"   << endl;
        cout << "   attacks. Cast again to disable." << endl << endl;
        cout << "   After using, you can still cast another ability." << endl;
        cout << endl << "   Cooldown: 0 Turns" << endl;
        cout << "========================================================================" << endl;
        cout << "Select this fighter?" << endl;
        cout << "1 - Yes, 0 - No" << endl;
        cout << "Enter selection: ";
        scanf("%d", lock_in);
    }

    void GiveAttribute_BloodHarvester(hero *Fighter)
    {
        Fighter->AD = 60;
        Fighter->HP = Fighter->MaxHP = 650;
        Fighter->AR = 35;
        Fighter->MR = 28;
        Fighter->CRIT = 0;
        Fighter->armor_pen = 0;
        Fighter->magic_pen = 0;
        Fighter->damage_modifier = 0;
        Fighter->enemy_extra_damage = 0;
        Fighter->immune = false;
        Fighter->stunned = false;
    }

    void BloodHarvesterBasicAttack(hero *Fighter, enemy *Alien)
    {
        srand((unsigned long)time(NULL)*(unsigned long)GetTickCount());
        int damage = 0;
        int crit = rand() % 101;
        if(crit > (100 - Fighter->CRIT))
        {
            damage = (Fighter->AD * 2);
            if(Alien->hero_extra_damage > 0)
            {
                damage += (damage*Alien->hero_extra_damage);
                Alien->hero_extra_damage = 0;
            }
            damage -= (Alien->AR - Fighter->armor_pen) / 2;
            Alien->HP -= damage;
            cout << "CRITICAL!" << endl;
            cout << "Blood Harvester used a basic attack dealing " << damage << " physical damage!" << endl;
        }
        else
        {
            damage = Fighter->AD;
            if(Alien->hero_extra_damage > 0)
            {
                damage += (damage*Alien->hero_extra_damage);
                Alien->hero_extra_damage = 0;
            }
            damage -= (Alien->AR - Fighter->armor_pen) / 2;
            Alien->HP -= damage;
            cout << "Blood Harvester used a basic attack, dealing " << damage << " physical damage!" << endl;
        }
    }

    void Consume(hero *Fighter, enemy *Alien)
    {
        int damage = 0; int heal = 0;
        damage = 50 + (Fighter->MP * 0.65);
        if(Alien->hero_extra_damage > 0)
        {
            damage += (damage*Alien->hero_extra_damage);
            Alien->hero_extra_damage = 0;
        }
        damage -= (Alien->MR - Fighter->magic_pen) / 2;
        heal += damage / 2;
        Alien->HP -= damage;
        Fighter->HP += heal;
        cout << "Blood Harvester used Consume, dealing " << damage << " magic damage!" << endl;
        cout << "Consume restored " << heal << " HP." << endl;
    }

    void BloodSpike(hero *Fighter, enemy *Alien)
    {
        int damage = 0; int maxHP = Alien->MaxHP * 0.05;
        damage = 55 + (Fighter->MP * 0.50) + maxHP;
        if(Alien->hero_extra_damage > 0)
        {
            damage += (damage*Alien->hero_extra_damage);
            Alien->hero_extra_damage = 0;
        }
        damage -= (Alien->MR - Fighter->magic_pen) / 2;
        Alien->HP -= damage;
        cout << "Blood Harvester used Blood Spike, dealing " << damage << " magic damage!" << endl;
    }

    void BloodSurge(hero *Fighter)
    {
        Fighter->damage_modifier = Fighter->MaxHP * 0.03;
        cout << "Blood Harvester used Blood Surge, his next attacks will deal bonus damage." << endl;
    }

    void BloodHarvesterSkillset(enemy *Alien)
    {
        cout << "Select your move:" << endl;
        cout << "0. Basic Attack" << endl;
        cout << "1. Dark Pact" << endl;
        cout << "2. Nullify" << endl;
        cout << "3. Shadow Execute | [" << Alien->MaxHP * 0.25 << "]" << endl;
    }

    void blood_harvester_hero_attack(int attack, hero *Fighter, enemy *Alien)
    {
        switch(attack)
        {
        case 0:
            {
                BloodHarvesterBasicAttack(Fighter, Alien);
                break;
            }
        case 1:
            {
                Consume(Fighter, Alien);
                break;
            }
        case 2:
            {
                BloodSpike(Fighter, Alien);
                break;
            }
        case 3:
            {
                BloodSurge(Fighter);
                break;
            }
        }
    }
}

#endif // BloodHarvester
